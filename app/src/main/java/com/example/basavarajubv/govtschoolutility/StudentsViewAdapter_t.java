package com.example.basavarajubv.govtschoolutility;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
  Note: If StudentsViewAdapter_t has been created with List view, then following item will be visible in the app
        1. Image to the left
        2. Student Name
        3. Gender, Id
        4. Marks or Attendance!

        Otherwise, Only Phone, Id and Gender/Marks/Attendance will be visible in Portrait Mode
 */

public class StudentsViewAdapter_t extends RecyclerView.Adapter<StudentsViewAdapter_t.StudentCardViewHolder_t>
{
  private Context context;
  private List<Student_t> students;
  private boolean listView;
  private boolean showAttendence;
  private boolean showMarks;

  public StudentsViewAdapter_t(Context context_, List<Student_t> students_, boolean listView_)
  {
    context = context_;
    students = students_;
    listView = listView_;
    showAttendence = false;
    showMarks = false;
  }

  public boolean isShowAttendence()
  {
    return showAttendence;
  }

  public void setShowAttendence(boolean showAttendence)
  {
    this.showAttendence = showAttendence;
  }

  public boolean isShowMarks()
  {
    return showMarks;
  }

  public void setShowMarks(boolean showMarks)
  {
    this.showMarks = showMarks;
  }

  @NonNull
  @Override
  public StudentCardViewHolder_t onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
  {
    View view;
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    if(listView)
    {
      view = layoutInflater.inflate(R.layout.student_card_view_landscape, viewGroup, false);
    }
    else
    {
      view = layoutInflater.inflate(R.layout.student_card_view_portrait, viewGroup, false);
    }

    StudentCardViewHolder_t studentCardViewHolder = new StudentCardViewHolder_t(view, listView);
    return studentCardViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull StudentCardViewHolder_t studentCardViewHolder, int position)
  {
    studentCardViewHolder.studentPic.setImageResource(students.get(position).getPicture());

    String studentId = "Id: " + students.get(position).getId();
    studentCardViewHolder.studentId.setText(studentId);


    String gender = students.get(position).getGender().toUpperCase();
    String additionalInfo = gender;
    if(listView)
    {
      studentCardViewHolder.studentName.setText(students.get(position).getName());
      studentCardViewHolder.studentGender.setText("Gender: " + gender + " ,");
      additionalInfo = "Class: " + students.get(position).getWhichClass() + students.get(position).getWhichSectoin();
    }

    if(showMarks)
    {
      additionalInfo = students.get(position).getLatestExamMarks() + "/" + students.get(position).getMaxScore();
    }
    else if(showAttendence)
    {
      additionalInfo = "" + students.get(position).isPresent();
    }

    studentCardViewHolder.additionalInfo.setText(additionalInfo);
  }

  @Override
  public int getItemCount()
  {
    return students.size();
  }

  public static class StudentCardViewHolder_t extends RecyclerView.ViewHolder
  {
    ImageView studentPic;
    TextView studentName;
    TextView studentGender;
    TextView studentId;
    TextView additionalInfo;

    public StudentCardViewHolder_t(@NonNull View itemView, boolean listView)
    {
      super(itemView);

      //TODO for Name and Gender from landscape view

      if(listView)
      {
        studentPic = (ImageView)itemView.findViewById(R.id.cardViewLandscapeStudentPic);
        studentName = (TextView)itemView.findViewById(R.id.cardViewLandscapeStudentName);
        studentGender = (TextView)itemView.findViewById(R.id.cardViewLandscapeGender);
        studentId = (TextView)itemView.findViewById(R.id.cardViewLandscapeStudentId);
        additionalInfo = (TextView) itemView.findViewById(R.id.cardViewLandscapeLastField);
      }
      else
      {
        studentPic = (ImageView)itemView.findViewById(R.id.cardViewPortraitStudentPic);
        studentId = (TextView)itemView.findViewById(R.id.cardViewPortraitStudentId);
        additionalInfo = (TextView) itemView.findViewById(R.id.cardViewPortraitSecondField);
      }
    }
  }
}