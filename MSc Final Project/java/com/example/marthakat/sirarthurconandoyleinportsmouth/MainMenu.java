package com.example.marthakat.sirarthurconandoyleinportsmouth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private CameraUpdate zoom;
    private SQLiteDatabase db;
    private Marker place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //create NavigationView element and value
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onBackPressed() {
        //this method is for closing the menu and return to the map
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //method created when the NavigationView.OnNavigationItemSelectedListener is implemented
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //for each category is clicked, the only locations which will be displayed are the ones which have the tags
        //that belong in the selected category
        if (id == R.id.books) {
            mMap.clear();
            Cursor books = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Society%'",null);
            int sum = books.getCount(); System.out.println("!!!!!sum-books: " + sum);
            if (books.moveToFirst() && books.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        books.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (books.moveToNext());
            }//end if
        } else if (id == R.id.conan) {
            mMap.clear();
            Cursor conan = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Writer%' OR Tags LIKE '%Doctor%' OR Tags LIKE '%Family%'",null);
            int sum = conan.getCount(); System.out.println("!!!!!sum: " + sum);
            if (conan.moveToFirst() && conan.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        conan.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (conan.moveToNext());
            }//end if
        } else if (id == R.id.groups) {
            mMap.clear();
            Cursor groups = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Friends%'",null);
            int sum = groups.getCount(); System.out.println("!!!!!sum-groups: " + sum);
            if (groups.moveToFirst() && groups.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        groups.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (groups.moveToNext());
            }//end if
        } else if (id == R.id.news) {
                mMap.clear();
                Cursor news = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Politics%'",null);
                int sum = news.getCount(); System.out.println("!!!!!sum-news: " + sum);
                if (news.moveToFirst() && news.getCount() != 0) {
                    Log.d("initView", "inside if -> do-while, for!");
                    do {
                        Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                        for (int i=0; i < sum; i++) {

                            news.moveToPosition(i);
                            cursor2.moveToPosition(i);
                            float lat = cursor2.getFloat(0);//position-column 0
                            float lng = cursor2.getFloat(1);//1
                            String loc = cursor2.getString(2);//2
                            LatLng marker = new LatLng(lat,lng);
                            place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                        }
                    } while (news.moveToNext());
                }//end if
            } else if (id == R.id.sherlock_holmes) {
                mMap.clear();
                Cursor holmes = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Sherlock%'",null);
                int sum = holmes.getCount(); System.out.println("!!!!!sum-holmes: " + sum);
                if (holmes.moveToFirst() && holmes.getCount() != 0) {
                    Log.d("initView", "inside if -> do-while, for!");
                    do {
                        Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                        for (int i=0; i < sum; i++) {

                            holmes.moveToPosition(i);
                            cursor2.moveToPosition(i);
                            float lat = cursor2.getFloat(0);//position-column 0
                            float lng = cursor2.getFloat(1);//1
                            String loc = cursor2.getString(2);//2
                            LatLng marker = new LatLng(lat,lng);
                            place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                        }
                    } while (holmes.moveToNext());
                }//end if
        } else if (id == R.id.spiritualism) {
            mMap.clear();
            Cursor spiritualism = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Spiritualism%'",null);
            int sum = spiritualism.getCount(); System.out.println("!!!!!sum-spiritualism: " + sum);
            if (spiritualism.moveToFirst() && spiritualism.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        spiritualism.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (spiritualism.moveToNext());
            }//end if
        } else if (id == R.id.sport) {
            mMap.clear();
            Cursor sport = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Sport%'",null);
            int sum = sport.getCount(); System.out.println("!!!!!sum-sport: " + sum);
            if (sport.moveToFirst() && sport.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        sport.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (sport.moveToNext());
            }//end if
        } else if (id == R.id.travel) {
            mMap.clear();
            Cursor travel = db.rawQuery(" SELECT * FROM portsmouth WHERE Tags LIKE '%Portsmouth%' OR Tags LIKE '%Southsea%'",null);
            int sum = travel.getCount(); System.out.println("!!!!!sum-travel: " + sum);
            if (travel.moveToFirst() && travel.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        travel.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (travel.moveToNext());
            }//end if
        } else if (id == R.id.everything) {
            Cursor cursor = db.rawQuery("SELECT * FROM portsmouth", null);
            int sum = cursor.getCount();System.out.println("!!!!!sum: " + sum);
            if (cursor.moveToFirst() && cursor.getCount() != 0) {
                Log.d("initView", "inside if -> do-while, for!");
                do {
                    Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                    for (int i=0; i < sum; i++) {

                        cursor.moveToPosition(i);
                        cursor2.moveToPosition(i);
                        float lat = cursor2.getFloat(0);//position-column 0
                        float lng = cursor2.getFloat(1);//1
                        String loc = cursor2.getString(2);//2
                        LatLng marker = new LatLng(lat,lng);
                        place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));

                    }
                } while (cursor.moveToNext());
            }//end if
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        //this method is called when the OnMapReadyCallback interface is called
        mMap = googleMap;

        //create an DataBaseHelper (class) attribute in order to create the connection with the database
                DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        myDbHelper.openDataBase();

        db = myDbHelper.getDatabase();
        //when the connection is installed, we use MYSQL queries to retrieve data
        Cursor cursor1 = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name='portsmouth'", null);
        Cursor cursor = db.rawQuery("SELECT * FROM portsmouth", null);
        int sum = cursor.getCount();
        System.out.println("sum: " + sum);

        if (cursor.moveToFirst() && cursor.getCount() != 0) {
            Log.d("initView", "inside if -> do-while, for!");
            do {
                //retrieve all the locations' data in order to create the markers on the map
                Cursor cursor2 = db.rawQuery("SELECT CoordinateA, CoordinateB, Location FROM portsmouth", null);
                for (int i=0; i < sum; i++) {

                    cursor.moveToPosition(i);
                    cursor2.moveToPosition(i);
                    float lat = cursor2.getFloat(0);//position-column 0
                    float lng = cursor2.getFloat(1);//1
                    String loc = cursor2.getString(2);//2
                    LatLng marker = new LatLng(lat,lng);
                    place = mMap.addMarker(new MarkerOptions().position(marker).title(loc));
                }
            } while (cursor.moveToNext());
        }//end if

        // Add a marker in Portsmouth and move the camera
        LatLng portsmouth = new LatLng(50.814266, -1.071873);
        zoom = CameraUpdateFactory.newLatLngZoom(portsmouth, 12);//zoom 12
        mMap.moveCamera(CameraUpdateFactory.newLatLng(portsmouth));
        mMap.animateCamera(zoom);

        googleMap.setOnMarkerClickListener(this);// define method

    }//onMapReady

    @Override
    public void onInfoWindowClick(Marker marker) {
        //this method is implemented when the GoogleMap.OnInfoWindowClickListener is called
        //not used in the development phase
//        Toast.makeText(this, "Info window clicked",
//                Toast.LENGTH_LONG).show();
    }//end

    @Override
    public boolean onMarkerClick(Marker marker) {

        //this method is implemented when the GoogleMap.OnMarkerClickListener is called
        final String loc = marker.getTitle();

        /*when a marker is clicked an alert window will pop up asking a question which is set below
        two buttons are created and they are called "Yes" and "No"
        according to the answer, different actions will occur*/
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainMenu.this);
        builder1.setMessage("See Info?");//the question
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String still = null, add = null,date = null,info = null,tags = null;

                        Cursor cursor = db.rawQuery("SELECT * FROM portsmouth", null);
                        int sum = cursor.getCount();
                        if (cursor.moveToFirst() && cursor.getCount() != 0) {
                            Log.d("initView", "inside if -> do-while, for!");
                            do {
                                Cursor cursor2 =  db.rawQuery("SELECT Date, Information, Tags FROM portsmouth WHERE Location = '" + loc + "'", null);

                                cursor2.moveToPosition(0);

                                date = cursor2.getString(0);
                                info = cursor2.getString(1);
                                tags = cursor2.getString(2);

                                /*due to the fact that I changed the name of two columns (Still Standing & Additional info)
                                there was a problem with retrieving the information for these two columns when I used their names
                                        in the query above, so I used the code below.
                                        I run the for loop for all the locations until it finds the selected one and then retrieve
                                        the data for these two columns*/
                                for (int i=0; i < sum; i++) {
                                    cursor.moveToPosition(i);
                                    if(cursor.getString(0).equals(loc)){
                                        still = cursor.getString(1);
                                        add = cursor.getString(2);
                                        System.out.println("Let's try: " + "\n Still: " + still + "\n Addi: " + add);
                                    }
                                }
                            } while (cursor.moveToNext());
                        }//end if

                        System.out.println("Loc: " + loc + "\n" + "\n Date: " + date
                                + "\n Info: " + info + "\n  Tags: " + tags);
//                        System.out.println("Loc: " + loc + "\n" + "  Still: " + still + "\n AddI: " + add + "\n Date: " + date
//                                + "\n Info: " + info + "\n  Tags: " + tags);

                        /*we keep the data for the specific location in order to use them when the Info Details class
                        is called in order to be able to use them*/
                        Intent intent = new Intent(MainMenu.this, InfoDetails.class);
                        intent.putExtra("loc",loc);
                        intent.putExtra("still",still);
                        intent.putExtra("addi",add);
                        intent.putExtra("date",date);
                        intent.putExtra("info",info);
                        intent.putExtra("tags",tags);
                        Toast.makeText(MainMenu.this, "Loading...",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        dialog.cancel();
                        }
                }

        );

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(MainMenu.this, "no",Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

                    AlertDialog alert11 = builder1.create();
        alert11.show();

        return false;
    }

}