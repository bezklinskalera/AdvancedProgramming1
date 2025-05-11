package grading2;

import java.util.List;

public class ArithmeticMean implements AverageCalculation{
    @Override
    public double calculate(List<Student> sts) throws StudentException, grading.StudentException {

            if (sts.isEmpty()){
                throw new StudentException("No students");
            }
            double studentGrade = 0;

            for (Student student : sts) {
                studentGrade = studentGrade + student.getGrade();
            }

            return studentGrade/sts.size();

    }
}
