package grading2;

import grading.StudentException;

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

  public double getGrade() throws StudentException {

    /*if (grade<0){
      throw new StudentException("Negative grade");
    }*/
    return grade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return dni.equalsIgnoreCase(student.dni) &&
            name.equals(student.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dni.toUpperCase(), name) ;
  }

  @Override
  public String toString() {
    return name +
            " (" + dni + ")";
  }
}
