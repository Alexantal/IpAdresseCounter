package com.yourcodereview.ipcounter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        FileNameHolder fileNameHolder = new FileNameHolder();
        fileNameHolder.readFileName();

        String fileName = fileNameHolder.getFileName();

        if (fileName.equalsIgnoreCase(fileNameHolder.getForExitWord())) {
            return;
        }

        UniqueIpCounter ipCounter = new UniqueIpCounter();

        try {
            ipCounter.countIp(fileName);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException in IpParser::parseIpIndex() method");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Read line error in UniqueIpCounter::countIp()!");
            e.printStackTrace();
        }

        System.out.println(ipCounter.getIpQuantity());
    }
}