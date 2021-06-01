package com.example.tutumconductorv2.Registro.menus_rol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainDocumentosOk extends AppCompatActivity {

    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";
    private TextView test_p1,test_p2,test_p3,test_p4,test_p5,test_p6,test_p7,test_p8,test_p9;

    private HashMap <String,String> respuesta = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_documentos_ok);
        test_p1 = findViewById(R.id.test_post1);
        test_p2 = findViewById(R.id.test_post2);
        test_p3 = findViewById(R.id.test_post3);
        test_p4 = findViewById(R.id.test_post4);
        test_p5 = findViewById(R.id.test_post5);
        test_p6 = findViewById(R.id.test_post6);
        test_p7 = findViewById(R.id.test_post7);
        test_p8 = findViewById(R.id.test_post8);
        test_p9 = findViewById(R.id.test_post9);

    }
    public void refresh(View v){
        realizarPost(url_timeline);
    }

    private void realizarPost(String url) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", cadenas_registro.telefono);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Respuesta1!!!!! "+response);
                    try {
                          JSONArray respuesta = jsonObject.getJSONArray("data");
                          for(int i=0;i<respuesta.length();i++)
                          {
                              JSONObject c = respuesta.getJSONObject(i);
                              String status = c.getString("status");
                              String id = c.getString("registry_id");
                              Log.d("my tag","Respuesta 2: "+status+"/"+id+"/");
                              if(c.has("timeline"))
                              {
                                  JSONArray detail = c.getJSONArray("timeline");
                                  for(int j=0;j<detail.length();j++){
                                      JSONObject d = respuesta.getJSONObject(i);
                                      String status2 = d.getString("status");
                                      String date = d.getString("date");
                                      String nombre = d.getString("name");
                                      String descripcion = d.getString("descripcion");
                                      String sucess = d.getString("true");
                                      Log.d("my tag","Respuesta 3: "+status2+"/"+date+"/"+nombre+"/"+descripcion+"/"+sucess);
                                  }
                              }

                          }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}