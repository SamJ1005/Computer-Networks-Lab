import java.util.*;

public class PlayFair {
    static char[][] m=new char[5][5];

    static void key(String k){
        boolean[] u=new boolean[26];
        k=(k+"ABCDEFGHIKLMNOPQRSTUVWXYZ").toUpperCase().replace("J","I").replaceAll("[^A-Z]","");
        for(int i=0,r=0,c=0;i<k.length();i++){
            int x=k.charAt(i)-'A';
            if(!u[x]){ 
                u[x]=true; 
                m[r][c++]=k.charAt(i); 
                if(c==5){
                    c=0;r++;
                } 
            }
        }
    }

    static int[] f(char ch){
        for(int i=0;i<5;i++) 
            for(int j=0;j<5;j++)
                if(m[i][j]==ch) return new int[]{i,j};
        return null;
    }

    static String enc(String t){
        t=t.toUpperCase().replace("J","I").replaceAll("[^A-Z]","");
        StringBuilder p=new StringBuilder(), c=new StringBuilder();
        for(int i=0;i<t.length();i++){
            p.append(t.charAt(i));
            if(i+1<t.length() && t.charAt(i)==t.charAt(i+1)) p.append('X');
        }
        if(p.length()%2==1) p.append('X');
        for(int i=0;i<p.length();i+=2){
            int[] a=f(p.charAt(i)), b=f(p.charAt(i+1));
            if(a[0]==b[0]) 
                c.append(m[a[0]][(a[1]+1)%5]).append(m[b[0]][(b[1]+1)%5]);
            else if(a[1]==b[1]) 
                c.append(m[(a[0]+1)%5][a[1]]).append(m[(b[0]+1)%5][b[1]]);
            else 
                c.append(m[a[0]][b[1]]).append(m[b[0]][a[1]]);
        }
        return c.toString();
    }

    public static void main(String[] a){
        Scanner s=new Scanner(System.in);
        System.out.print("Key: "); key(s.nextLine());
        System.out.print("Text: "); String c=enc(s.nextLine());
        System.out.println("\nMatrix:");
        for(char[] r:m){ 
            for(char x:r) 
                System.out.print(x+" "); 
                System.out.println(); 
        }
        System.out.println("Cipher: "+c);
    }
}
