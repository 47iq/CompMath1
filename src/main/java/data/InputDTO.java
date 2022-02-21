package data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputDTO {
    private double inaccuracy;
    private Matrix matrix;
}
