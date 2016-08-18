package com.hackx.common;

import java.io.*;

/**
 * Created by hackx on 8/2/16.
 */
public class FileUtil {

    public static void writeToFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true), "UTF-8"));
            writer.write(content + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}