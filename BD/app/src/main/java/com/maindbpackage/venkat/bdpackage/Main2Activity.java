package com.maindbpackage.venkat.bdpackage;

import android.Manifest;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Spinner sp;
    private Button b1, b2, b3;
    LocationManager lm;
    LocationListener lc;
    EditText t1, t2;
    EditText loc_c, loc_d, loc_p;
    private static int SPLASH_TIME_OUT = 5000;
    public String str, lcp;
    String a, a1, a3, record;
    public String c, l, p;
    public String lcc, lcd, lll;
    public int STORAGE_PREMISSION_CODE=1;
    public FirebaseAuth firebaseAuthl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        sp = (Spinner) findViewById(R.id.spinner);
        if (ContextCompat.checkSelfPermission(Main2Activity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
           // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

        }else
        { //Toast.makeText(getApplicationContext(),"else",Toast.LENGTH_LONG).show();
            onRequestPermissions();
        } // Toast.makeText(getApplicationContext(), "enter the missing data", Toast.LENGTH_LONG).show();
        List<String> list = new ArrayList<String>();
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
                record = String.valueOf(sp.getSelectedItem());
                // Toast.makeText(Main3Activity.this,record+,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Toast.makeText(getApplicationContext(),"Getting location",Toast.LENGTH_LONG).show();
        b1 = (Button) findViewById(R.id.b1);

        loc_c = (EditText) findViewById(R.id.loc_c);
        t2 = (EditText) findViewById(R.id.editText);
        loc_d = (EditText) findViewById(R.id.loc_d);
        loc_p = (EditText) findViewById(R.id.loc_p);
        //  a= t1.getText().toString();
        b3 = (Button) findViewById(R.id.button);



        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                try {
                    Toast.makeText(getApplicationContext(), "WAIT FOR A SEC", Toast.LENGTH_LONG).show();


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
                                    str += addressList.get(1).getLocality() + ",";
                                    l = addressList.get(1).getLocality();
                                    // Toast.makeText(getApplicationContext(),"inside",Toast.LENGTH_LONG).show();
                                    str += addressList.get(2).getPostalCode();
                                    p = addressList.get(2).getPostalCode();
                                    loc_c.setText(c);
                                    loc_d.setText(l);
                                    loc_p.setText(p);
                                    // Toast.makeText(getApplicationContext(),lcp,Toast.LENGTH_LONG).show();


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
    /*   else if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override

                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 5);
                        str = addressList.get(0).getCountryName() + ",";
                        c=addressList.get(0).getCountryName() ;
                        str += addressList.get(1).getLocality() + ",";
                        l=addressList.get(1).getLocality();
                        str += addressList.get(2).getPostalCode() ;
                        p=addressList.get(2).getPostalCode();
                        new Handler().postDelayed(new Runnable() {
                            @Override

                            public void run() {
                                //Toast.makeText(getApplicationContext(),"N",Toast.LENGTH_LONG).show();
                                if (c!=null && l!=null && p!=null)  {
                                    t1.setText(str.toString());

                                } else {
                                  //  Toast.makeText(getApplicationContext(), "TYPE YOUR LOCATION", Toast.LENGTH_SHORT).show();

                                }
                            }

                        },SPLASH_TIME_OUT);

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

                    else {
                        Toast.makeText(getApplicationContext(), "DIFFCULT TO FIND YOYR LOCATION", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "TYPE YOUR LOCATION", Toast.LENGTH_SHORT).show();

                    }


                    //Toast.makeText(getApplicationContext(), p, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main2Activity.this, info.class);

                if (loc_c.toString().length() == 0 || loc_d.toString().length() == 0 || loc_p.toString().length() == 0 || record == "--select--") {
                    Toast.makeText(getApplicationContext(), "enter the missing data", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),lll,Toast.LENGTH_LONG).show();
                } else {
                    // Intent in=new Intent(Main2Activity.this,info.class);
                    // Toast.makeText(getApplicationContext(),lll,Toast.LENGTH_LONG).show();
                    if (c == null || l == null || p == null) {
                        lcc = loc_c.getText().toString().toUpperCase();
                        lcd = loc_d.getText().toString().toUpperCase();
                        lcp = loc_p.getText().toString();

                    } else {
                        lcc = c;
                        lcd = l;
                        lcp = p;
                    }

                    a = record;
                    a1 = lcd;
                    a3 = lcd;
                    in.putExtra("value", a);
                    in.putExtra("value1", a1);
                    in.putExtra("value3", a3);
                    startActivity(in);
                    finish();
                }


            }


        });


    }

    @Override
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

                //Intent in=new Intent(getApplicationContext(),OTP.class);
                //startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getActivity() {
        Intent in = new Intent(Main2Activity.this, Main4Activity.class);
        startActivity(in);
        this.finish();

    }

    @Override
    public void onBackPressed() {
        getActivity();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    private void onRequestPermissions(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This premission is needed")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(Main2Activity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},STORAGE_PREMISSION_CODE);
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
                Toast.makeText(Main2Activity.this,"PREMISSION GRANTED",Toast.LENGTH_LONG).show();
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(Main2Activity.this, "PREMISSION DENIED", Toast.LENGTH_SHORT).show();
            }

        }

        // other 'case' lines to check for other
        // permissions this app might request
    }



}

