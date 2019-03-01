package com.maindbpackage.venkat.bdpackage;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    TextInputLayout e1, e2,e3;
    String email,password,name;
    private DatabaseReference mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1 = findViewById(R.id.email);
        e2 =findViewById(R.id.password);
        e3=findViewById(R.id.name);

       // password = e2.getEditText().getText().toString();
       // Intent in = new Intent(register.this, Main3Activity.class);
        //in.putExtra("value", a);
        //in.putExtra("value1", b);
       // startActivity(in);
       // finish();

    }
    public void registerclick(View v)

    {
        if(!validatemail()|!validatepassword()|!validateusername()) {
            return;
        }
        reg bd;
        String a,b;
        a=email;
        b=password;
        // Toast.makeText(getApplication(), "before bd",Toast.LENGTH_SHORT).show();
        bd = new reg(a.toString().trim(),b.toString().trim());
        //Toast.makeText(getApplication(), "after bd",Toast.LENGTH_SHORT).show();
        saveData(bd);
        Toast.makeText(getApplicationContext(),"REGISTER SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        Intent in= new Intent(register.this,LoginActivity.class);
        startActivity(in);
        finish();
    }

    public void saveData(reg bd) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref1 = database.getReference("Email");
        myref1.push().setValue(bd);


    }
    private boolean validatemail(){
        email = e1.getEditText().getText().toString();
        if(email.isEmpty())
        {
            e1.setError("Field can't be empty" );
            return false;
        }//else if (!email.isEmpty()) {
            ////e1.setError(getString(R.string.error_invalid_email));
            //e1.setError(getString(R.string.fui_invalid_email_address));
          //  return false;
        //}
        else{
            e1.setError(null);
            e1.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateusername(){
         name= e3.getEditText().getText().toString();
        if(name.isEmpty())
        {
            e3.setError("Field can't be empty" );
            return false;
        }else if (name.length()>15){
            e3.setError("user name too long" );
            return false;
        }else{
            e3.setError(null);
            e3.setErrorEnabled(false);
            return true;

        }
    }
    private boolean validatepassword(){
        password = e2.getEditText().getText().toString();
        if(password.isEmpty())
        {
            e2.setError("Field can't be empty" );
            return false;
        }else if (password.length()<8){
            e2.setError("password weak must have 8 characters" );
            return false;
        }else{
            e2.setError(null);
            e2.setErrorEnabled(false);
            return true;
        }
    }
}
