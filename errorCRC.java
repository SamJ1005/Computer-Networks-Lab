import java.util.Scanner;

public class errorCRC {

    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    static String crcCCITT(String data, String key) {
        int keyLen = key.length();

        if (data.length() < keyLen) {
            return "ERROR: INPUT_TOO_SHORT";
        }

        String temp = data.substring(0, keyLen);

        for (int i = keyLen; i < data.length(); i++) {
            if (temp.charAt(0) == '1')
                temp = xor(key, temp) + data.charAt(i);
            else
                temp = xor("0".repeat(keyLen), temp) + data.charAt(i);
        }

        if (temp.charAt(0) == '1')
            temp = xor(key, temp);
        else
            temp = xor("0".repeat(keyLen), temp);

        return temp;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String key = "10001000000100001"; // CRC-CCITT

        // ------------------- SENDER -------------------
        System.out.print("Enter Sender Data Bits: ");
        String data = sc.nextLine();

        String appendedData = data + "0".repeat(16);
        String crc = crcCCITT(appendedData, key);
        String transmitted = data + crc;

        System.out.println("\n--- SENDER SIDE ---");
        System.out.println("Generated CRC: " + crc);
        System.out.println("Transmitted Data: " + transmitted);
        System.out.println("\nNOTE: Receiver must enter EXACTLY this many bits: " + transmitted.length());


        // ------------------- RECEIVER -------------------
        System.out.print("\nEnter Received Data Bits: ");
        String received = sc.nextLine();

        if (received.length() != transmitted.length()) {
            System.out.println("\n❌ ERROR: Received bits must be " + transmitted.length() + " bits long!");
            sc.close();
            return;
        }

        System.out.println("\n--- RECEIVER SIDE ---");
        String remainder = crcCCITT(received, key);

        if (remainder.contains("1")) {
            System.out.println("❌ ERROR DETECTED!");
        } else {
            System.out.println("✅ No error detected.");
        }

        sc.close();
    }
}
