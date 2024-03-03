import java.time.Duration;
import java.time.Instant;

public class Modul12 {
    public static void main(String[] args) {
        // Перший потік для виведення часу
        Thread timeThread = new Thread(() -> {
            Instant start = Instant.now();
            while (true) {
                Instant now = Instant.now();
                Duration duration = Duration.between(start, now);
                System.out.println("Час, що минув: " + duration.getSeconds() + " секунд");
                try {
                    Thread.sleep(1000); // Затримка 1 секунда
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Другий потік для виведення повідомлення кожні 5 секунд
        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Затримка 5 секунд
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Запускаємо обидва потоки
        timeThread.start();
        messageThread.start();
    }
}
