import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Project2 {

    public static List<String> uppercaseAndSortDescending(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase) // Переводимо кожен рядок у верхній регістр
                .sorted(Collections.reverseOrder()) // Сортуємо у зворотньому порядку
                .collect(Collectors.toList()); // Повертаємо список
    }

    public static void main(String[] args) {
        List<String> strings = List.of("apple", "banana", "orange", "kiwi", "grape");
        List<String> result = uppercaseAndSortDescending(strings);
        System.out.println(result);
    }
}