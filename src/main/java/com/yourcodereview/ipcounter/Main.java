package com.yourcodereview.ipcounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String filePath = "c:\\Projects\\TestData\\IpList.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            UniqueIpCounter ipCounter = new UniqueIpCounter();

            while ((line = reader.readLine()) != null) {
                long index = IpParser.getIpIndex(line);
                ipCounter.setIpArrayBit(index);
            }

            System.out.println(ipCounter.countUniqueIp());
        } catch (IOException e) {
            System.out.println("Exception!!!");
        }
    }
}