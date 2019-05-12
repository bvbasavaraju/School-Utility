package com.example.basavarajubv.govtschoolutility;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject_t implements Parcelable
{
  private String name;
  private int score;
  private int passingScore;
  private int MAXIMUM_MARKS = 100;

  public Subject_t() {}

  public Subject_t(String name_)
  {
    name = name_;
    score = -1;
    passingScore = 35;
  }

  public Subject_t(String name_, int passingScore_)
  {
    name = name_;
    score = -1;
    passingScore = passingScore_;
  }

  public Subject_t(String name_, int passingScore_, int maxMarks_)
  {
    name = name_;
    score = -1;
    passingScore = passingScore_;
    MAXIMUM_MARKS = maxMarks_;
  }

  @Override
  public int describeContents()
  {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags)
  {
    dest.writeString(name);
    dest.writeInt(score);
    dest.writeInt(passingScore);
    dest.writeInt(MAXIMUM_MARKS);
  }

  public static final Parcelable.Creator<Subject_t> CREATOR = new Parcelable.Creator<Subject_t>() {
      public Subject_t createFromParcel(Parcel in)
      {
        return new Subject_t(in);
      }

      public Subject_t[] newArray(int size)
      {
        return new Subject_t[size];
      }
  };

  private Subject_t(Parcel in)
  {
    name = in.readString();
    score = in.readInt();
    passingScore = in.readInt();
    MAXIMUM_MARKS = in.readInt();
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getScore()
  {
    return score;
  }

  public void setScore(int score)
  {
    this.score = score;
  }

  public int getMAXIMUM_MARKS()
  {
    return this.MAXIMUM_MARKS;
  }

  public boolean Result()
  {
    if(score >= passingScore)
    {
      return true;
    }

    return false;
  }

  public String Percentage()
  {
    double retVal = ((score * 1.0) / MAXIMUM_MARKS ) * 100;

    return String.valueOf(retVal) + "%";
  }
}
