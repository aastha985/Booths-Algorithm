import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

public class Aastha_Project2{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("START");
        System.out.println("ENTER MULTIPLICAND");
        try{
            long multiplicand = in.nextLong();
            System.out.println("ENTER MULTIPLIER");
            try{
                long multiplier = in.nextLong();
                int n = findn(multiplicand, multiplier);
                int n1 = n;
                String q = Binary1(multiplier, n, 1);
                char q0 = '0';
                char q1=findq1(q);
                String accumulator = Binary1(0, n, 1);
                String s="";
                while(n1>0){
                    if(q1=='1' && q0=='0'){
                        //A=A-M
                        accumulator=add(Binary1(multiplicand, n, 0),accumulator,n);
                    }
                    else if(q1=='0' && q0=='1'){
                        //A=A+M
                        accumulator=add(Binary1(multiplicand, n, 1),accumulator,n);
                    }
                    s= ArithmeticShiftRight(accumulator, q);
                    accumulator=getaccumulator(s, n);
                    q=getq(s, n);
                    q0=getq0(s, n);
                    q1=findq1(q);
                    n1--;
                    }
                    try{
                        System.out.println("ANSWER");
                        System.out.println(answer(s));
                    }
                    catch(NumberFormatException e){
                        System.out.println("Answer is out of the range of long. Please enter numbers within the range.");
                    }
                }
                catch(InputMismatchException e2){
                    System.out.println("Multiplier not of the type long. Please enter multiplier of the type long.");
                }
            }
            catch(InputMismatchException e1){
                System.out.println("Multiplicand not of the type long. Please enter multiplicand of the type long.");
            } 
        }
    public static int findn(long multiplicand,long multiplier){
        long m1 =(long) Math.abs(multiplicand);
        long m2 =(long) Math.abs(multiplier);
        if(m1>m2) return Long.toBinaryString(m1).length()+1;
        else return Long.toBinaryString(m2).length() +1;
    }
    public static char findq1(String q){
        char arr[] = q.toCharArray();
        char a=arr[arr.length-1];
        return a;
    }
    public static String ArithmeticShiftRight(String accumulator,String q){
        String s1=accumulator;
        char c=s1.toCharArray()[0];
        return c+accumulator+q;
    }
    public static String getaccumulator(String s,int n){
        char arr[] = s.toCharArray();
        String s1 = "";
        for(int i=0;i<n;i++){
            s1+=arr[i];
        }
        return s1;
    }
    public static String getq(String s,int n){
        char arr[] = s.toCharArray();
        String s1 = "";
        for(int i=n;i<2*n;i++){
            s1+=arr[i];
        }
        return s1;
    }
    public static char getq0(String s,int n){
        char arr[] = s.toCharArray();
        return arr[arr.length-1];
    }
    public static String add(String s1,String s2,int n){
        int carry=0;
        char s3[] = s1.toCharArray();
        char s4[] = s2.toCharArray();
        String s5="";
        for(int i=n-1;i>=0;i--){
            if(carry==0){
                if(s3[i]=='0' && s4[i]=='0') s5+="0";
                else if(s3[i]=='0' && s4[i]=='1') s5+="1";
                else if(s3[i]=='1' && s4[i]=='0') s5+="1";
                else{
                    s5+="0";
                    carry=1;
                }
            }
            else{
                if(s3[i]=='0' && s4[i]=='0'){
                    s5+="1";
                    carry=0;
                }
                else if(s3[i]=='0' && s4[i]=='1') s5+=0;
                else if(s3[i]=='1' && s4[i]=='0') s5+="0";
                else s5+="1";
            }
        }
        String s6="";
        char s7[] = s5.toCharArray();
        for(int i=n-1;i>=0;i--){
            s6+=s7[i];
        }
        return s6;
    }
    public static String answer(String s){
        char c[] = s.toCharArray();
        if(c[0]=='1'){
            String s1="";
            for(int i=0;i<s.length()-1;i++){
                if(c[i]=='0') s1+="1";
                else s1+="0";
            }
            String s2 = add(s1, Binary1(1, s.length()-1, 1), s.length()-1);
            return "-"+Long.parseLong(s2,2);
        }
        else{
            String s1="";
            for(int i=0;i<s.length()-1;i++){
                s1+=c[i];
            }
            return ""+Long.parseLong(s1,2);
        }
    }
    public static String Binary1(long no,int n,int pos){
        String s="";
        if(no>=0){
            if(pos==0) s= Long.toBinaryString(-no);
            else s=Long.toBinaryString(no);
        }
        else{
            if(pos==0) s= Long.toBinaryString(-no);
            else s= Long.toBinaryString(no);
        }
        String s2="";
        if(s.length()<n){
            int p1 = n-s.length();
            for(int i=0;i<p1;i++){
                s2+="0";
            }
            s2+=s;
        }
        else if(s.length()==n) s2=s;
        else{
            char new1[] = s.toCharArray();
            for(int i=s.length()-n;i<s.length();i++){
                s2+=new1[i];
            }
        }
        return s2;
    }
}