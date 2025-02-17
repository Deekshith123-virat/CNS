import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AES {

    public static void main(String[] args) throws Exception {
        Scanner sc= new Scanner(System.in);
        // Generate a secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit key
        SecretKey secretKey = keyGen.generateKey();

        // Convert the secret key to a byte array
        byte[] keyBytes = secretKey.getEncoded();

        // Create a SecretKeySpec from the byte array
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Create a Cipher instance for AES
        Cipher cipher = Cipher.getInstance("AES");

        // Encrypt a message
        System.out.println("enter the message:");
        String originalMessage = sc.nextLine();
        System.out.println("original message is :"+originalMessage);
        byte[] encryptedMessage = encrypt(originalMessage, secretKeySpec, cipher);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, secretKeySpec, cipher);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    public static byte[] encrypt(String message, SecretKeySpec secretKeySpec, Cipher cipher) throws Exception {
        // Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Convert the message to bytes
        byte[] messageBytes = message.getBytes();

        // Encrypt the message
        return cipher.doFinal(messageBytes);
    }

    public static String decrypt(byte[] encryptedMessage, SecretKeySpec secretKeySpec, Cipher cipher) throws Exception {
        // Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // Decrypt the message
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);

        // Convert the decrypted bytes to a string
        return new String(decryptedBytes);
}
}
