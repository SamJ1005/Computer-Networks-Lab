import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            // Create server socket on port 7000
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("Server started. Waiting for client...");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Input and output streams
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String msg;
            while (true) {
                msg = in.readLine();
                if (msg == null || msg.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Client: " + msg);
                out.println(msg); // Echo back
            }

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}