import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA1Example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create Scanner object
        System.out.print("Enter text to hash: ");
        String input = scanner.nextLine();  // Read user input
        scanner.close(); // Close the scanner

        String sha1Hash = getSHA1Hash(input);
        System.out.println("Original Text: " + input);
        System.out.println("SHA-1 Hash: " + sha1Hash);
    }

    public static String getSHA1Hash(String input) {
        try {
            // Create MessageDigest instance for SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Compute hash bytes
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert hash bytes to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString(); // Return hashed string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 Algorithm not found", e);
        }
    }
}

