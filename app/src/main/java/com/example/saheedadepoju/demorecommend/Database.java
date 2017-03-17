package com.example.saheedadepoju.demorecommend;

import android.util.Log;

import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;
import java.util.ArrayList;
import org.json.*;
import org.apache.http.Header;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.util.*;
import android.util.Log;

/**
 * Created by saheedadepoju on 1/15/17.
 */

public class Database {

    private User user;

    public Database(){

    }

    public Database(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    String saveUser(User user){
       String val = "";
        List<NameValuePair> params;
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", user.getName()));
        params.add(new BasicNameValuePair("email", user.getEmail()));
        params.add(new BasicNameValuePair("password", user.getPassword()));
        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://35.167.140.1:8080/register",params);
        if(json != null){
            try{


                //String a = json.toString();




                String jsonstr = json.getString("response_register");
               // System.out.println(jsonstr);
                val = jsonstr;
                //Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
               // System.out.println("ERROR "+jsonstr);
                Log.d("Register", jsonstr);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return val;
    }

    List<String> getCities(String state){


        String val = "";
        List<NameValuePair> params;
        List<String> city_lists = new ArrayList<String>();
        List<JSONObject> city_objects = new ArrayList<JSONObject>();
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("state", state));

        ServerRequest sr = new ServerRequest();
        //JSONObject s = sr.get

        JSONObject json = sr.getJSON("http://35.167.140.1:8080/getCities",params);
        if(json != null) {
            try{

                JSONArray js = json.getJSONArray("data_getCities");
                JSONObject anobj = js.getJSONObject(0);

                String b = anobj.getString("city");





                 city_lists = new ArrayList<String>();

                for(int j=0;j<js.length();j++){
                    city_lists.add(j,js.getJSONObject(j).getString("city"));
                }


              //  String jsonstr = json.getString("city");
                // System.out.println(jsonstr);
              //  val = jsonstr;
                //Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
               //  System.out.println("ERROR "+a);
               // Log.d("Hello", a);
               // Log.d("Second",b);

            }catch (JSONException e) {
                e.printStackTrace();
            }



        }



        return city_lists;

    }

    List<String> getBusinessName(Business business){
        String city = business.getCity();
        String state = business.getState();
        String category = business.getCategory();

        List<NameValuePair> params;
        params = new ArrayList<NameValuePair>();
        List<String> business_lists = new ArrayList<String>();

        params.add(new BasicNameValuePair("city", city));
        params.add(new BasicNameValuePair("state", state ));
        params.add(new BasicNameValuePair("category", category));
//        params.add(new BasicNameValuePair("password", password ));
        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://35.167.140.1:8080/getBusiness",params);

        if(json != null) {
            try{

                JSONArray js = json.getJSONArray("data_getBusiness");
                JSONObject anobj = js.getJSONObject(0);

                String b = anobj.getString("name");





                business_lists = new ArrayList<String>();

                for(int j=0;j<js.length();j++){
                    business_lists.add(j,js.getJSONObject(j).getString("name"));
                    GlobalClass.businessID.add(j,js.getJSONObject(j).getString("business"));
                    GlobalClass.longitude.add(j,Double.parseDouble(js.getJSONObject(j).getString("longitude")));
                    GlobalClass.latitude.add(j,Double.parseDouble(js.getJSONObject(j).getString("latitude")));
                }


                //  String jsonstr = json.getString("city");
                // System.out.println(jsonstr);
                //  val = jsonstr;
                //Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
                //  System.out.println("ERROR "+a);
                // Log.d("Hello", a);
                 Log.d("Get Business",b);

            }catch (JSONException e) {
                e.printStackTrace();
            }



        }

        return business_lists;

    }

    void getCoordinates(){

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        GlobalClass.recommend_longitude.clear();
        GlobalClass.recommend_latitude.clear();
        GlobalClass.ClearRecommendObjects();
      //  int k = 0;
        for(int i=0;i<GlobalClass.recommend_businessID.size();i++){
            Recommend recommend = new Recommend();
            params.add(0,new BasicNameValuePair("business_id",GlobalClass.recommend_businessID.get(i)));
            ServerRequest sr = new ServerRequest();
            JSONObject json = sr.getJSON("http://35.167.140.1:8080/getCoordinates",params);

            if(json != null) {
                try {
                    JSONArray string = json.getJSONArray("data_getCoord");
                    JSONObject anobj = string.getJSONObject(0);

                    double longitude = anobj.getDouble("longtitude");
                    double latitude = anobj.getDouble("latitude");
                    String business_name = anobj.getString("name");
                    GlobalClass.recommend_latitude.add(latitude);
                    GlobalClass.recommend_longitude.add(longitude);

                    recommend.setBusinessID(GlobalClass.recommend_businessID.get(i));
                    recommend.setLongitude(longitude);
                    recommend.setLatitude(latitude);
                    recommend.setBusinessname(business_name);

                    Log.d("GetCoord_Longitude",String.valueOf(recommend.getLongitude()));
                    Log.d("GetCoord_Latitude",String.valueOf(recommend.getLatitude()));
                    Log.d("GetCoord_BusinessID",recommend.getBusinessname());
                }catch(JSONException e){

                }

                GlobalClass.AddToRecommendObjects(recommend);
            }



//            Log.d("GetCoordRecommendID",""+GlobalClass.list_of_recommend_objects.get(0).getBusinessID());
            GlobalClass.AddToRecommendObjects(recommend);
           // k++;
        }//end of For loop


        //GlobalClass.list_of_recommend_objects.add(recommend);

    }

    void GetAverageStars(){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        GlobalClass.clearMap();

        for(int i=0;i<GlobalClass.recommend_businessID.size();i++){
            Recommend recommend = new Recommend();
            Double aBigDouble;
            double average_obj = 0;
            params.add(0,new BasicNameValuePair("businessID",GlobalClass.recommend_businessID.get(i)));
            ServerRequest sr = new ServerRequest();
            JSONObject json = sr.getJSON("http://35.167.140.1:8080/getStarsAverage",params);
            if(json != null) {
                try {
                  //  JSONArray string = json.getJSONArray("data_countAvg");
                  //  JSONObject anobj = string.getJSONObject(0);

                     average_obj = json.getDouble("data_countAvg");

                    aBigDouble = average_obj;

                   // double stars_average = anobj.getDouble("Stars");
                    //String business_name = anobj.getString("business_id");
                    recommend = GlobalClass.getListofRecommend().get(i);
                    recommend.setAverage_stars(aBigDouble);
                    GlobalClass.AddToRecommendObject_location(i,recommend);
                  // GlobalClass.AddtoStarsMap(business_name,stars_average);


                   // Log.d("GetAverage_Longitude",String.valueOf(recommend.getLongitude()));
                    //Log.d("GetAverge_Latitude",String.valueOf(recommend.getLatitude()));
                    //Log.d("GetAverage_BusinessID",recommend.getBusinessname());
                    //Log.d("GetAverage_StarsAvg",String.valueOf(recommend.getAverage_stars()));
                }catch(JSONException e){

                }

                GlobalClass.AddToRecommendObjects(recommend);
            }




        }//end of for loop



    }

    void GetRecommendation(Business business,double confidence){
        String businessID = business.getBusinessID();
        int stars = (int)business.getStars();

        GlobalClass.id_to_weigh.clear();
        GlobalClass.recommend_businessID.clear();

        List<NameValuePair> params;
        params = new ArrayList<NameValuePair>();
      //  List<String> business_lists = new ArrayList<String>();

        params.add(new BasicNameValuePair("business_id", businessID));
        params.add(new BasicNameValuePair("star", String.valueOf(stars) ));

        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://35.167.140.1:8080/getRecommendation",params);

        if(json != null){
            try{

                    //JSONArray dataArray = json.getJSONArray("response");
                    String string = json.getString("response_recommendation");
                    JSONObject anObj = new JSONObject(string);

                    JSONArray recommendation = anObj.getJSONArray("recommendations");


                    int j = 0;
                    //String b = firstArray.get("")
                    for(int i=0;i<recommendation.length();i++){
                       // if(confidence > recommendation.getJSONObject(i).getDouble("weight")) {
                            GlobalClass.recommend_businessID.add(recommendation.getJSONObject(i).getString("thing"));


                       // }
                     //   GlobalClass.id_to_weigh.put(recommendation.getJSONObject(i).getString("thing"),recommendation.getJSONObject(i).getDouble("weight"));


                    }


                //Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
                 //System.out.println("ERROR "+string);
                Log.d("Recommendation_Weight",recommendation.getJSONObject(0).getString("weight").toString() );
                Log.d("RecommendaBusinessID",recommendation.getJSONObject(0).getString("thing"));
                Log.d("RecommendationBusiness",GlobalClass.recommend_businessID.get(0));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //getCoordinates();
      //  mapToWeight();




    }

    void mapToWeight(){

        Recommend r = new Recommend();
        double weight = 0.0;
        for(int i=0;i<GlobalClass.list_of_recommend_objects.size();i++){

            if(GlobalClass.id_to_weigh.get((GlobalClass.list_of_recommend_objects.get(i).getBusinessID()))!=null) {
                weight = (double) GlobalClass.id_to_weigh.get((GlobalClass.list_of_recommend_objects.get(i).getBusinessID()));
            }
            GlobalClass.list_of_recommend_objects.get(i).setConfidence(weight);

        }

    }

    double getLatitude(int index){

        return GlobalClass.latitude.get(index);
    }

    double getLongitude(int index){

        return GlobalClass.longitude.get(index);
    }

    String login(User user){
        String username = user.getEmail();
        String password = user.getPassword();

        String val = "";
        List<NameValuePair> params;
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", username));
        params.add(new BasicNameValuePair("password", password ));

        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://35.167.140.1:8080/login",params);

        if(json != null){
            try{


                //String a = json.toString();


                String jsonstr = json.getString("response_login");
                // System.out.println(jsonstr);
                val = jsonstr;
                //Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
                // System.out.println("ERROR "+jsonstr);
                Log.d("Response Login", jsonstr);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return val;



        //return null;
    }


}
