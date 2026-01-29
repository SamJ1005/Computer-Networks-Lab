import java.util.*;

public class PlayFair2 {
    static char[][] m = new char[5][5];

    static void keyMatrix(String k){
        boolean[] u = new boolean[26];
        k = (k+"ABCDEFGHIKLMNOPQRSTUVWXYZ").toUpperCase()
                .replace("J","I").replaceAll("[^A-Z]","");
        int r=0,c=0;
        for(char ch:k.toCharArray()){
            int i=ch-'A';
            if(!u[i]){
                u[i]=true; m[r][c++]=ch;
                if(c==5){c=0; r++;}
            }
        }
    }

    static int[] find(char ch){
        for(int i=0;i<5;i++)
            for(int j=0;j<5;j++)
                if(m[i][j]==ch) return new int[]{i,j};
        return null;
    }

    static String prepare(String t){
        t=t.toUpperCase().replace("J","I").replaceAll("[^A-Z]","");
        StringBuilder b=new StringBuilder();
        for(int i=0;i<t.length();i++){
            b.append(t.charAt(i));
            if(i+1<t.length() && t.charAt(i)==t.charAt(i+1)) b.append('X');
        }
        return b.length()%2==0?b.toString():b.append('X').toString();
    }

    static String encrypt(String t){
        t=prepare(t); StringBuilder c=new StringBuilder();
        for(int i=0;i<t.length();i+=2){
            int[] a=find(t.charAt(i)), b=find(t.charAt(i+1));
            if(a[0]==b[0])
                c.append(m[a[0]][(a[1]+1)%5]).append(m[b[0]][(b[1]+1)%5]);
            else if(a[1]==b[1])
                c.append(m[(a[0]+1)%5][a[1]]).append(m[(b[0]+1)%5][b[1]]);
            else
                c.append(m[a[0]][b[1]]).append(m[b[0]][a[1]]);
        }
        return c.toString();
    }

    static void printMatrix(){
        System.out.println("\nMatrix:");
        for(char[] r:m){
            for(char c:r) System.out.print(c+" ");
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.print("Key: ");
        keyMatrix(s.nextLine());
        System.out.print("Text: ");
        String cipher=encrypt(s.nextLine());
        printMatrix();
        System.out.println("Cipher: "+cipher);
        s.close();
    }
}
