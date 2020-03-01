package com.sathej.geomark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class consumerHome extends AppCompatActivity {
    private ListView listView;
    String[] listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_home);
        listView=(ListView)findViewById(R.id.searchList);
        listItem = getResources().getStringArray(R.array.Products);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);

    }

    public void Logout(View view) {
        finish();
    }

    public void search(View view) {

    }
}
