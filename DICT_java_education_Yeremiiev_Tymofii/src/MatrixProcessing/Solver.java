package MatrixProcessing;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.NavigableMap;

public class Solver {

    public Matrix addMatrixToMatrix(Matrix a, Matrix b){

        if (a.height != b.height || a.width != b.width){
            float[][] c = new float[0][0];
            System.out.println("Condition (a.height != b.height || a.width != b.width) is true");
            return new Matrix(0,0,c);
        }

        float[][] cont = new float[a.height][a.width];
        for (int y = 0; y < a.height; y++){
            for (int x = 0; x < a.width; x++){
                cont[y][x] = a.content[y][x] + b.content[y][x];
            }
        }
        return new Matrix(a.height,a.width, cont);
    }

    public Matrix multiplyMatrixByConst(Matrix a, float b){
        float[][] cont = new float[a.height][a.width];
        for (int y = 0; y < a.height; y++){
            for (int x = 0; x < a.width; x++){
                cont[y][x] = a.content[y][x] * b;
            }
        }
        return new Matrix(a.height,a.width, cont);
    }

    public Matrix multiplyMatrixByMatrix(Matrix a, Matrix b){
        float[][] cont = new float[a.height][b.width];

        if (a.width != b.height){
            float[][] c = new float[0][0];
            System.out.println("Condition (a.width != b.height) is true");
            return new Matrix(0,0,c);
        }


        for (int x = 0; x < a.height ; x++){
            for (int y = 0; y < b.width; y++){
                float res = 0;
                for (int z = 0; z < a.width ; z++){
                    res += a.content[x][z] * b.content[z][y];
                }
                cont[x][y] = res;
            }

        }
        return new Matrix(a.height, b.width, cont);

    }

    public Matrix transposeDiagonal(Matrix a){
        float[][] cont = new float[a.height][a.width];
        for (int x = 0; x < a.height ; x++) {
            for (int y = 0; y < a.width; y++) {
                cont[y][x] = a.content[x][y];
            }
        }
        return new Matrix(a.height, a.width, cont);
    }

    public Matrix transposeSubDiagonal(Matrix a){
        float[][] cont = new float[a.height][a.width];
        for (int x = 0; x < a.height ; x++) {
            for (int y = 0; y < a.width; y++) {
                cont[(y - a.width + 1) * -1][x] = a.content[x][y];
            }
        }
        return new Matrix(a.height, a.width, cont);
    }

    public Matrix transposeHorizontal(Matrix a){
        float[][] cont = new float[a.height][a.width];
        for (int x = 0; x < a.height ; x++) {
            for (int y = 0; y < a.width; y++) {
                cont[x][(y - a.width + 1) * -1] = a.content[x][y];
            }
        }
        return new Matrix(a.height, a.width, cont);
    }

    public Matrix transposeVertical(Matrix a){
        float[][] cont = new float[a.height][a.width];
        for (int x = 0; x < a.height ; x++) {
            for (int y = 0; y < a.width; y++) {
                cont[(x - a.height + 1) * -1][y] = a.content[x][y];
            }
        }
        return new Matrix(a.height, a.width, cont);
    }

    public Matrix minor(Matrix a, int x, int y){
        float [][] cont = new float[a.height-1][a.width-1];
        int g = 0;
        for(int i = 0; i < a.height; i++){
            if (i == x){
                g = -1;
            } else {
                int h = 0;
                for(int j = 0; j < a.width; j++){
                    if (j == y){
                        h = -1;
                    } else {
                        float p = a.content[i][j];
                        cont[i + g][j + h] = p;
                    }
                }
            }
        }
        return new Matrix(a.height-1, a.width - 1, cont);
    }

    public float determinant(Matrix a){
        if (a.height != a.width){
            System.out.println("Condition (a.width != b.height) is true");
            return -1f;
        }

        if (a.height == 2){
            return a.content[0][0] * a.content[1][1] - a.content[0][1] * a.content[1][0];
        }
        float res = 0;
        for (int i = 0; i < a.height; i++){
            res += (float) (Math.pow(-1, i) * a.content[0][i] * determinant(minor(a, 0, i)));
        }
        return res;
    }

    private Matrix minor_trans(Matrix a){
        if (a.height == 2){
            float[][] c = { {a.content[1][1], -a.content[1][0]},
                    {-a.content[0][1], a.content[0][0]}};
            return new Matrix(a.height, a.width, c);
        }
        float[][] c = new float[a.height][a.width];
        for (int x = 0; x < a.height; x++){
            for (int y = 0; y < a.height; y++){
                c[x][y] = (float) Math.pow(-1, x+y) * determinant(minor(a, x, y));
            }
        }
        return new Matrix(a.height, a.width, c);
    }

    public Matrix inverseMatrix(Matrix a){
        if (determinant(a) == 0){
            float[][] c = new float[0][0];
            System.out.println("Condition ( DET(matrix) == 0 ) is true");
            return new Matrix(0,0,c);
        }
        return multiplyMatrixByConst(transposeDiagonal(minor_trans(a)), 1/determinant(a));
    }

}
