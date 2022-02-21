package solver;

public interface Matrix {

    Matrix submatrix(int leftI, int rightI, int leftJ, int rightJ);

    double getAt(int i, int j);

    double[] getDiagonal();

    double getMaximum();

    void setAt(int i, int j, double val);

    int getWidth();

    int getHeight();

    Matrix copy();

    Matrix plus(Matrix b);

    Matrix minus(Matrix b);

    Matrix multiply(Matrix b);

    Matrix divideByRow(double[] row);
}
