package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class vendorHome extends AppCompatActivity {
    EditText editText_productName,editText_quantity,editText_updateQuantity;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home);

        Spinner spin1=(Spinner) findViewById(R.id.Products);
        ArrayAdapter<String> myadp= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.shoptype));
        myadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(myadp);
        editText_productName=(EditText)findViewById(R.id.editText_productName);
        editText_quantity=(EditText)findViewById(R.id.editText_quantity);
        editText_updateQuantity=(EditText)findViewById(R.id.editText_updateQuantity);

    }
    public void storedb() {
        String pname = editText_productName.getText().toString();



        String quant = editText_quantity.getText().toString();

        productdb bg = new productdb(this);
        bg.execute(pname,quant);
    }


    public void vendorHome(View view) {
        try{

            storedb();

            Intent intent=new Intent(vendorHome.this,vendorHome.class);
            startActivity(intent);
            finish();

        }catch(Exception e){
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void Logout(View view) {
        finish();
    }
}
