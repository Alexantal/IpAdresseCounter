package com.yourcodereview.ipcounter;

import lombok.Getter;

import java.util.BitSet;

import static com.yourcodereview.ipcounter.IpParser.MAX_IP_CELL_VALUE;
import static com.yourcodereview.ipcounter.IpParser.MAX_IP_INDEX_VALUE;

@Getter
public class UniqueIpCounter {

    private final byte[] ipArrayInBytes;

    public UniqueIpCounter() {
        ipArrayInBytes = new byte[(int)(MAX_IP_INDEX_VALUE/8)];
    }

    public void setIpArrayBit(long ipIndex) {
        int arrayIndex =  (int)(ipIndex / MAX_IP_CELL_VALUE);
        int arrayBitShift =(int) (MAX_IP_CELL_VALUE - ipIndex % MAX_IP_CELL_VALUE);
        ipArrayInBytes[arrayIndex] = (byte)(ipArrayInBytes[arrayIndex] | (1<<(arrayBitShift - 1)));
    }

    public long countUniqueIp() {
        return BitSet.valueOf(ipArrayInBytes).stream().count();
    }
}
