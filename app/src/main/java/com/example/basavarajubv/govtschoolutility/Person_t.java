package com.example.basavarajubv.govtschoolutility;

import android.graphics.Bitmap;
import android.media.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Person_t
{
  private String name;
  private String gender;
  private String age;
  private String fatherName;
  private String motherName;
  private Date dateOfBirth;
  private Image picture;

  private ContactInformation_t contactDetails;

  public Person_t()
  {
    this.dateOfBirth = Calendar.getInstance().getTime();
    contactDetails = new ContactInformation_t();
  }

  public Person_t(String name, String gender)
  {
    this.name = name;
    this.gender = gender;
    this.dateOfBirth = Calendar.getInstance().getTime();
    contactDetails = new ContactInformation_t();
  }

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

  public void setGender(String gender) { this.gender = gender; }

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

  public Date getDateOfBirth() { return dateOfBirth; }

  public String getDateOfBirthInString()
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    return dateFormat.format(dateOfBirth);
  }

  public void setDateOfBirth(Date dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) throws ParseException
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    this.dateOfBirth = dateFormat.parse(dateOfBirth);
  }

  public Image getPicture() { return picture; }

  public void setPicture(Image picture) { this.picture = picture; }

  public ContactInformation_t getContactDetails() { return contactDetails; }

  public void setContactDetails(ContactInformation_t contactDetails) { this.contactDetails = contactDetails; }
}
