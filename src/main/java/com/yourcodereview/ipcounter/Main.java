package com.yourcodereview.ipcounter;

import com.yourcodereview.ipcounter.util.FileUtil;

import static com.yourcodereview.ipcounter.util.FileUtil.filePath;

public class Main {

    public static void main(String[] args) {
        FileUtil.readFileName();

        if (filePath.equalsIgnoreCase("exit")) {
            return;
        }

        FileUtil.countFileIp(filePath);
    }


}