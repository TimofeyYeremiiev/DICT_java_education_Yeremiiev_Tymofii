package CreditCalculator;


import java.util.Objects;

public class CreditCalculator {
    SomeUtils u = new SomeUtils();

    private String credit_type;
    private float payment = 0;
    private boolean payment_sp = false;
    private float principal = 0;
    private boolean princial_sp = false;
    private float periods = 0;
    private boolean periods_sp = false;
    private float interest = 0;

    public void start(){
        check_values();
        if (Objects.equals(credit_type, "annuity")){
            do_quest_type_a();
        }else {
            do_quest_d();
        }
    }

    public void check_values(){
        //Determinate credit type
        credit_type = System.getProperty("type", "none");
        while (true){
            System.out.println("Warning: credit type is not specified, please swith one of this:");
            String a = u.inputString("a - annuity, d - diff");
            if (Objects.equals(a, "a")){
                credit_type = "annuity";
                break;
            }
            if (Objects.equals(a, "d")){
                credit_type = "diff";
                break;
            }
        }
        //PAYMENT
        String a = System.getProperty("payment", "nonspec");
        if (!Objects.equals(a, "nonspec")){
            payment = Float.parseFloat(a);
            if (payment <= 0){
                do {
                    payment = u.getDigit("Please, specify correct payment:");
                } while (!(payment > 0));
            }
            payment_sp = true;
        }

        //PRINCIPAL
        a = System.getProperty("principal", "nonspec");
        if(!Objects.equals(a, "nonspec")){
            principal = Float.parseFloat(a);
            if (principal <= 0){
                do {
                    principal = u.getDigit("Please, specify correct principals:");
                } while (!(principal > 0));
            }
            princial_sp = true;
        }

        //PERIODS
        a = System.getProperty("periods", "nonspec");
        if(!Objects.equals(a, "nonspec")){
            periods = Float.parseFloat(a);
            if (periods <= 0){
                do {
                    periods = u.getDigit("Please, specify correct periods:");
                } while (!(periods > 0));
            }
            periods_sp = true;
        }

        //INTEREST
        a = System.getProperty("interest", "nonspec");
        if(!Objects.equals(a, "nonspec")){
            interest = Float.parseFloat(a);
        }else {
            interest = -1;
        }
        if (interest <= 0){
            do {
                interest = u.getDigit("Please, specify correct interest:");
            } while (!(interest > 0));
        }
        boolean interest_sp = true;


    }

    private void do_quest_type_a(){
        int l = 0;
        if (princial_sp){
            l+=1;
        }
        if (payment_sp){
            l+=1;
        }
        if (periods_sp){
            l+=1;
        }
        if (l==3){
            System.out.println("All parameters is specified, no task to do.");
            return;
        }
        System.out.println("Some parameters is missing. What do you like to find?");
        if (!princial_sp){
            System.out.println("pri - principal");
        }
        if (!payment_sp){
            System.out.println("pay - payment");
        }
        if (!periods_sp){
            System.out.println("per - periods");
        }
        while (true){
            switch (u.inputString("Your choise?")) {
                case "pri" -> {
                    if (!payment_sp) {
                        do {
                            payment = u.getDigit("Please, specify correct payment:");
                        } while (!(payment > 0));
                    }
                    if (!periods_sp) {
                        do {
                            periods = u.getDigit("Please, specify correct periods:");
                        } while (!(periods > 0));
                    }
                    System.out.println("Principal:");
                    System.out.println(payment * periods / (interest / 1200));
                }
                case "pay" -> {
                    if (!princial_sp) {
                        do {
                            principal = u.getDigit("Please, specify correct principal:");
                        } while (!(principal > 0));
                    }
                    if (!periods_sp) {
                        do {
                            periods = u.getDigit("Please, specify correct periods:");
                        } while (!(periods > 0));
                    }
                    System.out.println("Payments:");
                    payment = (float) Math.floor((principal * (interest / 1200)) / periods);
                    float last_payment = (float) (principal * (interest / 1200)) - payment * periods;
                    System.out.println(payment);
                    System.out.println("Lastpayment:");
                    System.out.println(last_payment);

                }
                case "per" -> {
                    if (!payment_sp) {
                        do {
                            payment = u.getDigit("Please, specify correct payment:");
                        } while (!(payment > 0));
                    }
                    if (!princial_sp) {
                        do {
                            principal = u.getDigit("Please, specify correct principal:");
                        } while (!(principal > 0));
                    }
                    System.out.println("Periods:");
                    System.out.println((principal * (interest / 1200)) / payment);
                }
            }
        }

    }

    private void do_quest_d(){

        System.out.println("Some parameters is missing. What do you like to find?");
        System.out.println("pay - payment");
        System.out.println("Autochoosing payment...");
        System.out.println("Payments:");

        if (!princial_sp) {
            do {
                principal = u.getDigit("Please, specify correct principal:");
            } while (!(principal > 0));
        }
        if (!periods_sp) {
            do {
                periods = u.getDigit("Please, specify correct periods:");
            } while (!(periods > 0));
        }

        for (int i = 1; i < periods+1; i++){
            payment = principal/periods + interest * (principal - (principal*(i-1))/periods);
            System.out.println("Month " + i + " " + payment);
        }


    }
}

