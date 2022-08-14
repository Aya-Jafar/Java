package helloworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.HashSet;

public class exercise3 {
    public static void main(String[] args) {
        int num[] = { -5, 4, -1, 7, 8 };
        System.out.println("\n\n\n" + maxSubArray(num) + "\n\n\n");

        System.out.println(findMaxSum(num));

        System.out.println("\n" + palindromRec("kayak"));

        System.out.println(reverseRec("hello"));
        System.out.println(check("Kayak"));

        decimalToBinery(254);
        System.out.println();

        decimalToHexa(26);
        System.out.println();
        rgbToHexa(254, 211, 23);// yellow color

        System.out.println("\n" + sumNatural(10));

        int[] arr = { 2, 7, 11, 15 };
        System.out.println(Arrays.toString(twoSum(arr, 9)) + "\n");

        int ar[] = { 1, 2, 2, 3, 3, 5 };
        removeDupes(ar);
        stackRev("hello");

        System.out.println("\nFib. using map: " + fibMap(7));
        char[] l = { 'h', 'e', 'l', 'l', 'o' };

        System.out.println(Arrays.toString(getWordsNums("HI!  IM JIAN, nice to see you ")));

        System.out.println(sentenceNum("HEY, how're you?"));

        // System.out.println("___________________\n" + addDigits(99));

        System.out.println("______________\n" + getLucky("zxab", 2));
        int matrix[]={3,2,2,3};
        System.out.println("\n\n"+ removeElement(matrix,3));
    }

    public static void decimalToBinery(int decimal) {
        if (decimal == 0) {
            return;
        } else {
            System.out.print(decimal % 2);
            decimalToBinery(decimal / 2);
        }
    }

    public static void decimalToHexa(int decimal) {
        char[] hexa = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int hex = 0;
        if (decimal == 0) {
            return;
        } else {
            hex = decimal % 16;
            decimalToHexa(decimal / 16);
        }
        System.out.print(hexa[hex]);

    }

    public static void rgbToHexa(int red, int green, int blue) {
        decimalToHexa(red);
        System.out.print("-");
        decimalToHexa(green);
        System.out.print("-");
        decimalToHexa(blue);
    }

    public static String[] getWordsNums(String s) {
        s = s.replaceAll("[^a-zA-Z]+", " ");
        // System.out.println(s);
        String[] list = { s };
        list = s.split(" +");// split if there's one space or more
        System.out.println(list.length);
        return list;
    }

    public static int sentenceNum(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '?' || c == '!' || c == '.') {
                s = s.replaceAll("[!?.]+", "");
                count++;
                System.out.println(s);
            }
        }
        return count;
    }
    public static int removeElement(int[] nums, int val) {
        //[3,2,2,3],val=3
        //[2,2,_,_],return 2
        ArrayList<Integer> set=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                set.add(nums[i]);
            }
            else{
                continue;
            }
        }
        System.out.println("\n"+set);
        return set.size();
     
    }

    public static void regularEXP(String s) {
        s = s.replaceAll("[^a-zA-Z]+", "");// not alphabatic
        System.out.println(s);
    }

    public static int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        if (num <= 9) {
            return num;
        }
        int digit = 0;
        int sum = 0;
        System.out.println("DIGITS:\n");
        while (num > 0) {
            digit = num % 10;
            System.out.println(digit);
            sum += digit;
            num = num / 10;
        }
        if (sum < 9) { // one digit
            return sum;
        } else {
            return addDigits(sum);
        }
    }

    public static int getLucky(String s, int k) {// "iiii"-->9
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        int letterPos = 0;
        int sum = 0;
        int temp = 0;
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letterPos = alpha.indexOf(c);
            letterPos++;
            // System.out.println(letterPos);
            if (letterPos > 9) {
                digits.add(getDigits(letterPos));
                digits.add(letterPos / 10);
            } else {
                digits.add(letterPos);
            }
        }
        System.out.println(digits);
        int sum2 = 0;
        int dig = 0;
        for (int j = 0; j < digits.size(); j++) {
            sum += digits.get(j);
        }
        if (k == 1) {
            return sum;
        } else if (k > 1) {
            for (int i = 0; i < k; i++) {
                dig= getDigits(sum);
                sum2=sum/10;
                sum2 += dig;
            }
        }
        return sum2;

    }
    private static int getDigits(int letterPos) {
        int temp = 0;
        while (letterPos > 9) {
            temp = letterPos % 10;
            letterPos = letterPos / 10;
            // System.out.println(letterPos);
        }
        return temp;
        // return new int[]{letterPos,temp};
    }

    public static int fibMap(int n) { // memiozed solution using map
        HashMap<Integer, Integer> map = new HashMap<>();
        // base cases
        map.put(1, 1);
        map.put(0, 1);
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int fib = fibMap(n - 1) + fibMap(n - 2);
        map.put(n, fib);
        return fib;

    }

    public static String reverseRec(String input) {
        if (input.length() == 0) {
            return input; // return the reverse letters from the stack
        } else {
            // System.out.println((input.substring(1)) + input.charAt(0));
            return reverseRec(input.substring(1)) + input.charAt(0);
        }
    }

    public static void stackRev(String input) { // reverse a word using stack
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }
        PrintStack(stack);
    }

    private static void PrintStack(Stack<Character> s) {// from top to buttom
        if (s.empty())// base case
            return;
        // check top of the stack without popping it
        char x = s.peek();
        // Pop the top element
        s.pop();
        System.out.print(x);
        // print the remaining stack items
        PrintStack(s);
    }

    public static void removeDupes(int[] arr) { // of sorting arr
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < arr.length; j++) {
            if (map.containsKey(arr[j])) {
                map.remove(arr[j]);
            }
            map.put(arr[j], j);
        }
        for (int i : map.keySet()) {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);// copy the array and indicies to the map
        }
        for (int i = 0; i < nums.length; i++) {
            int differance = target - nums[i]; // find the second value of the sum
            if (map.containsKey(differance) && map.get(differance) != i) {// if the value is a key
                return new int[] { map.get(differance), i };// return the indecies
            }
        }
        return null;
    }

    public static boolean check(String inpuString) { // check palindrom using the prevoius func
        inpuString = inpuString.toLowerCase();
        if (inpuString.equals(reverseRec(inpuString))) {
            System.out.println(inpuString + "\t" + reverseRec(inpuString));
            System.out.println("\tThe word is palindrom");
            return true;
        }
        System.out.println("\tThe word is not palindrom");
        return false;
    }

    public static int sumNatural(int n) { // sum recursivly
        if (n == 0) {
            return 0;
        } else {
            return n + sumNatural(n - 1);
        }
    }

    public static boolean palindromRec(String toCheck) {
        if (toCheck.length() == 0 || toCheck.length() == 1) {
            return true;
        }
        if (toCheck.charAt(0) == toCheck.charAt(toCheck.length() - 1)) {
            return palindromRec(toCheck.substring(1, toCheck.length() - 1));// shrinking the input utill it's in the mid
        }
        return false;
    }

    // Brute Force Algorithm
    public static int maxSubArray(int[] nums) { // O(n^2)
        int curSum;
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j];// updating with each inner iteration
                // System.out.println(curSum);
                maxSum = Math.max(maxSum, curSum);
                // if (curSum > maxSum) {
                // maxSum = curSum;
                // }
            }
            // System.out.println("Inner iteration ends");
        }
        return maxSum;
    }

    public static int findMaxSum(int[] arr) {// O(n)
        int sum = 0;
        int max = arr[0];
        for (int num : arr) {
            sum += num;
            System.out.println(sum);
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
                // continue;
            }
        }
        return max;
    }

}
