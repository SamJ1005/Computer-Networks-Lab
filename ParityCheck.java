import java.util.Scanner;

public class ParityCheck {

    // Helper function to print 2D parity matrix
    public static void printMatrix(int[][] m, int r, int c) {

        // Print data rows + row parity
        for (int i = 0; i < r; i++) {

            // print data bits
            for (int j = 0; j < c; j++) {
                System.out.print(m[i][j] + " ");
            }

            // vertical bar and row parity bit
            System.out.println("| " + m[i][c]);
        }

        // print horizontal line
        for (int i = 0; i < (2 * c) - 1; i++) {
            System.out.print("-");
        }
        System.out.println();

        // print column parity bits
        for (int j = 0; j < c; j++) {
            System.out.print(m[r][j] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int r = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int c = sc.nextInt();

        int[][] sender = new int[r][c];
        int[][] senderParity = new int[r + 1][c + 1];

        System.out.println("\nSENDER: Enter the data bits (0 or 1):");

        // Read sender data
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sender[i][j] = sc.nextInt();
                senderParity[i][j] = sender[i][j];
            }
        }

        // Compute row parity
        for (int i = 0; i < r; i++) {
            int sum = 0;
            for (int j = 0; j < c; j++)
                sum ^= senderParity[i][j];
            senderParity[i][c] = sum;
        }

        // Compute column parity
        for (int j = 0; j < c; j++) {
            int sum = 0;
            for (int i = 0; i < r; i++)
                sum ^= senderParity[i][j];
            senderParity[r][j] = sum;
        }

        // Overall parity (not required but included)
        senderParity[r][c] = 0;

        System.out.println("\nSENDER SIDE 2D PARITY TABLE:");
        printMatrix(senderParity, r, c);

        // ---------------- RECEIVER ------------------

        int[][] receiver = new int[r][c];
        int[][] receiverParity = new int[r + 1][c + 1];

        System.out.println("\nRECEIVER: Enter received bits (0 or 1):");

        // Read receiver data
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                receiver[i][j] = sc.nextInt();
                receiverParity[i][j] = receiver[i][j];
            }
        }

        // Compute receiver row parity
        for (int i = 0; i < r; i++) {
            int sum = 0;
            for (int j = 0; j < c; j++)
                sum ^= receiverParity[i][j];
            receiverParity[i][c] = sum;
        }

        // Compute receiver column parity
        for (int j = 0; j < c; j++) {
            int sum = 0;
            for (int i = 0; i < r; i++)
                sum ^= receiverParity[i][j];
            receiverParity[r][j] = sum;
        }

        receiverParity[r][c] = 0;

        System.out.println("\nRECEIVER SIDE 2D PARITY TABLE:");
        printMatrix(receiverParity, r, c);

        // ---------------- ERROR DETECTION ------------------

        int errorRow = -1, errorCol = -1, incorrectBit = -1;

        // ONLY compare data bits — NOT parity bits
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sender[i][j] != receiver[i][j]) {
                    errorRow = i + 1; // convert to 1-based index
                    errorCol = j + 1; // convert to 1-based index
                    incorrectBit = receiver[i][j];
                    break;
                }
            }
        }

        if (errorRow == -1)
            System.out.println("\nNO ERROR DETECTED.");
        else {
            System.out.println("\nERROR DETECTED at:");
            System.out.println("Row: " + errorRow + ", Column: " + errorCol);
            System.out.println("Incorrect bit = " + incorrectBit);
        }

        sc.close();
    }
}
