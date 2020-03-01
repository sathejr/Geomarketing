package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class vendor_login extends AppCompatActivity {
    EditText editText_vendorEmail, editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
        editText_vendorEmail = (EditText) findViewById(R.id.editText_emailId);
        editText_password = (EditText) findViewById(R.id.editText_password);


    }
    private void storedb() {
        String email = editText_vendorEmail.getText().toString();
        String password = editText_password.getText().toString();


        vendorlogindb bg = new vendorlogindb(this);
        mapdb mp=new mapdb(this);
        bg.execute(email,password);
        mp.execute(email);
    }


    public void vendorLogin(View view) {
        try {
            if (editText_vendorEmail.getText().toString().isEmpty()) {
                Toast.makeText(vendor_login.this, "Enter an Email ID", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(Pattern.matches("[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-z]+", editText_vendorEmail.getText().toString()))) {
                Toast.makeText(vendor_login.this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_password.getText().toString().isEmpty()){
                Toast.makeText(vendor_login.this,"Enter a Password",Toast.LENGTH_SHORT).show();
                return;
            }
            if (editText_password.getText().toString().length() < 8) {
                Toast.makeText(vendor_login.this, "Password must contain 8 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            storedb();
            Intent intent = new Intent(vendor_login.this, vendorHome.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            Toast.makeText(vendor_login.this, "Error:" + e, Toast.LENGTH_LONG).show();
            return;
        }
    }
}