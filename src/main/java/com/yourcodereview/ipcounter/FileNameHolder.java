package com.yourcodereview.ipcounter;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

@Getter
public class FileNameHolder {

    private String fileName = null;
    private final String forExitWord = "exit";

    public void readFileName() {
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the file path. For leaving the programme print \"exit\"");

            while (true) {
                fileName = consoleReader.readLine();
                if (fileName.equalsIgnoreCase("exit")) {
                    consoleReader.close();
                    return;
                }
                if (fileName.equals("")) {
                    continue;
                }
                if (!Files.exists(Path.of(fileName))) {
                    System.out.println("The file: '" + fileName + "' doesn't exist. Try again or print \"exit\"");
                } else {
                    consoleReader.close();
                    break;
                }
            }
        } catch (InvalidPathException e) {
            System.out.println("Entered data isn't regular file path. Try again or print \"exit\"");
            readFileName();
        } catch (IOException e) {
            System.out.println("Console reading error!");
            e.printStackTrace();
        }
    }
}
