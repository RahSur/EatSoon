package com.example.android.eat_soon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Main2Activity extends AppCompatActivity {

    ImageView b1,b2,b3,b4;
//    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        b1=findViewById(R.id.img1_bb);
        b2=findViewById(R.id.img2_amaz);
        b3=findViewById(R.id.img3_grofers);
        b4=findViewById(R.id.img4_hat);

//                mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    }
    public void big(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bigbasket.com/"));
        startActivity(browserIntent);
    }
    public void amaz(View view )
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/"));
        startActivity(browserIntent);
    }
    public void grof(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://grofers.com/"));
        startActivity(browserIntent);
    }
    public void hat(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zopnow.com/dairy-products-c.php"));
        startActivity(browserIntent);
    }



}
