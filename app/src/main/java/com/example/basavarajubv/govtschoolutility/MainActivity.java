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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
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
