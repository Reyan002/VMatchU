package com.example.vmatchu;

import android.app.ProgressDialog;
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

import com.example.vmatchu.CustomAlert.CustomAlert;
import com.example.vmatchu.DBhelper.DBhelper;
import com.example.vmatchu.Pojo.AreaResponse;
import com.example.vmatchu.Pojo.AreaTypeResponse;
import com.example.vmatchu.Pojo.CityResponse;
import com.example.vmatchu.Pojo.InsertPropertyResponse;
import com.example.vmatchu.Pojo.SectorResponse;
import com.example.vmatchu.Pojo.SubAreaResponse;
import com.example.vmatchu.Pojo.UserSignup;
import com.example.vmatchu.Rest.APIService;
import com.example.vmatchu.Rest.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPropertyDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    Spinner spinType;
    Spinner spinStatus;
    String spin_val_status;
    String spin_val_type;
    Button submit;
    APIService apiService;
    ProgressDialog progressDialog;

    String[] proType = { "None","Agriculture/Dairy","Apartment/Flat","Banglow/House","Commercial Plot","Commercial Portion/Office Area","Farm House","Hotel","Industrial land","Industrial Plot" ,"Land","Penthouse","Plot","Plot File","Residential Lower Portion","Residential Upper Portion","Restuarent","Shop/Showroom","villa"};//array of strings used to populate the spinner

    TextInputEditText title,areaType;
    ArrayList<String> country=new ArrayList<>();
    ArrayList<String> city=new ArrayList<>();
    ArrayList<String> areaTypeArray=new ArrayList<>();
    SpinnerDialog spinnnerDialogue,spinnerDialog,DialogAreaType;

    DBhelper dBhelper;

   String[] proStatus = { "For Rent","For Purchase" };//array of strings used to populate the spinner

    AutoCompleteTextView countrytxt,citytxt,areatxt,subareatxt,sectortxt;
    TextInputEditText price,size,rooms,bedroom,bathroom,garages,details,video_url,image360_url;

    String[] tit={""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_property_detail);

        initialize();

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


        DialogAreaType=new SpinnerDialog(this,areaTypeArray,"select Item");
        DialogAreaType.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                areaType.setText(item);
            }
        });
//        areaType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogAreaType.showSpinerDialog();
//            }
//        });

        btn = findViewById(R.id.btnImage);
        gvGallery = (GridView)findViewById(R.id.gv);
        spinType = (Spinner) findViewById(R.id.type);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val_type = proType[position];//saving the value selected


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
        spinType.setAdapter(spinnerArrayAdapter);
        spinStatus = (Spinner) findViewById(R.id.status);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val_status = proStatus[position];//saving the value selected


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
        spinStatus.setAdapter(spinnerArrayAdapter1);

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

    private void getCitiesApi() {
        Call<CityResponse> call = apiService.getCities();

        call.enqueue(new Callback<CityResponse>() {

            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if(response.isSuccessful()) {
                    CityResponse cityResponse = response.body();
                    if(cityResponse.getError().equals("-1")){
                        for(int i=0; i < cityResponse.getCity().size(); i++){
                            dBhelper.addCities(cityResponse.getCity().get(i).getTermId(),
                                    cityResponse.getCity().get(i).getName());
                        }
                        progressDialog.dismiss();
                    }else{
                        CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Cities Not Fetched");
                    }
                    Log.i("response", "post submitted to API." + cityResponse);
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                progressDialog.dismiss();
                CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Response Failed");
                Log.e("response_Failed", "Unable to submit post to API." + t);
            }
        });
    }

    private void initialize() {
        title=(TextInputEditText) findViewById(R.id.pro_title_ed) ;
//        areaType=(TextInputEditText) findViewById(R.id.areaType_ed) ;
        countrytxt=(AutoCompleteTextView)findViewById(R.id.Country_ed) ;
        citytxt=(AutoCompleteTextView)findViewById(R.id.City_ed) ;
        areatxt=(AutoCompleteTextView)findViewById(R.id.Area_ed) ;
        subareatxt=(AutoCompleteTextView)findViewById(R.id.Subarea_ed) ;
        sectortxt=(AutoCompleteTextView)findViewById(R.id.sector_ed) ;
        price=(TextInputEditText)findViewById(R.id.price_ed) ;
        size=(TextInputEditText)findViewById(R.id.size_ed) ;
        areaType=(TextInputEditText)findViewById(R.id.areaType_ed) ;
        rooms=(TextInputEditText)findViewById(R.id.rooms_ed) ;
        bedroom = (TextInputEditText)findViewById(R.id.bedrooms_ed);
        bathroom = (TextInputEditText)findViewById(R.id.bathroom_ed);
        garages = (TextInputEditText)findViewById(R.id.garages_ed);
        details = (TextInputEditText)findViewById(R.id.desc_ed);
        video_url = (TextInputEditText)findViewById(R.id.vedioURL_ed);
        image360_url = (TextInputEditText)findViewById(R.id.image360_ed);
        dBhelper = new DBhelper(this);
        submit=findViewById(R.id.submitProp) ;

        submit.setOnClickListener(this);
        citytxt.setOnClickListener(this);
        areatxt.setOnClickListener(this);
        subareatxt.setOnClickListener(this);
        sectortxt.setOnClickListener(this);
        areaType.setOnClickListener(this);
//        submit.setOnClickListener(this);



        progressDialog = new ProgressDialog(EnterPropertyDetailActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        apiService = ApiUtil.getAPIService();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitProp:
                progressDialog.show();
                postPropertyDetails();
                break;

            case R.id.City_ed:
                progressDialog.show();
                getCitiesApi();
                spinnnerDialogue.showSpinerDialog();
                break;

            case R.id.Area_ed:
                progressDialog.show();
                getAreaApi();
                spinnnerDialogue.showSpinerDialog();
                break;

            case R.id.Subarea_ed:
                progressDialog.show();
                getSubAreaApi();
                spinnnerDialogue.showSpinerDialog();
                break;

            case R.id.sector_ed:
                progressDialog.show();
                getSectorsApi();
                spinnnerDialogue.showSpinerDialog();
                break;

            case R.id.areaType_ed:
                progressDialog.show();
                getAreaTypeApi();
                spinnnerDialogue.showSpinerDialog();
                break;

//            case R.id.status:
//                progressDialog.show();
//                getCitiesApi();
//                spinnnerDialogue.showSpinerDialog();
//                break;


        }
    }

    private void getAreaTypeApi() {
        Call<AreaTypeResponse> call = apiService.getAreaType();

        call.enqueue(new Callback<AreaTypeResponse>() {

            @Override
            public void onResponse(Call<AreaTypeResponse> call, Response<AreaTypeResponse> response) {
                if(response.isSuccessful()) {
                    AreaTypeResponse areaTypeResponse = response.body();
                    if(areaTypeResponse.getError().equals("-1")){
                        for(int i=0; i < areaTypeResponse.getAreaType().size(); i++){
                            dBhelper.addArea(areaTypeResponse.getAreaType().get(i).getTermId(),
                                    areaTypeResponse.getAreaType().get(i).getName());
                        }
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Area Type Not Fetched");
                    }
                    Log.i("response", "post submitted to API." + areaTypeResponse);
                }
            }

            @Override
            public void onFailure(Call<AreaTypeResponse> call, Throwable t) {
                progressDialog.dismiss();
                CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Response Failed");
                Log.e("response_Failed", "Unable to submit post to API." + t);
            }
        });
    }

    private void getSectorsApi() {
        Call<SectorResponse> call = apiService.getSector();

        call.enqueue(new Callback<SectorResponse>() {

            @Override
            public void onResponse(Call<SectorResponse> call, Response<SectorResponse> response) {
                if(response.isSuccessful()) {
                    SectorResponse subAreaResponse = response.body();
                    if(subAreaResponse.getError().equals("-1")){
                        for(int i=0; i < subAreaResponse.getSectors().size(); i++){
                            dBhelper.addArea(subAreaResponse.getSectors().get(i).getTermId(),
                                    subAreaResponse.getSectors().get(i).getName());
                        }
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Sectors Not Fetched");
                    }
                    Log.i("response", "post submitted to API." + subAreaResponse);
                }
            }

            @Override
            public void onFailure(Call<SectorResponse> call, Throwable t) {
                progressDialog.dismiss();
                CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Response Failed");
                Log.e("response_Failed", "Unable to submit post to API." + t);
            }
        });
    }

    private void getSubAreaApi() {
        Call<SubAreaResponse> call = apiService.getSubArea();

        call.enqueue(new Callback<SubAreaResponse>() {

            @Override
            public void onResponse(Call<SubAreaResponse> call, Response<SubAreaResponse> response) {
                if(response.isSuccessful()) {
                    SubAreaResponse subAreaResponse = response.body();
                    if(subAreaResponse.getError().equals("-1")){
                        for(int i=0; i < subAreaResponse.getSubAreas().size(); i++){
                            dBhelper.addArea(subAreaResponse.getSubAreas().get(i).getTermId(),
                                    subAreaResponse.getSubAreas().get(i).getName());
                        }
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Sub Areas Not Fetched");
                    }
                    Log.i("response", "post submitted to API." + subAreaResponse);
                }
            }

            @Override
            public void onFailure(Call<SubAreaResponse> call, Throwable t) {
                progressDialog.dismiss();
                CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Response Failed");
                Log.e("response_Failed", "Unable to submit post to API." + t);
            }
        });
    }

    private void getAreaApi() {
        Call<AreaResponse> call = apiService.getArea();

        call.enqueue(new Callback<AreaResponse>() {

            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if(response.isSuccessful()) {
                    AreaResponse areaResponse = response.body();
                    if(areaResponse.getError().equals("-1")){
                        for(int i=0; i < areaResponse.getAreas().size(); i++){
                            dBhelper.addArea(areaResponse.getAreas().get(i).getTermId(),
                                    areaResponse.getAreas().get(i).getName());
                        }
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Areas Not Fetched");
                    }
                    Log.i("response", "post submitted to API." + areaResponse);
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                progressDialog.dismiss();
                CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Response Failed");
                Log.e("response_Failed", "Unable to submit post to API." + t);
            }
        });
    }

    private void postPropertyDetails() {
        Call<InsertPropertyResponse> call = apiService.postInsertSellProperty(title.getText().toString(),"1", spin_val_type, spin_val_status,
                countrytxt.getText().toString(),citytxt.getText().toString(),areatxt.getText().toString(),subareatxt.getText().toString(),sectortxt.getText().toString(),
                price.getText().toString(),size.getText().toString(),areaType.getText().toString(),rooms.getText().toString(),
                bedroom.getText().toString(),bathroom.getText().toString(),garages.getText().toString(),details.getText().toString(),
                "image",video_url.getText().toString(),image360_url.getText().toString());

        call.enqueue(new Callback<InsertPropertyResponse>() {

            @Override
            public void onResponse(Call<InsertPropertyResponse> call, Response<InsertPropertyResponse> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    InsertPropertyResponse insertResponse = response.body();
                    if(insertResponse.getError().equals("-1")){

                        CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Your Property Has Been Inserted !");

                    }
                    Log.i("response", "post submitted to API." + insertResponse);
                }
            }

            @Override
            public void onFailure(Call<InsertPropertyResponse> call, Throwable t) {
                progressDialog.dismiss();
                CustomAlert.alertDialog(EnterPropertyDetailActivity.this,"Property Inserted");
                Log.e("response_Failed", "Unable to submit post to API." + t);
            }
        });
    }
}

