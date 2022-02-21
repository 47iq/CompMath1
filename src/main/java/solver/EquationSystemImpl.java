package solver;

import lombok.Getter;

@Getter
public class EquationSystemImpl implements EquationSystem {

    private final Matrix matrix;

    private final int quantity;

    public EquationSystemImpl(Matrix matrix) {
        if(matrix.getWidth() - 1 != matrix.getHeight())
            throw new IllegalArgumentException();
        this.matrix = matrix;
        this.quantity = matrix.getHeight();
    }

    @Override
    public Matrix getCoefficientMatrix() {
        return this.matrix.submatrix(0, this.matrix.getHeight(), 0, this.matrix.getWidth() - 1);
    }

    @Override
    public Matrix getFreeMembersMatrix() {
        return this.matrix.submatrix(0, this.matrix.getHeight(), this.matrix.getWidth(), this.matrix.getWidth());
    }
}
