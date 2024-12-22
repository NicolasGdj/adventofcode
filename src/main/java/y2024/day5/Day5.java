package y2024.day5;

import common.Day;

import java.lang.reflect.Array;
import java.util.*;

public class Day5 extends Day {

    public static void main(String[] args) {
        new Day5();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    private Set<Integer> getDependingPages(Map<Integer, Set<Integer>> rules, Integer page, List<Integer> among) {
        Set<Integer> result = new HashSet<>();
        if(rules.containsKey(page)) {
            for (Integer neededPage : rules.get(page)) {
                if (among.contains(neededPage)) {
                    result.add(neededPage);
                }
            }
        }
        return result;
    }

    @Override
    public void resolveOne(List<String> lines) {
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        int i = 0;
        while(i < lines.size() && !lines.get(i).isEmpty()) {
            String line = lines.get(i);
            String[] rule = line.split("\\|");
            rules.computeIfAbsent(Integer.parseInt(rule[1]), (ignored) -> new HashSet<>()).add(Integer.parseInt(rule[0]));
            ++i;
        }

        System.out.println(rules);
        ++i;

        int total = 0;
        while(i < lines.size()) {
            String line = lines.get(i);
            List<Integer> pages = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
            Set<Integer> previousPages = new HashSet<>();
            boolean rightOrder = true;
            checkPage: for(int j = 0; j < pages.size(); ++j) {
                Integer page = pages.get(j);
                Set<Integer> dependings = getDependingPages(rules, page, pages);
                for(Integer depending : dependings) {
                    if(!previousPages.contains(depending)) {
                        rightOrder = false;
                        break checkPage;
                    }
                }
                previousPages.add(page);
            }
            if(rightOrder)
                total += pages.get(pages.size()/2);
            ++i;
        }
        System.out.println(total);
    }

    @Override
    public void resolveTwo(List<String> lines) {
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        int i = 0;
        while(i < lines.size() && !lines.get(i).isEmpty()) {
            String line = lines.get(i);
            String[] rule = line.split("\\|");
            rules.computeIfAbsent(Integer.parseInt(rule[1]), (ignored) -> new HashSet<>()).add(Integer.parseInt(rule[0]));
            ++i;
        }

        System.out.println(rules);
        ++i;

        int total = 0;
        while(i < lines.size()) {
            String line = lines.get(i);
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> pages = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
            for(int j = 0; j < pages.size(); ++j) {
                Integer page = pages.get(j);
                Set<Integer> dependings = getDependingPages(rules, page, pages);
                map.put(page, dependings.size());
            }
            ++i;


            var list = new ArrayList<>(map.entrySet().stream().toList());
            list.sort(Map.Entry.comparingByValue());
            var keys = list.stream().map(Map.Entry::getKey).toList();
            if(!keys.equals(pages))
                total += list.get(list.size()/2).getKey();

        }
        System.out.println(total);

    }
}
