package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.adapters.TestAdapter;
import com.example.tutumconductorv2.models.Model_Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_TestRecycler extends AppCompatActivity {



    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Model_Test> model_testList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_recyclerview);

        recyclerView = findViewById(R.id.recyclerviewtest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //requestQueue= VolleySingleton.getmInstance(this).getRequestQueue();

        model_testList= new ArrayList<>();
        fetchModelTest();


    }

    private void fetchModelTest() {


        String URL="https://www.tutumapps.com/api/driver/journeyHistory";
        try {
            SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login",Context.MODE_PRIVATE);
            RequestQueue requestQueue = VolleySingleton .getmInstance(this).getRequestQueue();
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            //jsonObject.put("email", email);
            jsonObject.put("api_token",preferences.getString("api_token",""));


            final String requestBody = jsonObject.toString();
            final String TAG;


            //mod
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Respuesta Registro","Exitoso!!!: "+response);

                 //   JSONObject repsuesta1 = response;
                    try {
                      //  JSONObject exito = response.getJSONObject("data");

                        JSONArray exito2 = response.getJSONArray("data");
                      //  JSONObject

                        Log.e("Respuesta exito 2","Respuesta!!!: "+exito2);



                        for (int j=0; j<exito2.length(); j++){

                            JSONObject exito3 = exito2.getJSONObject(j);

                           // JSONArray

                            int id_viaje = exito3.getInt( ("journey_id").toString());
                            String destination =  exito3.getString ("end_address");
                            String origen =  exito3.getString("start_address");
                            String date =  exito3.getString("starter_date");
                            //Date date = Calendar.getInstance().getTime("starter_date");
                            int price =  exito3.getInt("fare");




                            Log.e("TAG","Exito id: "+origen+"/"+destination+"/"+id_viaje+"/"+price+"/"+date);


                            Log.e("Respuesta exito 3","Respuesta!!!: "+exito3);


                            //  this.idHistoryBooking= idHistoryBooking;
                            //        this.destination = destination;
                            //        this.origin = origin;
                            //        this.date = date;
                            //        this.price = price;

                            Model_Test model_test=new Model_Test(id_viaje,destination,origen,date,price);
                            model_testList.add(model_test);

                            //Log.e("Respuesta Registro","Exito!!!: "+respuesta2);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                    TestAdapter adapter=new TestAdapter(MainActivity_TestRecycler.this,model_testList);
                    recyclerView.setAdapter(adapter);



                    //codigo no funcional

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            })



            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    return params;}
            };

          requestQueue.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }




      /*  try {
            RequestQueue requestQueue1 = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
            String apiToken=preferences.getString("api_token","");
            jsonObject.put("api_token",apiToken);
            final String requestBody = jsonObject.toString();
            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.e("tag","respuesta"+response);
                    for (int i=0; i<response.length() ;i++){


                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            String destination =  jsonObject.getString("end_address");
                            String origen =  jsonObject.getString("start_address");

                            // Date date =  jsonObject.getDate("starter_date");
                            int price =  jsonObject.getInt("precio");



                            //  this.destination = destination;
                            //        this.origin = origin;
                            //        this.date = date;
                            //        this.price = price;

                            Model_Test model_test=new Model_Test(destination,origen,price);
                            model_testList.add(model_test);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        TestAdapter adapter=new TestAdapter(MainActivity_TestRecycler.this,model_testList);
                        recyclerView.setAdapter(adapter);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText( MainActivity_TestRecycler.this  , error.getMessage(), Toast.LENGTH_SHORT).show();
                }




            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    return params;

                }
            };
            requestQueue1.add(jsonArrayRequest);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }*/















    }


}