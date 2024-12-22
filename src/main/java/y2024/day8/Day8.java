package y2024.day8;

import common.Day;
import y2024.day6.Day6;

import java.util.*;
import java.util.function.BiFunction;

public class Day8 extends Day {

    public static void main(String[] args) {
        new Day8();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    public record Location(int x, int y) {}

    public record Pair(Location loc1, Location loc2) {}

    @Override
    public void resolveOne(List<String> lines) {
        Map<Character, Set<Location>> locations = new HashMap<>();
        char[][] map = new char[lines.size()][lines.get(0).length()];
        for (int x = 0; x < lines.size(); x++) {
            map[x] = lines.get(x).toCharArray();
            for(int y = 0; y < lines.get(x).length(); y++) {
                if (map[x][y] != '.') {
                    locations.computeIfAbsent(map[x][y], k -> new HashSet<>()).add(new Location(x, y));
                }
            }
        }
        System.out.println(locations);
        Set<Pair> pairs = new HashSet<>();
        for(char c : locations.keySet()) {
            Set<Location> locationSet = locations.get(c);
            for(Location location : locationSet) {
                for(Location otherLocation : locationSet) {
                    if(!location.equals(otherLocation)) {
                        pairs.add(new Pair(location, otherLocation));
                    }
                }
            }
        }
        System.out.println(pairs);

        Set<Location> nodes = new HashSet<>();
        for(Pair pair : pairs) {
            int vX = pair.loc2.x - pair.loc1.x;
            int vY = pair.loc2.y - pair.loc1.y;

            int x = pair.loc1.x + 2 * vX;
            int y = pair.loc1.y + 2 * vY;

            if(0 <= x && x < map.length && 0 <= y && y < map[0].length) {
                nodes.add(new Location(x, y));
            }
        }
        System.out.println(nodes);
        System.out.println(nodes.size());
    }

    @Override
    public void resolveTwo(List<String> lines) {

        Map<Character, Set<Location>> locations = new HashMap<>();
        char[][] map = new char[lines.size()][lines.get(0).length()];
        for (int x = 0; x < lines.size(); x++) {
            map[x] = lines.get(x).toCharArray();
            for(int y = 0; y < lines.get(x).length(); y++) {
                if (map[x][y] != '.') {
                    locations.computeIfAbsent(map[x][y], k -> new HashSet<>()).add(new Location(x, y));
                }
            }
        }
        System.out.println(locations);
        Set<Pair> pairs = new HashSet<>();
        for(char c : locations.keySet()) {
            Set<Location> locationSet = locations.get(c);
            for(Location location : locationSet) {
                for(Location otherLocation : locationSet) {
                    if(!location.equals(otherLocation)) {
                        pairs.add(new Pair(location, otherLocation));
                    }
                }
            }
        }
        System.out.println(pairs);

        Set<Location> nodes = new HashSet<>();
        for(Pair pair : pairs) {
            int vX = pair.loc2.x - pair.loc1.x;
            int vY = pair.loc2.y - pair.loc1.y;
            for(int i = 0; ; i++) {
                int x = pair.loc1.x + i * vX;
                int y = pair.loc1.y + i * vY;

                if(0 <= x && x < map.length && 0 <= y && y < map[0].length) {
                    nodes.add(new Location(x, y));
                } else {
                    break;
                }
            }

        }
        System.out.println(nodes);
        System.out.println(nodes.size());
    }
}
