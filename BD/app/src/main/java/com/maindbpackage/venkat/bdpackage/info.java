package com.maindbpackage.venkat.bdpackage;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;


        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;


        import java.util.ArrayList;

public class info extends AppCompatActivity {
    private ListView lv3, lv1, lv2;
    private DatabaseReference mdb1, mdb2, mdb3;
    public int i;
    public  String adap;
    private DatabaseReference mdb, mdba, mdbb;
    private ArrayList<String> arraylist = new ArrayList<>();
    // private ArrayList<String> arraylist1 = new ArrayList<>();
    // private ArrayList<String> arraylist2 = new ArrayList<>();
    //private ArrayList<String> arraylist4 = new ArrayList<>();
    String c, d, loc_dist;
    String[] mobileArray;
    public FirebaseAuth firebaseAuthl;

    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lv1 = (ListView) findViewById(R.id.lv1);
        // lv2 = (ListView) findViewById(R.id.lv3);
        //  lv3= (ListView) findViewById(R.id.lv1);
        c = getIntent().getExtras().getString("value");
        d = getIntent().getExtras().getString("value1");
        loc_dist = getIntent().getExtras().getString("value3");
        //  Toast.makeText(getApplicationContext(), c+"/n"+d, Toast.LENGTH_SHORT).show();
        c.toUpperCase();
        d.toUpperCase();

        //  mdb= FirebaseDatabase.getInstance().getReference();
        // mdb2= FirebaseDatabase.getInstance().getReference();
        //mdb3= FirebaseDatabase.getInstance().getReference();


        Query mdb = FirebaseDatabase.getInstance().getReference(c).orderByChild("loc_dist").equalTo(d);
       // DatabaseReference drbase=FirebaseDatabase.getInstance().getReference(c);
        //drbase.removeValue();
        //Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG);


        // Toast.makeText(getApplicationContext(), "query run", Toast.LENGTH_SHORT).show();

        //Query mdba=FirebaseDatabase.getInstance().getReference("bdgrpDB").orderByChild("loc_pin").equalTo("613001");

        if (mdb != null) {
            // Toast.makeText(getApplicationContext(), "query work", Toast.LENGTH_SHORT).show();
        }
        //mdb=mdb.child("bdgrpDB");
        //mdb2=mdb2.child("bdgrpDB");
        // mdb3=mdb3.child("bdgrpDB");

        // Toast.makeText(getApplicationContext(),"in layout",Toast.LENGTH_LONG).show();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview, arraylist);
        // final ArrayAdapter adapter1 = new ArrayAdapter<String>(this, R.layout.textview, arraylist1);
        // final ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.textview, arraylist2);
            mdb.addValueEventListener(new ValueEventListener() {
         @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
//
          for (DataSnapshot dsp : dataSnapshot.getChildren()) {
        // Toast.makeText(getApplicationContext(),"hj" , Toast.LENGTH_SHORT).show();
           arraylist.add("Name: "+dsp.child("name").getValue().toString());
            arraylist.add("Blood Group: "+dsp.child("bgrp").getValue().toString());
           arraylist.add("Phone No: "+dsp.child("phno").getValue().toString()+"\n");
           key=dsp.getKey();
           //Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();




                  /*  arraylist.remove(dataSnapshot.child("name"));
                    arraylist.remove(dataSnapshot.child("brp"));
                    arraylist.remove(dataSnapshot.child("phno"));
                    arraylist.remove(dataSnapshot.child("loc_pin"));
                    arraylist.remove(dataSnapshot.child("loc_dist"));
                    arraylist.remove(dataSnapshot.child("loc_country"));
                    Toast.makeText(getApplicationContext(),"removed",Toast.LENGTH_LONG).show();;*/
            }
        //  Toast.makeText(getApplicationContext(),"value fetched",Toast.LENGTH_LONG).show();
        // ArrayAdapter adapter4 = new ArrayAdapter<String>(this, R.layout.textview, sdap);

          lv1.setAdapter(adapter);
        // lv1.setAdapter(adapter1);
        //lv3.setAdapter(adapter2);
        //  Toast.makeText(getApplicationContext(), "afteradapter", Toast.LENGTH_LONG).show();

           }

          @Override
           public void onCancelled(DatabaseError databaseError) {
          }
            });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity();
                return true;
            case R.id.log_out:
                firebaseAuthl=FirebaseAuth.getInstance();
                FirebaseUser user=firebaseAuthl.getCurrentUser();
                if(user!=null)
                {
                    user.delete();
                }
                //Toast.makeText(getApplicationContext(), "Log_Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getActivity() {
        Intent in=new Intent(info.this,Main2Activity.class);
        startActivity(in);
        this.finish();
    }

    public void onBackPressed() {
        getActivity();
        super.onBackPressed();
    }

}




