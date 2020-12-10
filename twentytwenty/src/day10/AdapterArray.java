package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterArray {

    public static long array(List<String> lines) {
        List<Long> numbers = lines.stream().map(Long::parseLong).sorted().collect(Collectors.toList());

        long current = 0l;
        Map<Long, Long> map = new HashMap<>();
        for (Long number : numbers) {
            if (number - current <= 3) {
                map.put(number, number - current);
                current = number;
            }
        }

        int ones = Collections.frequency(map.values(), 1l);
        int threes = Collections.frequency(map.values(), 3l) + 1;

        return ones * threes;
    }

    public static long pt2(List<String> lines) {
        Stream<Long> longStream = lines.stream().map(Long::parseLong);
        List<Long> joltages = new ArrayList(longStream.collect(Collectors.toList()));
        joltages.add(joltages.stream().reduce(0l, Long::sum) + 3);
        joltages.add(0l);
        Collections.sort(joltages);
        long[] routes = new long[joltages.size()];
        routes[joltages.size() - 1] = 1;

        for (int i = joltages.size() - 2; i >= 0; i--) {
            routes[i] = routes[i + 1];
            if (i + 3 < joltages.size() && joltages.get(i + 3) <= joltages.get(i) + 3) {
                routes[i] += routes[i + 3];
            }
            if (i + 2 < joltages.size() && joltages.get(i + 2) <= joltages.get(i) + 3) {
                routes[i] += routes[i + 2];
            }
        }
        return routes[0];
    }
}
