package Hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static final String[] words = new String[] {"java", "python", "javascript", "kotlin", "c++", "css", "brainfuck"};
    private static char[] gameWord = new char[] {};
    private static char[] targetWord = new char[] {};
    private static String usedLetters = "";

    private static final Scanner scammer = new Scanner(System.in);

    private boolean isInWord(char symb){
        return String.valueOf(targetWord).contains(String.valueOf(symb));
    }

    private boolean isInUsed(char symb){
        return usedLetters.contains(String.valueOf(symb));
    }

    private boolean isNotOpenedLetter(){
        for (char c : gameWord) {
            if (c == '_') {
                return true;
            }
        }
        return false;
    }

    private char getInput(){
        while (true) {
            System.out.print("> ");
            var text = scammer.nextLine().trim();
            if (text.isEmpty())
            {
                System.out.println("Incorrect input");
                continue;
            }
            return text.toLowerCase().charAt(0);
        }
    }

    private void printState(int pops){
        System.out.println("Gues the word: " + String.valueOf(gameWord));
        System.out.println("Popit-ok: " + String.valueOf(pops) );
    }

    private void openLetter(char sym){
        for (int num = 0; num < targetWord.length; num++){
            if (targetWord[num] == sym){
                gameWord[num] = sym;
            }
        }
    }

    public boolean gameCycle(){
        //Якщо користувач приймає рішення про початок гри, обирається слово зі
        //списку: це буде відповідь на загадку.
        usedLetters = "";
        var rnd = new Random();
        var value = rnd.nextInt(0, words.length);
        targetWord = words[value].toCharArray();
        gameWord = new char[targetWord.length];
        for (int num=0; num < targetWord.length; num++){
            gameWord[num] = '_';
        }

        int tryCount = 0;

        while (true) {
            if (!isNotOpenedLetter()) {
                System.out.println("Game ended! You won!");
                return true;
            }
            printState(tryCount);
            //Гравцеві потрібно ввести букву, яка, на його думку, є в слові.
            char answ = getInput();
            //Якщо буква зустрічається в слові, виводиться повідомлення гравцеві.
            if (isInWord(answ)){
                System.out.println("This letter is in word.");
                openLetter(answ);
                usedLetters += answ;
                continue;
            }
            //Якщо ця буква не зявляється в слові і ця буква ще не вгадана, це
            //вважається помилкою.
            if (!isInWord(answ) | !isInUsed(answ)){
                tryCount += 1;
                System.out.println("Incorrect!");
            }
            //До закінчення гри гравець може припуститися
            //тільки 8 помилок.
            if (tryCount == 8){
                System.out.println("Game over! Word was: " + String.valueOf(targetWord));
                return false;
            }

        }
    }

}
