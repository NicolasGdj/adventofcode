package y2024.day9;

import common.Day;

import java.util.*;

public class Day9 extends Day {

    public static void main(String[] args) {
        new Day9();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    @Override
    public void resolveOne(List<String> lines) {
        String input = lines.get(0);
        boolean block = true;
        List<Integer> ids = new ArrayList<>();
        char id = 0;
        for(String character : input.split("")) {
            int count = Integer.parseInt(character);
            for (int i = 0; i < count; i++) {
                ids.add(block ? id :  -1);
            }
            block = !block;
            if(block) {
                ++id;
            }
        }
        System.out.println(ids);
       primary: for(int i = 0; i < ids.size(); ++i) {
            if(ids.get(i) == -1) {
                //Free space
                for(int j = ids.size() - 1; j > i; --j) {
                    if(ids.get(j) != -1) {
                        Collections.swap(ids, i, j);
                        continue primary;
                    }
                }
            }
        }
        System.out.println(ids);

        long total = 0;
        for(int i = 0; i < ids.size(); ++i) {
            if(ids.get(i) > 0) {
                total += ids.get(i) * i;
            }
        }
        System.out.println(total);
    }

    @Override
    public void resolveTwo(List<String> lines) {
        String input = lines.get(0);
        boolean block = true;
        List<Integer> ids = new ArrayList<>();
        char id = 0;
        for(String character : input.split("")) {
            int count = Integer.parseInt(character);
            for (int i = 0; i < count; i++) {
                ids.add(block ? id :  -1);
            }
            block = !block;
            if(block) {
                ++id;
            }
        }
        System.out.println(ids);
        for(int j = ids.size() - 1; j >= 0; --j) {
            if(ids.get(j) != -1) {
                int blockSize = 0;
                for(int k = j; k >= 0; --k) {
                    if(ids.get(k).equals(ids.get(j))) {
                        blockSize++;
                    } else {
                        break;
                    }
                }

                for(int i = 0; i < j; ++i) {
                    if(ids.get(i) == -1) {
                        int freeSpace = 1;
                        for(int k = i+1; k < ids.size(); ++k) {
                            if(ids.get(k) == -1) {
                                ++freeSpace;
                            } else {
                                break;
                            }
                        }
                        if(freeSpace >= blockSize) {
                            for(int k = 0; k < blockSize; ++k) {
                                Collections.swap(ids, i + k , j - k);
                            }
                        }
                    }
                }
                j -= blockSize - 1;
            }
        }
        System.out.println(ids);

        long total = 0;
        for(int i = 0; i < ids.size(); ++i) {
            if(ids.get(i) > 0) {
                total += ids.get(i) * i;
            }
        }
        System.out.println(total);
    }
}
