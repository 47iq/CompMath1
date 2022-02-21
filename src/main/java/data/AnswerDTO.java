package data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {

    private double[] contents;

    private double[] inaccuracies;

    private int iterationCount;

    private int MAX_ITERS;
}
