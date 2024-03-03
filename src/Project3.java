import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Project3 {

    public static String sortedNumbers(String[] arr) {
        // Створюємо список для зберігання чисел
        List<Integer> numbers = new ArrayList<>();

        // Проходимо по кожному рядку масиву
        for (String s : arr) {
            // Розділяємо рядок на числа за комами
            String[] parts = s.split(", ");
            // Конвертуємо кожну частину у ціле число і додаємо його до списку чисел
            for (String part : parts) {
                numbers.add(Integer.parseInt(part));
            }
        }

        // Сортуємо числа
        numbers.sort(Integer::compareTo);

        // Формуємо результуючий рядок, розділяючи числа комами
        return numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        String[] arr = {"1, 2, 0", "4, 5"};
        String result = sortedNumbers(arr);
        System.out.println(result);
    }
}