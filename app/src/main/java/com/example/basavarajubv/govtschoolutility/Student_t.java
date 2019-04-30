package com.example.basavarajubv.govtschoolutility;

import java.util.Date;
import java.util.List;

public class Student_t
{
  private String name;
  private String gender;
  private String age;
  private String fatherName;
  private String motherName;
  private Date dateOfBirth;
  private int picture;

  private Date dateOfJoining;
  private int id;
  private int present;

  private ContactInformation_t contactDetails;
  private List<Subject_t> subjects;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getGender()
  {
    return gender;
  }

  public void setGender(String gender)
  {
    this.gender = gender;
  }

  public String getAge()
  {
    return age;
  }

  public void setAge(String age)
  {
    this.age = age;
  }

  public String getFatherName()
  {
    return fatherName;
  }

  public void setFatherName(String fatherName)
  {
    this.fatherName = fatherName;
  }

  public String getMotherName()
  {
    return motherName;
  }

  public void setMotherName(String motherName)
  {
    this.motherName = motherName;
  }

  public Date getDateOfBirth()
  {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public int getPicture()
  {
    return picture;
  }

  public void setPicture(int picture)
  {
    this.picture = picture;
  }

  public Date getDateOfJoining()
  {
    return dateOfJoining;
  }

  public void setDateOfJoining(Date dateOfJoining)
  {
    this.dateOfJoining = dateOfJoining;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int isPresent()
  {
    return present;
  }

  public void setPresent(int present)
  {
    this.present = present;
  }

  public int getLatestExamMarks()
  {
    int retVal = 0;
    for (Subject_t sub : subjects)
    {
      retVal += sub.getScore();
    }

    return retVal;
  }

  public int getMaxScore()
  {
    int retVal = 0;
    for (Subject_t sub : subjects)
    {
      retVal += sub.getMAXIMUM_MARKS();
    }

    return retVal;
  }
  //TODO : Add methods to add contact information and Subjects
}
