//BitwiseMatchingPattern.java
import java.util.Scanner;

public class BitwiseMatchingPattern {
    
    // Function to get next greater number with same number of set bits (1s)
    public static int getNext(int n) {
        int c = n;
        int c0 = 0;  // count of zeros
        int c1 = 0;  // count of ones

        // Step 1: Count trailing zeros (c0)
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // Step 2: Count ones after trailing zeros (c1)
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // If n == 11..1100..00, then there's no bigger number with same 1s
        if (c0 + c1 == 31 || c0 + c1 == 0)
            return -1;

        // Position of rightmost non-trailing zero
        int p = c0 + c1;

        // Step 3: Flip rightmost non-trailing zero
        n |= (1 << p);

        // Step 4: Clear all bits to the right of p
        n &= ~((1 << p) - 1);

        // Step 5: Insert (c1-1) ones on the right
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input: ");
        int n = sc.nextInt();

        int next = getNext(n);

        if (next == -1)
            System.out.println("No larger number with same number of 1s exists.");
        else {
            System.out.println("Output:  " + next);
        }

        sc.close();
    }
}
