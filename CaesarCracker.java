package edu;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

//this class is to find key for a splited words in Vigenare ciphar with known key length 

/* given ciphar="rijz[ko[G;ek;yLniP;RDeAu%h;" ,key length=3

    -split the ciphar with key length
        rij z[ko [G; ek....etc  //without spaces
        3 letters for each word 
    -take the first letter of each word (or the indecrement loop in 3 steps)
    group1 --> rzo;;n;Du;
    group2 starts from the 2nd char in the splitting ciphar --> i[[eyiRAh
    group3 starts from the 3rd char in the splitting ciphar --> jkGkLPDu

    -find the key for each group that was used to encrypt it using ceasar getKey()

*/

public class CaesarCracker {

    char mostCommon;

    public CaesarCracker() {
        mostCommon = 'e';
    }

    public CaesarCracker(char c) {
        mostCommon = c;
    }

    public int[] countLetters(String message) {// counting each letter in the ciphar
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    // the freqs. of the ciphar and the plain text is the same,if we have 'h' as the
    // most frequent char in the ciphar, the decrypted msg should be 'e' (the most
    // frequent letter in alphabet)
    // 'h' has index 8 in the alphabet,if we do the shift of 3 ,we get 5 which is
    // the index of 'e' in the original freqs. alphabet

    public int maxIndex(int[] vals) {// the index of the most frequent letter of the ciphar in the alphabet
        int maxIdx = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxIdx]) {
                maxIdx = k;
            }
        }
        return maxIdx;
    }

    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxIdx = maxIndex(freqs);
         int mostCommonPos = mostCommon - 'a';//asscci number ('e'-'a')-->returns the
        // index of 'e' which is 4
        int decryptKey = maxIdx - mostCommonPos;// the idx of the most common letter of the ciphar - the idx of the most common
                                    // letter in English('e' index is 4)
        if (maxIdx < mostCommonPos) {// to avoid having unvalid index
            decryptKey = 26 - (mostCommonPos - maxIdx);
        }
        return decryptKey;
    }

    public String decrypt(String encrypted) {
        CaesarCiphar cc = new CaesarCiphar(getKey(encrypted));
        return cc.decrypt(encrypted);

    }

    // if the encrypted message was “Qbkm Zgis”, then you would split this String
    // into two Strings: “Qk gs”, representing the characters in the odd number
    // positions and “bmZi” representing the characters in the even number
    // positions. Then you would get the key for each half String and use the two
    // key encryption method to find the message. Note this example is so small it
    // likely won’t find the keys, but it illustrates how to take the Strings apart.

    private String halfOfString(String encryptedWith2, int start) {
        StringBuilder halvedMessage = new StringBuilder();

        for (int i = start; i < encryptedWith2.length(); i += 2) {
            halvedMessage.append(encryptedWith2.charAt(i));
        }

        return halvedMessage.toString();
    }

    public String decryptWith2Keys(String encryptedWith2) {
        String oddString = halfOfString(encryptedWith2, 0);
        String evenString = halfOfString(encryptedWith2, 1);

        int key1 = getKey(oddString);
        System.out.println("The 1st key " + key1);
        int key2 = getKey(evenString);
        System.out.println("The 2nd key " + key2);

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);

        return cc.decrypt(encryptedWith2);
    }

    public static void main(String[] args) throws FileNotFoundException{

        CaesarCracker c = new CaesarCracker();
        String engCiphar="Htfq-gqfhp nx gjyyjw ymfs fstymjw mzj, Ns ymfy ny xhtwsx yt gjfw fstymjw mzj; Ktw fqq ymj bfyjw ns ymj thjfs Hfs sjajw yzws ymj xbfs'x gqfhp qjlx yt bmnyj, Fqymtzlm xmj qfaj ymjr mtzwqd ns ymj kqtti.";
       // System.out.println(c.decrypt(engCiphar)+"\n"+c.getKey(engCiphar));


        String nonEngCiphar="Rj ridrj v fj sriÃµvj rjjzercrufj Hlv, ur ftzuvekrc girzr cljzkrer, Gfi drivj eletr uv rekvj ermvxrufj Grjjrird rzeur rcÃ©d ur Krgifsrer, Vd gvizxfj v xlviirj vjwfiÃ§rufj, Drzj uf hlv gifdvkzr r wfiÃ§r yldrer, V vekiv xvekv ivdfkr vuzwztrird Efmf ivzef, hlv krekf jlsczdrird. ..... Trekreuf vjgrcyrivz gfi kfur r grikv, Jv r krekf dv raluri f vexveyf v rikv";
        CaesarCracker c2=new CaesarCracker('a');
       // System.out.println(c2.decrypt(nonEngCiphar)+"\n"+c2.getKey(nonEngCiphar));



        //+ " \t " + c.getKey("brx duh dzhvrph"));

    
    }

}
