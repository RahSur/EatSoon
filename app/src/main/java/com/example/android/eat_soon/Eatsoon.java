package com.example.android.eat_soon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Eatsoon extends AppCompatActivity
{
    ImageView imgv;
    TextView tev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        imgv = (ImageView) findViewById(R.id.img);
        tev = (TextView) findViewById(R.id.tv);

        Animation an = AnimationUtils.loadAnimation(this,R.anim.animation);
        imgv.startAnimation(an);
        tev.startAnimation(an);

        final Intent i = new Intent(this,Login.class);


        Thread th = new Thread(){
            public void run()
            {
                try
                {
                    sleep(5000);
                }
                catch(Exception e)
                {
                    System.out.println("Exp" + e.getStackTrace());
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };

        th.start();
    }

}
