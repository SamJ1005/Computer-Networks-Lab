import java.util.*;

public class TokenPassing {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number fo processes: ");
        int n=sc.nextInt();

        System.out.println("Enter sender processes: ");
        int sender=sc.nextInt();

        System.out.println("Enter receiver processes: ");
        int receiver=sc.nextInt();

        System.out.println("Token passing: ");
        for(int i=sender; i!=receiver; i=(i+1)%n) {
            System.out.println("Token passed from "+i+" to "+((i+1)%n));
        }

        System.out.println("Receiver received the token "+receiver);
        sc.close();
    }
}
