package edu;

import java.util.ArrayList;
import java.util.HashMap;

public class MyVigenare {
    public static void main(String[] args) {
        String original = "meet me at dawn now";

        String keyStr = "dice";

        // original = original.trim();
        String withoutSpaces = original.replaceAll("\\s+", "");

        System.out.println(withoutSpaces);

        keyStr = makeKey(withoutSpaces, keyStr);

        System.out.println(keyStr);

        HashMap<Character, Integer> alpha = new HashMap<>();

        int idxOfkey[] = new int[keyStr.length()];
        int idxOforiginal[] = new int[withoutSpaces.length()];

        alpha.put('a', 1);
        alpha.put('b', 2);
        alpha.put('c', 3);
        alpha.put('d', 4);
        alpha.put('e', 5);
        alpha.put('f', 6);
        alpha.put('g', 7);
        alpha.put('h', 8);
        alpha.put('i', 9);
        alpha.put('j', 10);
        alpha.put('k', 11);
        alpha.put('l', 12);
        alpha.put('m', 13);
        alpha.put('n', 14);
        alpha.put('o', 15);
        alpha.put('p', 16);
        alpha.put('q', 17);
        alpha.put('r', 18);
        alpha.put('s', 19);
        alpha.put('t', 20);
        alpha.put('u', 21);
        alpha.put('v', 22);
        alpha.put('w', 23);
        alpha.put('x', 24);
        alpha.put('y', 25);
        alpha.put('z', 26);

        idxOfkey = makeIdxOfKey(keyStr, alpha, idxOfkey);
        System.out.println("\n_____________________\n");

        idxOforiginal = makeIdxOfOriginal(withoutSpaces, alpha, idxOforiginal);

        System.out.println("\n_____________________\n");

        System.out.println(idxOfkey.length + "\t" + idxOforiginal.length);

        System.out.println(encrypt(original, keyStr, alpha, idxOfkey, idxOforiginal));

        System.out.println(decrypt("pmgx ug db hdep", keyStr));

        // System.out.println(decrypt("tcmp-pxety mj nikhqv htee mrfhtii tyv",
        // keyStr,alpha, idxOfkey, idxOforiginal));

    }

    private static int[] makeIdxOfOriginal(String withoutSpaces, HashMap<Character, Integer> alpha,
            int[] idxOforiginal) {
        for (int j = 0; j < withoutSpaces.length(); j++) {

            if (Character.isLetter(withoutSpaces.charAt(j))) {
                idxOforiginal[j] += alpha.get(withoutSpaces.charAt(j));
                System.out.print(idxOforiginal[j] + " ");
            }
        }
        return idxOforiginal;
    }

    private static int[] makeIdxOfKey(String keyStr, HashMap<Character, Integer> alpha, int[] idxOfkey) {
        for (int i = 0; i < keyStr.length(); i++) {

            if (alpha.containsKey(keyStr.charAt(i))) {
                idxOfkey[i] += alpha.get(keyStr.charAt(i));
                System.out.print(idxOfkey[i] + " ");
            }
        }
        return idxOfkey;
    }

    private static String makeKey(String withoutSpaces, String keyStr) {

        String key = "";
        // repeate the key with original length

        key = keyStr.repeat(withoutSpaces.length());

        return key.substring(0, withoutSpaces.length());

    }

    public static String encrypt(String original, String keyStr, HashMap<Character, Integer> alpha, int[] idxOfkey,
            int[] idxOforiginal) {

        String result = "";
        int idxSum[] = new int[original.replaceAll("[^a-zA-Z+]", "").length()];

        System.out.println("key's indecies + original's indecies :\n");

        for (int i = 0; i < idxSum.length; i++) {

            idxSum[i] = (idxOfkey[i] + idxOforiginal[i]) % 26;

            if (idxSum[i] == 0) {
                idxSum[i] += 1;// to match with the map
            }
            System.out.println(idxOfkey[i] + " + " + idxOforiginal[i] + " mod 26" + "= " + idxSum[i]);
            for (char c : alpha.keySet()) {
                if (idxSum[i] == alpha.get(c)) {
                    result += c;
                }
            }
        }
        StringBuilder finalRes = makeSpaces(original, result);
        return finalRes.toString();

    }

    private static StringBuilder makeSpaces(String original, String withOutSpaces) {

        // meet me at dawn now
        // qnhyqndyhjasrxa
        // qnhy qn dy hjas rxa

        StringBuilder withSpaces = new StringBuilder(withOutSpaces);// take the contant of the string without spaces
        withSpaces.setLength(original.length());// and take the length of the original with spaces

        ArrayList<Integer> list = new ArrayList<Integer>();
        char space = ' ';

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == space) {
                list.add(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            int idx = list.get(i);
            withSpaces.insert(idx, space);
        }
        return withSpaces;
    }

   

    public static String decrypt(String cipherText, String key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int alphabetSize = alphabet.length();
        int keySize = key.length();

        int shiftedPos = 0;
        StringBuilder decryptedText = new StringBuilder(cipherText.length());

        for (int i = 0; i < cipherText.length(); i++) {
            char encyrptChar = cipherText.charAt(i); // get the current character to be shifted
            char keyChar = key.charAt(i % keySize); // use key again if the end is reached

            int plainPos = alphabet.indexOf(encyrptChar); // plain character's position in alphabet string

            // decrypt the input text
            int keyPos = alphabet.indexOf(keyChar); // key character's position in alphabet

            shiftedPos = plainPos - keyPos;
            if (shiftedPos < 0) {
                shiftedPos += alphabetSize;
            }
            decryptedText.append(alphabet.charAt(shiftedPos));

        }
        String res=replaceAll(decryptedText.toString(), cipherText);
        return "\n" + res;
    }

    private static String replaceAll(String withOutSpaces, String cipharWithSpaces) {
        // meetwmevatxdawn
        // qnhy qn dy hjas 
        // replace every char in "withOutSpaces" with space of the same index of the
        // ciphar
        ArrayList<Integer>spaceIdx=new ArrayList<>();
        
        for (int i = 0; i < cipharWithSpaces.length(); i++) {
            if(cipharWithSpaces.charAt(i)==' '){
                spaceIdx.add(i);
            }
        }
        StringBuilder sb=new StringBuilder(withOutSpaces);
        for (int i = 0; i < spaceIdx.size(); i++) {
            sb.setCharAt(spaceIdx.get(i), ' ');
        }
        return sb.toString();//return new string of withOutSpaces seperated with spaces 
    }
}
