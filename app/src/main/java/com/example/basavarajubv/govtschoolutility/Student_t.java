package com.example.basavarajubv.govtschoolutility;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Student_t extends Person_t implements Parcelable
{
  private int id;
  private boolean present;
  private Class_t associatedClass;
  private char section;
  private Date dateOfJoining;

  private List<Subject_t> subjectsWithMarks;

  private List<String> sports;
  private List<String> culturalActivities;
  private List<String> others;

  public Student_t(String name_, String gender_, int id_, Class_t associatedClass_)
  {
    //TODO : Check for uniqueness of ID!
    super(name_, gender_);
    this.id = id_;
    this.associatedClass = associatedClass_;
    this.dateOfJoining = Calendar.getInstance().getTime();

    List<String> subjectNames = associatedClass.getSubjectNames();
    if((subjectNames == null) || (subjectNames.isEmpty()))
    {
      return;
    }

    this.subjectsWithMarks = new ArrayList<Subject_t>();
    for (String subjectName: subjectNames)
    {
      if(!subjectName.isEmpty())
      {
        Subject_t subject = new Subject_t(subjectName);
        this.subjectsWithMarks.add(subject);
      }
    }
  }

  @Override
  public int describeContents()
  {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags)
  {
    dest.writeString(getName());
    dest.writeString(getGender());
    dest.writeString(getAge());
    dest.writeString(getFatherName());
    dest.writeString(getMotherName());
    dest.writeString(getDateOfBirthInString());
    //TODO : picture
    dest.writeString(getContactDetails().getPhoneNumber());
    dest.writeString(getContactDetails().getEmailAddress());
    dest.writeString(getContactDetails().getAddress());
    dest.writeInt(id);
    dest.writeByte((byte)(present ? 1 : 0));
    dest.writeInt(associatedClass.getNumber()); //TODO : Implement Parcelable in Class_t.
    dest.writeInt((int)section);
    dest.writeString(getDateOfJoiningInString());
    //TODO: dest.writeParcelableArray((Parcelable[]) this.subjects.toArray(), flags);
    //TODO: dest.writeStringList(sports);
    //TODO: dest.writeStringList(culturalActivities);
    //TODO: dest.writeStringList(others);
  }

  public static final Parcelable.Creator<Student_t> CREATOR = new Parcelable.Creator<Student_t>() {
    public Student_t createFromParcel(Parcel in)
    {
      try
      {
        return new Student_t(in);
      }
      catch (ParseException e)
      {
        e.printStackTrace();
      }
      return null;
    }

    public Student_t[] newArray(int size)
    {
      return new Student_t[size];
    }
  };

  private Student_t(Parcel in) throws ParseException
  {
    setName(in.readString());
    setGender(in.readString());
    setAge(in.readString());
    setFatherName(in.readString());
    setMotherName(in.readString());
    setDateOfBirth(in.readString());
    //TODO picture
    getContactDetails().setPhoneNumber(in.readString());
    getContactDetails().setEmailAddress(in.readString());
    getContactDetails().setAddress(in.readString());
    id = in.readInt();
    present = in.readByte() != 0;

    ////TODO : Implement Parcelable in Class_t.
    //associatedClass.setNumber(in.readInt());
    associatedClass = new Class_t(in.readInt());

    section = (char) in.readInt();
    setDateOfJoining(in.readString());
    //TODO : subjects = in.readParcelableArray();
    //TODO: in.readStringList(sports);
    //TODO: in.readStringList(culturalActivities);
    //TODO: in.readStringList(others);
  }

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  public boolean isPresent() { return present; }

  public int getClassNumber() { return associatedClass.getNumber(); }

  public void setClassNumber(int classNumber) { this.associatedClass.setNumber(classNumber); }

  public char getSection() { return section; }

  public void setSection(char section) { this.section = section; }

  public void setPresent(boolean present) { this.present = present; }

  public Date getDateOfJoining() { return dateOfJoining; }

  public String getDateOfJoiningInString()
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    return dateFormat.format(dateOfJoining);
  }

  public void setDateOfJoining(Date dateOfJoining) { this.dateOfJoining = dateOfJoining; }

  public void setDateOfJoining(String dateOfJoining) throws ParseException
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    this.dateOfJoining = dateFormat.parse(dateOfJoining);
  }

  public int getLatestExamMarks()
  {
    int retVal = 0;
    if(subjectsWithMarks == null)
    {
      return retVal;
    }

    for (Subject_t sub : subjectsWithMarks)
    {
      retVal += sub.getScore();
    }

    return retVal;
  }

  public int getMaxScore()
  {
    int retVal = 0;
    if(subjectsWithMarks == null)
    {
      return retVal;
    }

    for (Subject_t sub : subjectsWithMarks)
    {
      retVal += sub.getMAXIMUM_MARKS();
    }

    return retVal;
  }
  //TODO : Add methods to add contact information and Subjects
}
