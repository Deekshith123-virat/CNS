import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Example {
    public static void main(String[] args) {
        // Create Scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);
       
        // Prompt user to enter a string
        System.out.print("Enter text to hash: ");
        String input = scanner.nextLine();  // Read the user input
       
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Perform the hash computation
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));  // Convert each byte to 2-digit hex
            }

            // Output the resulting hash
            System.out.println("MD5 Hash of '" + input + "': " + hexString.toString());

        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm not found!");
        } finally {
            scanner.close();  // Close the scanner resource
        }
    }
}
