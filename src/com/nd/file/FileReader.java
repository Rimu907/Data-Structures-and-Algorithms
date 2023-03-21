package com.nd.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.nd.file.FileConstants.PATH;
/**
 * 读文件(业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/3/21 14:18
 */
public class FileReader {
    public static void main(String[] args) {
        try {
            File file = new File(PATH);
            FileInputStream fs = new FileInputStream(file);
            BufferedInputStream bs = new BufferedInputStream(fs);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bs.read(buffer)) != -1) {
                String content = new String(buffer, 0 , len);
                System.out.println(content);
            }
            bs.close();
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
