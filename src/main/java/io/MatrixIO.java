package io;

import data.InputDTO;

import java.io.IOException;

public interface MatrixIO {
    InputDTO readMatrix() throws IOException;
    void printAnswer(double[] contents, double[] inaccuracies, int iterationCount, int max_iters);
}
