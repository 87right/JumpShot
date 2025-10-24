package main.java.com.right.github.core;

public class Matrix {
    private float[][] value;
    public Matrix(float[][] value){
        this.value = value;
    }
    public Matrix(int rowSize, int columnSize){
        value = new float[rowSize][columnSize];
    }
    public static Matrix product(Matrix matrix1, Matrix matrix2){
        if (matrix1 != null && matrix2 != null
                &&matrix1.value[0].length == matrix2.value.length){
            int resultRowSize = matrix1.value.length;
            int resultColumnSize = matrix2.value[0].length;

            int innerDimSize = matrix2.value.length;
            Matrix matrix = new Matrix(resultRowSize, resultColumnSize);

            for (int row = 0; row < resultRowSize; row++) {
                for (int column = 0; column < resultColumnSize; column++) {
                    for (int k = 0; k < innerDimSize; k++) {
                        matrix.value[row][column] += matrix1.value[row][k] * matrix2.value[k][column];
                    }
                }
            }
            return matrix;
        }
        throw new RuntimeException("The Product of these Matrix is undefined");
    }

    public float[][] getValue() {
        return value;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for (float[] row: value){
            for (float content: row){
                result.append(content).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
