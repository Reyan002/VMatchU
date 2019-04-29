package com.example.vmatchu;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class EnterPropertyDetailActivity extends AppCompatActivity {

    private Button btn;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    Spinner spin;
    String spin_val;

    String[] proType = { "None","Agriculture/Dairy","Apartment/Flat","Banglow/House","Commercial Plot","Commercial Portion/Office Area","Farm House","Hotel","Industrial land","Industrial Plot" ,"Land","Penthouse","Plot","Plot File","Residential Lower Portion","Residential Upper Portion","Restuarent","Shop/Showroom","villa"};//array of strings used to populate the spinner

    TextInputEditText title,areaType;
    ArrayList<String> country=new ArrayList<>();
    ArrayList<String> city=new ArrayList<>();
    ArrayList<String> areaTypeArray=new ArrayList<>();
    SpinnerDialog spinnnerDialogue,spinnerDialog,DialogAreaType;

   String[] proStatus = { "For Rent","For Purchase" };//array of strings used to populate the spinner

    AutoCompleteTextView countrytxt,citytxt,areatxt,subareatxt,sectortxt;


    String[] tit={""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_property_detail);
        title=(TextInputEditText) findViewById(R.id.pro_title_ed) ;
        areaType=(TextInputEditText) findViewById(R.id.areaType_ed) ;
        countrytxt=(AutoCompleteTextView)findViewById(R.id.Country_ed) ;
        citytxt=(AutoCompleteTextView)findViewById(R.id.City_ed) ;
        areatxt=(AutoCompleteTextView)findViewById(R.id.Area_ed) ;
        subareatxt=(AutoCompleteTextView)findViewById(R.id.Subarea_ed) ;
        sectortxt=(AutoCompleteTextView)findViewById(R.id.sector_ed) ;
        country.add("Pakistan");
        country.add("India");
        country.add("Bangladesh");
        country.add("Iran");
        country.add("Iraq");
        country.add("Dubai");
        country.add("America");
        city.add("Karachi");
        city.add("lahore");
        city.add("Islamabad");
        city.add("Hyderabad");
        areaTypeArray.add("None");
        areaTypeArray.add("Acre");
        areaTypeArray.add("Kanal");
        areaTypeArray.add("Marla");
        areaTypeArray.add("Square Feet");
        areaTypeArray.add("Square Meter");
        areaTypeArray.add("Square Yard");
        spinnerDialog=new SpinnerDialog(this,country,"select Item");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                countrytxt.setText(item);
            }
        });
        countrytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              spinnerDialog.showSpinerDialog();
            }
        });
        spinnnerDialogue=new SpinnerDialog(this,city,"select Item");
        spinnnerDialogue.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                citytxt.setText(item);
            }
        });
        citytxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnnerDialogue.showSpinerDialog();
            }
        });


        DialogAreaType=new SpinnerDialog(this,areaTypeArray,"select Item");
        DialogAreaType.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                areaType.setText(item);
            }
        });
        areaType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAreaType.showSpinerDialog();
            }
        });

        btn = findViewById(R.id.btnImage);
        gvGallery = (GridView)findViewById(R.id.gv);
        spin = (Spinner) findViewById(R.id.type);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val = proType[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //setting array adaptors to spinners
        //ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
               //ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proType);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,proType
        );
        // setting adapteers to spinners
        spin.setAdapter(spinnerArrayAdapter);
        spin = (Spinner) findViewById(R.id.status);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val = proStatus[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //setting array adaptors to spinners
        //ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
     //   ArrayAdapter<String> spin_adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proStatus);
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,proStatus
        );
        // setting adapteers to spinners
        spin.setAdapter(spinnerArrayAdapter1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

