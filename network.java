import java.util.Scanner;

public class network {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input rows and columns
        System.out.print("Enter number of rows: ");
        int r = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int c = sc.nextInt();

        int data[][] = new int[r][c];
        int rowParity[] = new int[r];
        int colParity[] = new int[c];

        // --- INPUT DATA ---
        System.out.println("Enter the data bits (0 or 1):");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                data[i][j] = sc.nextInt();
            }
        }

        // --- CALCULATE ROW PARITY ---
        for (int i = 0; i < r; i++) {
            int sum = 0;
            for (int j = 0; j < c; j++)
                sum += data[i][j];

            rowParity[i] = sum % 2; // Even parity
        }

        // --- CALCULATE COLUMN PARITY ---
        for (int j = 0; j < c; j++) {
            int sum = 0;
            for (int i = 0; i < r; i++)
                sum += data[i][j];

            colParity[j] = sum % 2; // Even parity
        }

        // --- DISPLAY ORIGINAL DATA + PARITY BITS ---
        System.out.println("\n2D Parity Table:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                System.out.print(data[i][j] + " ");

            System.out.println("| " + rowParity[i]); // Row parity
        }

        // Print column parity
        for (int j = 0; j < c; j++)
            System.out.print("--");
        System.out.print("\n");
        for (int j = 0; j < c; j++)
            System.out.print(colParity[j] + " ");
        System.out.println("| column parity");
    }
}
