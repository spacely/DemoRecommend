package com.example.saheedadepoju.demorecommend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.OnItemSelectedListener;


/**
 * Created by saheedadepoju on 2/23/17.
 */

public class SearchActivity extends AppCompatActivity {

    private String stateCode[] = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
    private String stateCode1[] = {"Alabama","Arizona","Arkansas","Baden-Wuttemberg(Germany)","California","Edinburg","Illinois","Pennslyvania","Massachusetts","Nevada","North Carolina","North Rhine-W(Germany)","Ontario","Quebec","Rhineland(Germany)","South Carolina","Scotland(East Lothian)","Scotland(Mid Lothian)","Scotland(Fife)","Scotland","Wisconsin"};
    private String stars[] = {"1","2","3","4","5"};
    private String confidence[] = {"10%","20%","30%","40%"};

    private Spinner spinner; //contains the StateCode
    private Spinner spinner2; //Contains the Various cities
    private Spinner business_spinner; //the list of businesses that fit the criteria
    private Spinner stars_spinner;// the number of stars to be used to review the business.
    private Spinner confidence_spinner;// the confidence level required to match the recommendation
    private Button searchButton;// the search button
    private Button RecommendButton;

    private TextView category;

    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> business_adapater;

    private Database db;
    //@InjectView(R.id.spinner) Spinner _nameText;

    public void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        business_spinner = (Spinner)findViewById(R.id.spinner3);
        stars_spinner = (Spinner)findViewById(R.id.spinner4);
        searchButton = (Button)findViewById(R.id.button);
        category = (TextView)findViewById(R.id.editText);
        RecommendButton = (Button)findViewById(R.id.recommend);
        confidence_spinner = (Spinner)findViewById(R.id.spinner5);






        db = new Database();
        List<String> list = Arrays.asList(stateCode1);
        final List<String> stars_list = Arrays.asList(stars);
        final List<String> confidence_list = Arrays.asList(confidence);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        ArrayAdapter<String> adapter_stars = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,stars_list);
        ArrayAdapter<String> adapter_confidence = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,confidence_list);
        spinner.setAdapter(adapter); //sets the lists of States within a dropdown(Spinner)
        stars_spinner.setAdapter(adapter_stars);
        confidence_spinner.setAdapter(adapter_confidence); //sets the Confidence level being sought for the return computations
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String val = "";
                int state_index = parent.getSelectedItemPosition();
                List<String> a = null;

                if(state_index == 0) {
                    val = "AL";
                     a = getCities(val);

                }
                else if(state_index == 1){
                    val = "AZ";
                    a = getCities(val);
                }

                else if(state_index == 2){
                    val = "AR";
                    a = getCities(val);
                }
                else if(state_index == 3){
                    val = "BW";
                    a = getCities(val);
                }
                else if(state_index ==4){
                    val = "CA";
                    a = getCities(val);
                }
                else if(state_index == 5){
                    val = "EDH";
                    a = getCities(val);
                }
                else if(state_index == 6){
                    val = "IL";
                    a = getCities(val);
                }
                else if(state_index == 7){
                    val = "PA";
                    a = getCities(val);
                }
                else if(state_index == 8){
                    val = "MA";
                    a = getCities(val);
                }
                else if(state_index == 9){
                    val = "NV";
                    a = getCities(val);
                }
                else if(state_index == 10){
                    val = "NC";
                    a = getCities(val);
                }
                else if(state_index == 11){
                    val = "NW";
                    a = getCities(val);
                }
                else if(state_index == 12){
                    val = "ON";
                    a = getCities(val);
                }
                else if(state_index == 13){
                    val = "QC";
                    a = getCities(val);
                }
                else if(state_index == 14){
                    val = "RP";
                    a = getCities(val);
                }
                else if(state_index == 15){
                    val = "SC";
                    a = getCities(val);
                }
                else if(state_index == 16){
                    val = "ELN";
                    a = getCities(val);
                }
                else if(state_index == 17){
                    val = "MLN";
                    a = getCities(val);
                }
                else if(state_index == 18){
                    val = "FIF";
                    a = getCities(val);
                }
                else if(state_index == 19){
                    val = "SCB";
                    a = getCities(val);
                }
                else if (state_index == 20){
                    val = "WI";
                    a = getCities(val);
                }



                setAdapter(a);
               // ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a);

                spinner2.setAdapter(adapter1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> business_list = new ArrayList<String>();
                String category_string = category.getText().toString();
                String state = "";
                int state_index = spinner.getSelectedItemPosition();
                if(state_index == 0) {
                    state = "AL";


                }
                else if(state_index == 1){
                    state = "AZ";

                }

                else if(state_index == 2){
                    state = "AR";

                }
                else if(state_index == 3){
                    state = "BW";

                }
                else if(state_index ==4){
                    state = "CA";

                }
                else if(state_index == 5){
                    state = "EDH";

                }
                else if(state_index == 6){
                    state = "IL";

                }
                else if(state_index == 7){
                    state = "PA";

                }
                else if(state_index == 8){
                    state = "MA";

                }
                else if(state_index == 9){
                    state = "NV";

                }
                else if(state_index == 10){
                    state = "NC";

                }
                else if(state_index == 11){
                    state = "NW";

                }
                else if(state_index == 12){
                    state = "ON";

                }
                else if(state_index == 13){
                    state = "QC";

                }
                else if(state_index == 14){
                    state = "RP";

                }
                else if(state_index == 15){
                    state = "SC";

                }
                else if(state_index == 16){
                    state = "ELN";

                }
                else if(state_index == 17){
                   state = "MLN";

                }
                else if(state_index == 18){
                   state = "FIF";

                }
                else if(state_index == 19){
                    state = "SCB";

                }
                else if (state_index == 20){
                    state = "WI";

                }



                String city = spinner2.getSelectedItem().toString();

                Business business = new Business();
                business.setCategory(category_string);
                business.setState(state);
                business.setCity(city);
                Database db = new Database();
                business_list = db.getBusinessName(business);
                setBusinessAdapter(business_list);
                business_spinner.setAdapter(business_adapater);

            }
        });

        RecommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String business_ID =
                Database db = new Database();
                double confidence = 0;
                if(confidence_spinner.getSelectedItemPosition() == 0){
                    confidence = 0.1;
                }
                else if(confidence_spinner.getSelectedItemPosition() == 1){
                    confidence = 0.2;
                }
                else if(confidence_spinner.getSelectedItemPosition() == 2){
                    confidence = 0.3;
                }
                else if(confidence_spinner.getSelectedItemPosition() == 3){
                    confidence = 0.4;
                }
                int index = business_spinner.getSelectedItemPosition();
                int index1 = stars_spinner.getSelectedItemPosition();
                String businessID = GlobalClass.businessID.get(index);
                Business b = new Business();
                b.setBusinessID(businessID);
                b.setStars(index1);

                final ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Getting Recommendations");
                progressDialog.show();
                db.GetRecommendation(b,confidence);
                db.getCoordinates();
                db.GetAverageStars();
              //  db.mapToWeight();

                double chosen_biz_long = GlobalClass.longitude.get(business_spinner.getSelectedItemPosition());
                double chosen_biz_lat =  GlobalClass.latitude.get(business_spinner.getSelectedItemPosition());
                Intent intent = new Intent(getApplicationContext(), MapClassActivity.class);


                intent.putExtra("Index",(int)business_spinner.getSelectedItemPosition());
                intent.putExtra("Business",business_spinner.getSelectedItem().toString());
                startActivity(intent);




            }
        });


    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(false);
    }

    List<String> getCities(String state){
        List<String> city = new ArrayList<String>();
        city = db.getCities(state);
        return city;
    }

    void setAdapter(List<String> a){
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a);
    }

    void setBusinessAdapter(List<String> business){
        business_adapater = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,business);
    }


}

