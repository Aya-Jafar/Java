package edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MyCaesar {

    public static void main(String[] args) throws FileNotFoundException {

       // System.out.println(encrypt("I AM", 17));
        //System.out.println(encrypt2("you".toUpperCase(), 15) + "\n");
        System.out.println("EMAX\n"+encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?".toUpperCase(), 21,8));
    

       System.out.println("\n\nVDG QNKCKJCH JCIG QB VCRC IWU QWW ".toLowerCase());
        testCeasar();
        System.out.println("Test the decryption with 1 key => " + decrypt("qajacf", 3));

        // System.out.println("Here " + dec("brx"));

        // System.out.println( "Test the decryption with 2 key => " + decrypt2("Akag tjw Xibhr awoa aoee xakex znxag xwko", 2, 20));

    }

    public static String encrypt(String msg, int key) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alpha.substring(key);
        shifted = shifted + alpha.substring(0, key);
        
        System.out.println(shifted);
        char[] shiftedAlpha = shifted.toCharArray();
        char letters[] = msg.toCharArray();
        char alphaC[] = alpha.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i, j;
        for (j = 0; j < letters.length; j++) {
            if (letters[j] == ' ') {
                sb.append(" ");
            }
            for (i = 0; i < alphaC.length; i++) {
                if (letters[j] == alphaC[i]) {
                    sb.append(shiftedAlpha[i]);
                }
            }
        }
        return sb.toString();
    }

    private static void testCeasar() throws FileNotFoundException {
        File file = new File("\\Users\\SMART WAY\\Desktop\\sample.txt");
        Scanner sc = new Scanner(file);
        String message = sc.nextLine().toString().toUpperCase();

        System.out.println("Unencypted messege:\n" + message);

        String encrypted = encrypt2(message, 3);
        System.out.println("key is 3\n____________________________________________________\n" + encrypted);
        sc.close();
    }

    public static String encrypt2(String msg, int key) {

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // String alpha2=alpha.toUpperCase();
        String shifted = alpha.substring(key) + alpha.substring(0, key);
        // System.out.println("shifted alpha " + shifted);

        StringBuilder sb = new StringBuilder(msg);
        for (int i = 0; i < sb.length(); i++) {
            char letter = msg.charAt(i);
            int idx = alpha.indexOf(letter);// find the index of the letter in alpha
            if (idx != -1) { // i.e if it does excist in the alpha
                char ShiftedChar = shifted.charAt(idx);// find the char in the shifted alpha
                sb.setCharAt(i, ShiftedChar);// replace the old char with the new shifted char
            }
        }
        return sb.toString();
    }

    public static String decrypt(String message, int key) {
        char ch;
        String decryptedMessage = "";
        for (int i = 0; i < message.length(); ++i) {
            ch = message.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - key);
                if (ch < 'a') {
                    ch = (char) (ch + 'z' - 'a' + 1);
                }
                decryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - key);

                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }
                decryptedMessage += ch;
            } else {
                decryptedMessage += ch;
            }
        }
        return decryptedMessage;
    }

    // encryptTwoKeys(“First Legion”, 23, 17) should return “Czojq Ivdzle”. Note the
    // ‘F’ is encrypted with key 23, the first ‘i’ with 17, the ‘r’ with 23, and the
    // ‘s’ with 17, etc. Be sure to test this method.
    public static String encryptTwoKeys(String input, int key1, int key2) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] alphaC = alpha.toCharArray();
        char[] input2 = input.toCharArray();
        String output = "";
        for (int i = 0; i < input2.length; i++) {
            if (input.charAt(i) == ' ') {
                output += " ";
            }
            if (input.indexOf(input.charAt(i)) % 2 == 0) {
                char[] shiftedAlpha = shiftedAlpha(key1, alpha);
                output = check(alphaC, input2, output, i, shiftedAlpha);

            } else if (input.indexOf(input.charAt(i)) % 2 != 0) {
                char[] shiftedAlpha2 = shiftedAlpha(key2, alpha);
                output = check(alphaC, input2, output, i, shiftedAlpha2);
            }
        }
        return output;
    }

    public static char[] shiftedAlpha(int key, String alpha) {
        String shifted = alpha.substring(key) + alpha.substring(0, key);
        char shiftedAlpha[] = shifted.toCharArray();
        return shiftedAlpha;
    }

    public static String check(char[] alphaC, char[] input2, String output, int i, char[] shiftedAlpha) {
        for (int j = 0; j < alphaC.length; j++) {
            if (input2[i] == alphaC[j]) {
                output += shiftedAlpha[j];
            }
        }
        return output;
    }

}
