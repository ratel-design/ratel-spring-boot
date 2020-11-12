package com.github.rateldesign.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description: banner处理
 * @Author stephen
 * @Date 2020/10/10 4:41 下午
 */
public class Banner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Banner.class);

    public static final String DEFAULT_BANNER_LOCATION = "banner.txt";

    private static final String[] BANNER = {
            "                                     ,DW######E.                   . .,:",
            "                                 E################G              :########:",
            "                               #########E;..:jW#######i       :#####     D#L",
            "                            .fW######;            j#############W . .      #",
            "                         . .#######     .                ,jLt           .   #.",
            "                          :######        G                              ,#   #",
            "                         i#####E         #:                                   #",
            "                        ,#####;         ###                                   G",
            "                       K#####           ###                              f#tK#",
            "                     K####W#.## .        ###.         t              .E#####D",
            "              ,  ;W#######,.####          ##          #            W###G",
            "               f#W#######  #####W         ##.        W#          W##j",
            "                 ####W.  W##L.W##         ##.       j##       .###.",
            "                  :#######t    ##K        ##       E##:      ###",
            "                    .###f      .##       :#       t##K     D###",
            "                                ##       W;  E#######     ###t",
            "                                L#      E,    ,#####    j###.",
            "                                 #;    iE       ###    ###E.",
            "                                 ##    #        ##.  .####",
            "                                 j#.   #       D#D  .####",
            "                                  ##   #       ##   ####",
            "                                  j#   #.      #f  #####",
            "                                  .L#   #     ##  f#W #G",
            "                                    iW   #   L#   #f .#",
            "                                      # . :  #   #f  E#",
            "                                      :i:   W;  D#   #.",
            "                                           #, .L#   #E",
            "                                          #   .#  i#:.",
            "                                         #    W  .",
            "                                        t.   #:",
            "                                        .. :#"};

    public static void print(PrintStream out) {
        out.println();
        for (String line : BANNER) {
            out.println(line);
        }
        out.println();
    }

    public static void print(Class<?> sourceClass, PrintStream out) {
        if (sourceClass == null) {
            print(out);
            return;
        }

        URL url = sourceClass.getClassLoader().getResource(DEFAULT_BANNER_LOCATION);
        if (url == null) {
            print(out);
            return;
        }
        try {
            Path path = Paths.get(url.toURI());
            Files.lines(path).forEach(out::println);
        } catch (URISyntaxException | IOException e) {
            LOGGER.error("banner print failed", e);
        }
    }

}
