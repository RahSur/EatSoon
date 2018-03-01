package com.example.android.eat_soon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QR_Generator extends AppCompatActivity {
    TextView txt_id,txt_name,txt_desc;
    EditText edt_id,edt_name,edt_desc;
    DatePicker datepicker;
    Button generate;

    String id,name,desc,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__generator);

        txt_id=(TextView)findViewById(R.id.text_view_enter_id);
        txt_name=(TextView)findViewById(R.id.text_view_enter_name);
        txt_desc=(TextView)findViewById(R.id.text_view_enter_desc);
        datepicker=(DatePicker)findViewById(R.id.calender_view);
        generate=(Button)findViewById(R.id.generate);
        edt_id=(EditText)findViewById(R.id.edit_text_enter_id);
        edt_name=(EditText)findViewById(R.id.edit_text_enter_name);
        edt_desc=(EditText)findViewById(R.id.edit_text_enter_desc);

        edt_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_id.setVisibility(View.VISIBLE);
            }
        });
        edt_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_name.setVisibility(View.VISIBLE);
            }
        });

        edt_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_desc.setVisibility(View.VISIBLE);
            }
        });


    }
    public  void generate(View view)
    {
        id=edt_id.getText().toString().trim();
        name=edt_name.getText().toString().trim();
        desc=edt_desc.getText().toString().trim();



        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth() ;
        int year = datepicker.getYear()-1900;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);

        Intent nxt=new Intent(QR_Generator.this,Generated_QR.class);
        nxt.putExtra("QR_DETAILS",id+"_"+name+"_"+desc+"_"+strDate);
        startActivity(nxt);

    }
}
