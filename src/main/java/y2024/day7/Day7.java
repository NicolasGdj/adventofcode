package y2024.day7;

import common.Day;

import java.util.*;
import java.util.function.BiFunction;

public class Day7 extends Day {

    public static void main(String[] args) {
        new Day7();
    }

    @Override
    protected boolean debug() {
        return false;
    }

    enum Operand{
        PLUS ("+", (a, b) -> a + b),
        TIMES ("*", (a, b) -> a * b),
        CONCAT("||", (a, b) -> Long.parseLong(a + "" + b));

        private final String operator;
        private final BiFunction<Long, Long, Long> computeFunc;

        Operand(String operator, BiFunction<Long, Long, Long> computeFunc) {
            this.computeFunc = computeFunc;
            this.operator = operator;
        }

        public long apply(long a, long b) {
            return computeFunc.apply(a, b);
        }

        public String getOperator() {
            return operator;
        }
    }

    public boolean resolve(long result, long currentValue, int currentIndex, List<Long> values, Operand[] operands) {
        if(result == currentValue && currentIndex == values.size()) {
            return true;
        }
        if(currentIndex == values.size()) {
            return false;
        }

        long value = values.get(currentIndex);
        for(Operand operand : operands) {
            if(resolve(result, operand.apply(currentValue, value), currentIndex + 1, values, operands))
                return true;
        }
        return false;
    }

    @Override
    public void resolveOne(List<String> lines) {
        long total = 0;
        for(String line : lines) {
            String[] split = line.split(": ");
            long result = Long.parseLong(split[0]);
            List<Long> values = (Arrays.stream(split[1].split(" ")).map(Long::parseLong).toList());
            if(resolve(result, values.get(0), 1, values, new Operand[]{Operand.PLUS, Operand.TIMES})) {
                total += result;
            }
        }
        System.out.println(total);
    }

    @Override
    public void resolveTwo(List<String> lines) {
        long total = 0;
        for(String line : lines) {
            String[] split = line.split(": ");
            long result = Long.parseLong(split[0]);
            List<Long> values = (Arrays.stream(split[1].split(" ")).map(Long::parseLong).toList());
            if(resolve(result, values.get(0), 1, values, new Operand[]{Operand.PLUS, Operand.TIMES, Operand.CONCAT})) {
                total += result;
            }
        }
        System.out.println(total);
    }
}
