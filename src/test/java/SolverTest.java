import data.AnswerDTO;
import data.InputDTO;
import io.MatrixIO;
import io.MatrixIOImpl;
import org.junit.Test;
import solver.IterationalSolver;
import solver.Solver;

public class SolverTest {


    @Test
    public void test_number() {
        System.out.println("Тест: ввод некорректного числа переменных");
        String inputData = "2\ntest_number.txt";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        main_no_exits();
        System.out.println();
        String input = "1\n21";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        main_no_exits();
    }

    @Test
    public void test_inaccuracy() {
        System.out.println("Тест: ввод некорректной погрешности");
        String inputData = "2\ntest_inaccuracy.txt";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        main_no_exits();
        System.out.println();
    }

    @Test
    public void test_from_pres() {
        System.out.println("Тест: тест из презентации");
        String inputData = "2\ntest_from_pres.txt";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        main_no_exits();
        System.out.println();
    }

    @Test
    public void test_custom() {
        System.out.println("Тест: тест матрицы 19x19");
        String inputData = "2\ntest_custom.txt";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        main_no_exits();
        System.out.println();
    }

    @Test
    public void test_no_diagonal() {
        System.out.println("Тест: тест матрицы без диагонального преобладания");
        String inputData = "2\ntest_no_diagonal.txt";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        main_no_exits();
        System.out.println();
    }

    @Test
    public void test_from_site() {
        System.out.println("Тест: тест матрицы 8x8");
        String inputData = "2\ntest_site.txt";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        main_no_exits();
        System.out.println();
    }

    private void main_no_exits() {
        MatrixIO matrixIO = new MatrixIOImpl();
        InputDTO inputDTO = null;
        try {
            inputDTO = matrixIO.readMatrix();
        } catch (Exception e) {
            if (e.getMessage() != null)
                System.err.println(e.getMessage());
            else
                System.err.println("ERR_IO");
            return;
        }
        Solver solver = new IterationalSolver(inputDTO.getMatrix(), inputDTO.getInaccuracy());
        AnswerDTO answerDTO = null;
        try {
            answerDTO = solver.solve();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        matrixIO.printAnswer(answerDTO.getContents(), answerDTO.getInaccuracies(), answerDTO.getIterationCount(), answerDTO.getMAX_ITERS());
    }
}
