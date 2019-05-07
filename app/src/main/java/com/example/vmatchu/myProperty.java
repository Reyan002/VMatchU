package com.example.vmatchu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vmatchu.Adpaters.PropertyAdapter;
import com.example.vmatchu.Classes.PropertiesDetails;

import java.util.ArrayList;
import java.util.Properties;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class myProperty extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  String[] DropDet = {"","Deal Close","Edit","Delete","Pause Matching"};
    private ArrayList<String> status=new ArrayList<>();
    private ArrayList<String> title=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
   private String spin_val;
   private String[] proType={"All status","For Rent","For Sell","want Rent","want Buy"};

   SpinnerDialog spinnerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_property);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        status.add("For Rent");
        status.add("For Sell");
        status.add("Want Rent");
        status.add("Want Buy");
        status.add("All Status");

        title.add("Home");
        title.add("Shop");
        title.add("Hall");
        title.add("Office");
        title.add("None");



        ArrayList<PropertiesDetails> properties=properties();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.spinner_item,DropDet);
        recyclerView=(RecyclerView)findViewById(R.id.content_my_property);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new PropertyAdapter(arrayAdapter,this,properties);
        recyclerView.setAdapter(adapter);
   


        //Register a callback to be invoked when an item in this AdapterView has been selected





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(myProperty.this);
                View view1=getLayoutInflater().inflate(R.layout.filter,null);
                Spinner spinner=(Spinner) view1.findViewById(R.id.spinT);

                final EditText tittle_ed=(EditText) view1.findViewById(R.id.proTitle);

                builder.setView(view1);
                AlertDialog dialog=builder.create();
                dialog.show();

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long id) {

                        spin_val = proType[position];


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }

                });

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(myProperty.this, R.layout.spinner_item,proType);

                spinner.setAdapter(spinnerArrayAdapter);

                spinnerDialog=new SpinnerDialog(myProperty.this,title,"select Item");
                spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item, int position) {
                        tittle_ed.setText(item);
                    }
                });
                tittle_ed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinnerDialog.showSpinerDialog();
                    }
                });

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_submit) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<PropertiesDetails> properties(){
        ArrayList<PropertiesDetails> list =new ArrayList<>();

        list.add(new PropertiesDetails(0340,"https://www.google.com/search?q=house+for+sale+in+karachi&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjHx-Lyy_zhAhUDxYUKHdjjDw0Q_AUIDigB&biw=1366&bih=657#imgrc=tBn8Y1d11bfLuM:","House","Active","Karachi","For Sell","09-02-2019"));

        return list;

    }


}
