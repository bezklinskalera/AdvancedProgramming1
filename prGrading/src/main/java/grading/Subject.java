package grading;

import java.util.ArrayList;
import java.util.List;

public class Subject {

  private String name;
  private List<String> errors;
  private List<Student> students;

  public Subject(String name, String[] students) throws StudentException {
    this.name = name;
    this.students = new ArrayList<>();
    this.errors = new ArrayList<>();

    for (String s : students) {
      try {
        String[] studentDate = s.split(";");

        if (studentDate.length < 3) {
          throw new StudentException("Missing data");
        }

        if(!isDouble(studentDate[2])){
          throw new StudentException("Non numerical grade");
        }

        Student student = new Student(studentDate[0], studentDate[1], Double.parseDouble(studentDate[2]));
        this.students.add(student);
      } catch (StudentException e) {
        this.errors.add("ERROR. " + e.getMessage() + ": " + s);
      }
    }

  }

  public List<Student> getStudents() {
    return students;
  }

  public static boolean isDouble(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public List<String> getErrors() {
    return errors;
  }

  public String getName() {
    return name;
  }

  public double getGrade(Student st) throws StudentException {

    for (Student student : students) {
      if (student.getName().equalsIgnoreCase(st.getName()) ||
              student.getDni().equalsIgnoreCase(st.getDni())) {
        return student.getGrade();
      }
    }
    String message = "Student " + st + " has not been found";
    throw new StudentException(message);
  }


  public double getAverage() throws StudentException {
    if (students.isEmpty()){
      throw new StudentException("No students");
    }
    double studentGrade = 0;

    for (Student student : students) {
      studentGrade = studentGrade + student.getGrade();
    }

    return studentGrade/students.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(": { ");
    sb.append("[");

    for (int i = 0; i < students.size(); i++) {
      sb.append(students.get(i).toString());

      if (i < students.size() - 1) {
        sb.append(", ");
      }
    }

    sb.append("]");
    sb.append(", ");
    sb.append("[");

    for (int i = 0; i < errors.size(); i++) {
      sb.append(errors.get(i));

      if (i < errors.size() - 1) {
        sb.append(", ");
      }
    }

    sb.append("]");
    sb.append(" }");

    return sb.toString();
  }

}