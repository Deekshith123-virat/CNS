import java.math.BigInteger;
import java.util.Scanner;

public class SimpleRSA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Input prime numbers
        System.out.print("Enter first prime number (p): ");
        int p = scanner.nextInt();

        System.out.print("Enter second prime number (q): ");
        int q = scanner.nextInt();

        // Step 2: Compute n = p * q
        int n = p * q;

        // Step 3: Compute Euler's Totient function φ(n) = (p-1) * (q-1)
        int phi = (p - 1) * (q - 1);

        // Step 4: Choose e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1
        int e;
        do {
            System.out.print("Enter public exponent (e) such that 1 < e < " + phi + " and gcd(e, φ(n)) = 1: ");
            e = scanner.nextInt();
        } while (gcd(e, phi) != 1);

        // Step 5: Compute d, the modular inverse of e modulo φ(n)
        int d = modInverse(e, phi);

        // Display the Public and Private Keys
        System.out.println("\nGenerated RSA Keys:");
        System.out.println("Public Key (n, e): (" + n + ", " + e + ")");
        System.out.println("Private Key (n, d): (" + n + ", " + d + ")");

        // Step 6: Input message to encrypt
        System.out.print("\nEnter a number to encrypt (less than " + n + "): ");
        int message = scanner.nextInt();

        // Step 7: Encrypt the message
        int encrypted = modPow(message, e, n);
        System.out.println("Encrypted Message: " + encrypted);

        // Step 8: Decrypt the message
        int decrypted = modPow(encrypted, d, n);
        System.out.println("Decrypted Message: " + decrypted);

        scanner.close();
    }

    // Function to compute Greatest Common Divisor (GCD)
    private static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    // Function to compute modular inverse using Extended Euclidean Algorithm
    private static int modInverse(int e, int phi) {
        for (int d = 2; d < phi; d++) {
            if ((d * e) % phi == 1) {
                return d;
            }
        }
        return -1; // No modular inverse found
    }

    // Function to compute (base^exp) % mod efficiently
    private static int modPow(int base, int exp, int mod) {
        return BigInteger.valueOf(base).modPow(BigInteger.valueOf(exp), BigInteger.valueOf(mod)).intValue();
    }
}
