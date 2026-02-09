import java.util.Scanner;

public class PasswordEncryption {
    static String encrypt(String password, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : password.toCharArray()) {
            encrypted.append((char) (c + key));
        }
        return encrypted.toString();
    }

    static String decrypt(String encrypted, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            decrypted.append((char) (c - key));
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter shift value(number): ");
        int key = sc.nextInt();

        String encrypted = encrypt(password, key);
        System.out.println("\nEncrypted Password: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Password: " + decrypted);

        sc.close();
    }
}
