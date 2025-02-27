package com.example.demo.util;

import com.google.ortools.Loader;

import java.io.*;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class OrToolsLibraryLoader {

    public static void loadOrToolsLibraries() throws IOException {
        // 获取当前JAR文件的路径
        String jarPath = OrToolsLibraryLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String tempDir = System.getProperty("java.io.tmpdir") + "/ortools-libraries";

        // 创建临时目录
        Files.createDirectories(Paths.get(tempDir));

        // 打开JAR文件
        try (JarFile jarFile = new JarFile(jarPath)) {
            // 遍历JAR文件中的所有条目
            for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();

                // 检查是否为OR-Tools的本地库文件
                if (entryName.startsWith("BOOT-INF/lib/ortools-") && entryName.endsWith(".dll")) {
                    // 提取文件到临时目录
                    InputStream inputStream = jarFile.getInputStream(entry);
                    Path outputPath = Paths.get(tempDir, entryName.substring(entryName.lastIndexOf('/') + 1));

                    Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        // 设置系统属性，指定本地库路径
        System.setProperty("java.library.path", tempDir);
        System.out.println("OR-Tools libraries extracted to: " + tempDir);

        // 加载OR-Tools库
        Loader.loadNativeLibraries();
    }



}
