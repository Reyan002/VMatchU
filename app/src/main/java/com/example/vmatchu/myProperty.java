package com.example.vmatchu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class myProperty extends AppCompatActivity {

    Spinner spin;
    String spin_val;
    String[] gender = { "All status","For Rent","Want Rent","Want Buy","For Sale" };//array of strings used to populate the spinner
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_property);//setting layout
        spin = (Spinner) findViewById(R.id.spinnerid);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val = gender[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //setting array adaptors to spinners
        //ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        // setting adapteers to spinners
        spin.setAdapter(spin_adapter);
    }

}
