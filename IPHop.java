/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;
import java.lang.String;
import java.util.Arrays;
import java.util.Scanner; 
/**
 *
 * @author SuzyQ
 */



public class IPHop {
    public static String binary(String stringTest){

        String s1 = stringTest;
        String[] test = s1.split("\\.");
        String test1 = test[0];
        String test2 = test[1];
        String test3 = test[2];
        String test4 = test[3];
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(s1);
        int idk = Integer.valueOf(test1);
        int idk1 = Integer.valueOf(test2);
        int idk2 = Integer.valueOf(test3);
        int idk3 = Integer.valueOf(test4);
        String beep = Integer.toBinaryString(idk);
        String beep1 = Integer.toBinaryString(idk1);
        String beep2 = Integer.toBinaryString(idk2);
        String beep3 = Integer.toBinaryString(idk3);
        String padded = "00000000".substring(beep.length())+beep;
        String padded2 = "00000000".substring(beep1.length())+beep1;
        String padded3 = "00000000".substring(beep2.length())+beep2;
        String padded4 = "00000000".substring(beep3.length())+beep3;
        String fullIP = padded+padded2+padded3+padded4;
        System.out.println(fullIP);
        return fullIP;
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Enter an IP address");
        Scanner scan = new Scanner(System.in);
        String s1=" ";
        s1=scan.next();

        String[] test = s1.split("\\.");
        String test1 = test[0];
        String test2 = test[1];
        String test3 = test[2];
        String test4 = test[3];
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(s1);
        int idk = Integer.valueOf(test1);
        int idk1 = Integer.valueOf(test2);
        int idk2 = Integer.valueOf(test3);
        int idk3 = Integer.valueOf(test4);
        String beep = Integer.toBinaryString(idk);
        String beep1 = Integer.toBinaryString(idk1);
        String beep2 = Integer.toBinaryString(idk2);
        String beep3 = Integer.toBinaryString(idk3);
        String padded = "00000000".substring(beep.length())+beep;
        String padded2 = "00000000".substring(beep1.length())+beep1;
        String padded3 = "00000000".substring(beep2.length())+beep2;
        String padded4 = "00000000".substring(beep3.length())+beep3;
        String fullIP = padded+padded2+padded3+padded4;
        System.out.println(fullIP);
        String c = " ";
        if(test1=="135"){
            c="11111111111111111111110000000000";
        }
        else {
            c="11111111111111111111111000000000";
        }

       System.out.println(c);
       char top = ' ';
       int i=0;
       String finalYes = " ";
       StringBuilder sb = new StringBuilder();

       for(i=0;i<32;i++){
           if(c.charAt(i)=='1' && fullIP.charAt(i)=='1'){
               top='1';
               sb.append(top);
               finalYes=sb.toString();
           }
           else{
               top='0';
               sb.append(top);
               finalYes=sb.toString();
           }
       }
       System.out.println(finalYes);
       
       if(finalYes.equals(binary("135.46.56.0"))){
           System.out.println("IP "+s1+" hops to interface0");
       }
       else if(finalYes.equals(binary("135.46.60.0"))){
           System.out.println("IP "+s1+" hops to interface1");
       }
       else if(finalYes.equals(binary("192.53.40.0"))){
           System.out.println("IP "+s1+" hops to router1");
       }
       else{
           System.out.println("IP "+s1+" hops to router2");
       }
       
        
    }
    
}


