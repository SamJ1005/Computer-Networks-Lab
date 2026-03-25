import java.util.*;

public class TokenBucket {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Bucket capacity: ");
        int capacity=sc.nextInt();

        System.out.println("Token rate: ");
        int rate=sc.nextInt();

        int tokens=0;

        for(int i=0; i<=5; i++){
            tokens = Math.min(capacity, tokens+rate);

            System.out.println("Packet size: ");
            int packet=sc.nextInt();

            if(tokens>packet) {
                tokens-=packet;
                System.out.println("Packet sent");
            } else {
                System.out.println("Packet delayed");
            }

            System.out.println("Tokens Left: "+tokens);
        }
        sc.close();
    }
}
