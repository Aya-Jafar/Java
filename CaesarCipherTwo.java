package edu;

public class CaesarCipherTwo {
    private String shiftedAlpha1;
    private String shiftedAlpha2;
    private String alpha;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int key1, int key2) {
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlpha1 = alpha.substring(key1) + alpha.substring(0, key1);
        shiftedAlpha2 = alpha.substring(key2) + alpha.substring(0, key2);
        this.key1 = key1;
        this.key2 = key2;
    }

    public String deciphar(String input) {

        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            char currentCharacter = input.charAt(i);
            //find the index of each char in the ciphar in the alphabet
            int index = alpha.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));

            if (index != -1) {//does excist in alpha
                String AlphabetUsed;

                if (i % 2 == 0) {//letters with even indecies
                    //decide which shifted alphabed in the constructor to use 
                    AlphabetUsed = shiftedAlpha1; 
                } else {
                    AlphabetUsed = shiftedAlpha2;
                }
                //checking the case of the ciphar letters and append it to the output 
                if (Character.isLowerCase(currentCharacter)) {//original char is lower case
                    encryptedMessage.append(Character.toLowerCase(AlphabetUsed.charAt(index)));//change the case of the shifted letters
                } else { //upper case ciphar letter
                    encryptedMessage.append(AlphabetUsed.charAt(index)); //append the output as it is  
                }
            }
            else {//not alphabatic letter
                encryptedMessage.append(currentCharacter);//append it as it is
            }
        } 
        return encryptedMessage.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.deciphar(input);
    }
    public static void main(String[] args) {
        CaesarCipherTwo cc = new CaesarCipherTwo(2, 20);
        System.out.println(cc.decrypt("Top ncmy qkff vi vguv vbg ycpx"));
        CaesarCipherTwo c = new CaesarCipherTwo(14,24);
        System.out.println(c.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
    }

}
