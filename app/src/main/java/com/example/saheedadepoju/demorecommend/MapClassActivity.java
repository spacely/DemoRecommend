package com.example.saheedadepoju.demorecommend;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import android.util.*;
import java.util.*;

/**
 * Created by saheedadepoju on 3/8/17.
 */

public class MapClassActivity extends AppCompatActivity implements OnMapReadyCallback{

    private LatLngBounds bounds;
    private LatLngBounds.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        int index = getIntent().getExtras().getInt("Index");
        String business_name_display = getIntent().getExtras().getString("Business");
        double latitude = GlobalClass.latitude.get(index);
        double longitude = GlobalClass.longitude.get(index);
        LatLng mark = new LatLng(latitude, longitude);



       // LatLng markings[] = new LatLng[GlobalClass.list_of_recommend_objects.size()+1];
        List<LatLng> markings_arraylist = new ArrayList<>();

      //  markings[0] = mark;
        markings_arraylist.add(0,mark);

        for(int i=0;i<GlobalClass.getListofRecommend().size();i++){
            LatLng marks = new LatLng(GlobalClass.getListofRecommend().get(i).getLatitude(),GlobalClass.getListofRecommend().get(i).getLongitude());
         //   markings[i] = marks;
            markings_arraylist.add(i,marks);
            Log.d("MapReadyCoordinatesLati",String.valueOf(GlobalClass.getListofRecommend().get(i).getLatitude()));
            Log.d("MapReadyCoordinatesLati",String.valueOf(GlobalClass.getListofRecommend().get(i).getLongitude()));
        }

        for(int j=0;j<GlobalClass.getListofRecommend().size();j++){
            googleMap.addMarker(new MarkerOptions().position(markings_arraylist.get(j)).title(GlobalClass.getListofRecommend().get(j).getBusinessname()).snippet("Average Stars "+String.valueOf(GlobalClass.getListofRecommend().get(j).getAverage_stars())));
        }

        googleMap.addMarker(new MarkerOptions().position(mark).title(business_name_display).snippet("Hello").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //googleMap.addMarker(new MarkerOptions().position(mark1).title(business_name_display));


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markings_arraylist.get(1),5));


        Log.d("Map Data Latitude",String.valueOf(GlobalClass.getListofRecommend().get(2).getBusinessname()));
        Log.d("Map Data Latitude",String.valueOf(GlobalClass.getListofRecommend().size()));








    }


}
