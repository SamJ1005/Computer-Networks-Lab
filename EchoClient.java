import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to server
            Socket socket = new Socket("localhost", 7000);
            System.out.println("Connected to server");

            // Input and output streams
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String msg;

            while (true) {
                System.out.print("Client: ");
                msg = sc.nextLine();
                out.println(msg);

                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Server: " + in.readLine());
            }

            socket.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}