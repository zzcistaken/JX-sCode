package com.example.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static final int MIN_PREFIX_LENGTH = 3;
    private static final String NATIVE_FOLDER_PATH_PREFIX = "nativeutils_tmp";
    private static File temporaryDir;
    private static boolean loaded;

    public static void loadNativeLibraries() {
        if(!Loader.loaded){
            try {
                loadLibraryFromJar("/darwin");
                Loader.loaded = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void loadLibraryFromJar(String path) throws IOException {
        if (null == path || !path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }
        if(!path.endsWith("/")){
            path += "/";
        }
        String[] files = { "libortools.dylib", "libjniortools.dylib"};
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory(NATIVE_FOLDER_PATH_PREFIX);
            temporaryDir.deleteOnExit();
        }
        List<String> tempPathList = new ArrayList<>();
        for (String file : files) {
            File temp = new File(temporaryDir, file);
            try (InputStream is = Loader.class.getResourceAsStream(path + file)) {
                Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                tempPathList.add(temp.getAbsolutePath());
            } catch (IOException e) {
                temp.delete();
                throw e;
            } catch (NullPointerException e) {
                temp.delete();
                throw new FileNotFoundException("File " + path + " was not found inside JAR.");
            }
        }

        for(String temPath : tempPathList){
            System.load(temPath);
        }
        for(String temPath : tempPathList){
            File temp = new File(temPath);
            if (isPosixCompliant()) {
                temp.delete();
            } else {
                temp.deleteOnExit();
            }
        }
    }

    private static boolean isPosixCompliant() {
        try {
            return FileSystems.getDefault()
                    .supportedFileAttributeViews()
                    .contains("posix");
        } catch (FileSystemNotFoundException
                | ProviderNotFoundException
                | SecurityException e) {
            return false;
        }
    }

    private static File createTempDirectory(String prefix) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File generatedDir = new File(tempDir, prefix);

        if(!generatedDir.exists()){
            if(!generatedDir.mkdir()){
                throw new IOException("Failed to create temp directory " + generatedDir.getName());
            }
        } else {
            System.out.println("Directory already exists.");
        }
        return generatedDir;
    }

    static {
        Loader.loaded = false;
    }
}
