import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Project3 {

    public static String sortedNumbers(String[] arr) {
        return Arrays.stream(arr) // Перетворюємо масив в потік
                .flatMap(s -> Arrays.stream(s.split(", "))) // Розділяємо кожен рядок на числа та об'єднуємо їх у один потік
                .map(Integer::parseInt) // Конвертуємо кожний рядок у ціле число
                .sorted() // Сортуємо числа
                .map(Object::toString) // Конвертуємо числа назад у рядки
                .collect(Collectors.joining(", ")); // З'єднуємо всі рядки через кому
    }

    public static void main(String[] args) {
        String[] arr = {"1, 2, 0", "4, 5"};
        String result = sortedNumbers(arr);
        System.out.println(result);
    }
}
