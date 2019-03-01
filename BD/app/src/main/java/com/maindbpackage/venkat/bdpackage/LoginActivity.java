package com.maindbpackage.venkat.bdpackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by venkat on 7/20/2018.
 */

public class LoginActivity extends AppCompatActivity {
    TextInputLayout e1, e2;
    String email, password;
    Button b1,b2;
    String a;
    private DatabaseReference mdb1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = (Button) findViewById(R.id.login);
        b2 = (Button) findViewById(R.id.signup);
        e1 = findViewById(R.id.email);
        e2 =findViewById(R.id.password);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this, register.class);
                startActivity(in);
                finish();
            }
        });
        Query mdb = FirebaseDatabase.getInstance().getReference("Email").orderByChild("password").equalTo(password);
        mdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if(!validatemail()|!validatepassword()){
                       // a=password.toString();
                       // Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
                    return;
                   }

               /* else if(a==password) {
                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(LoginActivity.this, Main4Activity.class);
                        startActivity(in);
                        finish();
                    }*/
                    else{
                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(LoginActivity.this, Main4Activity.class);
                        startActivity(in);
                        finish();
                        Toast.makeText(getApplicationContext(), " Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Email or Password Incorrect", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private boolean validatemail() {
        email = e1.getEditText().getText().toString();
        if (email.isEmpty()) {
            e1.setError("Field can't be empty");
            return false;
        }/* else if (!email.isEmpty()) {
            e1.setError(getString(R.string.fui_invalid_email_address));
            e1.setError(getString(R.string.fui_missing_email_address));
            return false;
        } */else {
            e1.setError(null);
            e1.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatepassword() {
        password = e2.getEditText().getText().toString();
        if (password.isEmpty()) {
            e2.setError("Field can't be empty");
            return false;
        } else if (password.length() < 8) {
            e2.setError("password weak must have 8 characters");
            return false;
        } else {
            e2.setError(null);
            e2.setErrorEnabled(false);
            return true;
        }
    }




}
