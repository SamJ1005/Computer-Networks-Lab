import java.util.Scanner;

public class errorCRC {
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++)
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return result.toString();
    }

    static String CIT(String data, String key) {
        int keyLen = key.length();
        if (data.length() < keyLen)
            return "ERROR: INPUT_TOO_SHORT";
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
        System.out.print("Enter Sender Data Bits: ");
        String data = sc.nextLine();
        String appendedData = data + "0".repeat(16);
        String crc = CIT(appendedData, key);
        String transmitted = data + crc;
        System.out.println("\n--- SENDER SIDE ---");
        System.out.println("Data: " + data);
        System.out.println("CRC: " + crc);
        System.out.println("Transmitted: " + transmitted);
        sc.close();
    }
}
