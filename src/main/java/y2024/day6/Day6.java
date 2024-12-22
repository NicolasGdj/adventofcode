package y2024.day6;

import common.Day;

import java.util.*;

public class Day6 extends Day {

    public static void main(String[] args) {
        new Day6();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    enum Direction{
        UP(-1, 0),
        DOWN(1, 0),
        RIGHT(0, 1),
        LEFT(0, -1);

        private final int dx;
        private final int dy;
        Direction(int x, int y) {
            this.dx = x;
            this.dy = y;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }
    }

    void print(char[][] board, int x, int y, Direction direction) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(i == x && j == y) {
                    System.out.print(switch (direction) {
                        case UP -> "^";
                        case DOWN -> "v";
                        case RIGHT -> ">";
                        case LEFT -> "<";
                    });
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void resolveOne(List<String> lines) {
        char[][] map = new char[lines.size()][lines.get(0).length()];
        int positionX = 0, positionY = 0;
        Direction direction = Direction.UP;
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
            for(int j = 0; j < lines.get(i).length(); j++) {
                switch(map[i][j]) {
                    case '^' -> direction = Direction.UP;
                    case 'v' -> direction = Direction.DOWN;
                    case '>' -> direction = Direction.RIGHT;
                    case '<' -> direction = Direction.LEFT;
                    default -> {}
                }
                if (map[i][j] != '.' && map[i][j] != '#') {
                    positionY = j;
                    positionX = i;
                    map[i][j] = '@';
                }
            }
        }


        int total = 1;
        while(0 <= direction.dx + positionX && direction.dx + positionX < map.length && 0 <= direction.dy + positionY && direction.dy + positionY < map[0].length) {
            //print(map, positionX, positionY, direction);

            int nextPositionX = positionX + direction.getDx();
            int nextPositionY = positionY + direction.getDy();
            char next = map[nextPositionX][nextPositionY];
            if(next == '#') {
                switch (direction) {
                    case UP -> direction = Direction.RIGHT;
                    case DOWN -> direction = Direction.LEFT;
                    case LEFT -> direction = Direction.UP;
                    case RIGHT -> direction = Direction.DOWN;
                }
                continue;
            }

            if(map[nextPositionX][nextPositionY] == '.') {
                map[nextPositionX][nextPositionY] = '@';
                ++total;
            }

            positionX = nextPositionX;
            positionY = nextPositionY;
        }
        print(map, positionX, positionY, direction);
        System.out.println(total);
    }

    record Position(int x, int y, Direction direction) {}
    private boolean isGuardWillLoop(char[][] map, int positionX, int positionY, Direction direction) {

        Set<Position> positions = new HashSet<>();
        while(0 <= direction.dx + positionX && direction.dx + positionX < map.length && 0 <= direction.dy + positionY && direction.dy + positionY < map[0].length) {
            int nextPositionX = positionX + direction.getDx();
            int nextPositionY = positionY + direction.getDy();
            char next = map[nextPositionX][nextPositionY];
            if(next == '#') {
                switch (direction) {
                    case UP -> direction = Direction.RIGHT;
                    case DOWN -> direction = Direction.LEFT;
                    case LEFT -> direction = Direction.UP;
                    case RIGHT -> direction = Direction.DOWN;
                }
                Position position = new Position(positionX, positionY, direction);
                if(!positions.add(position))
                    return true;
                continue;
            }

            if(map[nextPositionX][nextPositionY] == '.') {
                map[nextPositionX][nextPositionY] = '@';
            }
            positionX = nextPositionX;
            positionY = nextPositionY;
            Position position = new Position(positionX, positionY, direction);
            if(!positions.add(position))
                return true;
        }

        return false;
    }

    @Override
    public void resolveTwo(List<String> lines) {
        char[][] map = new char[lines.size()][lines.get(0).length()];
        int positionX = 0, positionY = 0;
        Direction direction = Direction.UP;
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
            for(int j = 0; j < lines.get(i).length(); j++) {
                switch(map[i][j]) {
                    case '^' -> direction = Direction.UP;
                    case 'v' -> direction = Direction.DOWN;
                    case '>' -> direction = Direction.RIGHT;
                    case '<' -> direction = Direction.LEFT;
                    default -> {}
                }
                if (map[i][j] != '.' && map[i][j] != '#') {
                    positionY = j;
                    positionX = i;
                    map[i][j] = '@';
                }
            }
        }

        int total = 0;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                char[][] copy = new char[map.length][map[i].length];
                for(int k = 0; k < map.length; k++) {
                    copy[k] = Arrays.copyOf(map[k], map[k].length);
                }
                copy[i][j] = '#';
                if(isGuardWillLoop(copy, positionX, positionY, direction)) {
                    ++total;
                }
            }
        }
        System.out.println(total);
    }
}
