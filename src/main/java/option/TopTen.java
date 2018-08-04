package option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PSH
 * Date: 2018/8/4
 */
public class TopTen {

    public static void main(String[] args) {

        String nums = TopTen.class.getClassLoader().getResource("nums").getPath();

        String [] paths = {nums};
        Arrays.stream(paths)
            .flatMap(TopTen::fileLins)
            .flatMap(line -> Arrays.stream(line.split("\\b")))
            .map(word -> word.replaceAll("[^a-zA-Z]", ""))
            .filter(word -> word.length() > 0)
            .map(word -> word.toUpperCase())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted((a,b) -> -a.getValue().compareTo(b.getValue()))
            .limit(10)
            .forEach(e -> System.out.format("%s = %d%n", e.getKey(), e.getValue()));
    }

    private static Stream<String> fileLins (String path) {
        try {
            Stream<String> lines = Files.lines(Paths.get(path));
            return lines;
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
