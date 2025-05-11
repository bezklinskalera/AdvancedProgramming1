package grading2;

import java.util.List;

public class TrimmedMean implements AverageCalculation {
    private double min;
    private double max;

    public TrimmedMean(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double calculate(List<Student> students) throws StudentException {
        if (students == null || students.isEmpty()) {
            throw new StudentException("No students");
        }

        double sum = 0.0;
        int count = 0;

        for (Student student : students) {
            double grade = student.getGrade();

            if (grade >= min && grade <= max) {
                sum += grade;
                count++;
            }
        }

        if (count == 0) {
            throw new StudentException("No students within the given thresholds");
        }

        return sum / count;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}