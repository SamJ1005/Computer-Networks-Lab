import java.util.Scanner;

public class ParityCheck {

    static void printMatrix(int[][] m, int r, int c) {

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                System.out.print(m[i][j] + " ");
            System.out.println("| " + m[i][c]);
        }

        for (int i = 0; i < (2 * c) - 1; i++) System.out.print("-");
        System.out.println();

        for (int j = 0; j < c; j++) System.out.print(m[r][j] + " ");
        System.out.println();
    }

    // ---------- Generate Parity Matrix ----------
    static int[][] generateParity(int[][] data, int r, int c) {
        int[][] m = new int[r + 1][c + 1];

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                m[i][j] = data[i][j];

        // row parity
        for (int i = 0; i < r; i++) {
            int xor = 0;
            for (int j = 0; j < c; j++) xor ^= m[i][j];
            m[i][c] = xor;
        }

        // column parity
        for (int j = 0; j < c; j++) {
            int xor = 0;
            for (int i = 0; i < r; i++) xor ^= m[i][j];
            m[r][j] = xor;
        }

        // overall parity
        m[r][c] = 0;

        return m;
    }

    static void readBits(Scanner sc, int[][] arr, int r, int c, String who) {

        System.out.println("\n" + who + ": Enter bits (0 or 1)");

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                System.out.print(who + " [" + (i+1) + "][" + (j+1) + "]: ");
                int bit = sc.nextInt();
                while (bit != 0 && bit != 1) {
                    System.out.print("Invalid! Enter 0 or 1: ");
                    bit = sc.nextInt();
                }
                arr[i][j] = bit;
            }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int r = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int c = sc.nextInt();

        int[][] sender = new int[r][c];
        int[][] receiver = new int[r][c];

        readBits(sc, sender, r, c, "Sender");
        int[][] senderP = generateParity(sender, r, c);

        System.out.println("\nSENDER SIDE 2D PARITY TABLE:");
        printMatrix(senderP, r, c);

        readBits(sc, receiver, r, c, "Receiver");
        int[][] receiverP = generateParity(receiver, r, c);

        System.out.println("\nRECEIVER SIDE 2D PARITY TABLE:");
        printMatrix(receiverP, r, c);

        System.out.println("\nChecking errors...");

        int errorRow = -1, errorCol = -1;

        // Check row parity
        for (int i = 0; i < r; i++) {
            if (senderP[i][c] != receiverP[i][c]) {
                errorRow = i + 1;
            }
        }

        // Check column parity
        for (int j = 0; j < c; j++) {
            if (senderP[r][j] != receiverP[r][j]) {
                errorCol = j + 1;
            }
        }

        if (errorRow == -1 && errorCol == -1) {
            System.out.println("\nNO ERROR — Transmission Successful!");
        } else {
            System.out.println("\nSingle-bit error detected at position: [" + errorRow + "][" + errorCol + "]");
            System.out.println(" Error while transmission. Data Corrupted!");
        }

        sc.close();
    }
}
