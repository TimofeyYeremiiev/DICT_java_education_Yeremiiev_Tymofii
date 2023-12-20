package CreditCalculator;
import java.util.Scanner;
public class SomeUtils {
    private Scanner scanner = new Scanner(System.in);
    public String inputString(String message){
        System.out.println(message);
        System.out.print("> ");
        return scanner.nextLine();
    }

    public float getDigit(String message){
        System.out.println(message);
        System.out.print("> ");
        while(true) {
            if(scanner.hasNextInt()){
                var a = scanner.nextFloat();
                scanner.nextLine();
                return a;
            }
            else {
                System.out.println("Incorrect digit");
            }
            scanner.nextLine();
        }
    }

    public void printBorder(int lenght){
        System.out.print("[]");
        for (int c = 0; c < lenght; c++){
            System.out.print('=');
        }
        System.out.println("[]");
    }

    public void printString(int lenght, String str){
        System.out.print(" |");
        int b = 0;
        for (char c: str.toCharArray()){
            System.out.print(c);
            b++;
        }
        for (int c = 0; c < lenght-b; c++){
            System.out.print(' ');
        }
        System.out.println("| ");
    }

}
