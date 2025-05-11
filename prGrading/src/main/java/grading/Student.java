package grading;

import java.util.Objects;

public class Student {

  private String dni;
  private String name;
  private double grade;

  public Student(String dni, String name, double grade) throws StudentException {

    if (grade<0){
      throw new StudentException("Negative grade");
    }

    this.dni = dni;
    this.name = name;
    this.grade = grade;

  }

  public Student(String dni, String name) {
    this.dni = dni;
    this.name = name;
    grade = 0;
  }



  public String getDni() {
    return dni;
  }

  public String getName() {
    return name;
  }

  public double getGrade() {
    return grade;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return (dni != null ? dni.equalsIgnoreCase(student.dni) : student.dni == null)
            && Objects.equals(name, student.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dni == null ? 0 : dni.toLowerCase());
  }

  @Override
  public String toString() {
    return name +
            " (" + dni + ")";
  }
}
