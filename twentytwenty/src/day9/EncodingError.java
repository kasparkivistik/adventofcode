package day9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EncodingError {

    public static Long findTheError(List<String> lines) {
        List<Long> numbers = lines.stream().map(Long::parseLong).collect(Collectors.toUnmodifiableList());
       Long invalidNumber = -1l;

        for (int i = 25; i < numbers.size(); i++) {
            Long number = numbers.get(i);

            List<Long> pastFiveNumbers = numbers.subList(i - 25, i);

            List<Long> sums = new ArrayList<>();
            for (Long num : pastFiveNumbers) {
                for (Long otherNum : pastFiveNumbers) {
                    sums.add(num + otherNum);
                }
            }
            if (!sums.contains(number)) {
                invalidNumber = number;
            }
            sums = new ArrayList<>();
        }

        List<Long> nums = new ArrayList<>();
        for (Long number : numbers) {
            if (nums.stream().reduce(0L, Long::sum).equals(invalidNumber) && nums.size() > 1) {
                return nums.stream().min(Long::compareTo).orElseThrow() + nums.stream().max(Long::compareTo).orElseThrow();
            }
            nums.add(number);
            if (nums.stream().reduce(0L, Long::sum).equals(invalidNumber) && nums.size() > 1) {
                break;
            }

            while (nums.stream().reduce(0L, Long::sum) > invalidNumber) {
                nums.remove(0);
                if (nums.stream().reduce(0L, Long::sum).equals(invalidNumber)) break;
            }
        }
        return -1l;
    }
}
