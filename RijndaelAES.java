import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.*;

public class RijndaelAES {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
 
            // Step 1: Generate AES Key (128-bit)
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // AES-128
            SecretKey secretKey = keyGen.generateKey();
            
            // Step 2: Create Cipher Instance
            Cipher cipher = Cipher.getInstance("AES");

            // Plaintext Message
            System.out.println("enter plaintext:");
            String plaintext = sc.nextLine();
            System.out.println("Original Text: " + plaintext);

            // Step 3: Encrypt the Message
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted Text: " + encryptedText);

            // Step 4: Decrypt the Ciphertext
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
}
}
}
