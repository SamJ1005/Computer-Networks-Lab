import java.util.Scanner;

public class errorCRC {

    // XOR operation (same logic as Python)
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // CRC-CCITT calculation
    static String crcCCITT(String data, String key) {
        int keyLen = key.length();
        String temp = data.substring(0, keyLen);

        for (int i = keyLen; i < data.length(); i++) {
            if (temp.charAt(0) == '1') {
                temp = xor(key, temp) + data.charAt(i);
            } else {
                temp = xor("0".repeat(keyLen), temp) + data.charAt(i);
            }
        }

        if (temp.charAt(0) == '1') {
            temp = xor(key, temp);
        } else {
            temp = xor("0".repeat(keyLen), temp);
        }

        return temp;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ---------------- Sender ----------------
        System.out.print("Enter data bits: ");
        String data = sc.nextLine();

        String key = "10001000000100001"; // CRC-CCITT polynomial

        String appendedData = data + "0".repeat(16);
        String crc = crcCCITT(appendedData, key);
        String transmittedData = data + crc;

        System.out.println("\nSender Side");
        System.out.println("CRC: " + crc);
        System.out.println("Transmitted Data: " + transmittedData);

        // ---------------- Receiver (NO ERROR) ----------------
        System.out.println("\nReceiver Side (Without Error)");
        String receivedData = transmittedData;

        String check = crcCCITT(receivedData, key);
        if (check.contains("1")) {
            System.out.println("Error detected");
        } else {
            System.out.println("No error detected");
        }

        // ---------------- Receiver (WITH ERROR) ----------------
        System.out.println("\nReceiver Side (With Error)");

        char[] receivedError = transmittedData.toCharArray();
        receivedError[5] = (receivedError[5] == '0') ? '1' : '0'; // Flip one bit
        String receivedDataError = new String(receivedError);

        System.out.println("Received Data (with error): " + receivedDataError);

        String checkError = crcCCITT(receivedDataError, key);
        if (checkError.contains("1")) {
            System.out.println("Error detected");
        } else {
            System.out.println("No error detected");
        }

        sc.close();
    }
}