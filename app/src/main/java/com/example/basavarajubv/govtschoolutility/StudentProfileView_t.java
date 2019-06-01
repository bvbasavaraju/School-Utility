package com.example.basavarajubv.govtschoolutility;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StudentProfileView_t extends AppCompatActivity
{
  private static final String PRESENT = "present";
  private static final String ABSENT = "absent";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.student_profile_view_addons);

    Toolbar toolbar = findViewById(R.id.studentProfileViewToolbar);
    setSupportActionBar(toolbar);

//    FloatingActionButton fab = findViewById(R.id.studentProfileViewFloatingButton);
//    fab.setOnClickListener(new View.OnClickListener()
//    {
//      @Override
//      public void onClick(View view)
//      {
//        //TODO: take necessary action
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//          .setAction("Action", null).show();
//      }
//    });

    TextView name = (TextView) findViewById(R.id.studentProfileViewName);
    TextView classAndSection = (TextView) findViewById(R.id.studentProfileViewClassAndSection);
    TextView id = (TextView) findViewById(R.id.studentProfileViewId);
    TextView attendance = (TextView) findViewById(R.id.studentProfileViewAttendance);
    TextView joinintDate = (TextView) findViewById(R.id.studentProfileViewDateOfJoining);
    TextView marksScored = (TextView) findViewById(R.id.studentProfileViewMarksScored);
    TextView birthDate = (TextView) findViewById(R.id.studentProfileViewDOB);
    TextView age = (TextView) findViewById(R.id.studentProfileViewAge);
    TextView fatherName = (TextView) findViewById(R.id.studentProfileViewFatherName);
    TextView motherName = (TextView) findViewById(R.id.studentProfileViewMotherName);
    TextView phoneNum = (TextView) findViewById(R.id.studentProfileViewPhoneNumber);
    TextView eMailId = (TextView) findViewById(R.id.studentProfileViewEMailId);
    TextView addressLine1 = (TextView) findViewById(R.id.studentProfileViewAddressLine1);

    Student_t student = (Student_t) getIntent().getParcelableExtra(MainActivity.REQUEST_ID_STUDENT_OBJECT);

    name.setText(student.getName());
    classAndSection.setText("" + student.getClassNumber() + student.getSection());
    id.setText("" + student.getId());
    attendance.setText(student.isPresent() ? PRESENT : ABSENT);
    joinintDate.setText(student.getDateOfJoiningInString());
    marksScored.setText("" + student.getLatestExamMarks());
    birthDate.setText(student.getDateOfBirthInString());
    age.setText("" + student.getAge());
    fatherName.setText(student.getFatherName());
    motherName.setText(student.getMotherName());
    phoneNum.setText(student.getContactDetails().getPhoneNumber());
    eMailId.setText(student.getContactDetails().getEmailAddress());
    addressLine1.setText(student.getContactDetails().getAddress());
  }

}
