package edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class WhichLang {

    public static HashSet<String> readDictionary(File file) throws FileNotFoundException {

        HashSet<String> set = new HashSet<>();
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            set.add(s.toLowerCase());
        }
        scan.close();
        return set;

    }

    public static HashMap<String, HashSet<String>> makeDicMap() throws FileNotFoundException {
        // a map that will store langueges name with it's set of words
        HashMap<String, HashSet<String>> map = new HashMap<>();

        File english = new File("English.txt");
        HashSet<String> dic1 = readDictionary(english);
        map.put("English", dic1);

        File french = new File("French.txt");
        HashSet<String> dic2 = readDictionary(french);
        map.put("French", dic2);

        File germen = new File("German.txt");
        HashSet<String> dic3 = readDictionary(germen);
        map.put("Germen", dic3);

        File spanish = new File("Spanish.txt");
        HashSet<String> dic4 = readDictionary(spanish);
        map.put("Spanish", dic4);

        File dutch = new File("Dutch.txt");
        HashSet<String> dic5 = readDictionary(dutch);
        map.put("Ductch", dic5);

        File italian = new File("Italian.txt");
        HashSet<String> dic6 = readDictionary(italian);
        map.put("Italian", dic6);
        
        return map;
    }

    public static void WhichLanguage1(String text) throws FileNotFoundException {

        HashMap<String, HashSet<String>> dictionaries = makeDicMap();

        String words[] = text.split("\\s+");

        boolean isValid = false;
        String whichLang = "";
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
            for (String lang : dictionaries.keySet()) {
                if (dictionaries.get(lang).contains(words[i])) {
                    whichLang = lang;
                    isValid = true;
                    //break;
                }
            }
        }
        if (isValid == true) {
            System.out.println("The text was in " + whichLang);
        }

        if (isValid == false) {
            System.out.println("unvalid words in dictionaries");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        WhichLanguage1("du bist mutig");//you are brave , Germen

    }
}
