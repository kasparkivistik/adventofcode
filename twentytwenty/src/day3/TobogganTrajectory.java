package day3;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class TobogganTrajectory {

    public static final int VERTICAL_INCREMENTS = 1;
    public static final int HORIZONTAL_INCREMENTS = 3;

    public static long countTreesOnPathDefault(List<String> road) {
        return countTreesOnPath(road, VERTICAL_INCREMENTS, HORIZONTAL_INCREMENTS);
    }

    public static long countABunchOfTrees(List<String> road) {
        return Stream.of(
                countTreesOnPath(road, 1, 1),
                countTreesOnPath(road, 1, 3),
                countTreesOnPath(road, 1, 5),
                countTreesOnPath(road, 1, 7),
                countTreesOnPath(road, 2, 1)
        )
                .reduce(1L, (a, b) -> a * b);
    }

    public static long countTreesOnPath(List<String> road, int yIncrement, int xIncrement) {
        AtomicLong trees = new AtomicLong();
        int width = road.get(0).length();

        int currentIndex = 0;

        for (int i = 0; i < road.size(); i += yIncrement) {
            String roadRow = road.get(i);

            if (roadRow.charAt(currentIndex) == '#') trees.incrementAndGet();

            currentIndex += xIncrement;
            currentIndex = currentIndex % width;
        }

        return trees.get();
    }

}
