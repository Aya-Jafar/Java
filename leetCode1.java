package helloworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class exercise {
    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);

        int numbers[] = { -1, 0 };
        int target = -1;
        System.out.println(Arrays.toString(twoSum(numbers, target)));

        System.out.println(fib(7));

        int[] nums = { 3, 2, 2 };
        int arr = removeElement(nums, 3);
        System.out.println(arr);

        int numbers2[] = { 1, 3, 4, 5 };
        System.out.println(searchInsert(numbers2, 6));

        int s[] = { 3, 1, 3 };
        System.out.println(singleNumber(s));
        System.out.println("\n" + findDuplicate(s));

        System.out.println(reverse(-153));
        System.out.println(isPalindrome(-121));
        System.out.println("\n" + isValid("(){}}{"));

        int a[] = { 10, 4, 1, 4, -10, -50, 32, 21 };
        System.out.println("\n" + checkEnding("access", "ss"));

        System.out.println("\n" + calculator(2, '/', 0) + "\n");

        System.out.println("\n_____________\n" + isHappy(2) + "\n");

        System.out.println(reverseRec("hello"));
        System.out.println();
        char c[] = { 'h', 'e', 'l', 'l', 'o' };
        reverseString(c);
        System.out.println();

        System.out.println();
        int[] numb = { 50, 100, 149, 1, 200, 199, 3, 2 };
        System.out.println(warOfNumbers(numb));

        System.out.println(Arrays.deepToString(squarePatch(3)));

        System.out.println("_________\n" + sumRec(s, s.length));

        System.out.println("__________\n" + Arrays.toString(arrayOfMultiples(7, 5)) + "\n" + highestDigit(756));

        String dna = "CGATATA";
        System.out.println("The RNA original for " + dna + " DNA is " + dnaToRna(dna));

        int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println("\n" + diagonalSum(mat));

        System.out.println("\n\n" + getDigits(234, null));

    }

    public static int sumRec(int[] arr, int length) {
        if (length <= 0) {
            return 0;
        } else
            return sumRec(arr, length - 1) + arr[length - 1];
    }

    public static int diagonalSum(int[][] mat) {
        int sumDiag = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (i == j || ((i + j) == mat.length - 1)) {
                    sumDiag += mat[i][j];
                }
            }
        }
        return sumDiag;

    }

    public static String dnaToRna(String original) {
        // dnaToRna("CGATATA") ➞ "GCUAUAU"
        // A, T, G and C in DNA converts to U, A, C and G respectively in mRNA.
        String originalM = "";
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == 'A')
                originalM += "U";
            if (original.charAt(i) == 'T')
                originalM += "A";
            if (original.charAt(i) == 'G')
                originalM += "C";
            if (original.charAt(i) == 'C')
                originalM += "G";
        }
        return originalM;
    }

    public static int highestDigit(int n) {
        int digit = 0;
        int hight = 0;
        while (n > 0) {
            digit = (int) (n % 10);
            n = n / 10;
            if (digit > hight) {
                hight = digit;
            }
        }
        return hight;
    }

    public static int[] arrayOfMultiples(int num, int length) {
        int[] mult = new int[length];
        for (int i = 1; i <= length; i++) {
            mult[i - 1] = num * i;
        }
        return mult;
    }

    public static String reverseRec(String input) {
        if (input.length() == 0) {
            return input; // return the reverse letters from the stack
        } else {
            // System.out.println((input.substring(1)) + input.charAt(0));
            return reverseRec(input.substring(1)) + input.charAt(0);
        }
    }

    public static boolean isHappy(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int res = 0;
        if (n > 1 && n <= 4) {
            return false;
        }
        if (n == 1) {
            return true;
        } else {
            int temp = n;
            n = 0;
            while (temp != 0) {
                n += Math.pow(temp % 10, 2);
                temp = temp / 10;
            }
            return isHappy(n);
        }
    }

    private static ArrayList<Integer> getDigits(int n, ArrayList<Integer> list) {
        int digit;
        while (n > 0) {
            digit = (int) (n % 10);
            n = n / 10;
            list.add(digit);
        }
        System.out.println(list);
        return list;

    }

    private static int recursive(int res) {
        ArrayList<Integer> list = new ArrayList<>();
        getDigits(res, list);
        if (res == 1) {
            // System.out.println("here");
            return 1;
        } else if (res != 1) {
            for (int i = 0; i < list.size() - 1; i++) {
                res = (int) Math.pow(list.get(i), 2) + (int) Math.pow(list.get(i + 1), 2);
            }
            return recursive(res);
        }
        return -1;

    }

    // 3, 2, 2, 3---> [2,2_,_] length=2
    public static int removeElement(int nums[], int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                // shifting if it's not the val
                nums[index] = nums[i];// nums[0]=nums[1]
                index++;
            }
        }
        return index;
    }

    public static int searchInsert(int[] nums, int target) {
        int i;
        for (i = 0; i < nums.length; i++) {
            if (target == nums[i] || nums[i] > target) {
                return i;
            }
        }
        return nums.length;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int i, j;
        if (numbers.length == 0) {
            return null;
        }
        for (i = 0; i < numbers.length; i++) {
            for (j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    i++;
                    j++;
                    int[] result = { i, j };
                    return result;
                }
            }
        }
        return null;
    }

    // Input: nums = [4, 1, 2, 1, 2 ]
    // Output: 4
    // [2,2,1]-->output = 1
    // [-1]--->output=-1
    // [1,0,1]--->0
    public static int singleNumber(int[] nums) {
        int i, j;
        // HashSet<Integer> hs = new HashSet<>();

        if (nums.length == 1) {
            return nums[0];
        }
        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    break;
                }
                if (i != 0 && nums[i] != nums[j]) {
                    return nums[j];
                }
                if (nums[i] != nums[j] && i == 0) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    // { 3, 1, 3};
    public static int findDuplicate(int[] nums) {
        int i, j;
        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }

    // Input: s = "([)]"
    // Output: false
    // () true
    // Input: s = "{[]}"-->true
    // "(){}}{"-->false
    public static boolean isValid(String s) {
        int i;
        Stack<Character> stack = new Stack<>();
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else if (!stack.isEmpty() && ((s.charAt(i) == '}' && stack.peek() == '{')
                    || (s.charAt(i) == ']' && stack.peek() == '[') || (s.charAt(i) == ')' && stack.peek() == '('))) {

                stack.pop();
            } else
                return false;
        }
        return stack.isEmpty();

        // if (i != s.length() - 1) {
        // if (s.charAt(i) == '}' || s.charAt(i) == ')' || s.charAt(i) == ']') {
        // return false;
        // }
        // if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
        // return true;
        // }
        // if (s.charAt(i) == '[' && s.charAt(i + 1) == ']') {
        // return true;
        // }
        // if (s.charAt(i) == '{' && s.charAt(i + 1) == '}') {
        // return true;
        // }
        // }
        // }
        // return false;

    }

    public static boolean checkEnding(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(str1.length() - 1) == str2.charAt(str2.length() - 1)
                    && str1.charAt(str1.length() - 2) == str2.charAt(0)) {
                return true;
            }
        }
        return false;
    }

    public static int calculator(int num1, char operator, int num2) {
        if (num2 == 0 && operator == '/')
            return 0;
        if (operator == '*')
            return num1 * num2;
        if (operator == '+')
            return num1 + num2;
        if (operator == '-') {
            if (num1 > num2) {
                return num1 - num2;
            } else if (num2 > num1) {
                return num2 - num1;
            }
        }
        if (operator == '/') {
            if (num1 == num2) {
                return 1;
            }
            if (num1 > num2) {
                return num1 / num2;
            } else if (num2 > num1) {
                return num2 / num1;
            }
        }
        return 0;
    }

    // palindrome number
    public static boolean isPalindrome(int x) {
        int rev = 0;
        int temp = x;// because x will change after reversing,so the comparison will be between temp
                     // and the revesre of x
        int digitOfX;
        if (x < 0) {
            return false;
        }
        while (x != 0 && x > 0) {
            digitOfX = (int) (x % 10);
            rev = rev * 10 + digitOfX;
            x = x / 10;
        }
        System.out.println("The original number = " + temp + "\nIt's reverse = " + rev);
        return (rev == temp) ? true : false;
    }

    public static int reverse(int x) {
        long rev = 0;
        int digitOfX;
        while (x != 0) {
            digitOfX = (int) (x % 10); // getting the digits of x inversely
            rev = rev * 10 + digitOfX; // add them to the rev
            // *10 -->if rev=8,and a digit of x=2,then we should 8*10 + 2 =82 to add it the
            // rev
            x = (int) (x / 10);// redusing the digit of the original
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            // preventing integer over flow
            return 0;
        }
        return (int) rev;
    }

    public static int[][] squarePatch(int n) {
        int[][] array = new int[n][n];
        for (int i = 0; i < array.length; i++) {
            for (int k = 0; k < array[i].length; k++) {
                array[i][k] = n;
            }
        }
        return array;

    }

    public static int warOfNumbers(int[] numbers) {
        int sumEven = 0;
        int sumOdd = 0;
        int differance = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                sumEven += numbers[i];
            }
            if (numbers[i] % 2 > 0) {
                sumOdd += numbers[i];
            }
            if (sumEven >= sumOdd) {
                differance = sumEven - sumOdd;
            } else {
                differance = sumOdd - sumEven;
            }
        }
        return differance;

    }

    public static void reverseString(char[] s) {
        for (int i = s.length - 1; i >= 0; i--) {
            if (i == s.length - 1) {
                System.out.print("[");
            }
            System.out.print(s[i] + ", ");
            if (i == 0) {
                System.out.print("]");
            }
        }
    }

    // recursion fib by using memoization
    // Big O(n)
    public static int fib(int n) {
        int result;
        int memo[] = new int[n + 1];// size n+1 ,index 0 for value 1
        if (n == memo[n]) {// check if there is repetead n (n is changing with each recursion )
            return memo[n];// call the stored result from the array insted of calculating it again
        }
        if (n <= 2) {// stop point
            result = 1;
        } else {
            result = fib(n - 1) + fib(n - 2);// recursion call
            memo[n] = result;// store the result in the array
        }
        return result;
    }
}
