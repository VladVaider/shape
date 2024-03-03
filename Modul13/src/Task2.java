import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class Task2 {

    public static void main(String[] args) {
        // Приклад використання методу для користувача з id 1
        getUserLastPostCommentsAndSaveToFile(1);
    }

    public static void getUserLastPostCommentsAndSaveToFile(int userId) {
        try {
            // Отримуємо останній пост користувача з JSONPlaceholder
            ObjectMapper mapper = new ObjectMapper();
            URL userPostsUrl = new URL("https://jsonplaceholder.typicode.com/users/" + userId + "/posts");
            ObjectNode[] userPosts = mapper.readValue(userPostsUrl, ObjectNode[].class);
            ObjectNode lastPost = userPosts[userPosts.length - 1];

            // Отримуємо коментарі до останнього поста
            URL postCommentsUrl = new URL("https://jsonplaceholder.typicode.com/posts/" + lastPost.get("id").asText() + "/comments");
            ObjectNode[] postComments = mapper.readValue(postCommentsUrl, ObjectNode[].class);

            // Записуємо коментарі у файл
            writeCommentsToFile(userId, lastPost.get("id").asText(), postComments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCommentsToFile(int userId, String postId, ObjectNode[] comments) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode commentsArray = mapper.createArrayNode();
        for (ObjectNode comment : comments) {
            commentsArray.add(comment);
        }

        try {
            // Створюємо назву файлу
            String fileName = "user-" + userId + "-post-" + postId + "-comments.json";
            FileWriter fileWriter = new FileWriter(fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, commentsArray);
            fileWriter.close();
            System.out.println("Коментарі успішно записані у файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
