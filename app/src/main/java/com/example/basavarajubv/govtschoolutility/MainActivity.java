package com.example.basavarajubv.govtschoolutility;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
  public static final String REQUEST_ID_STUDENT_OBJECT = "studentObject";
  private final int REQUEST_ID_SEND_MSG_PERMISSION = 1;
  private final int REQUEST_ID_READ_PHONE_STATE_PERMISSION = 2;
  private boolean permissionToSendMsg = false;
  private boolean permissionToReadPhoneState = false;

  public static final String KEY_SEND_MSG_PERMISSION = "com.example.basavarajubv.govtschoolutility.KEY_SEND_MSG_PERMISSION";
  public static final String KEY_READ_PHONE_STATE_PERMISSION = "com.example.basavarajubv.govtschoolutility.KEY_READ_PHONE_STATE_PERMISSION";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Check for SendMsg permission. if not available Get the permission.
    CheckForRequiredPermissionAndAskIfRequired(REQUEST_ID_SEND_MSG_PERMISSION);
    CheckForRequiredPermissionAndAskIfRequired(REQUEST_ID_READ_PHONE_STATE_PERMISSION);

    Button buttonMidDayMean =(Button) findViewById(R.id.bMidDayMeal);
    buttonMidDayMean.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        MoveToMidDayMealActivity();
      }
    });

    Button classesView = (Button) findViewById(R.id.classesViewButton);
    classesView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        MoveToClassesView();
      }
    });
  }

  protected void CheckForRequiredPermissionAndAskIfRequired(int reqId)
  {
    int permissionStatus;
    String[] permissionReqInSting = new String[1];
    switch (reqId)
    {
      case REQUEST_ID_SEND_MSG_PERMISSION:
        permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionStatus == PackageManager.PERMISSION_GRANTED)
        {
          permissionToSendMsg = true;
          return;
        }
        else
        {
          permissionReqInSting[0] = Manifest.permission.SEND_SMS;
        }
        break;

      case REQUEST_ID_READ_PHONE_STATE_PERMISSION:
        permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(permissionStatus == PackageManager.PERMISSION_GRANTED)
        {
          permissionToReadPhoneState = true;
          return;
        }
        else
        {
          permissionReqInSting[0] = Manifest.permission.READ_PHONE_STATE;
        }
        break;

        default:
          return;
    }

    ActivityCompat.requestPermissions(this, permissionReqInSting, reqId);
  }

  protected void MoveToMidDayMealActivity()
  {
    Intent intent = new Intent(this, MidDayMeal.class);
    intent.putExtra(KEY_SEND_MSG_PERMISSION, permissionToSendMsg);
    intent.putExtra(KEY_READ_PHONE_STATE_PERMISSION, permissionToReadPhoneState);
    startActivityForResult(intent, 1);
  }

  protected void MoveToStudentProfileView(Student_t student_)
  {
    Intent intent = new Intent(this, StudentProfileView.class);
    intent.putExtra(REQUEST_ID_STUDENT_OBJECT, student_);
    startActivityForResult(intent, 1);
  }

  protected void MoveToStudentsView(boolean showAttendance, boolean showMarks, boolean listView, Class_t selectedClass)
  {
    if(showAttendance && showMarks)
    {
      //TODO: log an warning!
    }

    //TODO : These list of students should be retrieved from the Class and Section that user selected!
    List<Student_t> students = new ArrayList<>();
    students.add(new Student_t("Akash", "male", 1, selectedClass));
    students.add(new Student_t("Anirudh", "male", 2, selectedClass));
    students.add(new Student_t("Bhandhu", "male", 3, selectedClass));
    students.add(new Student_t("Chandru", "male", 4, selectedClass));
    students.add(new Student_t("Dinesh", "male", 5, selectedClass));
    students.add(new Student_t("Ejaz", "male", 6, selectedClass));
    students.add(new Student_t("Farooq", "male", 7, selectedClass));
    students.add(new Student_t("Govinda", "male", 8, selectedClass));
    students.add(new Student_t("Hruthik", "male", 9, selectedClass));
    students.add(new Student_t("Indra", "male", 10, selectedClass));

    students.add(new Student_t("Anitha", "female", 11, selectedClass));
    students.add(new Student_t("Bindya", "female", 12, selectedClass));
    students.add(new Student_t("Chummi", "female", 13, selectedClass));
    students.add(new Student_t("Deepa", "female", 14, selectedClass));
    students.add(new Student_t("Emi", "female", 15, selectedClass));
    students.add(new Student_t("Fida", "female", 16, selectedClass));
    students.add(new Student_t("Geetha", "female", 17, selectedClass));
    students.add(new Student_t("Hema", "female", 18, selectedClass));
    students.add(new Student_t("Indrani", "female", 19, selectedClass));
    students.add(new Student_t("Jyoti", "female", 20, selectedClass));

    //TODO: As of now it is hard coded, This should be given an option in Application Settings
    int numOfColumns = (listView) ? 1 : 3;

    StudentsRecyclerViewAdapter_t studentsViewHolderAdapter = new StudentsRecyclerViewAdapter_t(this, students, listView);
    studentsViewHolderAdapter.setShowAttendance(showAttendance);
    studentsViewHolderAdapter.setShowMarks(showMarks);
    studentsViewHolderAdapter.SetOnStudentItemClickListener(new StudentsRecyclerViewAdapter_t.OnStudentItemClickListener()
    {
      @Override
      public void OnStudentItemClick(Student_t student_)
      {
        MoveToStudentProfileView(student_);
      }
    });

    //Here, setting view to "activity_students_view" is necessary. without that "findViewById" will result in crash
    setContentView(R.layout.activity_recycler_view);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numOfColumns);
    recyclerView.setLayoutManager(gridLayoutManager);
    recyclerView.setAdapter(studentsViewHolderAdapter);
  }

  protected void MoveToClassesView()
  {
    List<Class_t> classes = new ArrayList<>();
    classes.add(new Class_t(1));
    classes.add(new Class_t(2));
    classes.add(new Class_t(3));
    classes.add(new Class_t(4));
    classes.add(new Class_t(5));
    classes.add(new Class_t(6));
    classes.add(new Class_t(7));
    classes.add(new Class_t(8));
    classes.add(new Class_t(9));
    classes.add(new Class_t(10));

    ClassesRecyclerViewAdapter_t classesRecyclerViewAdapter = new ClassesRecyclerViewAdapter_t(this, classes);
    classesRecyclerViewAdapter.SetOnItemClickListener(new ClassesRecyclerViewAdapter_t.OnItemClickListener()
    {
      @Override
      public void OnItemClick(Class_t selectedClass)
      {
        //TODO : From Class access students list!
        MoveToStudentsView(false, false, true, selectedClass);
      }
    });

    setContentView(R.layout.activity_recycler_view);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    recyclerView.setAdapter(classesRecyclerViewAdapter);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
  {
    switch (requestCode)
    {
      case REQUEST_ID_SEND_MSG_PERMISSION:
      {
        if ((grantResults.length > 0) && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
          permissionToSendMsg = true;
          if(!permissionToReadPhoneState)
          {
            CheckForRequiredPermissionAndAskIfRequired(REQUEST_ID_READ_PHONE_STATE_PERMISSION);
          }
        }
      }

      case REQUEST_ID_READ_PHONE_STATE_PERMISSION:
      {
        if ((grantResults.length > 0) && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
          permissionToReadPhoneState = true;
          if(!permissionToSendMsg)
          {
            CheckForRequiredPermissionAndAskIfRequired(REQUEST_ID_SEND_MSG_PERMISSION);
          }
        }
      }

      default:
        return;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if(resultCode == RESULT_OK)
    {
      ShowAlertDialog("Message Sent");
    }
    else
    {
      ShowAlertDialog("Sending Message Failed! Make sure that Permissions have been granted!");
    }
  }

  public void ShowAlertDialog(String msg)
  {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setMessage(msg);
    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int whichButton)
      {
        dialog.dismiss();
      }
    }).show();
  }
}
