import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {

    static String filename = "D:\\test.txt";
    public static void main(String[] args) throws IOException {
        System.out.println("1 - encryption");
        System.out.println("2 - decryption");
        System.out.println("3 - File cleaning ");
        System.out.println("4 - close program");
        choosing();
    }

    public static String StringFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void FilesWriter(String filename, String str) throws IOException {
        try (FileWriter file = new FileWriter(filename))
        {
            file.write(str);
        }
    }

   public static void FilesReader(String filename) throws  IOException {
        try (FileReader file = new FileReader(filename)){
            while (file.ready()){
                char ch = (char) file.read();
                System.out.print(ch);
            }
        }
   }
    public  static  void choosing() throws IOException {
        Scanner console = new Scanner(System.in);
        try {
            int choose = console.nextInt();
            switch (choose) {
                case 1 -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Input text:");
                    String s = scan.nextLine();
                    System.out.println("Input key:");
                    int key = scan.nextInt();
                    Encryption(s, key);
                    choosing();
                }
                case 2 -> {
                    System.out.println("Input key:");
                    Scanner scan = new Scanner(System.in);
                    int key = scan.nextInt();
                    Decrypt(key);
                    choosing();
                }
                case 3 -> {
                    PrintWriter writer = new PrintWriter(filename);
                    writer.close();
                    choosing();
                }
                case 4 -> {
                    break;
                }
                default -> {
                    System.out.println("Wrong number");
                    choosing();
                }
            }
        }catch (InputMismatchException exp) {
            System.out.println("Input word isnt number");
            choosing();
        }
    }
    public static void Decrypt(int n) throws IOException {
        String str = StringFromFile(filename);
        int k = Integer.parseInt("-" + n);
        String string = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c >= 'a' && c <='z')
            {
                c += k%26;
                if(c < 'a')
                    c += 26;
                if(c > 'z')
                    c -= 26;
            }else if(c >= 'A'&&c <= 'Z')
            {
                c += k%26;
                if(c < 'A')
                    c += 26;
                if(c > 'Z')
                    c -= 26;
            }
            string += c;
        }
        System.out.println("After decrypting : " + string);
    }

    public static void Encryption(String str, int k) throws IOException {

        String string = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c >= 'a' && c <= 'z')
            {
                c += k%26;
                if(c < 'a')
                    c += 26;
                if(c > 'z')
                    c -= 26;
            }else if(c >= 'A' && c <= 'Z')
            {
                c += k%26;
                if(c < 'A')
                    c += 26;
                if(c > 'Z')
                    c -= 26;
            }
            string += c;
        }
        FilesWriter(filename, string);
        System.out.println("After encrypting :");
        FilesReader(filename);
    }
}