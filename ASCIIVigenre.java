package edu;

import javafx.scene.paint.Paint;

public class ASCIIVigenre {

    public static String decrypt(String text, String keyword) {
        // String keyword = "SECRET";
        String decipheredText = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);// each char of the original
            if (c == ' ') {
                decipheredText += ' ';
            }
            if (c < 'A' || c > 'Z')// range in ASSCCI
                continue;
            decipheredText += (char) ((c - keyword.charAt(j) + 26) % 26 + 'A');
            j = ++j % keyword.length();// update j and split the length
        }
        return decipheredText;
    }

    static String encrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                res += ' ';
            }
            if (c < 'A' || c > 'Z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    static String autoKeyToEncrypt(String decrypted, String key) {
        String keyWord = "";
        // decrypted = decrypted.replaceAll(" ", "");
        keyWord = key.concat(decrypted);
        // if (keyWord.length() != decrypted.length()) {
        keyWord = keyWord.substring(0, keyWord.length() - key.length());
        // }
        System.out.println("IM KEY :-\n " + keyWord);
        System.out.println("\s" + encrypt(decrypted, keyWord));
        return keyWord;
    }

    static String autoKeyToDecrypt(String encrypted, String key) {
        String keyWord = "";
        // encrypted = encrypted.replaceAll(" ", "");
        String plain = decrypt(encrypted, key);// decrypt the encrypted
        // plain = plain.replaceAll(" ", "");
        System.out.println("PLAIN \n " + plain);

        keyWord += key + plain.substring(0, encrypted.length() - key.length()); // concatenate the decrypted text with
                                                                                // the old key

        System.out.println("\nIM KEY :-\n " + keyWord);
        System.out.println("\s" + decrypt(encrypted, keyWord));
        return keyWord;
    }

    public static void main(String[] args) {

        System.out.println(decrypt("PMGX PM CX GIYR", "DICE"));
        System.out.println(encrypt("MEET ME AT DAWN", "DICE"));
        System.out.println("_____________________\n");

        String autoKey = autoKeyToEncrypt("MEETMEATDAWN", "DICE");
        String autoKey2 = autoKeyToDecrypt("PMGX YI EM PEWG", "DICE");

    }

}
