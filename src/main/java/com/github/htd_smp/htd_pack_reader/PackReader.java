package com.github.htd_smp.htd_pack_reader;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PackReader {
    public static HtdPackV1 readV1(File pack) throws IOException {
        return new Gson().fromJson(readFile(pack.getAbsolutePath()), HtdPackV1.class);
    }

    public static HtdPackV1 readV1(Path path) throws IOException {
        return readV1(path.toString());
    }

    public static HtdPackV1 readV1(String path) throws IOException {
        return readV1(new File(path));
    }

    public static String readFile(Path target) throws IOException {
        try (BufferedReader bufferedreader = Files.newBufferedReader(target)) {
            return bufferedreader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static String  readFile(String target) throws IOException {
        return readFile(Paths.get(target));
    }
}
