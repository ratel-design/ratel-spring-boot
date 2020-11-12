package com.github.rateldesign.util;

/**
 * 字符串处理
 *
 * @author stephen
 * @date 2020/10/21 2:47 下午
 */
public class StringUtils {

    private static final String FOLDER_SEPARATOR = "/";

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    private static final String CURRENT_PATH = ".";

    /**
     * 替换 {@code path} 分隔符，当前包路径分隔符替换为文件路径分隔符
     * @param path 路径
     * @return java.lang.String
     * @author stephen
     * @date 2020/10/21 3:38 下午
     */
    public static String separatorCurrentToFolder(String path) {
        return replace(path, CURRENT_PATH, FOLDER_SEPARATOR);
    }

    /**
     * 替换 {@code path} 分隔符，文件路径分隔符替换为当前包路径分隔符
     * @param path 路径
     * @return java.lang.String
     * @author stephen
     * @date 2020/10/21 3:38 下午
     */
    public static String separatorFolderToCurrent(String path) {
        return replace(path, FOLDER_SEPARATOR, CURRENT_PATH);
    }

    /**
     * 将 {@code inString} 中的 {@code oldPattern} 替换为 {@code newPattern}
     *
     * @param inString 给定字符串
     * @param oldPattern 旧字符串
     * @param newPattern 新字符串
     * @return java.lang.String
     * @author stephen
     * @date 2020/10/21 3:24 下午
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            // 没有可替换的值，返回原值
            return inString;
        }
        int capacity = inString.length();
        capacity += newPattern.length() - oldPattern.length();
        StringBuilder sb = new StringBuilder(capacity);
        // 旧字符串出现的位置之后 - 用于定位不需要替换的原字符串起始值
        int pos = 0;
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        return sb.toString();
    }

    /**
     * 验证给定字符串是否有长度
     * @param str 给定字符串 - 可为空
     * @return boolean
     * @author stephen
     * @date 2020/10/21 2:51 下午
     */
    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }
}
