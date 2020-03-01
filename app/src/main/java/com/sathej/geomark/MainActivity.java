package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn2;
    Button btn1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView heading = (TextView) findViewById(R.id.heading);
        heading.setPaintFlags(heading.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);



        btn2=(Button)findViewById(R.id.consumer_signup);
        btn1=(Button)findViewById(R.id.login);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,signup.class);
                startActivity(i1);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,login.class);
                startActivity(i2);
            }
        });


    }
}
