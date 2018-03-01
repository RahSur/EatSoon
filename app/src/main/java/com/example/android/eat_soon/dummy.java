package com.example.android.eat_soon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class dummy extends AppCompatActivity {

    DatabaseReference database_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

    }


    public void delete(View view)
    {

        database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT FULLDETAILS").child("8901030585821_SUNDEEP_25-02-2018_o_Fruits_true");

        database_reference.removeValue();


    }



}
