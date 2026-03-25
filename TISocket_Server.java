import java.io.*;
import java.net.*;

public class TISocket_Server {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server waiting...");
        Socket s = ss.accept();
        System.out.println("Client connected");

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String fileName = dis.readUTF();
        File file = new File(fileName);

        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                dos.writeUTF(line);
            }
            br.close();
        } else {
            dos.writeUTF("File not found");
        }

        s.close();
        ss.close();
    }
}