package com.example.basavarajubv.govtschoolutility;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.List;

public class Class_t
{
  private int number;
  private Image picture;
  private Bitmap imageBitMap;
  private String classTeacher;
  private List<Student_t> students;

  public Class_t(int number)
  {
    this.number = number;
    classTeacher = "";
  }

  public int getNumber() { return number; }

  public void setNumber(int number) { this.number = number; }

  public Image getPicture() { return picture; }

  public void setPicture(Image picture) { this.picture = picture; }

  public String getClassTeacher() { return classTeacher; }

  public void setClassTeacher(String classTeacher) {  this.classTeacher = classTeacher; }

  public List<Student_t> getStudents()  { return students; }

  public void setStudents(List<Student_t> students) { this.students = students; }

  public int AddStudent(Student_t student)
  {
    for (Student_t tempStudent: students)
    {
      if(tempStudent == student)
      {
        //TODO : Log error as student exists
        return -1;
      }
    }

    students.add(student);
    return 0;
  }
}
