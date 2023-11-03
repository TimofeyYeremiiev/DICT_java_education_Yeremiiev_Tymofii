package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {

    private char[] gameField = new char[9];
    private final Scanner scan = new Scanner(System.in);


    public void start(){
        setGameField();
        boolean who_do_step = false;
        boolean endGame = false;
        do {
            printGameField();
            if (who_do_step) {
                makeStep('O');
            } else {
                makeStep('X');
            }
            who_do_step = !who_do_step;
            switch (checkGameState()) {
                case "Impossible" -> {
                    System.out.println("Impossible state: game ended.");
                    endGame = true;
                }
                case "NotEnd" -> {
                }
                case "Xwon" -> {
                    System.out.println("Player X won!");
                    endGame = true;
                }
                case "Owon" -> {
                    System.out.println("Player O won!");
                    endGame = true;
                }
                case "Draw" -> {
                    System.out.println("Nobody won!");
                    endGame = true;
                }
            }

        } while (!endGame);
    }

    private void setGameField(){
        System.out.println("Enter game field state (or null to empty)");
        //Input string
        String str = "";
        while (true) {
            str = scan.nextLine();
            if (Objects.equals(str, "")) {
                gameField = new char[]{'_','_','_','_','_','_','_','_','_'};
                return;
            }
            boolean is_not_allowed_symb = false;
            for (char element : str.toCharArray()) {
                if (!(element == 'X' || element == 'O' || element == '_')) {
                    is_not_allowed_symb = true;
                    break;
                }
            }

            if (str.toCharArray().length < 9) {
                System.out.println("Not enough symbs!");
                continue;
            }
            if (is_not_allowed_symb) {
                System.out.println("Found not allowed symb!");
            } else {
                break;
            }
        }
        //Apply to field
        System.arraycopy(str.toCharArray(), 0, gameField, 0, 9);
    }
    public void printGameField() {
        System.out.println("[]=======[]");
        System.out.println(" | " + gameField[0] + " " + gameField[1] + " " + gameField[2] + " |");
        System.out.println(" | " + gameField[3] + " " + gameField[4] + " " + gameField[5] + " |");
        System.out.println(" | " + gameField[6] + " " + gameField[7] + " " + gameField[8] + " |");
        System.out.println("[]=======[]");
    }

    public void makeStep(char a){
        int coord = 0;
        while (true){
            System.out.println("Player " + a + ", please make step. (format -3 char, 0 is digit, 2 is digit, in range [1,3])");
            String str = scan.nextLine();
            //is number test
            if (!Character.isDigit(str.toCharArray()[0])){
                System.out.println("First char is not digit!");
                continue;
            }
            if (!Character.isDigit(str.toCharArray()[2])){
                System.out.println("Vtoroi char is not digit!");
                continue;
            }
            //is in range
            int c1 = str.toCharArray()[0];
            if ((c1 > 0 && c1 < 4)) {
                System.out.println("First char is not in range [1,3]!");
                continue;
            }
            int c2 = str.toCharArray()[2];
            if ((c2 > 0 && c2 < 4)) {
                System.out.println("Vtoroi char is not in range [1,3]!");
                continue;
            }
            coord = Character.getNumericValue(c1)-1 + (Character.getNumericValue(c2)-1) * 3;

            //is occupied
            if (!(gameField[coord] == '_')){
                System.out.println("Already occupied!");
                continue;
            } else {
                gameField[coord] = a;
                break;
            }
        }
    }

    private String checkGameState() {
        //get data
        boolean is_free_place = (getSymbCount('_') > 0);
        int X_count = getSymbCount('X');
        int O_count = getSymbCount('O');
        boolean isXwon = isPlayerWon('X');
        boolean isOwon = isPlayerWon('O');
        //Impossible check
        if (Math.abs(X_count-O_count) > 1 || (isOwon && isXwon)){
            return "Impossible";
        }
        if (isOwon){
            return "Owon";
        }
        if (isXwon){
            return "Xwon";
        }
        if (is_free_place) {
            return "NotEnd";
        } else {
            return "Draw";
        }
    }

    private int getSymbCount(char b){
        int count = 0;
        for (char a:gameField){
            if (a == b){
               count += 1;
            }
        }
        return count;
    }

    private boolean isPlayerWon(char a){
        //Hor
        boolean is_p_won = false;
        for (int vert = 0; vert < 3; vert++){
            if (gameField[vert * 3] == a && gameField[vert * 3 + 1] == a && gameField[vert * 3 + 2] == a) {
                is_p_won = true;
                break;
            }
        }
        //Vert
        for (int hor = 0; hor < 3; hor++) {
            if (gameField[hor] == a && gameField[3 + hor] == a && gameField[6 + hor] == a) {
                is_p_won = true;
                break;
            }
        }
        //Diagonals
        if (gameField[0] == a && gameField[4] == a && gameField[8] == a) {
            is_p_won = true;
        }
        if (gameField[2] == a && gameField[4] == a && gameField[6] == a) {
            is_p_won = true;
        }
        return  is_p_won;
    }

}
