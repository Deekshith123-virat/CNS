import java.util.Scanner;

public class AdvancedEuclideanAlgorithm {

    // Function to implement the Extended Euclidean Algorithm
    public static int extendedEuclidean(int a, int b, int[] coefficients) {
        if (b == 0) {
            coefficients[0] = 1;
            coefficients[1] = 0;
            return a;  // gcd(a, 0) = a
        }

        // Recursively apply the algorithm
        int gcd = extendedEuclidean(b, a % b, coefficients);

        // Update coefficients using the results from the recursive call
        int temp = coefficients[0] - (a / b) * coefficients[1];
        coefficients[0] = coefficients[1];
        coefficients[1] = temp;

        return gcd;
    }

    public static void main(String[] args) {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Get input from the user for values of a and b
        System.out.print("Enter the first number (a): ");
        int a = scanner.nextInt();

        System.out.print("Enter the second number (b): ");
        int b = scanner.nextInt();

        // Array to store the coefficients x and y
        int[] coefficients = new int[2];

        // Get the GCD and Bézout coefficients
        int gcd = extendedEuclidean(a, b, coefficients);

        // Output the results
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd);
        System.out.println("coefficients are: x = " + coefficients[0] + ", y = " + coefficients[1]);
        System.out.println("Verification: " + a + " * " + coefficients[0] + " + " + b + " * " + coefficients[1] + " = " + gcd);

        // Close the scanner
        scanner.close();
    }
}
