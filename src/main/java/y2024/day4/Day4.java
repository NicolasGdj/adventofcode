package y2024.day4;

import common.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends Day {

    public static void main(String[] args) {
        new Day4();
    }

    @Override
    protected boolean debug() {
        return false;
    }


    @Override
    public void resolveOne(List<String> lines) {

        final String pattern = "XMAS";

        int occurences = 0;

        System.out.println("Row");
        // Row
        for(String line : lines) {
            occurences += getOccurences(line, pattern);
            occurences += getOccurences(new StringBuilder(line).reverse().toString(), pattern);
        }
        System.out.println();
        System.out.println(occurences);

        System.out.println("Column");
        // Column
        for(int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder column = new StringBuilder();
            for(String line : lines) {
                column.append(line.charAt(i));
            }
            occurences += getOccurences(column.toString(), pattern);
            occurences += getOccurences(column.reverse().toString(), pattern);
        }
        System.out.println();
        System.out.println(occurences);

        System.out.println("Diagonal");


        // Diagonal
        int maxLength = Integer.max(lines.size(), lines.get(0).length()) * 2;
        for(int ij = 0; ij < lines.size() && ij < lines.get(0).length(); ++ij) {
            StringBuilder diagonal = new StringBuilder();
            for(int k = -maxLength; k < maxLength; ++k) {
                int row = ij + k;
                int column = ij - k;
                if(row >= 0 && row < lines.size() && column >= 0 && column < lines.get(row).length()) {
                    diagonal.append(lines.get(row).charAt(column));
                }
            }
            occurences += getOccurences(diagonal.toString(), pattern);
            occurences += getOccurences(diagonal.reverse().toString(), pattern);
        }
            for(int ij = 0; ij < lines.size() && ij < lines.get(0).length(); ++ij) {
                StringBuilder diagonal = new StringBuilder();
                for(int k = -maxLength; k < maxLength; ++k) {
                    int row = ij + 1 + k;
                    int column = ij - k;
                    if(row >= 0 && row < lines.size() && column >= 0 && column < lines.get(row).length()) {
                        diagonal.append(lines.get(row).charAt(column));
                    }
                }
                occurences += getOccurences(diagonal.toString(), pattern);
                occurences += getOccurences(diagonal.reverse().toString(), pattern);
            }
        System.out.println(occurences);

        System.out.println();
                /*
        . 0 1 2 3 4
        0 A B C D E
        1 F G H I J
        2 K L M N O
        3 P Q R S T
         */
        System.out.println("Anti Diagonal");
        for(int ij = 0; ij < lines.size() && ij < lines.get(0).length(); ++ij) {
            StringBuilder diagonal = new StringBuilder();
            for(int k = -maxLength; k < maxLength; ++k) {
                int row = lines.size() - (ij - k) - 1;
                int column = ij + k;

                if(row >= 0 && row < lines.size() && column >= 0 && column < lines.get(row).length()) {

                    diagonal.append(lines.get(row).charAt(column));
                }

            }
            occurences += getOccurences(diagonal.toString(), pattern);
            occurences += getOccurences(diagonal.reverse().toString(), pattern);
        }

        for(int ij = 0; ij < lines.size() && ij < lines.get(0).length(); ++ij) {
            StringBuilder diagonal = new StringBuilder();
            for(int k = -maxLength; k < maxLength; ++k) {
                int row = lines.size() - (ij + 1 - k) - 1;
                int column = ij + k;
                if(row >= 0 && row < lines.size() && column >= 0 && column < lines.get(row).length()) {
                    diagonal.append(lines.get(row).charAt(column));
                }
            }
            occurences += getOccurences(diagonal.toString(), pattern);
            occurences += getOccurences(diagonal.reverse().toString(), pattern);
        }



        System.out.println(occurences);
    }

    private int getOccurences(String line, String pattern) {
        String withoutPattern = line.replaceAll(pattern, "");
        if(line.contains(pattern)) {
        }
        System.out.println(line.replaceAll(pattern, "?".repeat(pattern.length())));

        return (line.length() - withoutPattern.length()) / pattern.length();
    }


    @Override
    public void resolveTwo(List<String> lines) {


    }
}
