package com.example.android.eat_soon;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Add_Item extends AppCompatActivity {


    MediaPlayer mediaplayer;
    Vibrator vibrator;

    String username;


    private ZXingScannerView scan_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item);


        mediaplayer=MediaPlayer.create(this,R.raw.beep);
        vibrator=(Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);

        username=getIntent().getStringExtra("USERNAME");





    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Toast.makeText(Add_Item.this,"Back Prohibitted",Toast.LENGTH_SHORT).show();
            Intent main_screen=new Intent(Add_Item.this,MainActivity.class);
            startActivity(main_screen);
        }
        return super.onKeyDown(keyCode, event);
    }
    public  void  scan(View view)
    {

        scan_view=new ZXingScannerView(this);
        scan_view.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scan_view);
        scan_view.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scan_view.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler
    {
        @Override
        public void handleResult(Result result) {

            //Toast.makeText(Add_Item.this,""+result.getText(),Toast.LENGTH_SHORT).show();

            mediaplayer.start();
            vibrator.vibrate(90);
            scan_view.stopCamera();
            Intent intent=new Intent(Add_Item.this,Update_Product_Details.class);
            intent.putExtra("PRODUCT_ID",result.getText());
            intent.putExtra("USERNAME",username);
            startActivity(intent);
        }
    }
}
