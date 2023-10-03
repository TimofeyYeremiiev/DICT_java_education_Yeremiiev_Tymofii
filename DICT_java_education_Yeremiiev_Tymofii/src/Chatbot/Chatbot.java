package Chatbot;

import java.util.Scanner;

public class Chatbot {

    private final String name = "Omega Chat bot++";
    private final String birthYear = "2023";
    private final Scanner scan = new Scanner(System.in);

    //Main loop
    public void run(){
        greating();
        remind_name();
    }

    private void greating(){
        System.out.println("Hello, my name is " + name);
        System.out.println("I was created in " + birthYear);
    }

    private void remind_name(){
        System.out.println("Please, remind me your name");
        System.out.println("What a great name you have, " + get_input_string());
    }

    private String get_input_string(){
        return scan.nextLine();
    }

    private void ask_for_age(){

    }

    private int get_input_int(String text){
        System.out.println(text);
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

    private void counting(){

    }

    private void quitz(){

    }
}
