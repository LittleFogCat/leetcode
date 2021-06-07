package top.littlefogcat.leetcode;

public class P5645_FindTheHighestAltitude {
    public int largestAltitude(int[] gain) {
        int highest = 0, altitude = 0;
        for (int g : gain) {
            altitude += g;
            if (altitude > highest) highest = altitude;
        }
        return highest;
    }
}
