package com.maindbpackage.venkat.bdpackage;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
     FirebaseAuth auth;
     TextInputLayout e1,e2;
     PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
     String code;
    String in_code;
    boolean avwerify=false;
    public int STORAGE_PREMISSION_CODE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if (ContextCompat.checkSelfPermission(OTP.this,
                Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
            // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

        }else
        { //Toast.makeText(getApplicationContext(),"else",Toast.LENGTH_LONG).show();
            onRequestPermissions();
        } // Toast.makeText(getApplicationContext(), "enter the missing data", Toast.LENGTH_LONG).show();



            e1=findViewById(R.id.pno);
            e2=findViewById(R.id.verify);
            auth=FirebaseAuth.getInstance();
            mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    avwerify=false;
                    Toast.makeText(getApplicationContext()," Verification Complete",Toast.LENGTH_SHORT).show();
                    signinwithphno(phoneAuthCredential);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {

                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    code=s;
                    Toast.makeText(getApplicationContext(),"Code Sent to the Number",Toast.LENGTH_SHORT).show();
                }
            };
    }
    public void sendsms(View v) {
        try {
            String num = e1.getEditText().getText().toString();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    num, 60, TimeUnit.SECONDS, this, mcallback
            );
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please enter your mobile number",Toast.LENGTH_SHORT).show();
        }
    }
    public void signinwithphno(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener( new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Sign_in Successfully",Toast.LENGTH_SHORT).show();
                    Intent in =new Intent(OTP.this,Main4Activity.class);
                    startActivity(in);
                    finish();
                }else{
                    if(task.getException()!=null){
                        Toast.makeText(getApplicationContext(),"invalid OTP",Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
     //   Toast.makeText(getApplicationContext(),"invaloid",Toast.LENGTH_SHORT).show();

    }
    public  void verify(View v)
    {
        try {
             in_code = e2.getEditText()
                    .getText().toString();
            verifyphonenum(code, in_code);
          //  Toast.makeText(getApplicationContext(),"after verift",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Please enter your mobile number",Toast.LENGTH_SHORT).show();
            }

    }
    public void verifyphonenum(String verifycode,String input_code) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifycode, input_code);
             signinwithphno(credential);
         //   Toast.makeText(getApplicationContext(),"after credential",Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please enter OTP",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {

        super.finish();
    }

    private void onRequestPermissions(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This premission is needed")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(OTP.this,new String[] {Manifest.permission.SEND_SMS},STORAGE_PREMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},STORAGE_PREMISSION_CODE);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {

        if(requestCode==STORAGE_PREMISSION_CODE){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(OTP.this,"PREMISSION GRANTED",Toast.LENGTH_LONG).show();
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(OTP.this, "PREMISSION DENIED", Toast.LENGTH_SHORT).show();
            }

        }

        // other 'case' lines to check for other
        // permissions this app might request
    }


}

