import grading.Student;
import grading.StudentException;

public class MainStudent {

	public static void main(String[] args) {

		try{
		Student student1 = new Student("22456784F","Gonzalez Perez, Juan" , -6);
		Student student2 = new Student("33456777S","Gonzalez Perez, Juan" , 3.4);
		}catch (StudentException e){
			System.out.println(e.getMessage());
		}

	}

}
