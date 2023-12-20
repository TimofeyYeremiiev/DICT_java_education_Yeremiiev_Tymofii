package MatrixProcessing;

public class Matrix {
    public int height;
    public int width;
    public float[][] content;

    Matrix(int height, int width, float[][] cont){
        this.height = height;
        this.width = width;
        this.content = cont;
    }

    @Override
    public String toString(){
        if (height == 0 && width == 0){
            return "Calculation error";
        }
        StringBuilder str = new StringBuilder();
        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){
                str.append(Float.toString(content[x][y])).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
