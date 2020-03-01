package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class signup extends AppCompatActivity {

    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView heading = (TextView) findViewById(R.id.heading);
        heading.setPaintFlags(heading.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btn1=(Button)findViewById(R.id.vender_login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(signup.this,vendsignup.class);
                startActivity(i);
            }
        });
    }
}
