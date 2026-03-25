import java.util.Scanner;

public class ParityCheck {
    static void printMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    static int[][] parTable(int[][] data, int rows, int cols) {
        int[][] parity = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, parity[i], 0, cols);
            for (int j = 0; j < cols; j++)
                parity[i][cols] ^= parity[i][j];
        }
        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                parity[rows][j] ^= parity[i][j];
        return parity;
    }

    static int[][] readBits(Scanner sc, int rows, int cols, String label) {
        int[][] bits = new int[rows][cols];
        System.out.println("\n" + label + ": Enter bits (0 or 1)");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(label + " [" + (i + 1) + "][" + (j + 1) + "]: ");
                while ((bits[i][j] = sc.nextInt()) != 0 && bits[i][j] != 1)
                    System.out.print("Invalid! Enter 0 or 1: ");
            }
        }
        return bits;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] sData = readBits(sc, rows, cols, "Sender");
        int[][] sendPar = parTable(sData, rows, cols);
        System.out.println("SENDER SIDE 2D PARITY TABLE:");
        printMatrix(sendPar, rows, cols);

        int[][] rData = readBits(sc, rows, cols, "Receiver");
        int[][] recPar = parTable(rData, rows, cols);
        System.out.println("RECEIVER SIDE 2D PARITY TABLE:");
        printMatrix(recPar, rows, cols);

        int errorRow = 0, errorCol = 0;
        for (int i = 0; i < rows; i++) {
            if (sendPar[i][cols] != recPar[i][cols]) {
                errorRow++;
            }
        }
        for (int j = 0; j < cols; j++) {
            if (sendPar[rows][j] != recPar[rows][j]) {
                errorCol++;
            }
        }

        System.out.println("Checking errors...");
        if (errorRow == 0 && errorCol == 0)
            System.out.println("\nNO ERROR. Transmission Successful!");
        else if (errorRow == 1 && errorCol == 1) {
            System.out.println("\nSingle-bit error at position: [" + errorRow + "][" + errorCol + "]");
            System.out.println("Error while transmission. Data Corrupted!");
        } else {
            System.out.println("\nMultiple-bit error detected!");
            System.out.println("Error while transmission. Data Corrupted!");
        }
        sc.close();
    }
}