import java.io.*;
import java.net.*;
import java.util.stream.Collectors;

public class Modul13 {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    // Метод для відправлення HTTP запиту GET і повернення відповіді у вигляді рядка
    private static String sendGetRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } finally {
            connection.disconnect();
        }
    }

    // Метод для відправлення HTTP запиту POST або PUT з JSON-даними і повернення відповіді у вигляді рядка
    private static String sendRequestWithJsonData(String urlString, String method, String jsonData) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
            writer.write(jsonData);
            writer.flush();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } finally {
            connection.disconnect();
        }
    }

    // Метод для створення нового користувача
    public static String createUser(String jsonData) throws IOException {
        return sendRequestWithJsonData(BASE_URL, "POST", jsonData);
    }

    // Метод для оновлення інформації про користувача за id
    public static String updateUser(int userId, String jsonData) throws IOException {
        String urlString = BASE_URL + "/" + userId;
        return sendRequestWithJsonData(urlString, "PUT", jsonData);
    }

    // Метод для видалення користувача за id
    public static int deleteUser(int userId) throws IOException {
        String urlString = BASE_URL + "/" + userId;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        return connection.getResponseCode();
    }

    // Метод для отримання інформації про всіх користувачів
    public static String getAllUsers() throws IOException {
        return sendGetRequest(BASE_URL);
    }

    // Метод для отримання інформації про користувача за id
    public static String getUserById(int userId) throws IOException {
        String urlString = BASE_URL + "/" + userId;
        return sendGetRequest(urlString);
    }

    // Метод для отримання інформації про користувача за username
    public static String getUserByUsername(String username) throws IOException {
        String urlString = BASE_URL + "?username=" + URLEncoder.encode(username, "UTF-8");
        return sendGetRequest(urlString);
    }

    public static void main(String[] args) {
        try {
            // Приклад використання методів
            // Створення нового користувача
            String newUserJson = "{\"name\": \"John Doe\", \"username\": \"johndoe\", \"email\": \"johndoe@example.com\"}";
            String createdUser = createUser(newUserJson);
            System.out.println("Created user:\n" + createdUser);

            // Оновлення інформації про користувача
            int userIdToUpdate = 1;
            String updatedUserData = "{\"name\": \"Updated Name\", \"username\": \"updated_username\", \"email\": \"updated_email@example.com\"}";
            String updatedUser = updateUser(userIdToUpdate, updatedUserData);
            System.out.println("Updated user:\n" + updatedUser);

            // Видалення користувача
            int userIdToDelete = 1;
            int deleteStatusCode = deleteUser(userIdToDelete);
            System.out.println("Delete status code: " + deleteStatusCode);

            // Отримання інформації про всіх користувачів
            String allUsers = getAllUsers();
            System.out.println("All users:\n" + allUsers);

            // Отримання інформації про користувача за id
            int userIdToGet = 2;
            String userById = getUserById(userIdToGet);
            System.out.println("User by id:\n" + userById);

            // Отримання інформації про користувача за username
            String usernameToGet = "Bret";
            String userByUsername = getUserByUsername(usernameToGet);
            System.out.println("User by username:\n" + userByUsername);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
