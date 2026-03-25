import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TISocket_Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 5000);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();
        dos.writeUTF(fileName);

        try {
            while (true) {
                String data = dis.readUTF();
                System.out.println(data);
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        }

        s.close();
        sc.close();
    }
}