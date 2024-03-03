import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static String oddIndexedNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 0) // Відбираємо індекси, що є непарними
                .mapToObj(i -> (i + 1) + ". " + names.get(i)) // Формуємо рядок для кожного відібраного індексу
                .collect(Collectors.joining(", ")); // З'єднуємо рядки через кому
    }

    public static void main(String[] args) {
        List<String> names = List.of("Ivan", "Peter", "Maria", "John", "Anna");
        String result = oddIndexedNames(names);
        System.out.println(result);
    }
}
