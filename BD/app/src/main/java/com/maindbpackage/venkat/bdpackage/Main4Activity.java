package com.maindbpackage.venkat.bdpackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main4Activity extends AppCompatActivity {
    Button b1,b2;
    public FirebaseAuth firebaseAuthl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main4Activity.this, Main3Activity.class);
                startActivity(in);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main4Activity.this, Main2Activity.class);
                startActivity(in);
                finish();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                firebaseAuthl=FirebaseAuth.getInstance();
                FirebaseUser user=firebaseAuthl.getCurrentUser();
                if(user!=null)
                {
                    user.delete();
                }
             //   Toast.makeText(getApplicationContext(), "Log_Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public void finish() {
        super.finish();
    }

}
