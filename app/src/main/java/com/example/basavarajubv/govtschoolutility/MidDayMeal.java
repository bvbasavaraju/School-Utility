package com.example.basavarajubv.govtschoolutility;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MidDayMeal extends AppCompatActivity
{
  private static final String comma = ",";
  private String msgToSend = "";
  private String studentStrength = "";

  private boolean msgReadyToSend = false;
  private boolean permissionToSendMsg = false;
  private boolean permissionToReadPhoneState = false;

  String formattedDate;
  private EditText etCode;
  private EditText etStaffCount;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mid_day_meal);

    //Get the Permission to Send the message
    Intent intent = getIntent();
    permissionToSendMsg = intent.getBooleanExtra(MainActivity.KEY_SEND_MSG_PERMISSION, false);
    permissionToReadPhoneState = intent.getBooleanExtra(MainActivity.KEY_READ_PHONE_STATE_PERMISSION, false);

    //Code
    etCode = (EditText) findViewById(R.id.etCode);

    //Date
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    formattedDate = dateFormat.format(date);

    EditText etDate = (EditText) findViewById(R.id.etDate);
    etDate.setText(formattedDate.toString());

    //Staff Count
    etStaffCount = (EditText) findViewById(R.id.etStaffCount);

    //Enter Button
    Button bEnter = (Button) findViewById(R.id.bEnter);
    bEnter.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        MoveToStudentStrengthActivity();
      }
    });

    //Preview Message Button
    Button bPreviewMessage = (Button) findViewById(R.id.bPreview);
    bPreviewMessage.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        UpdatePreviewMessageArea();
      }
    });

    //Send Button
    Button bSend = (Button) findViewById(R.id.bSend);
    bSend.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        SendToRecipient();
      }
    });
  }

  protected void MoveToStudentStrengthActivity()
  {
    Intent intent = new Intent(this, StudentsStrengths.class);
    startActivityForResult(intent, 1);
  }

  protected void UpdateMsgToSend()
  {
    //By default it is set to ready to send
    msgReadyToSend = true;
    //Code
    msgToSend = etCode.getText().toString() + " ";

    //Date
    msgToSend += formattedDate.toString() + comma;

    //StaffCount;
    String staffCountStr = etStaffCount.getText().toString();
    if(staffCountStr.contains("-"))
    {
      msgReadyToSend = false;
      ShowAlertDialog("Staff count Cannot be Negative. Please enter the correct value");
    }
    else
    {
      if(!staffCountStr.isEmpty())
      {
        msgToSend += staffCountStr + comma;
      }
      else
      {
        msgReadyToSend = false;
      }
    }

    if(!staffCountStr.isEmpty())
    {
      //Student Strength
      if (!studentStrength.isEmpty())
      {
        msgToSend += studentStrength;
      }
      else
      {
        msgReadyToSend = false;
      }
    }
  }

  protected void UpdatePreviewMessageArea()
  {
    //Preview Message
    TextView tvPreviewMessage = (TextView) findViewById(R.id.tvMessage);
    UpdateMsgToSend();
    tvPreviewMessage.setText(msgToSend);
  }

  protected void SendToRecipient()
  {
    UpdatePreviewMessageArea();

    if(!msgReadyToSend)
    {
      ShowAlertDialog("All data is not available");
      return;
    }

    ReConfirmationAndSend();
  }

  protected void ReConfirmationAndSend()
  {
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
    {
      @Override
      public void onClick(DialogInterface dialog, int which)
      {
        switch (which)
        {
          case DialogInterface.BUTTON_POSITIVE:
            SendTheMessage();
            break;

          case DialogInterface.BUTTON_NEGATIVE:
            //Nothing to Do here!
            break;
        }
      }
    };

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Message is: \"" + msgToSend + "\"\nSend this message?").setPositiveButton("Yes", dialogClickListener)
      .setNegativeButton("No", dialogClickListener).show();
  }

  protected void SendTheMessage()
  {
    if(!permissionToSendMsg)
    {
      ShowAlertDialog("Permission to send message is not granted! Make sure to provide permission");
      return;
    }
    if(!permissionToReadPhoneState)
    {
      ShowAlertDialog("Permission to Read Phone State is not granted! Make sure to provide permission");
      return;
    }

    //Phone Number
    EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

    if (etPhoneNumber.getText().toString().isEmpty())
    {
      ShowAlertDialog("Number cannot be empty. Enter the Valid Number");
      return;
    }

    //Sending message
    Intent smsIntent = new Intent(this, MainActivity.class);

    PendingIntent pi = PendingIntent.getActivity(this, 0, smsIntent, 0);

    SmsManager sms = SmsManager.getDefault();
    sms.sendTextMessage(etPhoneNumber.getText().toString(), null, msgToSend, pi, null);

    //Go back to Main Activity
    Intent intent = new Intent(this, MainActivity.class);
    setResult(RESULT_OK, intent);

    finish();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if(resultCode != RESULT_OK)
    {
      ShowAlertDialog("Something Went wrong. Enter Strength again");
      return;
    }

    String strength = data.getStringExtra(StudentsStrengths.KEY_STRENGTH);
    studentStrength = strength;

    //Update Student strength
    TextView tvStudentStrenthEntered = (TextView) findViewById(R.id.tvStudentStrenthEntered);
    tvStudentStrenthEntered.setText(studentStrength);
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