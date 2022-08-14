package edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordPlay {

    public static void main(String[] args) {
        System.out.println(isVowel('a'));
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println();
        String phrase = "aya";
        char ch = 'a';
        StringBuilder sb = new StringBuilder(phrase);
        for (int k = 0; k < sb.length(); k++) {
            if (isVowel(sb.charAt(k))) {
                sb.setCharAt(k, '0');
            }
        }
        System.out.println(sb);
    }

    public static boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
                || ch == 'O' || ch == 'U') {
            return true;
        }
        return false;
    }

    public static String replaceVowels(String phrase, char ch) {
        int i;
        for (i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                phrase = phrase.replace(phrase.charAt(i), ch);
            }
        }
        return phrase;
    }
    // Write a method emphasize with two parameters, a String named phrase and a
    // character named ch. This method should return a String that is the string
    // phrase but with the Char ch (upper- or lowercase) replaced by

    // ‘*’ if it is in an odd number location in the string (e.g. the first
    // character has an odd number location but an even index, it is at index 0), or

    // ‘+’ if it is in an even number location in the string (e.g. the second
    // character has an even number location but an odd index, it is at index 1).

    // For example, the call emphasize(“dna ctgaaactga”, ‘a’) would return the
    // string “dn* ctg+*+ctg+”, and the call emphasize(“Mary Bella Abracadabra”,
    // ‘a’) would return the string “M+ry Bell+ +br*c*d*br+”. Be sure to test this
    // method.
    public static String emphasize(String phrase, char ch) {
        int i;
        int numberLoc = 0;
        StringBuilder sb = new StringBuilder(phrase);
        for (i = 0; i < phrase.length(); i++) {
            numberLoc = i + 1;
            if (phrase.toLowerCase().charAt(i) == ch && (numberLoc) % 2 == 0) {
                sb.setCharAt(i, '+');
            } else if (phrase.toLowerCase().charAt(i) == ch && (numberLoc) % 2 > 0) {
                sb.setCharAt(i, '*');
            }
        }
        return sb.toString();
    }
}
