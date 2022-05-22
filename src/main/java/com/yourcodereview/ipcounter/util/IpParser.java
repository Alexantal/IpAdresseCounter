package com.yourcodereview.ipcounter.util;

public class IpParser {
    public static final long MAX_IP_CELL_VALUE = 256L;

    public static long getIpIndex(String parsedLine) {
        String[] ipParts = parsedLine.split("\\.");
        long[] ipNums = new long[4];

        for (int i = 0; i < ipParts.length; i++) {
            ipNums[i] = Long.parseLong(ipParts[i]);
        }

        return (long)(Math.pow(MAX_IP_CELL_VALUE, 3) * ipNums[0]
                    + Math.pow(MAX_IP_CELL_VALUE, 2) * ipNums[1]
                    + MAX_IP_CELL_VALUE * ipNums[2] + ipNums[3]);
    }
}