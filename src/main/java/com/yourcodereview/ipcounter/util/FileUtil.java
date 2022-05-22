package com.yourcodereview.ipcounter.util;

import com.yourcodereview.ipcounter.UniqueIpCounter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileUtil {

    public static String filePath = null;

    public static void readFileName() {
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the file path. For leaving the programme print 'exit'");

            while (true) {
                filePath = consoleReader.readLine();
                if (filePath.equalsIgnoreCase("exit")) {
                    consoleReader.close();
                    return;
                }
                if (filePath.equals("")) {
                    continue;
                }
                if (!Files.exists(Path.of(filePath))) {
                    System.out.println("The file: '" + filePath + "' doesn't exist. Try again or print 'exit'");
                } else {
                    consoleReader.close();
                    break;
                }
            }
        } catch (InvalidPathException e) {
            System.out.println("Entered data isn't regular file path. Try again or print exit");
            readFileName();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void countFileIp(String filePath) {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            UniqueIpCounter ipCounter = new UniqueIpCounter();

            while ((line = fileReader.readLine()) != null) {
                long index = IpParser.getIpIndex(line);
                ipCounter.setIpArrayBit(index);
            }

            System.out.println(ipCounter.countUniqueIp());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Read line error!");
        }
    }
}
