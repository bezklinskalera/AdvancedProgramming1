import grading.Student;
import grading.StudentException;

import java.util.Locale;

public class MainStudent {
	private static String formatDni(String dni) {
		if (dni != null && dni.length() > 1) {
			return dni.substring(0, dni.length() - 1) + " " + dni.substring(dni.length() - 1);
		}

		return dni;
	}

	private static String formatName(String name) {
		if (name != null) {
			return name.replace(",", " , ");
		}

		return name;
	}

	public static void main(String[] args) {
		try {
			Student student1 = new Student("22456784F", "Gonzalez Perez, Juan", 5.5);
			Student student2 = new Student("33456777S", "Gonzalez Perez, Juan", 3.4);

			System.out.printf(Locale.US, "DNI : %s , Name : %s , Grade : %.6f\n",
					formatDni(student1.getDni()), formatName(student1.getName()), student1.getGrade());
			System.out.printf(Locale.US, "DNI : %s , Name : %s , Grade : %.6f\n",
					formatDni(student2.getDni()), formatName(student2.getName()), student2.getGrade());

			System.out.println("Students " + formatName(student1.getName()) + " (" + formatDni(student1.getDni())
					+ ") and " + formatName(student2.getName()) + " (" + formatDni(student2.getDni())
					+ ") are not equal grading . StudentException : Negative grade");
		} catch (StudentException e) {
			System.err.println(e.getMessage());
		}
	}
}