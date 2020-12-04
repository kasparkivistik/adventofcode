package day4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;

public class PassportProcessing {

    public static long process(List<String> rows) {
        List<Passport> passports = new ArrayList<>();

        Passport tempPassport = new Passport();
        for (String row : rows) {
            if (row.trim().equals("")) {
                passports.add(tempPassport);
                tempPassport = new Passport();
                continue;
            }

            String[] splitrow = row.split(" ");
            for (String r : splitrow) {
                tempPassport.addData(r);
            }
        }
        passports.add(tempPassport);

        return passports.stream().filter(Passport::isValid).count();
    }

    public static class Passport {
        public static final Predicate<String> HEX_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6})$").asMatchPredicate();
        public static final Predicate<String> HEIGHT_PATTERN = Pattern.compile("^((1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in)$").asMatchPredicate();
        public static final Predicate<String> EYE_PATTERN = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$").asMatchPredicate();

        String birth;
        String issue;
        String expiration;
        String height;
        String hair;
        String eye;
        String passport;

        public boolean isValid() {
            return Stream.of(validateBirth(), validateIssue(), validateExpiration(), validateHeight(), validateHair(), validateEye(), validatePassport())
                    .filter(b -> b == TRUE)
                    .count() == 7;
        }

        public boolean validateBirth() {
            return inTryCatch(() -> {
                int i = Integer.parseInt(birth);
                return birth.length() == 4 && i >= 1920 && i <= 2002;
            });
        }

        public boolean validateIssue() {
            return inTryCatch(() -> {
                int i = Integer.parseInt(issue);
                return birth.length() == 4 && i >= 2010 && i <= 2020;
            });
        }

        public boolean validateExpiration() {
            return inTryCatch(() -> {
                int i = Integer.parseInt(expiration);
                return birth.length() == 4 && i >= 2020 && i <= 2030;
            });
        }

        public boolean validateHeight() {
            return inTryCatch(() -> HEIGHT_PATTERN.test(height));
        }

        public boolean validateHair() {
            return inTryCatch(() -> HEX_PATTERN.test(hair));
        }

        public boolean validateEye() {
            return inTryCatch(() -> EYE_PATTERN.test(eye));
        }

        public boolean validatePassport() {
            return inTryCatch(() -> {
                if (passport.length() == 9) return true;
                long l = Long.parseLong(passport);
                int length = (int) (Math.log10(l) + 1);
                return length == 9;
            });
        }

        public boolean inTryCatch(Supplier<Boolean> supplier) {
            try {
                return supplier.get();
            } catch (Exception e) {
                return false;
            }
        }

        public void addData(String crypticString) {
            String[] split = crypticString.split(":");
            switch (split[0]) {
                case "ecl":
                    this.eye = split[1];
                    break;
                case "pid":
                    this.passport = split[1];
                    break;
                case "eyr":
                    this.expiration = split[1];
                    break;
                case "hcl":
                    this.hair = split[1];
                    break;
                case "byr":
                    this.birth = split[1];
                    break;
                case "iyr":
                    this.issue = split[1];
                    break;
                case "hgt":
                    this.height = split[1];
                    break;
            }
        }
    }
}
