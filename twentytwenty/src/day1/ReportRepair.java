package day1;

import java.util.List;
import java.util.stream.Collectors;

public class ReportRepair {

    public static int mh(List<String> list) {
        List<Integer> integerList = list.stream().map(Integer::valueOf).collect(Collectors.toList());
        for (int one : integerList) {
            for (int two : integerList) {
                for (int three : integerList) {
                    if (one + two + three == 2020) {
                        return one * two * three;
                    }
                }
            }
        }
        return 0;
    }
}
