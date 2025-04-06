import java.util.Arrays;

public class HillPay {
    static final int MOD = 26;
    
    // Key matrix
    static int[][] keyMatrix = {
        {17, 17, 5},
        {21, 18, 21},
        {2, 2, 19}
    };

    // Convert character to number (A=0, B=1, ..., Z=25)
    static int charToNum(char c) {
        return c - 'A';
    }
    
    // Convert number to character
    static char numToChar(int n) {
        return (char) ('A' + n);
    }
    
    // Multiply key matrix with the plaintext vector
    static int[] encrypt(int[] plaintext) {
        int[] ciphertext = new int[3];
        for (int i = 0; i < 3; i++) {
            ciphertext[i] = 0;
            for (int j = 0; j < 3; j++) {
                ciphertext[i] += keyMatrix[i][j] * plaintext[j];
            }
            ciphertext[i] %= MOD;
        }
        return ciphertext;
    }
    
    // Find determinant of the key matrix (mod 26)
    static int determinant(int[][] matrix) {
        int det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        return ((det % MOD) + MOD) % MOD;
    }

    // Find modular inverse of determinant
    static int modInverse(int det) {
        for (int i = 1; i < MOD; i++) {
            if ((det * i) % MOD == 1)
                return i;
        }
        return -1;
    }
    
    // Find inverse of key matrix (mod 26)
    static int[][] inverseMatrix(int[][] matrix) {
        int det = determinant(matrix);
        int detInv = modInverse(det);
        if (detInv == -1) {
            throw new ArithmeticException("Matrix is not invertible");
        }

        int[][] adjugate = new int[3][3];
        
        // Calculate adjugate matrix
        adjugate[0][0] = (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) % MOD;
        adjugate[0][1] = (matrix[0][2] * matrix[2][1] - matrix[0][1] * matrix[2][2]) % MOD;
        adjugate[0][2] = (matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]) % MOD;
        adjugate[1][0] = (matrix[1][2] * matrix[2][0] - matrix[1][0] * matrix[2][2]) % MOD;
        adjugate[1][1] = (matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0]) % MOD;
        adjugate[1][2] = (matrix[0][2] * matrix[1][0] - matrix[0][0] * matrix[1][2]) % MOD;
        adjugate[2][0] = (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]) % MOD;
        adjugate[2][1] = (matrix[0][1] * matrix[2][0] - matrix[0][0] * matrix[2][1]) % MOD;
        adjugate[2][2] = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % MOD;

        // Multiply adjugate by determinant inverse mod 26
        int[][] inverse = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverse[i][j] = (adjugate[i][j] * detInv) % MOD;
                if (inverse[i][j] < 0) inverse[i][j] += MOD;
            }
        }
        return inverse;
    }
    
    // Decrypt the ciphertext
    static int[] decrypt(int[] ciphertext) {
        int[][] inverseKey = inverseMatrix(keyMatrix);
        int[] plaintext = new int[3];
        for (int i = 0; i < 3; i++) {
            plaintext[i] = 0;
            for (int j = 0; j < 3; j++) {
                plaintext[i] += inverseKey[i][j] * ciphertext[j];
            }
            plaintext[i] %= MOD;
        }
        return plaintext;
    }
    
    public static void main(String[] args) {
        String message = "PAY";
        int[] plaintext = {charToNum(message.charAt(0)), charToNum(message.charAt(1)), charToNum(message.charAt(2))};

        // Encrypt
        int[] ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + numToChar(ciphertext[0]) + numToChar(ciphertext[1]) + numToChar(ciphertext[2]));
        
        // Decrypt
        int[] decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted Text: " + numToChar(decryptedText[0]) + numToChar(decryptedText[1]) + numToChar(decryptedText[2]));
}
}
