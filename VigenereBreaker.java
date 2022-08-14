package edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class VigenereBreaker {

    // this method split the encrypted msg to words, each word length = key length
    // (totalSlices)
    public String SliceString(String encrypted, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < encrypted.length(); i += totalSlices) {
            sb.append(encrypted.charAt(i));

        }
        // System.out.println(sb);
        return sb.toString();
    }

    // this method takes the splited words and find the key that was shifted with
    // for each word using caesar getKey()

    public int[] tryKeyLength(String encrypted, int keyLength, char mostCommon) {

        int[] key = new int[keyLength];

        CaesarCracker ccr = new CaesarCracker(mostCommon);

        for (int i = 0; i < keyLength; i++) {
            String s = SliceString(encrypted, i, keyLength);

            int a = ccr.getKey(s);

            key[i] = a;
        }
        return key;
    }

    public void breakVigenere(int keyLength) throws FileNotFoundException {

        /* KNOWN KEY LENGTH */
        File file = new File("\\Users\\SMART WAY\\Desktop\\ciphar.txt");

        Scanner scan = new Scanner(file);
        String read = "";

        String res = "";
        while (scan.hasNextLine()) {
            read += scan.nextLine();
        }
        int[] key = tryKeyLength(read, keyLength, 'e');
        VigenereCipher vc = new VigenereCipher(key);

        res = vc.decrypt(read);
        // int[] key = {5,11,20,19,4};
        System.out.print(res);
        // System.out.println(Arrays.toString(key));
    }

    /* UNKNOWN KEY LENGTH */
    public HashSet<String> readDictionary(File file) throws FileNotFoundException {
        // dictionary of any language
        HashSet<String> set = new HashSet<>();
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            set.add(s.toLowerCase());
        }
        scan.close();
        return set;
    }

    // checks if the decrypted (for all possiable key length) word is in the
    // dictionary (real words)
    public int countWords(String decrypted, HashSet<String> dictionary) {
        int count = 0;
        for (String s : decrypted.split("\\W+")) {
            String lower = s.toLowerCase();
            if (dictionary.contains(lower)) {
                count++;// counting real words
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {

        char mostCommon = getMostCommonChar(dictionary);
        String decrypted = "";
        VigenereCipher vc;
        int count = 0;
        int max = 0;
        // Note that there is nothing special about 100; we will just give you messages
        // with key lengths in the range 1–100. If you did not have this information,
        // you could iterate all the way to encrypted.length(). Your program would just
        // take a bit longer to run.

        for (int i = 1; i <= 100; i++) {// trying every possiable key length to decrypt
            int keys[] = tryKeyLength(encrypted, i, mostCommon);
            vc = new VigenereCipher(keys);
            decrypted = vc.decrypt(encrypted);
            count = countWords(decrypted, dictionary);// count real words
            if (count > max) {// the more real words in the decrypted msg,the more it's possiable it's the
                              // right decrypted msg
                max = count;// keep track of the max number of real words
            }
        }
        System.out.println(count);
        System.out.println(max);
        // iterate again to check if the max number we found = count of real words
        for (int j = 1; j <= 100; j++) {
            int[] key = tryKeyLength(encrypted, j, mostCommon);
            VigenereCipher v = new VigenereCipher(key);
            String s = v.decrypt(encrypted);
            // System.out.println(s+"\t");
            int a = countWords(s, dictionary);
            if (a == max) {
                System.out.println("\n" + a);
                return s; // return the right decrypted msg
            }
        }
        return null;
    }

    /* UNKNOWN LANGUAGE */

    private char getMostCommonChar(HashSet<String> dictionary) {
        /*
         * if the msg was encrypted with languges other than english then we should find
         * the most common letter in that langueges to use the frequensy analysis to
         * decrypt the msg
         */

        // a hashmap to store each letter and how many times it occured
        HashMap<Character, Integer> map = new HashMap<>();
        // loop through each word in the dictionary
        for (String eachWord : dictionary) {
            String words = eachWord.toLowerCase();
            // loop trough each letter in the dictionary
            for (char eachLetter : words.toCharArray()) {
                if (!map.containsKey(eachLetter)) {
                    map.put(eachLetter, 1);
                } else {
                    map.put(eachLetter, map.get(eachLetter) + 1);
                }
            }
        }
        int maxOccurences = 0;
        char mostCommon = ' ';
        for (char letter : map.keySet()) {
            if (map.get(letter) > maxOccurences) {
                maxOccurences = map.get(letter);
                mostCommon = letter;
            }
        }
        return mostCommon;
    }

    public HashMap<String,HashSet<String>> makeDicMap() throws FileNotFoundException {
        // a map that will store langueges name with it's set of words
        HashMap<String, HashSet<String>> map = new HashMap<>();

        File english = new File("\\Users\\SMART WAY\\Desktop\\Java Files\\dictionaries\\English.txt");
        HashSet<String> dic1 = readDictionary(english);
        map.put("English", dic1);

        File french = new File("\\Users\\SMART WAY\\Desktop\\Java Files\\dictionaries\\French.txt");
        HashSet<String> dic2 = readDictionary(french);
        map.put("French", dic2);

        File germen = new File("\\Users\\SMART WAY\\Desktop\\Java Files\\dictionaries\\German.txt");
        HashSet<String> dic3 = readDictionary(germen);
        map.put("German", dic3);

        return map;

    }

    // the string key in map represents the lang. and the value (set) is dictionary
    // of
    // this lang.
    public void breakForAllLang(String encrypted, HashMap<String, HashSet<String>> mapDic) {
        //trying to guess the language by trying every lang. 
        int max = 0;
        for (String language : mapDic.keySet()) {
           // Try breaking the encryption for each language, and see which gives the best results
            String lang = breakForLanguage(encrypted, mapDic.get(language));
            int count = countWords(lang, mapDic.get(language)); //count real words 
            if (count > max) {
                max = count;
            }
        }
        for (String language : mapDic.keySet()) {
            String lang = breakForLanguage(encrypted, mapDic.get(language));
            int count = countWords(lang, mapDic.get(language));
            if (count == max) {
                System.out.println("the decrypted msg was in " + language);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        VigenereBreaker v = new VigenereBreaker();

        File file = new File("\\Users\\SMART WAY\\Desktop\\Java Files\\dictionaries\\English.txt");

        HashSet<String> set = v.readDictionary(file);
        // System.out.println(set);

        File toDecrypt = new File("\\Users\\SMART WAY\\Desktop\\ciphar.txt");
        Scanner scan = new Scanner(toDecrypt);
        String read = "";
        while (scan.hasNext()) {
            read += scan.nextLine();
        }
        System.out.println(Arrays.toString(v.tryKeyLength(read, 5, 'e')));
        // v.breakVigenere(5);

        // System.out.println(v.findMostCommonChar2(set));
        // System.out.println(v.countWords(read, set));
        // System.out.println(v.getMostCommonChar(set));
        // v.makeDicMap();
        //System.out.println(v.breakForLanguage(read, set));
        //v.breakForAllLang(read, v.makeDicMap());

    }

}
