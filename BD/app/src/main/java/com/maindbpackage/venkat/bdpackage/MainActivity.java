package com.maindbpackage.venkat.bdpackage;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    public FirebaseAuth firebaseAuthl;
    private static int SPLASH_TIME_OUT=2000;
    public int STORAGE_PREMISSION_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        firebaseAuthl=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuthl.getCurrentUser();


                if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this,Main4Activity.class));
            finish();
        }
        else {
            Intent in = new Intent(MainActivity.this,Main4Activity.class);
            startActivity(in);
            finish();
        }



            }

        },SPLASH_TIME_OUT);

    }
     
    @Override
    public void finish() {
        super.finish();
    }

}
