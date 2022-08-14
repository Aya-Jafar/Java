package helloworld;

public class Exersice {
    public static void main(String[] args) {
        // int binery=Integer.parseInt("011", 2); //binery to decimal

        // System.out.println(Integer.parseInt("011", 2));
        String bin = Integer.toString(11, 2); // decimal to binary
        // decimalToBinery(5665);
        // System.out.println(bin);
        sumOnesInBinery(bin);
        // System.out.println();
        // toDec("011");
        System.out.println(addBinary("11", "1"));// 100
    }

    public static int toDec(String n) {
        String num = n;
        int dec_value = 0;
        // Initializing base value to 1,
        // i.e 2^0
        int base = 1;// {2^0,2^1,2^2,2^3,2^4}

        int len = num.length();
        for (int i = len - 1; i >= 0; i--) {
            if (num.charAt(i) == '1')
                dec_value += base;
            // {32,16,8,4,2,1}
            base = base * 2; // update the base
        }
         return  dec_value;
    }

    public static void decimalToBinery(int decimal) {

        if (decimal == 0) {
            return;
        } else {
            System.out.print(decimal % 2);
            decimalToBinery(decimal / 2);
        }
    }

    public static void sumOnesInBinery(String binery) {
        int sum = 0;
        for (int i = 0; i < binery.length(); i++) {
            if (binery.charAt(i) == '1') {
                String c = Character.toString(binery.charAt(i));
                sum += Integer.parseInt(c);
            }
        }
        System.out.println(sum);
    }

    public static String addBinary(String a, String b)  {

        // String res = "";
        // int carry = 0;
        // StringBuilder reversA = new StringBuilder(a);
        // reversA.reverse();
        // StringBuilder reversB = new StringBuilder(b);
        // reversB.reverse();

        // int maxLength = Math.max(a.length(), b.length());

        // for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
        //     if (i < a.length()) {
        //         char digitA = a.charAt(i);
        //     }
        //     else{
        //         char def='0';
        //     }
        // }
        // return b;
    
        // convert binery into decimal   
        int number0 = Integer.parseInt(a, 2);
        int number1 = Integer.parseInt(b, 2);
        //add them
        long sum =toDec(a)+toDec(b);
        //and then convert the sum into binery
        return Integer.toBinaryString((int) sum);

    }

}
