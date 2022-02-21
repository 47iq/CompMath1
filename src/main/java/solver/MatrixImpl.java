package solver;

import lombok.Getter;

@Getter
public class MatrixImpl implements Matrix{

    private final double[][] contents;

    private final int width;

    private final int height;

    public MatrixImpl(int width, int height) {
        this.width = width;
        this.height = height;
        this.contents = new double[width][height];
    }

    public MatrixImpl(double[][] contents) {
        if(contents.length < 1 || contents[0].length < 1)
            throw new IllegalArgumentException();
        this.contents = contents;
        this.width = contents.length;
        this.height = contents[0].length;
    }


    @Override
    public Matrix submatrix(int leftI, int rightI, int leftJ, int rightJ) {
        double[][] contents = new double[rightI - leftI][rightJ - leftJ];
        for(int i = 0; i < height; i++) {
            if (width >= 0) System.arraycopy(this.contents[i], 0, contents[i], 0, width);
        }
        return new MatrixImpl(contents);
    }

    @Override
    public double getAt(int i, int j) {
        return this.contents[i][j];
    }

    @Override
    public double[] getDiagonal() {
        double[] diagonal = new double[this.width];
        for(int i = 0; i < Math.min(this.getHeight(), this.getWidth()); i++)
            diagonal[i] = this.contents[i][i];
        return diagonal;
    }

    @Override
    public double getMaximum() {
        double max = -1;
        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                max = Math.max(max, this.getAt(i, j));
            }
        }
        return max;
    }

    @Override
    public void setAt(int i, int j, double val) {
        this.contents[i][j] = val;
    }

    @Override
    public Matrix copy() {
        return new MatrixImpl(this.contents);
    }

    @Override
    public Matrix plus(Matrix b) {
        Matrix c = this.copy();
        if(this.getWidth() != b.getWidth() || this.getHeight() != b.getHeight())
            throw new IllegalArgumentException();
        for(int i = 0; i < this.getHeight(); i++)
            for(int j = 0; j < b.getWidth(); j++) {
                c.setAt(i, j, b.getAt(i, j) + this.getAt(i, j));
            }
        return c;
    }

    @Override
    public Matrix minus(Matrix b) {
        Matrix c = this.copy();
        if(this.getWidth() != b.getWidth() || this.getHeight() != b.getHeight())
            throw new IllegalArgumentException();
        for(int i = 0; i < this.getHeight(); i++)
            for(int j = 0; j < this.getWidth(); j++) {
                c.setAt(i, j, b.getAt(i, j) - this.getAt(i, j));
            }
        return c;
    }

    @Override
    public Matrix multiply(Matrix b) {
        Matrix c = this.copy();
        for(int i = 0; i < this.getWidth(); i++)
            for(int j = 0; j < this.getHeight(); j++) {
                for(int k = 0; k < b.getHeight(); k++) {
                    c.setAt(i, j, this.getAt(i, k) + b.getAt(k, j));
                }
            }
        return c;
    }

    @Override
    public Matrix divideByRow(double[] row) {
        Matrix b = this.copy();
        if(row.length != this.width)
            throw new IllegalArgumentException();
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                b.setAt(i, j, this.getAt(i, j) / row[j]);
            }
        }
        return b;
    }
}
