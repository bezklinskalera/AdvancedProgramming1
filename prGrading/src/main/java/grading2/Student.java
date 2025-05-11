package grading2;

import java.util.Objects;

public class Student {

  private String dni;
  private String name;
  private double grade;

  public Student(String dni, String name) {
    this.dni = dni;
    this.name = name;
  }

  public Student(String dni, String name, double grade) throws StudentException {

    if (grade<0){
      throw new StudentException("Negative grade");
    }

    this.dni = dni;
    this.name = name;
    this.grade = grade;

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
    if (o == null || getClass() != o.getClass()) return false;  // перевірка на null
    Student student = (Student) o;

    // Перевірка на null перед порівнянням
    return (dni == null ? student.dni == null : dni.equalsIgnoreCase(student.dni)) &&
            (name == null ? student.name == null : name.equals(student.name));
  }

  @Override
  public int hashCode() {
    // Перевірка на null перед викликом toLowerCase()
    return Objects.hash(
            name == null ? 0 : name.toLowerCase(),
            dni == null ? 0 : dni.toLowerCase()
    );
  }



  @Override
  public String toString() {
    return name +
            " (" + dni + ")";
  }
}
