package edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Cipharo {
    private String shiftedAlpha2;
    private String shiftedAlpha;
    private String alpha;
    private String alpha2;
    private int key2;
    private int key;

    public Cipharo(int key) {
        this.key = key;
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alpha2 = alpha.toLowerCase();
        shiftedAlpha = alpha.substring(key) + alpha.substring(0, key);
    }


    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char letter = sb.charAt(i);
            if (checkCase(letter) == true) {
                helper(sb, i, letter);
            } else {
                helper(sb, i, letter);
            }
        }
        return sb.toString();

    }

    public char encryptLetter(char c) {
        c += key % 26;
        return c;
    }

    public char decryptLetter(char c) {
        c -= key % 26;
        return c;
    }

    private boolean checkCase(char letter) {
        if (Character.isLowerCase(letter)) {
            alpha = alpha.toLowerCase();
            shiftedAlpha = alpha.substring(key) + alpha.substring(0, key);
            return true;
        }
        return false;
    }

    private void helper(StringBuilder sb, int i, char letter) {
        int idx;
        idx = alpha.indexOf(letter);
        if (idx != -1) {
            char ShiftedChar = shiftedAlpha.charAt(idx);
            sb.setCharAt(i, ShiftedChar);
        }
    }

    public String encryptFile(File file) throws IOException {
        Scanner scan = new Scanner(file);
        String input = scan.nextLine().toString().toLowerCase();
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char letter = sb.charAt(i);
            // if (Character.isUpperCase(letter)) {
            // input = scan.nextLine().toString().toUpperCase();
            // }
            int idx = alpha.indexOf(letter);
            if (idx != -1) {
                char ShiftedChar = shiftedAlpha.charAt(idx);
                sb.setCharAt(i, ShiftedChar);
            }
        }
        String fileContant = "";
        fileContant = fileContant.concat(sb.toString());
        FileWriter writer = new FileWriter("\\Users\\SMART WAY\\Desktop\\newFile.txt");
        writer.write(fileContant);
        writer.close();
        scan.close();
        return sb.toString();
    }

    public String decrypt(String input) {
        Cipharo c = new Cipharo(26 - key);
        String output = c.encrypt(input);
        return output;
    }

    

    public String decryptFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String input = scan.nextLine().toString();
        Cipharo c = new Cipharo(26 - key);
        String output = c.encrypt(input);
        scan.close();
        return output;
    }

    public String figureKey(String input) {
        System.out.println("The key was " + key);
        Cipharo obj = new Cipharo(key);
        String output = obj.decrypt(input);
        return output;
    }


    public static void main(String[] args) throws IOException {
        Cipharo c = new Cipharo(3);

        System.out.println(c.encryptLetter('a'));
        System.out.println(c.decryptLetter('d'));

        
        // System.out.println(c.encrypt("Meet me at Elephant lake"));

        // System.out.println(c.decrypt("Phhw ph dw hohskdqw odnh") + "\n");
        // File file = new File("\\Users\\SMART WAY\\Desktop\\sample.txt");

        // System.out.println(c.encryptFile(file) + "\n\n");

        // File file2 = new File("\\Users\\SMART WAY\\Desktop\\newFile.txt");
        // System.out.println(c.decryptFile(file2));

        // Cipharo cc = new Cipharo(23, 17);
        // System.out.println(cc.figureKey2("Akag tjw Xibhr awoa aoee xakex znxag
        // xwko"));

        // System.out.println(cc.encryptWithTwokeys("you are awesome"));

        // System.out.println(cc.decryptTwo("czojq"));

    }

}
