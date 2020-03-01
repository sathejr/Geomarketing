package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class consumerLogin extends AppCompatActivity {
    EditText editText_userName,editText_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_login);
        editText_userName=(EditText)findViewById(R.id.editText_userName);
        editText_password=(EditText)findViewById(R.id.editText_password);
    }
    private void storedb() {
        String email = editText_userName.getText().toString();
        String password = editText_password.getText().toString();


        consulogdb bg = new consulogdb(this);

        bg.execute(email,password);
    }

    public void consumerLogin(View view) {
        try{
            if(editText_userName.getText().toString().isEmpty())
            {
                Toast.makeText(consumerLogin.this,"Enter a Username",Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_password.getText().toString().isEmpty())
            {
                Toast.makeText(consumerLogin.this,"Enter a Password",Toast.LENGTH_SHORT).show();
                return;
            }
            if(editText_password.getText().toString().length()<8){
                Toast.makeText(consumerLogin.this,"Password must contain 8 characters",Toast.LENGTH_SHORT).show();
                return;
            }
            storedb();
            Intent intent = new Intent(consumerLogin.this,consumerHome.class);
            startActivity(intent);
            finish();
        }
        catch(Exception e){
            Toast.makeText(consumerLogin.this,"error",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
