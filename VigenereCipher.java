package edu;


import java.io.FileNotFoundException;
import java.util.Arrays;
 


//this class is responsiable of decrypting a text using known key integers of caesar 


public class VigenereCipher{

    CaesarCiphar[] ciphers;

    public VigenereCipher(int[] key) {
        ciphers = new CaesarCiphar[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCiphar(key[i]);
        }
    }

    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
      
            CaesarCiphar thisCipher = ciphers[cipherIndex]; 
       
            answer.append(thisCipher.encryptLetter(c));
       
            i++;
        }
        return answer.toString();
    }

    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCiphar thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    public String toString() {
        return Arrays.toString(ciphers);
    }
   

    public static void main(String[] args) throws FileNotFoundException {
        int keys[]={17,14, 12 ,4};
        String ciphar="Tcmp-pxety mj nikhqv htee mrfhtii tyv";
        VigenereCipher vc=new VigenereCipher(keys);

        System.out.println(vc.decrypt(ciphar));
        System.out.println(vc.encrypt("Coal-black is better than another hue")); 

        // System.out.println(vc.decrypt("XNYGI TC. Febxrx. JYNYWX'X bhyxp."));


      
    }

}
