package com.example.basavarajubv.govtschoolutility;

public class Subject_t
{
  private String name;
  private int score;
  private int passingScore;
  private int MAXIMUM_MARKS = 100;

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
