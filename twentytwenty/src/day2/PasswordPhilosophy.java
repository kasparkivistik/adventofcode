package day2;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static day2.PasswordPhilosophy.TobogganPassword.of;

public class PasswordPhilosophy {

    public static int validPasswords(List<String> lines) {
        AtomicInteger count = new AtomicInteger();
        for (var st : lines) {
            TobogganPassword password = of(st);
            if (password.isValid()) count.incrementAndGet();
        }

        return count.get();
    }

    public static class TobogganPassword {
        String password;
        String key;
        int from;
        int to;

        public TobogganPassword(String password, String key, String from, String to) {
            this.password = password;
            this.key = key;
            this.from = Integer.parseInt(from);
            this.to = Integer.parseInt(to);
        }

        public static TobogganPassword of(String string) {
            String[] split = string.split(" ");
            String[] minusSplit = split[0].split("-");
            return new TobogganPassword(split[2], String.valueOf(split[1].charAt(0)), minusSplit[0], minusSplit[1]);
        }

        public boolean isValid() {
            if (!password.contains(key)) return false;

            char from;
            try {
                from = password.charAt(this.from - 1);
            } catch (Exception e) {
                return false;
            }
            char to = 0;
            try {
                to = password.charAt(this.to - 1);
            } catch (Exception ignore) {
            }

            boolean fromIs = from == key.charAt(0);
            boolean toIs = to == key.charAt(0);

            return Stream.of(fromIs, toIs).filter(b -> b == Boolean.TRUE).count() == 1;
        }
    }
}
