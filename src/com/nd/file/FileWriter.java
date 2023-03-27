package com.nd.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import static com.nd.file.FileConstants.PATH;

/**
 * 写文件(业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/3/21 14:18
 */
public class FileWriter {
    public static void main(String[] args) {
        String h = "nezu love u\n";
        try {
            File file = new File(PATH);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bw = new BufferedOutputStream(fileOutputStream, 8);
            byte[] bytes = h.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < 5; i++) {
                bw.write(bytes);
            }
            bw.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
