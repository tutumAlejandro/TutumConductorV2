package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.datos_personales.MainPopUpRegistro;
import com.example.tutumconductorv2.Registro.datos_personales.MainPopUpRegistroFail;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistroTelefono;

import org.json.JSONException;
import org.json.JSONObject;

public class MainScripRegistro extends AppCompatActivity {

    private int i;

    private String img="/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAFA3PEY8MlBGQUZaVVBfeMiCeG5uePWvuZHI////////" +
            "////////////////////////////////////////////2wBDAVVaWnhpeOuCguv/////////////" +
            "////////////////////////////////////////////////////////////wAARCAPAA8ADASIA" +
            "AhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAECAwT/xAAiEAEBAQABBQEBAAMBAAAAAAAAARES" +
            "AiExQVETYQMycUL/xAAVAQEBAAAAAAAAAAAAAAAAAAAAAf/EABQRAQAAAAAAAAAAAAAAAAAAAAD/" +
            "2gAMAwEAAhEDEQA/AOICKAAAAogCoACgACAAAAoIACgAAAAAAAAAAAAAAAAAAAAAAAAAAAKgCiKA" +
            "AAAAAAAAAAAAAAAAAAAAIAAAAAAAIqAKgAAAAAAAIoAAAAAALAAAAFQoCAAAAAAAACgAAAAAAgAA" +
            "AAAAAAAAAAAAAKIAqAAqAKIoAAAAAAAAAAAAAAAAAAAAAAAAAAKIoAAAAAAAACooIAAAAAAAAAAA" +
            "CAAAAAgAAAAAAAAAICiKAAAAACgAAAAFAEFAQAAAAAFAAAABAVFICAAAAAAAAAAAAAAAAAAKgCiK" +
            "CKgAKAgoAAAAAAAAAAAAAAAAAAAAAKgCooAAAAAAAAAAAAAAAAAAAAAIAAAAAAAioAAAAAAAioCi" +
            "KAAAAAsRYAAAAAACgAgoDIqARUUAABAABQRYjU8AyAAAAAAAAAAAAAAAAAAAAACiAKIoAAAAAAAA" +
            "AAAAAAAAAAAAAAAAKIAoAAAAAAAAAAAAAAAAAAAAAIKgAAAAIAAAAAAAAgAoigAAAAKigAAAAKig" +
            "AAAAJVZAVFAABBUAUARpIoMgAAAAAAAAAAAAAAAAAAAAAAoIAAqKAAAAAAAAAAAAAAAAAAACggoA" +
            "igIoAAAAAAAAAAAAAAAAAAAAAIqAAAAAgqAAAAAAAgUAVFAAAABQgAAAAAqAKAAAAytQBUUAAEBQ" +
            "EVAagICAAAAAAAAAAAAAAAAAAAAAAAAAAAAoAAAAAAAAAAAAAAAAAAAAAKgAogCiKAAAAAAAAAAA" +
            "AAAAAAioAAAAAACAAAAAAAAgACooAAAAKAAqAAAKioCiKAACVD2AKigAAAgCxFgAAIAAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAqAKIAoAAAAAAAAAAAAAAAAAAAKIoAAAAAAAAAAAAAAAAAAIAAAAAAAAgAA" +
            "AAAAgAACooAAALAAAUEABQAARQAKJQQVAFRQAAQABUigAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
            "AACoAKgCiKAIoAAAAAAAAAAAAAAAAAACooAAAAAAAAAAAAIKgAAAAAAIAAAAAAlVAAAFRQFAEWIo" +
            "AAKgAKigAAAAJVQBFARUUBAAABYAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKgCiAKIAqACooAA" +
            "AAAAAAAAAAAAAAAAAAAqACoAoAAAAAAgAAAAAAAACKgAAAAAioAAAoAoigigAAAACgAAAAAVFqAA" +
            "AAgAABBYAAAKgIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACooCKAAAAAAAAAAAAAAAA" +
            "AAAAAKgAAAAAAAAABlXjQRG+MXJPQMZV41sBnhF4z4oCcInCNAM8J9p+c+tAMfn/AE/P+tgMcL9O" +
            "F/jYDHGnGtgMcb8R0S4DA1x+JZYAgoAAAACooM+wAAAEVAAAFSKAKAgAIigAgCgAAAAAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAoIqAKgAKAAAAAAAAAAAAAGGABgAAoCCgIKAguavEGRrjFyAzi8aoCcY" +
            "uQAVABUAATTQU1NAaNZAVWQGhkBoZ00GhNNBUpqAsq6yoGSs+2mQAAAAVKqUAEAAARUAABYEAUQB" +
            "QAYAAAAVAFRQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABQAAAAAAAAAAAAAAAAAABRFAAABeme" +
            "wWTIKgAhoKianIGjWdNBrU1nQGtNZAa01kBdNQBdXWQGtGVBRAFVlQUQBRFAVE0GmQABQRTKvGgi" +
            "Ncf6cf6DKNXpqZQQVKCAACkAABQAAAYAABQQVAVFARQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
            "VAFEUAAAAAAAAAAAAAAAAAAAFncFk1okUEMUBMnw4z4AJxnw4xQE4ROEaAYvRWbs8x1ActNdL0y+" +
            "mb/j+UGUW9NnpAFQAFwwAMAAAUNNAUk6q1x+0GdNrfGfFBzy/F41sBmdH9OMaATJ8UAAAAAAAMjN" +
            "6ZWgGOHypwroA5WX4OoDkOnGJw/oMi8aZfgIADAoCAAAAAAqAAqAKIAogCiKAAAAAAAAAAAAAAAA" +
            "AAAAAAAACoAAKAAAAAAAAAAAAAAAAANTprUkgMzptakkAFQAAAAAAAAAAAAACyUAThGb01sByssH" +
            "VL0yg5mX46cc8JZQZ404/wBUBZI1MZAaVnV0FAAAAAAAAAAAAAAAAAAAAAAAAMnwAcFRQAARQAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABRAFEUAAAAAAAABembUb6Z2BQAAAAAAAAAAAA" +
            "AAAAAAAAAAAAAMTFAZwUBAAXV1kBpWF0GhNUAAAEBRNNBRNAUQBRFAAAAAAAABwAAAAVAFEAUQBR" +
            "AFEAURQBFAAAEUAAAAAAAAAAAAAAAAAAAAAAAAAAAAFRQAAAAAAAACd66sdE9tggUgAAAAAAAAAm" +
            "gKgAKiggAAAAAKIAogCoAAACKAgAKAAaIC6IoAAAAAACooAAAAKIAoigAA4AAAAAAAAAAAAKigAg" +
            "AAAAKIAoAAAAAAAAAAAAAAAAAAAAAAAAAAKCKAAAAAAAAL0zaDcmQVARSAAAAAAACWlZBQAAWAsA" +
            "BBUAAAFAEqlBBFAAAAAAAAAAARQEUAAAAAAAFRQAAAAAAAAAAcQAAAAAAAAAAAAAAAFQABQQABUA" +
            "UQBRFAAAAAAAAAAAAAAAAAAAAAVAFEUAAAAAABvonbWHTxAEVAWAAAAAAAlBKAAC4CRoxQAAQAAA" +
            "AAAAECgAAAAAAAAAAAAAAAAAAAACgAAAAAAAAAADiAAAAAAAAAAAAAAAAAAAAAAAAACiAKAAAAAA" +
            "AAAAAAAAAAAAAAAAqAKAAAAADXRO+tHTMgCLBQQUBBQEFARKq4DOGNYYCYuCggqAAAgAAigAAAAJ" +
            "igIAAAAAAAAAAAAAAAAAACgAAAAAAAAAAAA4gAAAAAAAAAAAAoIAAKAAAAAAAAAAAAAAAAAAAAAA" +
            "ACgIKAgoCCgIKAguAAAAAC9M2o30TzQaMFBBUAAAAAAAVFAAAAAABEVAEVAAAURQAAAAAAAL4BFZ" +
            "UAAAAAAAAAAAABQAAAAAAAAAAAABxFAQUBBQEFAQUBFEBQAAAAAAAAAAAAAAAAAAAAAAAAUEUAAA" +
            "AAAAAAAAURQAAAAGui98ZJ2oOoAAAIAAAAAAqKAAAAAAAy0AyjWJgIKgAACoAoAAACdSs3yBFJ4A" +
            "AUEFAQUBBQEFAAAAAAAAAAAAAAAAAcgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFQBQAAAAAAAA" +
            "AAAAAFRQAAAAAAb6L2aY6blbAAARUAAAAAABQAAAAABUAAAxnGgGRUAAARQARQL4ZnlaQAAAAAAA" +
            "AAABUAUQBRAFEUAAAAAAAQBRAHMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABUAUAAAAAAAAAAAA" +
            "GpO2gmLxUBOJxUBOJxVQZ4mKAmOk8MNdIKAAACAAAAAAKigAoAICooCCoAACI0gIAAKAgqUGVSeV" +
            "AAAAAAAAAAAAAAAAAAAVFARUABQQAAAHMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFAAAAAAAA" +
            "AAAAAdOi7HNZcoOiHKVQTDFATDFATDFATFnagCgAAAg";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrip_registro);

        for( i=10;i<99;i++){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    registro();
                    realizarPostSocio();
                    realizarPostIne();
                    realizarPostLicencia();
                    realizarPostCarac();
                    realizarPostPoliza();
                    realizarPosttarjeta();
                    realizarPosttarjeton();
                }
            },10000);

        }
    }
    public void registro(){
        String url = "https://www.tutumapps.com/api/driver/registryDriver";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            jsonObject.put("name","Test"+" T"+i+" T"+i);
            jsonObject.put("email","tesst"+i+"@gmail.com");
            jsonObject.put("phone","44958400"+i);
            jsonObject.put("password","123456789");
            jsonObject.put("status","ok");
            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Usuario Creado"+i+" "+response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void realizarPostSocio() {
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", "44958400"+i);
            jsonObject.put("status","0");
            jsonObject.put("type","1");
            jsonObject.put("only",0);
            jsonObject.put("confirmation_phone",'1');
            jsonObject.put("terms_confirmation","1");

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!:  "+i+" "+response);
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
    public void realizarPostIne() {
        String url = "https://tutumapps.com/api/driver/uploadRegistryINE";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", "44958400"+i);
            jsonObject.put("img_front",img);
            jsonObject.put("img_back",img);
            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!"+i+" "+response);
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
    public void realizarPostLicencia() {
        String url = "https://tutumapps.com/api/driver/uploadRegistriLicense";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", "44958400"+i);
            jsonObject.put("img_front",img);
            jsonObject.put("img_back",img);
            jsonObject.put("driver_license_expiry","29-05-2021");

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!"+i+" "+response);
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
    public void realizarPostCarac() {
        String url = "https://tutumapps.com/api/driver/uploadVehicleDetails";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", "44958400"+i);
            jsonObject.put("vehicle_model_id",i);
            jsonObject.put("vehicle_year","2020");
            jsonObject.put("vehicle_plates","AAAAAA");
            jsonObject.put("img_front",img);
            jsonObject.put("img_back",img);
            jsonObject.put("img_side",img);


            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!"+i+" "+response);
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
    public void realizarPostPoliza() {
        String url = "https://tutumapps.com/api/driver/uploadInsurance";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone","44958400"+i);
            jsonObject.put("img_front",img);
            jsonObject.put("insurance_policy_expiry","28-05-2021");

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!"+i+" "+response);
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
    public void realizarPosttarjeta() {
        String url = "https://tutumapps.com/api/driver/uploadCirculationCard";;
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone","44958400"+i);
            jsonObject.put("img_front",img);
            jsonObject.put("insurance_policy_expiry","28-05-2021");

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!"+i+" "+response);
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
    public void realizarPosttarjeton() {
        String url = "https://tutumapps.com/api/driver/uploadControlCard";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone","44958400"+i);
            jsonObject.put("img_front",img);
            jsonObject.put("insurance_policy_expiry","28-05-2021");

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!"+i+" "+response);
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