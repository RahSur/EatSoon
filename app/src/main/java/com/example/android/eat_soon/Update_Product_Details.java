package com.example.android.eat_soon;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Update_Product_Details extends AppCompatActivity  {


    public String value_from_before_screen_product_id,product_id_string,product_name_string,product_description_string,product_expirydate_string,product_type_string;
    Boolean product_environment_boolean;
    EditText product_id,product_name,product_description;
    DatePicker datepicker;
    Spinner product_type;
    Switch product_environment;
    ImageView add_photo;

    TextView expirydate;
    String product_picture_url="https://firebasestorage.googleapis.com/v0/b/eatsoon-101c5.appspot.com/o/PHOTO%2Fno_image.png?alt=media&token=6ac1a6cc-643d-425a-9fc5-d7416d7e4bec";

    static final int GALLERY_INTENT=2;
    private ProgressDialog mprogress;
    DatabaseReference database_reference;
    AlertDialog.Builder connectivity_alert;
    private boolean connection;
    StorageReference storage_reference;
    String username;

    String random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__product__details);

        Random rand = new Random();
        int value = rand.nextInt(10000);
        random=Integer.toString(value);

        username=getIntent().getStringExtra("USERNAME");





        value_from_before_screen_product_id=getIntent().getStringExtra("PRODUCT_ID");

        expirydate=(TextView)findViewById(R.id.expiry_date);

        product_id=(EditText) findViewById(R.id.product_id_edittext);
        product_name=(EditText) findViewById(R.id.product_name_edittext);
        product_description=(EditText) findViewById(R.id.product_desc_edittext);
        product_id.setText(value_from_before_screen_product_id);
        datepicker = (DatePicker) findViewById(R.id.calender_view);
        product_type=(Spinner)findViewById(R.id.product_type_spinner);
        product_environment=(Switch) findViewById(R.id.product_environment_switch);
        add_photo=(ImageView) findViewById(R.id.insert_image);

        mprogress=new ProgressDialog(this);


        storage_reference= FirebaseStorage.getInstance().getReference();

        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectivity_alert=new AlertDialog.Builder(Update_Product_Details.this);
                connectivity_alert.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 1);
                    }
                }).setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 1);
                    }
                }).setTitle("IMAGE PICKER").create();
                connectivity_alert.show();
            }
        });



        if(value_from_before_screen_product_id.equals("8901089013429"))
        {
            product_id.setText("8901089013429");
            product_name.setText("NASIVION");
            product_description.setText("Relief from blocked nose.");
            datepicker.setVisibility(View.INVISIBLE);
            expirydate.setText("Expity Date       12-02-2019");
            Picasso.with(Update_Product_Details.this).load("https://firebasestorage.googleapis.com/v0/b/eatsoon-101c5.appspot.com/o/PHOTO%2Fnasivion.png?alt=media&token=5087d208-2d9c-4df5-8422-256aa76e0e0f").fit().centerCrop().into(add_photo);


        }

    }  //oncreate------------





    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Toast.makeText(Update_Product_Details.this,"Back Prohibitted",Toast.LENGTH_SHORT).show();
            Intent nxt=new Intent(Update_Product_Details.this,Add_Item.class);
            startActivity(nxt);
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
//                if(resultCode == RESULT_OK){
//                    Uri selectedImage = imageReturnedIntent.getData();
//                  //  add_photo.setImageURI(selectedImage);
//                   // add_photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                    mprogress.setMessage("Uploading...");
//
//                    mprogress.show();
//                    StorageReference file_path=storage_reference.child("PHOTO").child(selectedImage.getLastPathSegment());
//                    file_path.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            mprogress.dismiss();
//                            Uri download_url=taskSnapshot.getDownloadUrl();
//                            product_picture_url=download_url.toString();
//                            Picasso.with(Update_Product_Details.this).load(download_url).fit().centerCrop().into(add_photo);
//                        }
//                    });
//                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    // add_photo.setImageURI(selectedImage);
                    // add_photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    mprogress.setMessage("Uploading...");

                    mprogress.show();
                    StorageReference file_path=storage_reference.child("PHOTO").child(selectedImage.getLastPathSegment());
                    file_path.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mprogress.dismiss();
                            Uri download_url=taskSnapshot.getDownloadUrl();
                            product_picture_url=download_url.toString();
                            Picasso.with(Update_Product_Details.this).load(download_url).fit().centerCrop().into(add_photo);
                        }
                    });
                }
                break;
        }
    }


    public void track(View view) {

        product_id_string=product_id.getText().toString().trim().replaceAll("\\s+","");;
        product_name_string=product_name.getText().toString().trim().toUpperCase();
        product_description_string=product_description.getText().toString().trim();
        product_type_string=product_type.getSelectedItem().toString();
        product_environment_boolean=product_environment.isChecked();


        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth() ;
        int year = datepicker.getYear()-1900;
         SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date(year, month, day);
        String strDate = dateFormatter.format(d);
       // Toast.makeText(Update_Product_Details.this,""+strDate,Toast.LENGTH_SHORT).show();

        if(TextUtils.isEmpty(product_name_string))
        {
            Toast.makeText(Update_Product_Details.this,"kindly dont leave Product Name Empty!",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(product_description_string))
        {
            Toast.makeText(Update_Product_Details.this,"kindly dont leave Product Description Empty!",Toast.LENGTH_SHORT).show();
        }
        else
        {

            try
            {
                String xx="Sundeep";
//            database_reference = FirebaseDatabase.getInstance().getReference(xx).child(product_id_string);
//            database_reference.child("PRODUCT_ID").setValue(product_id_string);
//            database_reference.child("PRODUCT_NAME").setValue(product_name_string);
//            database_reference.child("PRODUCT_EXPIRYDATE").setValue(strDate);
//            database_reference.child("PRODUCT_DESC").setValue(product_description_string);
//            database_reference.child("PRODUCT_TYPE").setValue(product_type_string);
//            database_reference.child("PRODUCT_ENVIRONMENT").setValue(product_environment_boolean);
//            database_reference.child("PRODUCT_PICTURE").setValue(product_picture_url);

//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_id");//.child("PRODUCT_ID");
//            database_reference.child(product_id_string+" PRODUCT_ID").setValue(product_id_string);
//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_names");//.child("PRODUCT_ID");
//            database_reference.child(product_name_string).setValue(product_name_string);
//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_expirydates");//.child("PRODUCT_ID");
//            database_reference.child(product_id_string+" PRODUCT_EXPIRYDATE").setValue(strDate);
//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_desc");//.child("PRODUCT_ID");
//            database_reference.child(product_id_string+" PRODUCT_DESC").setValue(product_description_string);
//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_type");//.child("PRODUCT_ID");
//            database_reference.child(product_id_string+" PRODUCT_TYPE").setValue(product_type_string);
//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_environment");//.child("PRODUCT_ID");
//            database_reference.child(product_id_string+" PRODUCT_ENVIRONMENT").setValue(product_environment_boolean);
//            database_reference = FirebaseDatabase.getInstance().getReference(xx+"_product_picture");//.child("PRODUCT_ID");
//            database_reference.child(product_id_string+" PRODUCT_PICTURE").setValue(product_picture_url);

//            database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT ID");
//            database_reference.child(product_id_string+"_"+product_id_string).setValue(product_id_string+"_"+product_id_string);
//            database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT NAME");
//            database_reference.child(product_name_string+"_"+product_id_string).setValue(product_name_string+"_"+product_id_string);
//            database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT EXPIRY DATE");
//            database_reference.child(strDate+"_"+product_id_string).setValue(strDate);
//            database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT DESC");
//            database_reference.child(product_description_string+"_"+product_id_string).setValue(product_description_string+"_"+product_id_string);
//            database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT TYPE");
//            database_reference.child(product_type_string+"_"+product_id_string).setValue(product_type_string+"_"+product_id_string);
//            database_reference = FirebaseDatabase.getInstance().getReference("PRODUCT ENVIRONMENT");
//            database_reference.child(product_environment_boolean+"_"+product_id_string).setValue(product_environment_boolean+"_"+product_id_string);


                database_reference = FirebaseDatabase.getInstance().getReference(product_id_string+"PRODUCT PICTURE");
                database_reference.child(product_id_string+"_URL").setValue(product_picture_url);

               // database_reference = FirebaseDatabase.getInstance().getReference(username).child("PRODUCT FULLDETAILS");
               // database_reference.child(product_id_string+"_"+product_name_string+"_"+strDate+"_"+product_description_string+"_"+product_type_string+"_"+product_environment_boolean).setValue(product_id_string+"_"+product_name_string+"_"+strDate+"_"+product_description_string+"_"+product_type_string+"_"+product_environment_boolean);


                database_reference = FirebaseDatabase.getInstance().getReference().child("PRODUCT FULLDETAILS");
                database_reference.child(product_id_string+"_"+product_name_string+"_"+strDate+"_"+product_description_string+"_"+product_type_string+"_"+product_environment_boolean).setValue(product_id_string+"_"+product_name_string+"_"+strDate+"_"+product_description_string+"_"+product_type_string+"_"+product_environment_boolean);


                connectivity_alert=new AlertDialog.Builder(Update_Product_Details.this);
                connectivity_alert.setTitle("DONE").setIcon(R.drawable.eatsoon_ok)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent main_screen=new Intent(Update_Product_Details.this,MainActivity.class);
//                                startActivity(main_screen);
                                dialogInterface.dismiss();
                            }
                        }).create();
                connectivity_alert.show();

            }
            catch (Exception e)
            {
                Toast.makeText(Update_Product_Details.this,"Sorry for Inconvenience! Server Busy-Try Again",Toast.LENGTH_SHORT).show();
            }



        }










    }




}
