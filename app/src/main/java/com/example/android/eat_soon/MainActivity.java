package com.example.android.eat_soon;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {


    private AdView mAdView;
    MediaPlayer mediaplayer;


    DrawerLayout drawer;
    ActionBarDrawerToggle abdt;
    NavigationView nav_view;
    DatabaseReference Database_reference;
    ListView lst;
    VideoView videoview;
    CustomAdapter custom;

    public String username;

    String id_arr;
    String name_arr;
    String date_arr;
    String desc_arr;
    String type_arr;
    String environment_arr;

    ArrayList<String> full=new ArrayList<String>();
    ArrayList<String> deleted_id=new ArrayList<String>();
    ArrayList<String> dummy=new ArrayList<String>();
    ArrayList<String> deleted_name=new ArrayList<String>();
    ArrayList<String> deleted_desc=new ArrayList<String>();
    ArrayList<String> id=new ArrayList<String>();
    ArrayList<String> name=new ArrayList<String>();
    ArrayList<String> expiry_date=new ArrayList<String>();
    ArrayList<String> desc=new ArrayList<String>();
    ArrayList<String> type=new ArrayList<String>();
    ArrayList<String> environment=new ArrayList<String>();
    ArrayList<String> url=new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username=getIntent().getStringExtra("USERNAME");

        videoview=(VideoView)findViewById(R.id.video_view);
        mediaplayer=MediaPlayer.create(this,R.raw.beep);

        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoview.start();
                mediaplayer.start();
                click();



//                Database_reference = FirebaseDatabase.getInstance().getReference().child(id_arr+"_"+name_arr+"_"+date_arr+"_"+desc_arr+"_"+type_arr+"_"+environment_arr);
//
//                Database_reference.removeValue();
            }
        });


        videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.into_video));
        videoview.requestFocus();
        videoview.start();

//        arr.add("Hello");
//        arr.add("sundeep");
        lst=(ListView) findViewById(R.id.list_view);
        custom=new CustomAdapter();

        lst.setAdapter(custom);

//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        Database_reference= FirebaseDatabase.getInstance().getReference().child("PRODUCT FULLDETAILS");


        Database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String value=child.getValue(String.class);

                    full.add(value);


                    lst=(ListView) findViewById(R.id.list_view);
                    CustomAdapter custom=new CustomAdapter();

                    lst.setAdapter(custom);




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });


//        for (int i=1;i<full.size();i++)
//        {
//            String strfull =full.get(i);
//            String[] outputfull = strfull.split("_");
//            String id_arr = outputfull[0];
//            String name_arr = outputfull[1];
//            String date_arr = outputfull[2];
//            String desc_arr = outputfull[3];
//            String type_arr = outputfull[4];
//            String environment_arr = outputfull[5];
//
//            if(deleted_id.contains(id_arr)&&deleted_name.contains(name_arr)&&deleted_desc.contains(desc_arr))
//            {
//                full.remove(i);
//
//            }
//            else
//            {
//
//            }
//
//        }










        drawer=(DrawerLayout)findViewById(R.id.drawer);
        abdt= new ActionBarDrawerToggle(this,drawer,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        drawer.addDrawerListener(abdt);
        abdt.syncState();








        nav_view=(NavigationView) findViewById(R.id.navigation_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id== R.id.about_us)
                {
                    Toast.makeText(MainActivity.this,"An Application from ITERATORS",Toast.LENGTH_SHORT).show();
                }
                if(id== R.id.profile)
                {
                    Intent main_screen1=new Intent(MainActivity.this,Credits.class);
                    main_screen1.putExtra("USERNAME",username);
                    startActivity(main_screen1);

                }
                if(id== R.id.Purchase_Products)
                {
                    Intent main_screen2=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(main_screen2);
                }
                if(id== R.id.invite)
                {
                    Intent main_screen3=new Intent(MainActivity.this,Invite.class);
                    startActivity(main_screen3);
                }
                if(id== R.id.log_out)
                {
                    Intent main_screen3=new Intent(MainActivity.this,Login.class);
                    startActivity(main_screen3);
                }

                return true;
            }
        });







        lst=(ListView) findViewById(R.id.list_view);
        custom=new CustomAdapter();

        lst.setAdapter(custom);



        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(MainActivity.this,""+full.get(i),Toast.LENGTH_SHORT).show();

                Intent next=new Intent(MainActivity.this,Item_Details.class);
                next.putExtra("VALUE",full.get(i));
                startActivity(next);

            }
        });






    }//oncreate------------------------------


    public void click()
    {

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Nasivion is about to Expire")
                .setContentText("Buy the product using EatSoon");
                //.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
    }



    public void add(View view)
    {
        Intent nxt = new Intent(MainActivity.this,Add_Item.class);
        nxt.putExtra("USERNAME",username);
        startActivity(nxt);
    }


    public void scan_screen(View view)
    {
        Intent main_screen=new Intent(MainActivity.this,Add_Item.class);
        startActivity(main_screen);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) ||  super.onOptionsItemSelected(item);
    }
    public void open(View view)
    {
        drawer.openDrawer(nav_view);
    }





    class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {

            return full.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {



            view=getLayoutInflater().inflate(R.layout.custom_row,null);

            TextView idt=(TextView)view.findViewById(R.id.product_id);
            TextView namet=(TextView)view.findViewById(R.id.product_name);
            TextView expirydatet=(TextView)view.findViewById(R.id.expiry_status);
            TextView desct=(TextView)view.findViewById(R.id.product_desc);
            ImageView image=(ImageView) view.findViewById(R.id.image_view_custom);
            ImageView environmentt=(ImageView) view.findViewById(R.id.product_environment_image);




            String strfull =full.get(i);
            String[] outputfull = strfull.split("_");
            id_arr = outputfull[0];
             name_arr = outputfull[1];
            date_arr = outputfull[2];
            desc_arr = outputfull[3];
            type_arr = outputfull[4];
            String environment_arr = outputfull[5];






                    idt.setText(id_arr);
                    namet.setText(name_arr);
                    expirydatet.setText(date_arr);
                    desct.setText(desc_arr);

                    if (environment_arr.equals("true"))
                    {
                        environmentt.setImageResource(R.drawable.cool);
                    }
                    else
                    {
                        environmentt.setImageResource(R.drawable.sunny);
                    }


                    if(type_arr.equals("Dairy Products"))
                    {
                        image.setImageResource(R.drawable.dairy);
                    }
                    else if (type_arr.equals("Fruits"))
                    {
                        image.setImageResource(R.drawable.fruits);
                    }
                    else if (type_arr.equals("Vegetables"))
                    {
                        image.setImageResource(R.drawable.vegetables);

                    }
                    else if (type_arr.equals("Medicines"))
                    {
                        image.setImageResource(R.drawable.med);

                    }
                    else if (type_arr.equals("Eggs"))
                    {
                        image.setImageResource(R.drawable.eggs);

                    }
                    else if (type_arr.equals("Juice"))
                    {
                        image.setImageResource(R.drawable.juice);

                    }
                    else if (type_arr.equals("Food Item"))
                    {
                        image.setImageResource(R.drawable.food);

                    }
                    else if (type_arr.equals("Milk"))
                    {
                        image.setImageResource(R.drawable.milk);

                    }
                    else if (type_arr.equals("Snacks"))
                    {
                        image.setImageResource(R.drawable.snacks);

                    }
                    else if (type_arr.equals("Sweets"))
                    {
                        image.setImageResource(R.drawable.sweets);

                    }
                    else
                    {
                        image.setImageResource(R.drawable.others);
                    }



              //  }







         return view;

        }


    }
}
