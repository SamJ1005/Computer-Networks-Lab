import java.net.*;
import java.io.*;

public class echoSender {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Echo Server Started...");

        Socket socket = serverSocket.accept();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String msg;
        while ((msg = in.readLine()) != null) {
            out.println(msg); // echo back
        }

        socket.close();
        serverSocket.close();
    }
}
