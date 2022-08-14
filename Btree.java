package helloworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Btree {
    int data; // of the root
    Btree left, right;

    Btree(int data) {
        this.data = data;
    }

    public void insert(int newData) {
        if (newData <= data) {
            if (left == null) {
                left = new Btree(newData);
            } else {
                left.insert(newData);// if the left != null,push the data until u find null node to insert
                // recursive call
                // first call=root.insert(8)
                // second call= 5.insert(8)
            }
        } else if (newData >= data) {
            if (right == null) {
                right = new Btree(newData);
            } else {
                right.insert(newData);
            }
        }
    }

    public boolean containing(int newData) {
        if (data == newData) { // base case
            return true;
        } else if (newData <= data) {
            if (left == null) {
                return false;
            } else {
                return left.containing(newData);
            }
        } else if (newData >= data) {
            if (right == null) {
                return false;
            } else {
                return right.containing(newData);
            }
        }
        return false;
    }

    public List<Integer> printInOrderRecusive(Btree root) { // print the left childs first,the root and then the right
        List<Integer> listOftree = new ArrayList<>();
        if (left != null) {
            left.printInOrderRecusive(root);
        }
        System.out.print(data + " ");
        listOftree.add(data);
        if (right != null) {
            right.printInOrderRecusive(root);
        }
        return listOftree;
    }

    public void printInorderRec(Btree node) {// print inorder recursivly II
        if (node == null) {
            return;
        } else {
            printInorderRec(node.left);// it keeps calling itself until there's no left or right child(when left
                                       // ==null)
            System.out.println(node.data);// go back to print the left(which is the root of null left child at first
                                          // call)
            // back here after if condition is true
            printInorderRec(node.right);// check the right of the root
        }

    }

    public static Stack<Btree> printInOrderIterative(Btree root) {
        Stack<Btree> stack = new Stack<>();
        // empty stack to save the roots and then pop from it after printing the left
        // childs
        Btree current = root;
        while (!stack.empty() || current != null) {
            if (current != null) {
                // if the current node exists, push it into the stack (defer it)
                // and move to its left child
                stack.push(current);
                current = current.left;
                continue;
            } else {
                // otherwise, if the current node is null, pop an element from
                // the stack, print it, and finally set the current node to its
                // right child
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.right;
                continue;
            }
        }
        return stack;
    }

    public String toString() {
        return this.data + " " + this.left + " " + this.right;
    }

    public static void main(String[] args) {
        Btree root = new Btree(10);
        root.left = new Btree(5);
        root.insert(8);
        root.right = new Btree(15);

        System.out.println("InOrder:");
        root.printInOrderRecusive(root);
        // printInOrderIterative(root);
        System.out.println("\nPostOrder:");
        printPostorder(root);

        System.out.println("\n" + root.containing(15));
        System.out.println(root.containing(40));

        int array[] = { -2, 3, 4, 7, 8, 9, 11, 13 };
        System.out.println(search(array, array[array.length - 1]));

        System.out.println("Rec 2:");
        root.printInorderRec(root);

        String toFind = "Jasmin";
        String arr[] = { "John", "Leo", "Taylor", "Sofia", "Yousif", "Peter", "Jasmin" };

        Arrays.sort(arr); // merge sort
        System.out.println("\n" + bineryString(arr, "Jasmin"));
        System.out.println(Arrays.toString(arr));

        int searchIndex = binarySearch(arr, toFind);
        System.out.println(searchIndex != -1 ? arr[searchIndex] + " - Index is " + searchIndex : "Not found");

        // int result = binarySearch(arr, toFind);

        // if (result == -1)
        // System.out.println("Element not present");
        // else
        // System.out.println("Element found at " + "index " + result);

    }

    // binery search
    public static int search(int array[], int target) {
        int left = 0; // index for the first item in array
        int right = array.length - 1; // index for the last item in array
        while (left <= right) {
            int mid = (left + right) / 2; // updating each iteration
            if (target == array[mid]) {
                return mid;
            }
            if (target < array[mid]) {
                right = mid - 1;
                // continue;

            } else if (target > array[mid]) {
                left = mid + 1;
                // continue;
            }
        }
        return -1; // when target is not found(or when left>=right)
    }

    public static int bineryString(String[] array, String toFind) {

        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = left + right / 2; // suitable for small data
            int compare = array[mid].compareTo(toFind);
            if (compare < 0) {// alphabaticly less
                left = mid + 1;
            }
            if (compare > 0) {
                right = mid - 1;
            } else
                return mid;
        }
        return -1;
    }

    public static int binarySearch(String[] a, String x) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) / 2); // to prevent storing larg integer with larg data
            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void printPostorder(Btree node) {
        if (node == null) {// root/right/left of exeternal or internal tree
            return;// Once return is executed, the rest of the code won't be executed.(break)
        }
        // left subtree
        printPostorder(node.left);
        // right subtree
        printPostorder(node.right);
        // root
        System.out.print(node.data + " "); // if left or right == null we go back to the root
    }
}
