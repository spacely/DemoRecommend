package com.example.saheedadepoju.demorecommend;
import java.util.*;
import android.util.*;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by saheedadepoju on 3/3/17.
 */

public class GlobalClass {

    static List<String> businessID = new ArrayList<String>();
    static List<Double> latitude = new ArrayList<Double>();
    static List<Double> longitude = new ArrayList<Double>();
    static List<String> recommend_businessID = new ArrayList<String>();
    static List<Double> confidence_level_lists = new ArrayList<Double>();

    static List<Double> recommend_longitude = new ArrayList<Double>();
    static List<Double> recommend_latitude = new ArrayList<Double>();

    static List<Recommend> list_of_recommend_objects = new ArrayList<Recommend>();

    static List<LatLng> list_of_latLong = new ArrayList<LatLng>();

    static Map<String,Double> business_id_to_stars = new HashMap<>();


    public static void AddtoStarsMap(String ID,double average){
        business_id_to_stars.put(ID,average);
    }

    public static void clearMap(){
        business_id_to_stars.clear();
    }

    public static void AddToRecommendObject_location(int index,Recommend recommend){
        list_of_recommend_objects.add(index,recommend);
    }

    //Recommend recommend;

    static Map<String,Double> id_to_weigh = new HashMap<>();
    public GlobalClass(){


    }

    public static void AddToRecommendObjects(Recommend recommend){

        list_of_recommend_objects.add(recommend);
//        Log.d("GlobalClassBiz",recommend.getBusinessname());
  //      Log.d("GlobalClassBizId",recommend.getBusinessID());
    //    Log.d("GlobalClassLat",String.valueOf(recommend.getLatitude()));
      //  Log.d("GlobalClassLong",String.valueOf(recommend.getLongitude()));
    }
    public static List<Recommend> getListofRecommend(){
        return list_of_recommend_objects;
    }

    public static void ClearRecommendObjects(){
        list_of_recommend_objects.clear();
    }



}
