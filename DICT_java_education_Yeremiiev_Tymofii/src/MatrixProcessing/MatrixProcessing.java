package MatrixProcessing;

import java.sql.Array;
import java.util.Scanner;

public class MatrixProcessing {
    private Scanner scanner = new Scanner(System.in);
    private final int menuLenght = 40;
    private Solver sol = new Solver();
    public void start(){
        boolean do_exit = false;
        while (true){
            printBorder(menuLenght);
            printString(menuLenght, "            Welcome to Matrix Processor!");
            printString(menuLenght, "            Here is your menu:");
            printString(menuLenght, "1. Add Matrices");
            printString(menuLenght, "2. Multiply matrix by const");
            printString(menuLenght, "3. Multiply matrices");
            printString(menuLenght, "4. Transpose diagonal matrix");
            printString(menuLenght, "5. Transpose sub-diagonal matrix");
            printString(menuLenght, "6. Transpose Vertical");
            printString(menuLenght, "7. Transpose Horizontal");
            printString(menuLenght, "8. Calculate a determinant");
            printString(menuLenght, "9. Inverse matrix");
            printString(menuLenght, "0. Exit");
            printBorder(menuLenght);

            switch ((int) getDigit("Pick your action: ")){
                case 0:
                    do_exit = true;
                    break;
                case 1:
                    System.out.println(sol.addMatrixToMatrix(getMatrix("Matrix 1:"), getMatrix("Matrix 2:")));
                case 2:
                    System.out.println(sol.multiplyMatrixByConst(getMatrix("Matrix: "), getDigit("Const: ")));
                case 3:
                    System.out.println(sol.multiplyMatrixByMatrix(getMatrix("Matrix 1:"), getMatrix("Matrix 2:")));
                case 4:
                    System.out.println(sol.transposeDiagonal(getMatrix("Matrix for transposing:")));
                case 5:
                    System.out.println(sol.transposeSubDiagonal(getMatrix("Matrix for transposing:")));
                case 6:
                    System.out.println(sol.transposeVertical(getMatrix("Matrix to transpose:")));
                case 7:
                    System.out.println(sol.transposeHorizontal(getMatrix("Matrix to transpose:")));
                case 8:
                    System.out.println(sol.determinant(getMatrix("Matrix for det:")));
                case 9:
                    System.out.println(sol.inverseMatrix(getMatrix("Matrix for inverse")));
            }

            if (do_exit){
                break;
            }
        }

    }

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

    public Matrix getMatrix(String message){
        int h = 0;
        int w = 0;
        System.out.println(message);
        while (true) {
            System.out.println("Please, write rows and columns count");
            String[] str = scanner.nextLine().split(" ");

            if (str.length < 2){
                System.out.println("Not enought digits!");
                continue;
            }
            h = Integer.parseInt(str[0]);
            w = Integer.parseInt(str[1]);
            break;
        }
        System.out.println("Please, write rows");
        float[][] floats = new float[h][w];
        for (int row = 0; row < h; row++) {
            while (true) {
                String[] str = scanner.nextLine().split(" ");
                if (str.length < w) {
                    System.out.println("Not enought digits!");
                    continue;
                }
                for (int col = 0; col < w; col++) {
                    floats[row][col] = Float.parseFloat(str[col]);
                }
                break;
            }
        }
        return new Matrix(h,w,floats);
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
