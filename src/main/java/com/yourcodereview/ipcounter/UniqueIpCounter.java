package com.yourcodereview.ipcounter;

import java.util.BitSet;

public class UniqueIpCounter {

    private static final long LOW_INDEX = 2_147_483_646L;
    private static final long MIDDLE_INDEX_1 = 2_147_483_647L;
    private static final long MIDDLE_INDEX_2 = 4_294_967_293L;
    private static final long UP_INDEX_1 = 4_294_967_294L;
    private static final long UP_INDEX_2 = 4_294_967_295L;

    private final BitSet[] ipBitSet = new BitSet[3];

    public UniqueIpCounter() {
        ipBitSet[0] = new BitSet(Integer.MAX_VALUE);
        ipBitSet[1] = new BitSet(Integer.MAX_VALUE);
        ipBitSet[2] = new BitSet(2);
        ipBitSet[0].clear();
        ipBitSet[1].clear();
        ipBitSet[2].clear();
    }

    public void setIpArrayBit(long ipIndex) {
       if (ipIndex < 0) {
           System.out.println("The ipIndex can't be less 0");
           return;
       }
       if (ipIndex <= LOW_INDEX) {
           ipBitSet[0].set((int)ipIndex);
           return;
       }
       if (ipIndex <= MIDDLE_INDEX_2) {
           long index = ipIndex - MIDDLE_INDEX_1;
           ipBitSet[1].set((int) index);
           return;
       }
       if (ipIndex <= UP_INDEX_2) {
           long index = ipIndex - UP_INDEX_1;
           ipBitSet[2].set((int)index);
       }
    }

    public long countUniqueIp() {
        return ipBitSet[0].stream().count()
                + ipBitSet[1].stream().count()
                + ipBitSet[2].stream().count();
    }
}
