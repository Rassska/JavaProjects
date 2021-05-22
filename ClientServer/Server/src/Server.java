import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Server connected!");

                while(true) {
                    Socket socket = server.accept();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    new Thread(() -> {
                        try {
                            String request = reader.readLine();

                            System.out.println("Request: " + request);
                            String response = ("Hello, " + request);

                            try{Thread.sleep(5000);} catch (InterruptedException exp) {}
                            writer.write(response);
                            writer.newLine();
                            writer.flush();
                            writer.close();
                            reader.close();
                        } catch (IOException exp){}




                    }).start();
                }
        } catch (IOException exp) {
            throw new RuntimeException(exp);

        }

    }
}
