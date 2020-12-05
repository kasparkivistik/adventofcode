package day5;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BinaryBoarding {

    public static int id(List<String> passes) {
        Optional<Integer> maybeMax = passes.stream().map(ticket -> {
            int row = 8 * Integer.parseInt(
                    ticket.substring(0, 7)
                            .replace('F', '0')
                            .replace('B', '1'),
                    2);
            int column = Integer.parseInt(ticket.substring(7, 10)
                            .replace('L', '0')
                            .replace('R', '1'),
                    2);
            return row + column;
        })
                .max(Comparator.comparing(i -> i));


        return maybeMax.orElse(0);
    }

    public static int pt2(List<String> passes) {
        List<Integer> seatIds = passes.stream().map(ticket -> {
            int row = 8 * Integer.parseInt(
                    ticket.substring(0, 7)
                            .replace('F', '0')
                            .replace('B', '1'),
                    2);
            int column = Integer.parseInt(ticket.substring(7, 10)
                            .replace('L', '0')
                            .replace('R', '1'),
                    2);
            return row + column;
        })
                .collect(Collectors.toList());
        int maxId = seatIds.stream()
                .max(Comparator.comparing(i -> i))
                .orElse(-1);

        for (int i = 1; i <= maxId; i++) {
            if (!seatIds.contains(i) && seatIds.contains(i - 1) && seatIds.contains(i + 1)) return i;
        }
        return -1;
    }

}
