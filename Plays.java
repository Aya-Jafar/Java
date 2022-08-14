package edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Plays {
    public static void main(String[] args) throws FileNotFoundException {

        File commonWords = new File("\\Users\\SMART WAY\\Desktop\\commonWords.txt");
        String common[] = new String[20];
        Scanner scan = new Scanner(commonWords);
        int j = 0;
        for (j = 0; j < common.length; j++) {
            if (scan.hasNext()) {
                common[j] = scan.next();
            }
        }
        // System.out.println(Arrays.toString(common) + "\n");

        File play = new File("\\Users\\SMART WAY\\Desktop\\romeo.txt");
        countWords(play);
        Scanner read = new Scanner(play);
        int countWords;

        try (Scanner sc = new Scanner(new FileInputStream(play))) {
            countWords = 0;
            while (sc.hasNext()) {
                sc.next();
                countWords++;
            }
        }

        int ind;
        String words[] = new String[countWords];
        for (ind = 0; ind < countWords; ind++) {
            if (read.hasNext()) {
                words[ind] = read.next();
                // System.out.println(words[ind]);
            }
        }
        // System.out.println("\n" + Arrays.toString(words));

        countCommon(common, words);
    }

    private static void countCommon(String[] common, String[] words) {
        int j;
        HashMap<String, Integer> hm = new HashMap<>();
        int count[] = new int[common.length];
        int i;
        int maxCommonLength = 0;
        int maxOccurLength = 0;
        
        for (i = 0; i < words.length; i++) {
            for (j = 0; j < common.length; j++) {
                if (words[i].equals(common[j])) {
                    count[j] += 1;
                    hm.put(common[j], count[j]);
                }
            }
        }
        System.out.println(hm);
    }

    public static void countWords(File file) throws FileNotFoundException {
        int countWords;
        int[] counts;
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            countWords = 0;
            while (sc.hasNext()) {
                sc.next();
                countWords++;
            }
            System.out.println("Number of words: " + countWords);
        }
    }
}
