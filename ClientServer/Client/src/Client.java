import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.*;
import java.util.*;



public class Client {

    public static String getRandomName() throws IOException {
        String file = "names";
        Path filePath = Paths.get(file);
        List<String> names = Files.readAllLines(filePath);
        return names.get((int)(Math.random() * names.size()));

    }

    public static void main(String[] args) throws IOException {

        try(Socket socket = new Socket("127.0.0.1", 8000);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            System.out.println("Connected to the server!");

            String randomName = getRandomName();
            System.out.println("Request: " + randomName);

            writer.write(randomName);
            writer.newLine();
            writer.flush();

            String response = reader.readLine();
            System.out.println("Response: " + response);

        } catch (IOException exp) {
            exp.printStackTrace();
        }

    }
}


