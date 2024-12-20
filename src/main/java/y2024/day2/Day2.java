package y2024.day2;

import common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day2 extends Day {

    public static void main(String[] args) {
        new Day2();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    @Override
    public void resolveOne(List<String> lines) {
        int safe = 0;
        for (String report : lines) {
            List<Integer> levels = Arrays.stream(report.split("\s+")).map(Integer::parseInt).collect(Collectors.toList());

            List<Integer> orderedLevels = levels.stream().sorted().collect(Collectors.toList());
            if(!levels.equals(orderedLevels)) {
                Collections.reverse(orderedLevels);
                if(!levels.equals(orderedLevels)) {
                    continue;
                }
            }

            int result = levels.stream().reduce((lastLevel, level) -> {
                if(lastLevel < 0)
                    return lastLevel;

                int delta = Math.abs(lastLevel - level);
                return delta > 0 && delta <= 3 ? level : -1;
            }).orElse(-1);
            if(result > 0)
                ++safe;

        }
        System.out.println(safe);

    }

    @Override
    public void resolveTwo(List<String> lines) {
        int safe = 0;
        for (String report : lines) {
            List<Integer> finalLevels = Arrays.stream(report.split("\s+")).map(Integer::parseInt).collect(Collectors.toList());
            boolean removeOne = false;
            int index = 0;
            do {
                List<Integer> levels = new ArrayList<>(finalLevels);
                if (removeOne) {
                    levels.remove(index);
                }
                List<Integer> orderedLevels = levels.stream().sorted().collect(Collectors.toList());
                if (!levels.equals(orderedLevels)) {
                    Collections.reverse(orderedLevels);
                    if (!levels.equals(orderedLevels)) {
                        if(removeOne) {
                            ++index;
                        } else {
                            removeOne = true;
                        }
                        continue;
                    }
                }

                int result = levels.stream().reduce((lastLevel, level) -> {
                    if (lastLevel < 0)
                        return lastLevel;

                    int delta = Math.abs(lastLevel - level);
                    return delta > 0 && delta <= 3 ? level : -1;
                }).orElse(-1);
                if (result > 0) {
                    ++safe;
                    break;
                }
                if(removeOne) {
                    ++index;
                } else {
                    removeOne = true;
                }
            } while (index < finalLevels.size());

        }
        System.out.println(safe);
    }
}
