package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
public class login extends AppCompatActivity {

    Button mapbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapbtn=(Button)findViewById(R.id.consumer_signup);
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,MapsActivity.class);
                startActivity(i);
            }
        });



        TextView heading = (TextView) findViewById(R.id.heading);
        heading.setPaintFlags(heading.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void Vendor_login(View view) {
        Intent i=new Intent(login.this,vendor_login.class);
        startActivity(i);

    }
}
