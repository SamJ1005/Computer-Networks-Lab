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

// import java.util.Scanner;

// public class testing {
//     static String poly = "10001000000100001";

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // 1. Sender Side
//         System.out.print("Enter binary data bits: ");
//         String data = sc.nextLine();
        
//         // Append 16 zeros to the data (for 16-bit CRC)
//         String code = data + "0000000000000000";
//         String remainder = divide(code);
        
//         String transmittedData = data + remainder;
//         System.out.println("Generated Checksum (Remainder): " + remainder);
//         System.out.println("Data to be transmitted: " + transmittedData);

//         // 2. Receiver Side (Error Detection)
//         System.out.println("\n--- Receiver Side ---");
//         System.out.print("Enter received data bits: ");
//         String receivedData = sc.nextLine();

//         String checkRemainder = divide(receivedData);
        
//         // If remainder is all zeros, there is no error
//         if (Integer.parseInt(checkRemainder, 2) == 0) {
//             System.out.println("No error detected. Data is valid.");
//         } else {
//             System.out.println("Error detected in transmission!");
//         }
//     }

//     // Function to perform Modulo-2 Division (XOR)
//     static String divide(String tempCode) {
//         int n = poly.length();
//         char[] codeArr = tempCode.toCharArray();
//         char[] polyArr = poly.toCharArray();

//         for (int i = 0; i <= tempCode.length() - n; i++) {
//             // If the leftmost bit is 1, perform XOR with polynomial
//             if (codeArr[i] == '1') {
//                 for (int j = 0; j < n; j++) {
//                     codeArr[i + j] = (codeArr[i + j] == polyArr[j]) ? '0' : '1';
//                 }
//             }
//         }
//         // Return the last 16 bits as the remainder
//         return new String(codeArr).substring(tempCode.length() - (n - 1));
//     }
// }