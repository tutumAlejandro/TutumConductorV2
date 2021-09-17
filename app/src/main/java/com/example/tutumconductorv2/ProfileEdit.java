package com.example.tutumconductorv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfileEdit {


    private String api_token;
    private String userToken;
    private boolean isSucess;



    public void cambioDatos(Context context){


        String url = "https://www.tutumapps.com/api/driver/updateProfile";

        userToken = getApi_token();
        try {
            SharedPreferences preferences = context.getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
            String password = preferences.getString("password","");
            String phone = preferences.getString("phone","");
            String email = preferences.getString("email","");

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            final JSONObject jsonObject = new JSONObject();


            jsonObject.put("password", password);
            jsonObject.put("phone", phone);
            jsonObject.put("email", email);
            jsonObject.put("api_token", userToken);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "Success! :D" + " " + response);
                    try {
                        isSucess = response.getBoolean("success");
                        if (isSucess) {
                            String getMsg = response.getString("msg");
                            Toast.makeText(context, getMsg, Toast.LENGTH_SHORT).show();
                            Log.d("SUCCESS", "Cambios guardados");
                        } else {
                            String getMsg = response.getString("msg");
                            Toast.makeText(context, getMsg, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("ERROR", "Hubo un error al guardar los cambios");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("TAG", "Error: " + error);
                }
            }) /*{


                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    return params;
                }
            };
*/
            {
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                String creds = String.format("%s:%s", email, password);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };




            requestQueue.add(jsonObjectRequest);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String getApi_token() {
        return api_token;
    }

}
