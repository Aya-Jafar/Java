package edu;

public class CaesarCiphar {
    private String shiftedAlpha;
    private String alpha;
    private int theKey;

    public CaesarCiphar(int key) {
        theKey=key;
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlpha = alpha.substring(key) + alpha.substring(0, key);
        alpha = alpha + alpha.toLowerCase();
        shiftedAlpha = shiftedAlpha + shiftedAlpha.toLowerCase();
    }

    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {//if it's a letter in alpha(from)
            return to.charAt(idx);//move it to 2nd string at the same position
        }
        return c;
    }

    public char encryptLetter(char c) {
        return transformLetter(c, alpha, shiftedAlpha);
    }

    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlpha, alpha);
    }

    private String transform(String input, String from, String to) {
        StringBuilder sb = new StringBuilder(input);
        char c;
        char letter;
        for (int i = 0; i < sb.length(); i++) {
            c = sb.charAt(i);
            letter = transformLetter(c, from, to);
            sb.setCharAt(i, letter);
        }
        return sb.toString();
    }

    public String encrypt(String input) {
        return transform(input, alpha, shiftedAlpha);//from alpha to shifted-alpha
    }

    public String decrypt(String input) {
        return transform(input, shiftedAlpha, alpha);//from shifted-alpha to alpha
    }
    public String toString() {
        return "" + theKey;
    }

    public static void main(String[] args) {
       // CaesarCiphar c = new CaesarCiphar(3);
        // System.out.println(c.encryptLetter('a'));

       //System.out.println(c.encrypt("you"));

    }
}
