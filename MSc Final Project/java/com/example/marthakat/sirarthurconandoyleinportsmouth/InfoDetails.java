package com.example.marthakat.sirarthurconandoyleinportsmouth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class InfoDetails extends AppCompatActivity implements View.OnClickListener{

    //this class is called when the user wishes to read more information about the place he has chosen
    private String location = null, still = null, addi = null, date = null, info = null, tags = null;

    private TextView address, addinfo, dt, information, tag;
    private CheckBox yes, no;
    private ImageView imv1;
    private ViewFlipper vfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        System.out.println("---------------InfoDetails--------------------");

        //data kept from the previous class (Main Menu)
        Intent in = getIntent();
        location = in.getStringExtra("loc");//address
        still = in.getStringExtra("still");//still standing Y|N
        addi = in.getStringExtra("addi");//additional info
        date = in.getStringExtra("date");//date
        info = in.getStringExtra("info");//information
        tags = in.getStringExtra("tags");//tags

        //making sure the data have been retrieved successfully
        System.out.println("Loc: " + location + "\n" + "  Still: " + still + "\n AddI: " + addi + "\n Date: " + date
                + "\n Info: " + info + "\n  Tags: " + tags);

        //create elements and set value for every textView in order to add the values and display them
        address = (TextView) findViewById(R.id.address);
        address.setText(location);

        addinfo = (TextView) findViewById(R.id.addi);
        addinfo.setText(addi);

        dt = (TextView) findViewById(R.id.date);
        dt.setText(date);

        information = (TextView) findViewById(R.id.info);
        information.setText(info);

        tag = (TextView) findViewById(R.id.tag);
        tag.setText(tags);

        //two CheckBoxes were created in order to show if the the building in the specific location is still standing or not
        yes = (CheckBox) findViewById(R.id.yes);

        no  = (CheckBox) findViewById(R.id.no);

        //first, it is essential check if the attribute is string is null in order to avoid the Null Exception
        // because it is an information which is not known for every location
        //then we click the checkbox according to the answer
        if(still != null)
        {
            if(still.equals("Y")) {
                yes.setChecked(true);
                no.setChecked(false);
            } else if (still.equals("N")){
                yes.setChecked(false);
                no.setChecked(true);
            }
        } else {
            yes.setChecked(false);
            no.setChecked(true);
        }

        //we create an element in order to create a slideshow to display the images for each location
        vfl = (ViewFlipper) this.findViewById(R.id.viewflipper);
        vfl.setOnClickListener(this);

        imv1 = (ImageView) findViewById(R.id.imVF);

        //however, right now there are few photos available so we check if the chosen location is one of the locations used below
        //if yes, then the photo available for this location will be displayed
        if(location.equals("Bush Villa")) {
            imv1.setImageResource(R.drawable.bushvilla_w);
        } else if(location.equals("Orontes, ship docked at Portsmouth Dockyard")){
            imv1.setImageResource(R.drawable.dockyard_w);
        } else if(location.equals("George Hotel")){
            imv1.setImageResource(R.drawable.george_hotel_w);
        } else if(location.equals("31 Green Road")){
            imv1.setImageResource(R.drawable.green_road_w);
        } else if(location.equals("Elmwood, Owen House")){
            imv1.setImageResource(R.drawable.grove_road_for_elmwoodhouse_w);
        } else if(location.equals("Grosvenor Hotel")){
            imv1.setImageResource(R.drawable.grovesnor_hotel_w);
        } else if(location.equals("Guildhall (Old)")){
            imv1.setImageResource(R.drawable.guildhall_w);
        } else if(location.equals("Guildhall (New)")){
            imv1.setImageResource(R.drawable.guildhall_w);
        } else if(location.equals("14 High Street")){
            imv1.setImageResource(R.drawable.high_street_w);
        } else if(location.equals("46 Portsmouth High Street")){
            imv1.setImageResource(R.drawable.high_street_w);
        } else if(location.equals("Phoenix Lodge, No 257 110 High Street")){
            imv1.setImageResource(R.drawable.high_street2_w);
        } else if(location.equals("70 Palmerston Road")){
            imv1.setImageResource(R.drawable.palmerston_road_w);
        } else if(location.equals("Queen Street Synagogue")){
            imv1.setImageResource(R.drawable.queen_street_w);
        } else if(location.equals("23 Silver Street")){
            imv1.setImageResource(R.drawable.silver_street_w);
        } else if(location.equals("No 4 Southsea Terrace")){
            imv1.setImageResource(R.drawable.southsea_terrace_w);
        } else if(location.equals("St Agatha Church")){
            imv1.setImageResource(R.drawable.st_agathas_church_w);
        } else if(location.equals("Yarborough Villa")){
            imv1.setImageResource(R.drawable.yarborough_w);
        }

    }

    @Override
    public void onClick(View v) {
        //when the ViewFlipper element is clicked the slideshow will start
        vfl.startFlipping();
        vfl.setFlipInterval(3000);//3 secs
    }
}
