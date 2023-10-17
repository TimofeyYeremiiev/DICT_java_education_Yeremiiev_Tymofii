package Hangman;
import java.util.Scanner;

public class menu {
    private static final Scanner scan = new Scanner(System.in);
    public static void menuCycle(){

        var game = new Hangman();
        boolean doExit = false;

        System.out.println("[]-------------------------------[]");
        System.out.println(" | Welcome to Hangman!           |");
        System.out.println(" | Here is menu: 0-Start, 1-Exit |");
        System.out.println(" | Enjoy your freedom!           |");
        System.out.println("[]-------------------------------[]");

        while (true){
            int answ = get_input_int();
            switch (answ){
                case 0:
                    game.gameCycle();
                    System.out.println("Welcome back to menu!");
                    break;
                case 1:
                    doExit = true;
                    break;
                default:
                    System.out.println("Unsupported command!");
            }
            if (doExit){
                break;
            }
        }
    }

    private static int get_input_int(){
        System.out.print("> ");
        while(true) {
            if(scan.hasNextInt()){
                var a = scan.nextInt();
                scan.nextLine();
                return a;
            }
            else {
                System.out.println("Incorrect int");
            }
            scan.nextLine();
        }
    }

}
