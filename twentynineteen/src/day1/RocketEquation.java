package day1;

import java.util.List;

public class RocketEquation {

    public static long calculateFuel(List<String> modules) {
        return modules.stream()
                .map(moduleString -> {
                    long i = Long.parseLong(moduleString);
                    long total = 0L;
                    while (i >= 0) {
                        long thisUnit = (long) Math.floor(i / 3L) - 2L;
                        if (thisUnit <= 0) break;
                        total += thisUnit;
                        i = thisUnit;
                    }

                    return total;
                })
                .reduce(0L, Long::sum);
    }
}
