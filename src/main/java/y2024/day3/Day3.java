package y2024.day3;

import common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 extends Day {

    public static void main(String[] args) {
        new Day3();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    @Override
    public void resolveOne(List<String> lines) {
        int result = 0;
        for(String input : lines) {
            Pattern pattern = Pattern.compile("mul\\((?<x>[0-9]{1,3}),(?<y>[0-9]{1,3})\\)");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                System.out.println(matcher.group("x") + " * " + matcher.group("y"));
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                result += x * y;
            }
        }
        System.out.println(result);

    }

    @Override
    public void resolveTwo(List<String> lines) {

        int result = 0;
        boolean state = true;
        for(String input : lines) {
            Pattern pattern = Pattern.compile("(?<state>don't\\(\\)|do\\(\\))|mul\\((?<x>[0-9]{1,3}),(?<y>[0-9]{1,3})\\)");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String stateMatch = matcher.group("state");
                if(stateMatch != null) {
                    if (stateMatch.equals("don't()")) {
                        state = false;
                    } else if (stateMatch.equals("do()")) {
                        state = true;
                    }
                    continue;
                }
                if(!state)
                    continue;
                System.out.println(matcher.group("x") + " * " + matcher.group("y"));
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                result += x * y;
            }
        }
        System.out.println(result);

    }
}
