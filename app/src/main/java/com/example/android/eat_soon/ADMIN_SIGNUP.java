package com.example.android.eat_soon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ADMIN_SIGNUP extends AppCompatActivity {
    DatabaseReference database_reference;
    EditText username;
    EditText password,mail_id,height,weight,blood;
    EditText confirm_password;
    Button signup;
    String usrname,mail_string;
    String pass;
    String com_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__signup);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        confirm_password=(EditText) findViewById(R.id.confirm_password);
        signup=(Button) findViewById(R.id.signup_button);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usrname = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                com_pass = confirm_password.getText().toString();

                if (TextUtils.isEmpty(usrname)) {
                    Toast.makeText(ADMIN_SIGNUP.this, "Enter Username", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(ADMIN_SIGNUP.this, "Enter Password", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(com_pass)) {

                    Toast.makeText(ADMIN_SIGNUP.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();

                }

                else  if(!pass.equals(com_pass))
                {
                    Toast.makeText(ADMIN_SIGNUP.this, "Password doesnt matches", Toast.LENGTH_SHORT).show();

                }

                else {
                    database_reference = FirebaseDatabase.getInstance().getReference("ADMIN DETAILS");
                    database_reference.child(usrname+"_"+pass).setValue(usrname+"_"+pass);

                    Intent nxt=new Intent(ADMIN_SIGNUP.this, Login.class);
                    startActivity(nxt);

                }

            }
        });
    }

    public void goto_admin(View view)
    {
        Intent nxt=new Intent(ADMIN_SIGNUP.this, Login.class);
        startActivity(nxt);
    }
}
