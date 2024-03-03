import java.util.stream.Stream;

public class Project4 {

    public static Stream<Long> linearCongruentialGenerator(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static void main(String[] args) {
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long seed = System.currentTimeMillis(); // Використовуємо час системи як початкове значення seed
        Stream<Long> randomStream = linearCongruentialGenerator(seed, a, c, m);

        // Виводимо перші 10 випадкових чисел
        randomStream.limit(10).forEach(System.out::println);
    }
}
