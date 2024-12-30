public class Main1 {
    public static void main(String[] args) {
        String str = "Hello World";
        char[] str1 = new char[str.length()];
        char[] str2 = str.toCharArray();
        char[] str3 = new char[str.length()];

        for (int i = 0; i < str.length(); i++) {
            str1[i] = (char) (str.charAt(i) & 127);
            System.out.print(str1[i]);
        }
        System.out.println();

        // Second operation: XOR with 127
        for (int i = 0; i < str.length(); i++) {
            str3[i] = (char) (str2[i] ^ 127);
            System.out.print(str3[i]);
        }
    }
}
