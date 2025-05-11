package grading2;

import java.util.List;

public class HarmonicMean implements AverageCalculation {
    @Override
    public double calculate(List<Student> students) throws StudentException {
        if (students == null || students.isEmpty()) {
            throw new StudentException("No students");
        }

        double sumOfReciprocals = 0.0;
        int count = students.size();

        for (Student student : students) {
            double grade = student.getGrade();

            if (grade <= 0) {
                throw new StudentException("Invalid grade for harmonic mean (must be > 0). Student: " + student);
            }

            sumOfReciprocals += (1.0 / grade);
        }

        return count / sumOfReciprocals;
    }
}