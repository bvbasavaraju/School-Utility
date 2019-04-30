package com.example.basavarajubv.govtschoolutility;

import android.content.Context;
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

  public StudentsViewAdapter_t(Context context_, List<Student_t> students_, boolean listView_)
  {
    context = context_;
    students = students_;
    listView = listView_;
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
    //TODO : May need to selectively set the required fields based on the listView value
    studentCardViewHolder.studentPic.setImageResource(students.get(position).getPicture());
    studentCardViewHolder.studentName.setText(students.get(position).getName());
    studentCardViewHolder.studentGender.setText(students.get(position).getGender());

    if(listView)
    {
      String studentId = "Id : " + students.get(position).getId();
      studentCardViewHolder.studentId.setText(studentId);
    }
    else
    {
      studentCardViewHolder.studentId.setText(students.get(position).getId());
    }

    String scoreInLatestExam_ = students.get(position).getLatestExamMarks() + "/" + students.get(position).getMaxScore();
    studentCardViewHolder.scoreInTestOrExam.setText(scoreInLatestExam_);

    studentCardViewHolder.attendance.setText(students.get(position).isPresent());
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
    TextView scoreInTestOrExam;
    TextView attendance;

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
        scoreInTestOrExam = (TextView) itemView.findViewById(R.id.cardViewLandscapeLastField);
        attendance = (TextView) itemView.findViewById(R.id.cardViewLandscapeLastField);
      }
      else
      {
        studentPic = (ImageView)itemView.findViewById(R.id.cardViewPortraitStudentPic);
        studentId = (TextView)itemView.findViewById(R.id.cardViewPortraitStudentId);
        scoreInTestOrExam = (TextView) itemView.findViewById(R.id.cardViewPortraitSecondField);
        attendance = (TextView) itemView.findViewById(R.id.cardViewPortraitSecondField);
      }
    }
  }
}