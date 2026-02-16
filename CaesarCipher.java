import java.util.Scanner;

public class CaesarCipher {
    static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value (1-25): ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        System.out.println("\nEncrypted: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}