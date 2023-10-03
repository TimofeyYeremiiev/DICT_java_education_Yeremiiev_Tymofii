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
        ask_for_age();
    }

    private void greating(){
        System.out.println("Hello, my name is " + name + ".");
        System.out.println("I was created in " + birthYear + ".");
    }

    private void remind_name(){
        System.out.println("Please, remind me your name.");
        System.out.println("What a great name you have, " + get_input_string() +"!");
    }

    private String get_input_string(){
        return scan.nextLine();
    }

    private void ask_for_age(){
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int remainder3 = get_input_int("By 3: ");
        int remainder5 = get_input_int("By 5: ");
        int remainder7 = get_input_int("By 7: ");
        var age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Your age is " + age + "; that`s a good time to start programming!");
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
