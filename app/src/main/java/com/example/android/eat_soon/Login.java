package com.example.android.eat_soon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    DatabaseReference database_reference;
    EditText username;
    EditText a;
    EditText b;

    EditText password,mail_id,height,weight,blood;
    EditText confirm_password;
    Button login;
    String usrname,mail_string;
    String pass;
    String com_pass;
    Button signin,signin_admin;

    ArrayList<String> url=new ArrayList<String>();
    ArrayList<String> details=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.username_user);
        password=(EditText) findViewById(R.id.password_user);

        a=(EditText) findViewById(R.id.username_admin);
        b=(EditText) findViewById(R.id.password_admin);

        signin=(Button) findViewById(R.id.signin_user);

        signin_admin=(Button) findViewById(R.id.signin_user_button);



        database_reference= FirebaseDatabase.getInstance().getReference("USER DETAILS");

        database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String value=child.getValue(String.class);

                    details.add(value);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        database_reference= FirebaseDatabase.getInstance().getReference("ADMIN DETAILS");

        database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String value=child.getValue(String.class);

                    url.add(value);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrname = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                String check=usrname+"_"+pass;
                if(details.contains(check))
                {
                    Intent nxt=new Intent(Login.this,MainActivity.class);

//                    Intent nxt1=new Intent(Login.this,Update_Product_Details.class);
//                    nxt1.putExtra("USERNAME",usrname);

                    nxt.putExtra("USERNAME",usrname);
                    startActivity(nxt);
                }
                else
                { Toast.makeText(Login.this,"NOT MATCHES",Toast.LENGTH_SHORT).show();

                }


            }
        });

        signin_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aa = a.getText().toString().trim();
                String bb= b.getText().toString().trim();
                String check=aa+"_"+bb;
                if(url.contains(check))
                {
                    Intent nxt=new Intent(Login.this,QR_Generator.class);
                    startActivity(nxt);
                }
                else
                { Toast.makeText(Login.this,"NOT MATCHES",Toast.LENGTH_SHORT).show();

                }

            }
        });




    }
    public void sign_up_user(View view)
    {
        Intent nxt=new Intent(Login.this,USER_SIGNUP.class);
        startActivity(nxt);
    }
    public void sign_up_admin(View view)
    {

        Intent nxt=new Intent(Login.this,ADMIN_SIGNUP.class);
        startActivity(nxt);
    }
}
