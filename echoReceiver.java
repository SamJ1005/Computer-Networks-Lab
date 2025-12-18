import java.net.*;
import java.io.*;

public class echoReceiver {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);

        BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.print("Enter message: ");
        String msg = userInput.readLine();
        out.println(msg);

        System.out.println("Echo from server: " + in.readLine());

        socket.close();
    }
}
