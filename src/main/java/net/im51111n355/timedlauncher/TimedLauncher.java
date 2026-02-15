package net.im51111n355.timedlauncher;

import java.util.concurrent.atomic.LongAdder;

public class TimedLauncher {

    private static final LongAdder timeCounter = new LongAdder();
    private static final LongAdder callsCounter = new LongAdder();

    // Тут только регистрируется Shutdown Hook в котором и пишется сколько времени тратили ASM из времени жизни приложения
    public static void init() {
        Thread thread = new Thread(TimedLauncher::logTime);
        Runtime.getRuntime().addShutdownHook(thread);
    }

    public static void measure(long v) {
        timeCounter.add(v);
        callsCounter.increment();
    }

    private static void logTime() {
        long totalNs = timeCounter.sum();
        long totalCalls = callsCounter.sum();

        System.out.println("===== Timed Launcher =====");
        System.out.println("Total NS: " + totalNs);
        System.out.println("Total Calls: " + totalCalls);
        System.out.println("==========================");
    }
}
