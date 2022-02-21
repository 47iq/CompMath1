package solver;

import data.AnswerDTO;
import data.Matrix;
import data.MatrixImpl;

import java.util.Arrays;
import java.util.ResourceBundle;

public class IterationalSolver implements Solver{

    private Matrix systemMatrix;

    private double currentInaccuracy = 1e18;

    private final double inaccuracy;

    private final int MAX_ITERS = 1000000;

    private double[] inaccuracies;

    public IterationalSolver(Matrix systemMatrix, double inaccuracy) {
        this.systemMatrix = systemMatrix;
        this.inaccuracy = inaccuracy;
        this.inaccuracies = new double[systemMatrix.getHeight()];
    }

    @Override
    public AnswerDTO solve() throws Exception {
        systemMatrix = diagonalize(systemMatrix);
        systemMatrix = modify(systemMatrix);
        return performIters(systemMatrix);
    }

    private AnswerDTO performIters(Matrix systemMatrix) {
        double iterAccuracy = 0;
        int iters = 0;
        double[] initialSolution = systemMatrix.getSolution();
        while (iters < MAX_ITERS && inaccuracy <= currentInaccuracy) {
            double[] tempSolution = systemMatrix.getSolution();
            iterAccuracy = 0;
            for (int i = 0; i < systemMatrix.getHeight(); i++) {
                double val = 0;
                for (int j = 0; j < systemMatrix.getHeight(); j++) {
                    if(i != j)
                        val += systemMatrix.getAt(i, j) * tempSolution[j];
                    else
                        val += initialSolution[i];
                }
                inaccuracies[i] = Math.abs(val - tempSolution[i]);
                iterAccuracy = Math.max(iterAccuracy, inaccuracies[i]);
                systemMatrix.setAt(i, systemMatrix.getWidth() - 1, val);
            }
            currentInaccuracy = iterAccuracy;
            iters++;
        }
        return new AnswerDTO(systemMatrix.getSolution(), inaccuracies, iters, MAX_ITERS);
    }

    private Matrix modify(Matrix systemMatrix) {
        Matrix modified = new MatrixImpl(new double[systemMatrix.getHeight()][systemMatrix.getWidth()]);
        for(int i = 0; i < systemMatrix.getHeight(); i++) {
            for(int j = 0; j < systemMatrix.getWidth(); j++) {
                modified.setAt(i, j, systemMatrix.getAt(i, j) / -systemMatrix.getAt(i, i));
            }
            modified.setAt(i, modified.getWidth() - 1, -modified.getAt(i, modified.getWidth() - 1));
            modified.setAt(i, i, 0);
        }
        return modified;
    }

    private Matrix diagonalize(Matrix systemMatrix) throws Exception {
        boolean[] isDiagonalized = new boolean[systemMatrix.getHeight()];
        boolean hasElemGreaterThanOthersSum = false;
        int[] rowIndexes = new int[systemMatrix.getHeight()];
        for(int i = 0; i < systemMatrix.getHeight(); i++) {
            double rowSum = Arrays.stream(systemMatrix
                        .submatrix(i, i, 0, systemMatrix.getWidth() - 2)
                        .getRow(0))
                    .map(Math::abs)
                    .sum();
            for(int j = 0; j < systemMatrix.getHeight(); j++) {
                if(rowSum / 2 <= systemMatrix.getAt(i, j)) {
                    isDiagonalized[j] = true;
                    rowIndexes[j] = i;
                    if(rowSum / 2 < systemMatrix.getAt(i, j))
                        hasElemGreaterThanOthersSum = true;
                }
            }
        }
        if(!hasElemGreaterThanOthersSum)
            throw new Exception(ResourceBundle.getBundle("messages").getString("ERR_DIAGONAL"));
        for(int i = 0; i < systemMatrix.getHeight(); i++) {
            if(!isDiagonalized[i])
                throw new Exception(ResourceBundle.getBundle("messages").getString("ERR_DIAGONAL"));
        }
        double[][] diagonalizedContents = new double[systemMatrix.getHeight()][systemMatrix.getWidth()];
        for(int i = 0; i < systemMatrix.getHeight(); i++) {
            diagonalizedContents[i] = systemMatrix.getRow(rowIndexes[i]);
        }
        return new MatrixImpl(diagonalizedContents);
    }
}
