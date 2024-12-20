package common;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Day {

    public Day() {
        if(debug()) {
            System.out.println("------------- " + getClass().getSimpleName() + " -------------");
            InputStream inputStream = getClass().getResourceAsStream("debug.txt");
            if (inputStream != null) {
                try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
                    List<String> list = lines.collect(Collectors.toList());
                    System.out.println("Debug One:");
                    resolveOne(list);
                    System.out.println();
                    System.out.println("Debug Two:");
                    resolveTwo(list);
                    System.out.println();
                }
            }
        } else {
            InputStream inputStream = getClass().getResourceAsStream("input.txt");
            if (inputStream != null) {
                try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
                    List<String> list = lines.collect(Collectors.toList());
                    System.out.println("Part One:");
                    resolveOne(list);
                    System.out.println();
                    System.out.println("Part Two:");
                    resolveTwo(list);
                    System.out.println();

                }
            } else {
                System.out.println("Ressource introuvable !");
            }
        }
    }

    protected abstract boolean debug();

    public abstract void resolveOne(List<String> lines);
    public abstract void resolveTwo(List<String> lines);

}
