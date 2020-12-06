package day6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomCustoms {

    public static int countQuestions(List<String> questions) {
        int totalCount = 0;
        Set<Character> chars = new HashSet<>();
        for (String question : questions) {
            if (question.equals("")) {
                totalCount += chars.size();
                chars = new HashSet<>();
            }

            for (char c : question.toCharArray()) {
                chars.add(c);
            }
        }
        totalCount += chars.size();

        return totalCount;
    }

    public static int countQuestions2(List<String> questions) {
        int totalCount = 0;
        int answerers = 0;
        Map<Character, Integer> questionAnswersOrSomething = new HashMap<>();
        for (String question : questions) {
            if (question.equals("")) {
                if (questionAnswersOrSomething.containsValue(answerers)){
                    int finalAnswerers = answerers;
                    long count = questionAnswersOrSomething.values().stream().filter(i -> i == finalAnswerers).count();
                    totalCount += count;
                }
                answerers = 0;
                questionAnswersOrSomething = new HashMap<>();
                continue;
            }

            answerers += 1;
            for (char c : question.toCharArray()) {
                questionAnswersOrSomething.computeIfPresent(c, (k, v) -> v + 1);
                questionAnswersOrSomething.putIfAbsent(c, 1);
            }
        }
        if (questionAnswersOrSomething.containsValue(answerers)) {
            int finalAnswerers = answerers;
            long count = questionAnswersOrSomething.values().stream().filter(i -> i == finalAnswerers).count();
            totalCount += count;

        }


        return totalCount;
    }
}
