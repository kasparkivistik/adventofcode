package day8;

import day7.HandyHaversacks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class HandheldHalting {

    public static int processor(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            List<String> strings = new java.util.ArrayList<>(List.copyOf(lines));
            String line = lines.get(i);
            String[] s = line.split(" ");
            if (s[0].equals("jmp")) s[0] = "nop";
            else if (s[0].equals("nop")) s[0] = "jmp";

            strings.set(i, s[0] + " " + s[1]);
            HandyHaversacks.Tuple<Integer, Integer> run = run(strings);
            if (run.right == lines.size()) return run.left;
        }

        return -1;
    }

    public static int processor1(List<String> lines) {
        return run(lines).left;
    }

    static HandyHaversacks.Tuple<Integer, Integer> run(List<String> lines) {
        AtomicInteger accumulator = new AtomicInteger();
        AtomicInteger pointer = new AtomicInteger();
        Set<Integer> positions = new HashSet<>();

        while ((0 <= pointer.get() && pointer.get() < lines.size()) && !positions.contains(pointer.get())) {
            positions.add(pointer.get());
            String line = lines.get(pointer.get());
            String[] s = line.split(" ");
            HandyHaversacks.Tuple<String, Integer> command = new HandyHaversacks.Tuple<>(s[0], Integer.parseInt(s[1]));
            switch (command.left) {
                case "acc":
                    accumulator.addAndGet(command.right);
                    pointer.incrementAndGet();
                    break;
                case "jmp":
                    pointer.addAndGet(command.right);
                    break;
                case "nop":
                    pointer.incrementAndGet();
                    break;
            }
        }

        return new HandyHaversacks.Tuple<>(accumulator.get(), pointer.get());
    }
}
