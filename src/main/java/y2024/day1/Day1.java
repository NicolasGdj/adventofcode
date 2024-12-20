package y2024.day1;

import common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 extends Day {

    public static void main(String[] args) {
        new Day1();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    @Override
    public void resolveOne(List<String> lines) {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        lines.stream().map((line) -> Arrays.stream(line.split("\s+")).map(Integer::parseInt).collect(Collectors.toList())).forEach((split) -> {
            listA.add(split.get(0));
            listB.add(split.get(1));
        });

        Collections.sort(listA);
        Collections.sort(listB);

        int result = 0;
        for (int i = 0; i < listA.size(); i++) {
            result += Math.abs(listA.get(i) - listB.get(i));
        }
        System.out.println(result);
    }

    @Override
    public void resolveTwo(List<String> lines) {
        List<Integer> listA = new ArrayList<>();
        Map<Integer, Integer> occurenceB = new HashMap<>();

        lines.stream().map((line) -> Arrays.stream(line.split("\s+")).map(Integer::parseInt).collect(Collectors.toList())).forEach((split) -> {
            listA.add(split.get(0));
            occurenceB.put(split.get(1), occurenceB.getOrDefault(split.get(1), 0) + 1 );
        });

        int result = 0;
        for (int i = 0; i < listA.size(); i++) {
            int value = listA.get(i);
            result += value * occurenceB.getOrDefault(value, 0);
        }
        System.out.println(result);
    }
}
