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
  Note: If StudentsRecyclerViewAdapter_t has been created with List view, then following item will be visible in the app
        1. Image to the left
        2. Student Name
        3. Gender, Id
        4. Marks or Attendance!

        Otherwise, Only Phone, Id and Gender/Marks/Attendance will be visible in Portrait Mode
 */

public class StudentsRecyclerViewAdapter_t extends RecyclerView.Adapter<StudentsRecyclerViewAdapter_t.StudentCardViewHolder_t>
{
  private Context context;
  private List<Student_t> students;
  private boolean listView;
  private boolean showAttendance;
  private boolean showMarks;
  private OnStudentItemClickListener onStudentItemClickListener;

  public StudentsRecyclerViewAdapter_t(Context context_, List<Student_t> students_, boolean listView_)
  {
    context = context_;
    students = students_;
    listView = listView_;
    showAttendance = false;
    showMarks = false;
  }

  public interface OnStudentItemClickListener
  {
    void OnStudentItemClick(Student_t student_);
  }

  public void SetOnStudentItemClickListener(OnStudentItemClickListener onStudentItemClickListener_)
  {
    onStudentItemClickListener = onStudentItemClickListener_;
  }

  public static class StudentCardViewHolder_t extends RecyclerView.ViewHolder
  {
    ImageView studentPic;
    TextView studentName;
    TextView studentGender;
    TextView studentId;
    TextView additionalInfo;

    public StudentCardViewHolder_t(@NonNull View itemView, boolean listView, final OnStudentItemClickListener onStudentItemClickListener_, final List<Student_t> students)
    {
      super(itemView);

      if(listView)
      {
        studentPic = (ImageView)itemView.findViewById(R.id.studentCardListViewPic);
        studentName = (TextView)itemView.findViewById(R.id.studentCardListViewName);
        studentGender = (TextView)itemView.findViewById(R.id.studentCardListViewGender);
        studentId = (TextView)itemView.findViewById(R.id.studentCardListViewId);
        additionalInfo = (TextView) itemView.findViewById(R.id.studentCardListViewLastField);
      }
      else
      {
        studentPic = (ImageView)itemView.findViewById(R.id.studentCardIconViewPic);
        studentId = (TextView)itemView.findViewById(R.id.studentCardIconViewId);
        additionalInfo = (TextView) itemView.findViewById(R.id.studentCardIconViewSecondField);
      }

      itemView.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          if(onStudentItemClickListener_ == null)
          {
            return;
          }

          int position = getAdapterPosition();
          if(position == RecyclerView.NO_POSITION)
          {
            return;
          }

          Student_t student_ = students.get(position);
          onStudentItemClickListener_.OnStudentItemClick(student_);
        }
      });
    }
  }

  public boolean getShowAttendance()
  {
    return showAttendance;
  }

  public void setShowAttendance(boolean showAttendance)
  {
    this.showAttendance = showAttendance;
  }

  public boolean getShowMarks()
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
      view = layoutInflater.inflate(R.layout.student_card_list_view, viewGroup, false);
    }
    else
    {
      view = layoutInflater.inflate(R.layout.student_card_icon_view, viewGroup, false);
    }

    StudentCardViewHolder_t studentCardViewHolder = new StudentCardViewHolder_t(view, listView, onStudentItemClickListener, students);
    return studentCardViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull StudentCardViewHolder_t studentCardViewHolder, int position)
  {
    //TODO : Set the image of the student!
    //studentCardViewHolder.studentPic.(students.get(position).getPicture());

    String studentId = "Id: " + students.get(position).getId();
    studentCardViewHolder.studentId.setText(studentId);


    String gender = students.get(position).getGender().toUpperCase();
    String additionalInfo = gender;
    if(listView)
    {
      studentCardViewHolder.studentName.setText(students.get(position).getName());
      studentCardViewHolder.studentGender.setText("Gender: " + gender + " ,");
      additionalInfo = "Class: " + students.get(position).getClassNumber() + students.get(position).getSection();
    }

    if(showMarks)
    {
      additionalInfo = students.get(position).getLatestExamMarks() + "/" + students.get(position).getMaxScore();
    }
    else if(showAttendance)
    {
      additionalInfo = "" + students.get(position).isPresent();
    }

    studentCardViewHolder.additionalInfo.setText(additionalInfo);
  }

  @Override
  public int getItemCount() { return students.size(); }
}