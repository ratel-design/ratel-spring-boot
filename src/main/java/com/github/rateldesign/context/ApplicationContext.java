package com.github.rateldesign.context;

import com.github.rateldesign.SpringApplication;
import com.github.rateldesign.context.annotation.ComponentScan;
import com.github.rateldesign.factory.SingletonBeanRegistry;
import com.github.rateldesign.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * @author stephen
 * @date 2020/10/20 4:57 下午
 */
public class ApplicationContext {

    private static final Logger log = LoggerFactory.getLogger(ApplicationContext.class);

    private final Class<?> mainClass;

    private static final String CLASS_SUFFIX = ".class";

    private SingletonBeanRegistry beanFactory;

    public ApplicationContext(Class<?> mainClass) {
        this.mainClass = mainClass;
        this.beanFactory = new SingletonBeanRegistry();
    }

    public void refresh() {
        Set<String> scanPackages = getScanPackages();
        for (String scanPackage : scanPackages) {
            String scanPackagePath = StringUtils.separatorCurrentToFolder(scanPackage);
            Enumeration<URL> enumeration;
            try {
                enumeration = SpringApplication.class.getClassLoader().getResources(scanPackagePath);
            } catch (IOException e) {
                log.error("Skipping refresh [{}] because it does not exist", scanPackagePath);
                return;
            }
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                URI uri;
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    log.error("Skipping register [{}] because it does not exist", url.getPath());
                    continue;
                }
                registerBean(new File(uri.getSchemeSpecificPart()).getAbsoluteFile(), scanPackage);
            }
        }
    }

    @NotNull
    private Set<String> getScanPackages() {
        Set<String> scanPackages;
        ComponentScan componentScan = mainClass.getAnnotation(ComponentScan.class);
        if (componentScan == null) {
            scanPackages = new HashSet<>(8);
            scanPackages.add(mainClass.getPackageName());
        } else {
            scanPackages = new HashSet<>(Arrays.asList(componentScan.value()));
        }
        return scanPackages;
    }

    /**
     * 注册指定文件目录下所有需要注册的class
     * @param rootDir 指定目录文件
     * @param scanPackage 扫描的包路径
     * @author stephen
     * @date 2020/10/20 6:09 下午
     */
    private void registerBean(File rootDir, final String scanPackage) {
        if (!rootDir.exists()) {
            // 默认跳过不存在的文件/目录
            log.debug("Skipping [{}] because it does not exist", rootDir.getAbsolutePath());
            return;
        }
        if (!rootDir.isDirectory()) {
            // 不是目录
            log.info("Skipping [{}] because it does not denote a directory", rootDir.getAbsolutePath());
            return;
        }
        if (!rootDir.canRead()) {
            // 目录文件不可读
            log.info("Skipping search for matching files underneath directory [{}] " +
                    "because the application is not allowed to read the directory", rootDir.getAbsolutePath());
            return;
        }
        registerClassFile(rootDir, scanPackage);
    }

    private void registerClassFile(File dir, final String scanPackage) {
        log.trace("Searching directory [{}]", dir.getAbsolutePath());
        for (File file : listDirectory(dir)) {
            if (!file.canRead()) {
                log.debug("Skipping subdirectory [{}] " +
                        "because the application is not allowed to read the directory",  dir.getAbsolutePath());
                continue;
            }
            if (file.isDirectory()) {
                registerClassFile(file, scanPackage);
            } else {
                String classPath = getClassPath(scanPackage, file.getPath());
                if (classPath == null) {
                    continue;
                }
                beanFactory.getBean(classPath);
                // TODO:MQH 2020/11/12 注册bean
//                registerClass();
            }
        }
    }

    private void registerClass(final String classPath) {
        Class<?> clazz;
        try {
            clazz = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            log.warn("Skipping [{}] because not find", classPath, e);
            return;
        }

    }

    @Nullable
    private String getClassPath(String scanPackage, String filePath) {
        if (!filePath.endsWith(CLASS_SUFFIX)) {
            log.info("Skipping register [{}] because it's not class file", filePath);
            return null;
        }
        String classPath = StringUtils.separatorFolderToCurrent(filePath);
        int scanPackageIndex = classPath.indexOf(scanPackage);
        if (scanPackageIndex == -1) {
            log.warn("Skipping register [{}] because not find package path [{}]", classPath, scanPackage);
            return null;
        }
        classPath = classPath.substring(scanPackageIndex);
        return classPath;
    }

    /**
     * 指定文件目录下的文件有序列表
     * 按名称排序
     *
     * @param dir 文件目录
     * @return java.io.File[]
     * @author stephen
     * @date 2020/10/20 4:20 下午
     */
    protected File[] listDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            log.info("Could not retrieve contents of directory [{}]", dir.getAbsolutePath());
            return new File[0];
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        return files;
    }

}
