import data.AnswerDTO;
import data.InputDTO;
import io.MatrixIO;
import io.MatrixIOImpl;
import solver.*;

public class Main {

    public static void main(String[] args) {
        MatrixIO matrixIO = new MatrixIOImpl();
        InputDTO inputDTO = null;
        try {
            inputDTO = matrixIO.readMatrix();
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.println(e.getMessage());
            else
                System.err.println("ERR_IO");
            System.exit(1);
        }
        Solver solver = new IterationalSolver(inputDTO.getMatrix(), inputDTO.getInaccuracy());
        AnswerDTO answerDTO = null;
        try {
            answerDTO = solver.solve();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        matrixIO.printAnswer(answerDTO.getContents(), answerDTO.getInaccuracies(), answerDTO.getIterationCount(), answerDTO.getMAX_ITERS());
    }

}
