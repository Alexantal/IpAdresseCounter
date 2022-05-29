package com.yourcodereview.ipcounter;

import com.yourcodereview.ipcounter.util.IpParser;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

@Getter
public class UniqueIpCounter {

    private long ipQuantity;

    private final long firstBitSetMaxBitIndex = 2_147_483_646L;
    private final long secondBitSetMinBitIndex = 2_147_483_647L;
    private final long secondBitSetMaxBitIndex = 4_294_967_293L;
    private final long thirdBitSetMinBitIndex = 4_294_967_294L;
    private final long thirdBitSetMaxBitIndex = 4_294_967_295L;

    private final BitSet[] ipBitSet = new BitSet[3];

    public UniqueIpCounter() {
        ipBitSet[0] = new BitSet(Integer.MAX_VALUE);
        ipBitSet[1] = new BitSet(Integer.MAX_VALUE);
        ipBitSet[2] = new BitSet(2);
    }

    public void setIpArrayBit(long ipIndex) {
       if (ipIndex < 0) {
           throw new IllegalArgumentException("setIpArrayBit method argument can't be less 0.");
       }
       if (ipIndex <= firstBitSetMaxBitIndex) {
           ipBitSet[0].set((int)ipIndex);
           return;
       }
       if (ipIndex <= secondBitSetMaxBitIndex) {
           long index = ipIndex - secondBitSetMinBitIndex;
           ipBitSet[1].set((int) index);
           return;
       }
       if (ipIndex <= thirdBitSetMaxBitIndex) {
           long index = ipIndex - thirdBitSetMinBitIndex;
           ipBitSet[2].set((int)index);
       }
    }

    public void countIp(String fileName) throws IllegalArgumentException, IOException {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = fileReader.readLine()) != null) {
                long index = IpParser.parseIpIndex(line);
                this.setIpArrayBit(index);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in UniqueIpCounter::countIp() method!");
        }

        this.ipQuantity = 0;

        for (BitSet b : ipBitSet) {
            this.ipQuantity += b.cardinality();
        }
    }
}
