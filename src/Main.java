import java.util.List;

public class Main {

    public static String oddIndexedNames(List<String> names) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) { // Перевіряємо, чи є індекс непарним
                if (result.length() > 0) {
                    result.append(", "); // Додаємо кому, якщо це не перше ім'я
                }
                result.append((i + 1)).append(". ").append(names.get(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List<String> names = List.of("Ivan", "Peter", "Maria", "John", "Anna");
        String result = oddIndexedNames(names);
        System.out.println(result);
    }
}