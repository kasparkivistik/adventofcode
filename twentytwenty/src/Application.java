import day9.EncodingError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        System.out.println(runWithFileInput(
                "/Users/kasparkivistik/dev/adventofcode/twentytwenty/src/day9/input.txt",
                EncodingError::findTheError)
        );
    }

    public static <T> T runWithFileInput(String fileName, Function<List<String>, T> function) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            List<String> stringLines = bufferedReader.lines().collect(Collectors.toList());
            return function.apply(stringLines);
        } catch (IOException e) {
            return null;
        }
    }
}
