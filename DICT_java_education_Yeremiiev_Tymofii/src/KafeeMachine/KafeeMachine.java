package KafeeMachine;
import java.util.Scanner;

public class KafeeMachine {

    public int beans = 100;
    public int milk = 100;
    public int cups = 100;
    public int water = 100;

    private int stored_Money = 0;

    private KafeeCupDescriber[] kofeeTypes = new KafeeCupDescriber[]{};

    private static final Scanner scan = new Scanner(System.in);

    public KafeeMachine(){
        kofeeTypes = new KafeeCupDescriber[] {
                new KafeeCupDescriber("Espresso", 250, 0, 16, 4),
                new KafeeCupDescriber("Latte", 350, 75, 20, 7),
                new KafeeCupDescriber("Capuchino", 200, 100, 12, 6)
        };
    }

    public void start(){
        //Главный цикл
        while(true){
            print_menu();
            int command = get_input_int("> Choose your action");
            boolean do_exit = false;
            switch (command) {
                case 1 -> do_exit = true;
                case 2 -> buy();
                case 3 -> fill();
                case 4 -> print_state();
                case 5 -> take();
                default -> System.out.println("> Command not recognized.");
            }
            if (do_exit) {
                System.out.println("Goodbye!");
                break;
            }
        }

    }

    private void print_menu(){
        System.out.println("[]===============================================[]");
        System.out.println(" |            Welcome to KofeeMachine            |");
        System.out.println(" |                                               |");
        System.out.println(" | Menu:                                         |");
        System.out.println(" | 1: exit  2: buy  3: fill  4: storage state    |");
        System.out.println(" | 5: take money (admin access required)         |");
        System.out.println("[]===============================================[]");
    }

    public void take(){
        System.out.println("> Authorizing...");
        System.out.println("> Done!");
        System.out.println("> Taked: " + String.valueOf(stored_Money));
        stored_Money = 0;
    }

    private void print_state(){
        System.out.println("State: ");
        System.out.println("Cups: " + String.valueOf(cups));
        System.out.println("Beans: " + String.valueOf(beans));
        System.out.println("Milk: " + String.valueOf(milk));
        System.out.println("Water: " + String.valueOf(water));
    }

    public void fill(){
        System.out.println("> Initiated refilling...");
        water += get_input_int("> Refill (or unfill) water count: ");
        beans += get_input_int("> Refill (or unfill) beans count: ");
        milk += get_input_int("> Refill (or unfill) milk count: ");
        cups += get_input_int("> Refill (or unfill) cups count: ");
        System.out.println("Refilling done!");
    }

    public void buy(){
        int kofee = 0;
        while (true){
            System.out.println("> Available kofee:");
            for (int i = 0; i < kofeeTypes.length; i++) {
                String label = "[" + String.valueOf(i) + "]";
                label += "-" + kofeeTypes[i].name + ": " + String.valueOf(kofeeTypes[i].price) + " EUR";
                System.out.println(label);
            }
            kofee = get_input_int("> Please, choose 1:");
            if (kofee < kofeeTypes.length) {
                break;
            }
        }
        int count = 0;
        while (true) {
            boolean do_buy = true;
            count = get_input_int("> Please, choose how much, or \"0\" to exit:");
            if (count == 0){
                break;
            }
            System.out.println("> Checking for ingredients...");
            //water
            if (water < count * kofeeTypes[kofee].water){
                System.out.println("Not enough water! Max is: " + String.valueOf(water / kofeeTypes[kofee].water) + "cups");
                do_buy = false;
            }
            //beans
            if (beans < count * kofeeTypes[kofee].beans){
                System.out.println("Not enough beans! Max is: " + String.valueOf(beans / kofeeTypes[kofee].beans) + "cups");
                do_buy = false;
            }
            //milk
            if (milk < count * kofeeTypes[kofee].milk){
                System.out.println("Not enough milk! Max is: " + String.valueOf(milk / kofeeTypes[kofee].milk) + "cups");
                do_buy = false;
            }
            //
            if (cups < count){
                System.out.println("Not enough cups! Max is: " + String.valueOf(cups) + "cups");
                do_buy = false;
            }


            //buy processing
            if (do_buy){
                System.out.println("> Check done... With your kofee to!");
                water -= count * kofeeTypes[kofee].water;
                beans -= count * kofeeTypes[kofee].beans;
                milk -= count * kofeeTypes[kofee].milk;
                cups -= count;
                stored_Money += count * kofeeTypes[kofee].price;
                break;
            }
        }
    }
    private static int get_input_int(String ask){
        System.out.println(ask);
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
