import grading.StudentException;
import grading.Student;
import grading.Subject;
public class MainSubject {
    static final String[] sts = {
            "25653443S;Garcia Gomez, Juan;8.1",
            "23322443K;Lopez Turo, Manuel;4.3",
            "24433522M;Merlo Martinez, Juana;5.3",
            "53553421D;Santana Medina, Petra;-7.1",
            "55343442L,Godoy Molina, Marina;6.3",
            "34242432J;Fernandez Vara, Pedro;2.k",
            "42424312G;Lopez Gama, Luisa;7.1" };
    public static void main(String[] args) {
        try {
            Subject algebra = new Subject("Algebra", sts);
            try {
                Student al1 = new Student("23322443k",
                        "Lopez Turo, Manuel");
                Student al2 = new Student("34242432J",
                        "Fernandez Vara, Pedro");
                System.out.println("Grade of " + al1 + ": "
                        + algebra.getGrade(al1));
                System.out.println("Grade of " + al2 + ": "
                        + algebra.getGrade(al2));
            } catch (StudentException e) {
                System.err.println(e.getMessage());
            }
            try {
                System.out.printf("Average %4.2f\n", algebra.getAverage());
            } catch (StudentException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Students...");
            for (Student student : algebra.getStudents()) {
                System.out.println(student + ": " + student.getGrade());
            }
            System.out.println("Errors...");
            for (String error : algebra.getErrors()) {
                System.out.println(error);
            }
            System.out.println(algebra);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
