package solver;

public interface EquationSystem {

    Matrix getCoefficientMatrix();

    Matrix getFreeMembersMatrix();
}
