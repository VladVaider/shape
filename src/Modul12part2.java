import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Modul12part2 {
    private static final int n = 15; // Максимальне число

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(); // Черга для виведення

        // Потік A для перевірки чисел на fizz (ділення на 3)
        Thread threadA = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0) {
                    try {
                        queue.put("fizz");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Потік B для перевірки чисел на buzz (ділення на 5)
        Thread threadB = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0) {
                    try {
                        queue.put("buzz");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Потік C для перевірки чисел на fizzbuzz (ділення на 3 та 5)
        Thread threadC = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    try {
                        queue.put("fizzbuzz");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Потік D для виведення чисел з черги
        Thread threadD = new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                try {
                    String value = queue.take();
                    if ("fizz".equals(value)) {
                        System.out.println("fizz");
                    } else if ("buzz".equals(value)) {
                        System.out.println("buzz");
                    } else if ("fizzbuzz".equals(value)) {
                        System.out.println("fizzbuzz");
                    } else {
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Запускаємо всі потоки
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
