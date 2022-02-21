package data;

import lombok.Getter;

@Getter
public class MatrixImpl implements Matrix{

    private final double[][] contents;


    public MatrixImpl(int width, int height) {
        this.contents = new double[width][height];
    }

    public MatrixImpl(double[][] contents) {
        if(contents.length < 1 || contents[0].length < 1)
            throw new IllegalArgumentException();
        this.contents = contents;
    }


    @Override
    public Matrix submatrix(int leftI, int rightI, int leftJ, int rightJ) {
        double[][] submatrix = new double[rightI - leftI + 1][rightJ - leftJ + 1];
        for(int i = leftI; i <= rightI; i++) {
           for(int j = leftJ; j <= rightJ; j++) {
               submatrix[i - leftI][j - leftJ] = this.contents[i][j];
           }
        }
        return new MatrixImpl(submatrix);
    }

    @Override
    public double getAt(int i, int j) {
        return this.contents[i][j];
    }

    @Override
    public void setAt(int i, int j, double val) {
        this.contents[i][j] = val;
    }

    @Override
    public int getWidth() {
        return this.contents[0].length;
    }

    @Override
    public int getHeight() {
        return this.contents.length;
    }

    @Override
    public double[] getRow(int i) {
        return submatrix(i, i, 0, getWidth() - 1).getContents()[0];
    }

    @Override
    public double[] getSolution() {
        double[] solution = new double[getHeight()];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = this.contents[i][getWidth() - 1];
        }
        return solution;
    }
}
