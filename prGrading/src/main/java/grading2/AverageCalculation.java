package grading2;

import java.util.List;

public interface AverageCalculation {

    double calculate(List<Student> sts) throws StudentException;

}
