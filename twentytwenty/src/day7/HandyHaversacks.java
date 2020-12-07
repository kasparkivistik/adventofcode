package day7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HandyHaversacks {

    public static int checkForGold(List<String> rows) {
        Map<String, Set<Tuple<String, Integer>>> map = new HashMap<>();

        for (String row : rows) {
            String[] outer = row.split("contain");
            String[] outerSplit = outer[0].split(" ");
            String outerBag = outerSplit[0] + " " + outerSplit[1];
            if (outer[1].contains("no other")) {
                map.put(outerBag, Set.of());
                continue;
            }

            String[] inner = outer[1].split("bag");

            for (String s : inner) {
                String[] bagContent = s.trim().replace("s, ", "").replace(", ", "").split(" ");
                if (bagContent.length != 3) continue;

                String innerBag = bagContent[1] + " " + bagContent[2];
                map.computeIfAbsent(outerBag, key -> new HashSet<>()).add(new Tuple<>(innerBag, Integer.parseInt(bagContent[0])));
            }

        }

        return countTheBags("shiny gold", map);
    }

    private static int countTheBags(String key, Map<String, Set<Tuple<String, Integer>>> data) {
        int total = 0;
        Set<Tuple<String, Integer>> tuples = data.get(key);
        if (tuples == null || tuples.size() == 0) return 0;

        for (Tuple<String, Integer> tuple : tuples) {
            total += tuple.right * (1 + countTheBags(tuple.left, data));
        }
        return total;
    }

    public static class Tuple<L, R> {
        L left;
        R right;

        public Tuple(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }
}
