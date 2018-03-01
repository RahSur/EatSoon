package com.example.android.eat_soon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        String usr=getIntent().getStringExtra("USERNAME");
        TextView tv=(TextView)findViewById(R.id.tv1_name);
        tv.setText(usr);
    }
    public void to_main_screen(View v)
    {
        Intent nxt=new Intent(Credits.this,MainActivity.class);
        startActivity(nxt);
    }
}
