package com.example.basavarajubv.govtschoolutility;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentsStrengths extends AppCompatActivity
{
  public static final String KEY_STRENGTH = "com.example.basavarajubv.govtschoolutility.KEY_STRENGTH";
  private static final String defaultStrength = "0";
  private static final String comma = ",";

  private String strengthToReturn = "";

  EditText classes[] = new EditText[10];

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_students_strengths);

    classes[0] = (EditText) findViewById(R.id.etClass1);
    classes[1] = (EditText) findViewById(R.id.etClass2);
    classes[2] = (EditText) findViewById(R.id.etClass3);
    classes[3] = (EditText) findViewById(R.id.etClass4);
    classes[4] = (EditText) findViewById(R.id.etClass5);
    classes[5] = (EditText) findViewById(R.id.etClass6);
    classes[6] = (EditText) findViewById(R.id.etClass7);
    classes[7] = (EditText) findViewById(R.id.etClass8);
    classes[8] = (EditText) findViewById(R.id.etClass9);
    classes[9] = (EditText) findViewById(R.id.etClass10);

    Button bDone = (Button) findViewById(R.id.bDone);
    bDone.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        ValidateAndSendStrengths();
      }
    });
  }

  protected void ValidateAndSendStrengths()
  {
    boolean finishActivity = true;
    for (int i = 0; i < 10; i++)
    {
      EditText etClassData = classes[i];
      String classData = etClassData.getText().toString();
      if(classData.isEmpty())
      {
        strengthToReturn += defaultStrength;
      }
      else
      {
        if(classData.contains("-"))
        {
          finishActivity = false;
          ShowAlertDialog("Strength Cannot be Negative");
          break;
        }

        if((classData.length() < 2) && (!classData.equals(defaultStrength)))
        {
          strengthToReturn += defaultStrength;
        }

        strengthToReturn += classData;
      }

      if( i < 9)
      {
        strengthToReturn += comma;
      }
    }

    if(finishActivity)
    {
      Intent intent = new Intent(this, MidDayMeal.class);
      intent.putExtra(KEY_STRENGTH, strengthToReturn);

      setResult(RESULT_OK, intent);
      finish();
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
