package com.example.tutumconductorv2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Registro.datos_personales.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class FCM  extends FirebaseMessagingService{



    public static final String TAG="NOTICIAS";


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
        Log.d(TAG, "Noticias ");

        /* if(remoteMessage.getNotification() != null){
            Log.e("TAG","Titulo de la notificacion"+remoteMessage.getNotification().getTitle());
            Log.e("TAG","Cuerpo de la notificacion"+remoteMessage.getNotification().getBody());
        }*/
        String titulo;
        String texto;



        if (remoteMessage.getData().size()>0  ){

            Log.e("TAG","Titulo de la notificacion"+remoteMessage.getNotification().getTitle());
            Log.e("TAG","Cuerpo de la notificacion"+remoteMessage.getNotification().getBody());

        }

    }


private void mayorqueoreo(){

        String id="mensaje";

        NotificationManager  nm =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id);
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        NotificationChannel nc= new NotificationChannel(id,"nuevo",NotificationManager.IMPORTANCE_HIGH);
        nc.setShowBadge(true);
        assert nm !=null;

        nm.createNotificationChannel(nc);

    }

builder.setAutoCancel(true)
        .setWhen(System.currentTimeMillis())
        .setContentTitle("")
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentText("")
        .setContentInfo("nuevo");

    Random random=new Random();
    int idNotify =random.nextInt(8000);

        assert  nm!=null;
        nm.notify(idNotify,builder.build());

}

public void Clicknoti(){
    Intent nf =new Intent(getApplicationContext(), MainActivity.class);
    //nf.putExtra();
    for(int i=0;i<2;i++){}

}




/*        if (remoteMessage.getNotification() != null) {


            titulo = remoteMessage.getNotification().getTitle();
            texto = remoteMessage.getNotification().getBody();


            Log.e( "TITULO: ", " " + titulo);
            Log.e("CUERPO: ", " " + texto);

            //Opcional: mostramos la notificación en la barra de estado
            showNotification(titulo, texto);



            Log.d(TAG, "notificaciones "+remoteMessage.getNotification().getBody());



        }*/














    private void showNotification(String titulo, String texto) {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_menu_camera)
                        .setContentTitle(titulo)
                        .setContentText(texto)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
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


