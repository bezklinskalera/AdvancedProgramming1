import grading.Student;
import grading.StudentException;
import grading.Subject;

import java.util.List;
import java.util.Locale;

public class MainSubject {
    static final String[] sts = {
            "25653443S;Garcia Gomez, Juan;8.1",
            "23322443K;Lopez Turo, Manuel;4.3",
            "24433522M;Merlo Martinez, Juana;5.3",
            "53553421D;Santana Medina, Petra;-7.1",
            "55343442L,Godoy Molina, Marina;6.3",
            "34242432J;Fernandez Vara, Pedro;2.k",
            "42424312G;Lopez Gama, Luisa;7.1"
    };

    public static void main(String[] args) {
        try {
            Subject algebra = new Subject("Algebra", sts);

            try {
                Student lookup1 = new Student("23322443k", "Lopez Turo, Manuel");
                double grade1 = algebra.getGrade(lookup1);

                System.out.printf(Locale.US, "Grade of %s (%s) : %.6f\n", formatName(lookup1.getName()),
                        formatDni(lookup1.getDni()), grade1);
            } catch (StudentException e) {
                System.out.println(e.getMessage());
            }

            try {
                Student lookup2 = new Student("34242432J", "Fernandez Vara, Pedro");
                double grade2 = algebra.getGrade(lookup2);

                System.out.printf(Locale.US, "Grade of %s (%s) : %.6f\n", formatName(lookup2.getName()),
                        formatDni(lookup2.getDni()), grade2);
            } catch (StudentException e) {
                System.out.println(e.getMessage());
            }

            try {
                double avg = algebra.getAverage();
                System.out.printf(Locale.US, "Average %.6f\n", avg);
            } catch (StudentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Students . . .");
            List<Student> validStudents = algebra.getStudents();

            for (Student s : validStudents) {
                System.out.printf(Locale.US, "%s : %.6f\n", formatName(s.getName()) + " (" +
                        formatDni(s.getDni()) + ")", s.getGrade());
            }

            System.out.println("Errors . . .");
            List<String> errs = algebra.getErrors();

            for (String err : errs) {
                System.out.println(err);
            }

            System.out.println("Subject . . . " + algebra);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String formatDni(String dni) {
        if (dni != null && dni.length() > 1) {
            return dni.substring(0, dni.length() - 1) + " " + dni.charAt(dni.length() - 1);
        }

        return dni;
    }

    private static String formatName(String name) {
        if (name != null) {
            return name.replace(",", " , ");
        }

        return name;
    }
}