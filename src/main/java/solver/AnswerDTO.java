package solver;

import lombok.Builder;
import lombok.Data;

@Data
public class AnswerDTO {

    private double[] contents;

    private double[] inaccuracies;

    private int iterationCount;
}
