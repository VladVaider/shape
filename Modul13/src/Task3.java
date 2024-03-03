import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.net.URL;

public class Task3 {
    public static void main(String[] args) {
        // Приклад використання методу для користувача з id 1
        printOpenTasksForUser(1);
    }

    public static void printOpenTasksForUser(int userId) {
        try {
            // Отримуємо список всіх задач користувача з JSONPlaceholder
            ObjectMapper mapper = new ObjectMapper();
            URL userTodosUrl = new URL("https://jsonplaceholder.typicode.com/users/" + userId + "/todos");
            ObjectNode[] userTodos = mapper.readValue(userTodosUrl, ObjectNode[].class);

            // Виводимо відкриті задачі для користувача
            System.out.println("Відкриті задачі для користувача з id " + userId + ":");
            for (ObjectNode todo : userTodos) {
                if (!todo.get("completed").asBoolean()) {
                    System.out.println("Задача " + todo.get("id").asText() + ": " + todo.get("title").asText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
