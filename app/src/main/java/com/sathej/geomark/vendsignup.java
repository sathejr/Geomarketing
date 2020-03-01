package com.sathej.geomark;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class vendsignup extends AppCompatActivity {
    //private Button btn_vendorSignup;
    private static final int REQUEST_LOCATION=1;

    Button getlocationbtn;
    TextView showLocationtxt;
    LocationManager locationManager;
    Double lat,longi;

    EditText editText_emailId,editText_password,editText_rePassword,editText_gstNumber,editText_licenseNumber,editText_addressOfShop;
    Spinner addressOfspin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendsignup);

        final Spinner spin1=(Spinner) findViewById(R.id.Products);
        ArrayAdapter<String> myadp= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.shoptype));
        myadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(myadp);


        editText_emailId=(EditText)findViewById(R.id.editText_emailId);
        editText_password=(EditText)findViewById(R.id.editText_password);
        editText_rePassword=(EditText)findViewById(R.id.editText_rePassword);
        editText_gstNumber=(EditText)findViewById(R.id.editText_gstNumber);
        editText_licenseNumber=(EditText)findViewById(R.id.editText_licenseNumber);
        editText_addressOfShop=(EditText)findViewById(R.id.editText_address);
        addressOfspin=(Spinner)findViewById(R.id.Products);

        //btn_vendorSignup=(Button) findViewById(R.id.btn_vendSignUp);
       /* btn_vendorSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmap();
            }
        }); */


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        showLocationtxt=findViewById(R.id.textView_location);
        getlocationbtn=findViewById(R.id.btn_vendSignUp);

        getlocationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    onGPS();
                    storedb();


                }
                else
                {
                    getLocation();
                    storedb();
                }
            }
        });




    }

    private void storedb() {
        String email = editText_emailId.getText().toString();
        String password = editText_password.getText().toString();


        String shoptype = addressOfspin.getSelectedItem().toString();
        String gst = editText_gstNumber.getText().toString();
        String licence = editText_licenseNumber.getText().toString();
        String address = editText_addressOfShop.getText().toString();
        String lat1=lat.toString();
        String log1=longi.toString();


        signupdb bg = new signupdb(this);
        bg.execute(email,password,shoptype,gst,licence,address,lat1,log1);
    }

    private void onGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

   /* public void openmap()
    {
        Intent intent=new Intent(this,map.class);
        startActivity(intent);
    }*/

    public void getLocation() {
        if(ActivityCompat.checkSelfPermission(vendsignup.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(vendsignup.this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }
        else
        {
            Location LocationGPS=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if(LocationGPS !=null)
            {
                double latt=LocationGPS.getLatitude();
                double langii=LocationGPS.getLongitude();

                lat=Double.valueOf(latt);
                longi=Double.valueOf(langii);

                showLocationtxt.setText("Location Cordinates"+"\n"+"Lattitude: "+lat+"\n"+"Longitude: "+longi);
            }
            else if(locationNetwork!=null)
            {
                double latt=locationNetwork.getLatitude();
                double langii=locationNetwork.getLongitude();

                lat=Double.valueOf(latt);
                longi=Double.valueOf(langii);

                showLocationtxt.setText("Location Cordinates"+"\n"+"Lattitude: "+lat+"\n"+"Longitude: "+longi);

            }
            else if(locationPassive!=null)
            {
                double latt=locationPassive.getLatitude();
                double langii=locationPassive.getLongitude();


                showLocationtxt.setText("Location Cordinates"+"\n"+"Lattitude: "+lat+"\n"+"Longitude: "+longi);

            }
            else
            {
                Toast.makeText(this,"Can't get your location",Toast.LENGTH_SHORT).show();

            }
        }

    }

    /*public void vendSignUp(View view) {
        try{
            if(editText_emailId.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter an Email Id", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(Pattern.matches("[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-z]+", editText_emailId.getText().toString()))) {
                Toast.makeText(vendsignup.this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_password.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter a Password", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_password.getText().toString().length()<8){
                Toast.makeText(this, "Password must contain 8 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!(editText_password.getText().toString().equals(editText_rePassword.getText().toString()))){
                Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_rePassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Confirm Password", Toast.LENGTH_SHORT).show();
            }
            if(editText_gstNumber.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter a GST Number", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!(Pattern.matches("[0-9]{2}[A-Z]{5}[0-9]{4}[0-9][A-Z][0-9]", editText_gstNumber.getText().toString()))){
                Toast.makeText(this, "Invalid GST Number", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_licenseNumber.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter a License Number", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_addressOfShop.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter the Address", Toast.LENGTH_SHORT).show();
                return;
            }


            Intent intent=new Intent(vendsignup.this,vendsignup.class);
            startActivity(intent);
            finish();

        }catch(Exception e){
            Toast.makeText(vendsignup.this, "error", Toast.LENGTH_SHORT).show();
            return;
        }

    }
   */
}
