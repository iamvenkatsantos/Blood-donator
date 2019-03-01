package com.maindbpackage.venkat.bdpackage;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    public Button  upload,b1;
    public EditText name;
    public String bgrp;
    public EditText phno;
    public EditText loc;
    LocationManager lm;
    LocationListener lc;
    public String str;
    private static int SPLASH_TIME_OUT = 5000;
    public String c,aa1,aa2,aa3;
    public String l;
    public String p;
    public String lca,locc,locd,locp;
    EditText loc_cc,loc_dc,loc_pc;
    public Spinner sp ;
    public String record;
    public String a,b,x;
    public int STORAGE_PREMISSION_CODE=1;
    private ArrayList<String> arraylist = new ArrayList<>();
    public FirebaseAuth firebaseAuthl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        init();
        //  setListeners();
        upload = (Button) findViewById(R.id.upload);
        name =(EditText)findViewById(R.id.loc1);
        //bgrp =(EditText) findViewById(R.id.bgrp);
        sp=(Spinner)findViewById(R.id.spinner2);
        phno =(EditText)findViewById(R.id.phno);
        loc_cc =(EditText)findViewById(R.id.loc_c);
        loc_dc=(EditText)findViewById(R.id.loc_d);
        loc_pc =(EditText)findViewById(R.id.editText4);
       // b = (Button) findViewById(R.id.button);
        b1 = (Button) findViewById(R.id.button2);
        if (ContextCompat.checkSelfPermission(Main3Activity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
           // Toast.makeText(getApplicationContext(),"edede",Toast.LENGTH_LONG).show();

        }else
        { //Toast.makeText(getApplicationContext(),"else",Toast.LENGTH_LONG).show();
            onRequestPermissions();
        } // Toast.makeText(getApplicationContext(), "enter the missing data", Toast.LENGTH_LONG).show();

        /*String[] parts = lca.split(",");
        c = parts[0];
        l = parts[1];
        p = parts[2];*/
        sp=(Spinner)findViewById(R.id.spinner2);
        List<String> list=new ArrayList<String>();
        list.add("--select--");
        list.add("O+ve");
        list.add("O-ve");
        list.add("A+ve");
        list.add("A-ve");
        list.add("B+ve");
        list.add("B-ve");
        list.add("AB+ve");
        list.add("AB-ve");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                record=String.valueOf(sp.getSelectedItem());
               // Toast.makeText(Main3Activity.this,record+,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Toast.makeText(Main3Activity.this,record+"the",Toast.LENGTH_LONG).show();



       // Toast.makeText(getApplicationContext(),"Getting location",Toast.LENGTH_LONG).show();



        lm = (LocationManager) getSystemService(LOCATION_SERVICE);




       // Toast.makeText(getApplicationContext(),"G",Toast.LENGTH_LONG).show();
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getApplicationContext(),"WAIT FOR A SEC",Toast.LENGTH_LONG).show();

                if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER))

                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            Geocoder geocoder = new Geocoder(getApplicationContext());
                            try {


                                List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 5);
                                str = addressList.get(0).getCountryName() + ",";
                                c = addressList.get(0).getCountryName();
                                str += addressList.get(1).getLocality()+ ",";
                                l = addressList.get(1).getLocality();
                                // Toast.makeText(getApplicationContext(),"inside",Toast.LENGTH_LONG).show();
                                str += addressList.get(2).getPostalCode();
                                p = addressList.get(2).getPostalCode();
                                loc_cc.setText(c);
                                loc_dc.setText(l);
                                loc_pc.setText(p);
                        //Toast.makeText(getApplicationContext(),c,Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(),l,Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),"net",Toast.LENGTH_LONG).show();
                       /* if(locp==null)
                        {
                            Toast.makeText(getApplicationContext(), "DIFFCULT TO FIND YOYR LOCATION", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "TYPE YOUR LOCATION", Toast.LENGTH_SHORT).show();
                            lca= loc.getText().toString();
                            String[] nt = lca.split(",");
                            c = nt[0];
                            l = nt[1];
                            p = nt[2];
                            //Toast.makeText(getApplicationContext(), l, Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            loc.setText(locc+","+locd+","+locp);
                        }*/
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
       /* else if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override

                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 5);
                        str = addressList.get(0).getCountryName()+",";
                        locc=addressList.get(0).getCountryName()+"," ;
                        str += addressList.get(1).getLocality() ;
                        locd=addressList.get(1).getLocality();
                       a= str += addressList.get(2).getPostalCode() ;
                        locp=addressList.get(2).getPostalCode();

                        String[] parts = str.split(",");
                        c = parts[0];
                        l = parts[1];
                        p = parts[2];
                       //Toast.makeText(getApplicationContext(),c,Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),l,Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),"gps",Toast.LENGTH_LONG).show();
                        if(locp==null)
                        {
                            Toast.makeText(getApplicationContext(), "DIFFCULT TO FIND YOYR LOCATION", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "TYPE YOUR LOCATION", Toast.LENGTH_SHORT).show();
                            lca= loc.getText().toString();
                            String[] gps = lca.split(",");
                            c = gps[0];
                            l = gps[1];
                            p = gps[2];
                            //Toast.makeText(getApplicationContext(), l, Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            loc.setText(locc+","+locd+","+locp);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }*/




            }
        });


    }




    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }




  //  private void setListeners()
   // {
   //     upload.setOnClickListener(this);
  //  }
    private void init(){



       // loc_country = ((EditText)findViewById(R.id.loc_c));
       // loc_dist = ((EditText)findViewById(R.id.loc_d));
        //loc_pin = ((EditText)findViewById(R.id.loc_p));

    }

        public void onClick (View v){
        DatabaseReference databaseReference;

          //  Toast.makeText(getApplication(), "buton clicked",Toast.LENGTH_SHORT).show();

              /*  DatabaseReference drdelete = FirebaseDatabase.getInstance().getReference(c).child("-LIviXgOcDjPcTgRc618");
              /*  DatabaseReference drdelete2 = FirebaseDatabase.getInstance().getReference(c).child("bgro");
                DatabaseReference drdelete3 = FirebaseDatabase.getInstance().getReference(c).child("phno");
                DatabaseReference drdelete4 = FirebaseDatabase.getInstance().getReference(c).child("loc_dist");
                DatabaseReference drdelete5 = FirebaseDatabase.getInstance().getReference(c).child("loc_pin");
                DatabaseReference drdelete6 = FirebaseDatabase.getInstance().getReference(c).child();*/


              //  drdelete.removeValue();
                /*drdelete2.removeValue();
                drdelete3.removeValue();
                drdelete4.removeValue();
                drdelete5.removeValue();
                drdelete6.removeValue();*/

              // Toast.makeText(getApplicationContext(), "DELETE", Toast.LENGTH_LONG).show();

        try
            {
        if (name.getText().length() == 0 || record.toString()=="--select--" || phno.getText().length() == 0 || loc_cc.getText().length() == 0|| loc_dc.getText().length() == 0|| loc_pc.getText().length() == 0) {

            Toast.makeText(getApplicationContext(), "enter the missing data", Toast.LENGTH_LONG).show();
        } else {
            switch (v.getId()) {


                case R.id.upload:



                        bgrp = record;

                        if (c == null && l == null && p == null) {
                            // Toast.makeText(getApplicationContext(), "enter the missing dataaaaaaaaaaaaa", Toast.LENGTH_LONG).show();

                            aa1 = loc_cc.getText().toString().trim();
                            aa2 = loc_dc.getText().toString().trim();
                            aa3 = loc_pc.getText().toString().trim();
                            locc = aa1;
                            locd = aa2;
                            locp = aa3;
                            b = phno.getText().toString();
                            //  Toast.makeText(getApplicationContext(), "enterrrrrrrrrrr the missing dataaaaaaaaaaaaa", Toast.LENGTH_LONG).show();

                        } else {
                            locc = c;
                            locd = l;
                            locp = p;
                        }
                        x = phno.getText().toString();
                        Query mdb = FirebaseDatabase.getInstance().getReference(record).orderByChild("name").equalTo("VENKAT");
                        //Toast.makeText(getApplicationContext(), mdb.toString() + x, Toast.LENGTH_LONG).show();
                       if (mdb.toString().equals(x)){
                            //if(loc==null)
                            Toast.makeText(getApplicationContext(), "Your Details Already Here", Toast.LENGTH_SHORT).show();
                        } else {

                            if (x.length() == 10) {
                                // Toast.makeText(getApplication(), "loc value illa",Toast.LENGTH_SHORT).show();
                                Blood bd;
                                // Toast.makeText(getApplication(), "before bd",Toast.LENGTH_SHORT).show();
                                bd = new Blood(name.getText().toString().toUpperCase().trim(), bgrp.toString().trim(), phno.getText().toString().toUpperCase().trim(), loc_cc.getText().toString().trim(), loc_dc.getText().toString().toUpperCase().trim(), loc_pc.getText().toString().trim());
                                //Toast.makeText(getApplication(), "after bd",Toast.LENGTH_SHORT).show();
                                saveData(bd);
                                //Toast.makeText(getApplication(), "after savebd",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Number", Toast.LENGTH_SHORT).show();
                                ;
                            }// Toast.makeText(getApplicationContext(),"run after obj",Toast.LENGTH_LONG).show();
                            ;

                        }
            }
            }

            }
            catch(Exception e)
            {
              a= loc_cc.getText().toString();
                Toast.makeText(getApplication(),"ERROR"+"\n"+e+"\n"+a,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplication(), a.toString(),Toast.LENGTH_SHORT).show();
            }
    }

    public void saveData(Blood blood)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        /*DatabaseReference myref=database.getReference("bdgrpDB");
        DatabaseReference myref1=database.getReference("O+ve");
        DatabaseReference myref2=database.getReference("O-ve");
        DatabaseReference myref3=database.getReference("A+ve");
        DatabaseReference myref4=database.getReference("B+ve");
        DatabaseReference myref5=database.getReference("AB+ve");
        DatabaseReference myref6=database.getReference("AB-ve");
        DatabaseReference myref7=database.getReference("A-ve");
        DatabaseReference myref8=database.getReference("B-ve");
        myref.push().setValue(blood);
        myref1.push().setValue(blood);
        myref2.push().setValue(blood);
        myref3.push().setValue(blood);
        myref4.push().setValue(blood);
        myref5.push().setValue(blood);
        myref6.push().setValue(blood);
        myref7.push().setValue(blood);
        myref8.push().setValue(blood);*/


        //String temp="B+ve";
        DatabaseReference myref1=database.getReference(record);
       myref1.push().setValue(blood);
      //  String[] parts = str.split(",");
       //c = parts[0];
       // l = parts[1];
       // p = parts[2];
       // str= loc.getText().toString();
      ///  String[] parts = str.split(",");
       /// c = parts[0];
      //   l = parts[1];
     //    p = parts[2];

        if(loc_pc.getText().toString().length()==0)
        {
            locc="country";
            locd="district";
            locp="pin";
            str=locc+locp+locd;
        }
        Toast.makeText(getApplicationContext(),"Your information has been stored",Toast.LENGTH_LONG).show();
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
               // Toast.makeText(getApplicationContext(), "Log_Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getActivity() {
        Intent in=new Intent(Main3Activity.this,Main4Activity.class);
        startActivity(in);
        this.finish();
    }

    public void onBackPressed() {
        getActivity();
        super.onBackPressed();
    }


    private void onRequestPermissions(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This premission is needed")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(Main3Activity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},STORAGE_PREMISSION_CODE);
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
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},STORAGE_PREMISSION_CODE);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {

        if(requestCode==STORAGE_PREMISSION_CODE){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Main3Activity.this,"PREMISSION GRANTED",Toast.LENGTH_LONG).show();
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(Main3Activity.this, "PREMISSION DENIED", Toast.LENGTH_SHORT).show();
            }

        }

        // other 'case' lines to check for other
        // permissions this app might request
    }


    }


