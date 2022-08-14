package edu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class wordsLen1 {
    public static void main(String[] args) throws IOException {

        File file = new File("\\Users\\SMART WAY\\Desktop\\smallHamlet.txt");
        Scanner scan = new Scanner(file);
        String contant = "";
        while (scan.hasNext()) {
            contant += scan.next() + " ";
        }
        scan.close();
        // System.out.println(contant);
        String strArr[] = contant.replaceAll("[^a-zA-Z '\"\"]", "").split("\\s+");// remove punctionation and make array of words

        Map<Integer, List<String>> lengthToWords = Stream.of(strArr).collect(Collectors.groupingBy(String::length));
        // Stream.of(strArr) same as Arrays.stream(strArr)
        // strArr is the source to the stream ,collect is the operation to that stream

        HashMap<Integer, List<String>> copy = new HashMap<Integer, List<String>>(lengthToWords);

        int MaxLen = 0;
        int len = 0;

        for (Integer n : copy.keySet()) {
            System.out.println(copy.get(n).size() + " words of length " + n + "\t" + copy.get(n));

            if (copy.get(n).size() > MaxLen) {
                MaxLen = copy.get(n).size();
                len = n;
            }
        }
        System.out.println("Most common word length:\n\s\s\s" + MaxLen + " words of " + len + " letters");




        // 2 words of length 2: My as

        // int j, k;
        // j = k = 0;
        // HashMap<String, Integer> map = new HashMap<>();

        // int countWords[] = new int[strArr.length];
        // String w[];
        // for (j = 0; j < strArr.length; j++) {
        // // Wordslen[j] = strArr[j].length();
        // strArr[j] = strArr[j].replaceAll("[^a-zA-Z]", "");

        // if (!map.containsKey(strArr[j])) {
        // map.put(strArr[j], strArr[j].length());
        // }

        // }

        // // String a = "";
        // HashMap<ArrayList<String>, Integer> res = new HashMap<>();

        // for (String key : map.keySet()) {

        // // System.out.println(key + "\t" + map.get(key));

        // for (String id : map.keySet()) {
        // if (map.get(id) == map.get(key)) {
        // // System.out.println(id + " " + map.get(id));
        // // Object[] keys = map.keySet().toArray();
        // // Object[] values = map.values().toArray();
        // String a = id + " ";

        // ArrayList<String> words=new ArrayList<>();

        // words.add(id);
        // if(!words.contains(key)){
        // words.add(key);
        // }
        // //System.out.println(words);

        // res.put(words, map.get(id));
        // System.out.println(words + "\t" + map.get(id));
        // break;
        // // System.out.println(Arrays.toString(keys)+"");
        // }
        // }
        // }
    }
}
