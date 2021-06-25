package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.Html;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Registro.datos_personales.MainOTP;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistroTelefono;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class FCM  extends FirebaseMessagingService{
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("Token","Token Actualizado: " + s);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        String phone = preferences.getString("phone","");
        if(!phone.isEmpty()){
            postTokenRefresh(phone,s);
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        if(remoteMessage.getNotification() != null){
            Log.e("TAG","Titulo de la notificacion"+remoteMessage.getNotification().getTitle());
            Log.e("TAG","Cuerpo de la notificacion"+remoteMessage.getNotification().getBody());
        }
    }


    private void postTokenRefresh(String phone, String token){
        try {
            String url = "https://www.tutumapps.com/api/driver/updateFCMToken";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("fcm_token",token);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.e("My Tag","Token actualizado en el servidor");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.e("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}


