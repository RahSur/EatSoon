package com.example.android.eat_soon;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Item_Details extends AppCompatActivity {

    String picture_reference;

    String id_arr,name_arr,date_arr,desc_arr, type_arr,environment_arr;

    TextView pro_name,pro_type,pro_date,pro_desc,pro_id;

    FloatingActionButton floatingActionButton;

    DatabaseReference database_reference;
    Toolbar tool;

    ImageView product_image,dummy;

    ArrayList<String> full=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__details);

        String value_from_mainscreen=getIntent().getStringExtra("VALUE");
        String[] outputfull = value_from_mainscreen.split("_");

                id_arr = outputfull[0];
             name_arr = outputfull[1];
             date_arr = outputfull[2];
               desc_arr = outputfull[3];
                type_arr = outputfull[4];
                 environment_arr = outputfull[5];

                 picture_reference=id_arr+"PRODUCT PICTURE";

        pro_id=(TextView)findViewById(R.id.pro_id);

                 pro_name=(TextView)findViewById(R.id.pro_name);
                pro_type=(TextView)findViewById(R.id.pro_type);
                 pro_date=(TextView)findViewById(R.id.pro_expiry_date);
                 pro_desc=(TextView)findViewById(R.id.pro_desc);
                 product_image=(ImageView) findViewById(R.id.product_image);
        dummy=(ImageView) findViewById(R.id.ima);

                 floatingActionButton=(FloatingActionButton)findViewById(R.id.float_type);

                 pro_id.setText(id_arr);
                 pro_name.setText(name_arr);
        pro_type.setText(type_arr);
        pro_date.setText(date_arr);
        pro_desc.setText(desc_arr);

        if(type_arr.equals("Dairy Products"))
        {
            floatingActionButton.setImageResource(R.drawable.dairy);
        }
        else if (type_arr.equals("Fruits"))
        {
            floatingActionButton.setImageResource(R.drawable.fruits);
        }
        else if (type_arr.equals("Vegetables"))
        {
            floatingActionButton.setImageResource(R.drawable.vegetables);

        }
        else if (type_arr.equals("Medicines"))
        {
            floatingActionButton.setImageResource(R.drawable.med);

        }
        else if (type_arr.equals("Eggs"))
        {
            floatingActionButton.setImageResource(R.drawable.eggs);

        }
        else if (type_arr.equals("Juice"))
        {
            floatingActionButton.setImageResource(R.drawable.juice);

        }
        else if (type_arr.equals("Food Item"))
        {
            floatingActionButton.setImageResource(R.drawable.food);

        }
        else if (type_arr.equals("Milk"))
        {
            floatingActionButton.setImageResource(R.drawable.milk);

        }
        else if (type_arr.equals("Snacks"))
        {
            floatingActionButton.setImageResource(R.drawable.snacks);

        }
        else if (type_arr.equals("Sweets"))
        {
            floatingActionButton.setImageResource(R.drawable.sweets);

        }
        else
        {
            floatingActionButton.setImageResource(R.drawable.others);
        }


             tool=(Toolbar) findViewById(R.id.tool_bar);

        tool.setTitle("");
             tool.setTitleTextColor(235226226);


        setSupportActionBar(tool);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        database_reference= FirebaseDatabase.getInstance().getReference(picture_reference);


        database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String value=child.getValue(String.class);

                    full.add(value);
                    try
                    {
                        tool.setTitle("Loading Image");
                        Picasso.with(Item_Details.this).load(value).fit().into(product_image);
                        tool.setTitle("");
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Item_Details.this,"Error",Toast.LENGTH_SHORT).show();
                    }





                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Item_Details.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });










    }

    public void delete(View view)
    {
        database_reference = FirebaseDatabase.getInstance().getReference("sundeep").child("PRODUCT FULLDETAILS");//  .child(id_arr+"_"+name_arr+"_"+date_arr+"_"+desc_arr+"_"+type_arr+"_"+environment_arr);

        database_reference.child(id_arr+"_"+name_arr+"_"+date_arr+"_"+desc_arr+"_"+type_arr+"_"+environment_arr).removeValue();
        Intent in=new Intent(Item_Details.this,MainActivity.class);
        startActivity(in);


    }

}
