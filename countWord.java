package edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class countWord {

    public static void main(String[] args) throws FileNotFoundException {

        //counting each word in file using Arraylist 
        ArrayList<Integer> occourences = new ArrayList<>();
        File file2 = new File("\\Users\\SMART WAY\\Desktop\\sample.txt");
        Scanner scan = new Scanner(file2);
        ArrayList<String> fileContant = new ArrayList<>(); // array that has file contant
        ArrayList<String> words = new ArrayList<>(); // empty array to store the result
        while (scan.hasNext()) {
            fileContant.add(scan.next());
        }
        // System.out.println(fileContant);

     
        for (String s : fileContant) {
            s = s.toLowerCase();
            int idx = words.indexOf(s);// get the index of each word

            if (idx == -1) {// if it's the 1st time the word occurs
                // when the word exsicts in the file but not in the empty array
                words.add(s);// add the word the to empty array
                occourences.add(1);// the word occures one time
            } else {// if it's seen befor(idx != -1)
                int val = occourences.get(idx); // val is the number of times the word occured
                occourences.set(idx, val + 1); // replace value with index !=-1 with value of the increment counter+1
            }
        }

         //counting each word in file using HashMap
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : fileContant) {
            word = word.toLowerCase();
            if (!map.containsKey(word)) {// the 1st time it occured //if the word have never seen befor in map
                map.put(word, 1);
                
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        // System.out.println(map);

        for (String s : map.keySet()) {
            System.out.println("\s\s\s" + map.get(s) + "\t\s" + s);
            
        }

        System.out.println("\s\s___________________\nWords occured more than once:");
        ArrayList<String> list = new ArrayList<>();
        int total = 0;

        for (String s : map.keySet()) {
            // System.out.println("\s\s" + map.get(s) + "\t\s" + s);
            if (map.get(s) > 1 && !list.contains(s)) {
                list.add(s);
            }
        }
        System.out.println(list);


        // finding most common word
        int max = 0;
        ArrayList<Integer> idx=new ArrayList<>();
        for (int k = 0; k < occourences.size(); k++) {

            //System.out.println(occourences.get(k) + "\t" + words.get(k));
            if (occourences.get(k) > max) {
                max = occourences.get(k);
                
                idx.add(k);
               
            } 
        }
        System.out.println("\nMost common word is \""+words.get(max)+"\" with freqs. of "+max+" and appears in index  "+idx);
    }
}
