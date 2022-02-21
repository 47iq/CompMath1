package data;

public interface Matrix {

    Matrix submatrix(int leftI, int rightI, int leftJ, int rightJ);

    double getAt(int i, int j);

    void setAt(int i, int j, double val);

    int getWidth();

    int getHeight();

    double[] getRow(int i);

    double[][] getContents();

    double[] getSolution();
}
