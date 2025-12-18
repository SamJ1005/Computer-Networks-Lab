public class errorCRC {
    public static int crc16(String data) {
        int crc = 0xFFFF;
        int poly = 0x1021;

        for (char c : data.toCharArray()) {
            crc ^= (c << 8);
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0)
                    crc = (crc << 1) ^ poly;
                else
                    crc <<= 1;
            }
        }
        return crc & 0xFFFF;
    }

    public static void main(String[] args) {
        String data = "HELLO";
        System.out.println("Data: " + data);
        System.out.println("CRC-CCITT: " + Integer.toHexString(crc16(data)));
    }
}
