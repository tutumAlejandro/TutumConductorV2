package com.example.tutumconductorv2.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.telecom.Connection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.tutumconductorv2.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CardViewMensajes_Adapter extends RecyclerView.Adapter<CardViewMensajes_Adapter.ViewHolder>
        {


    public CardViewMensajes_Adapter(List<CardViewMensajes> msg_list)
    {
        this.msg_list = msg_list;
    }
    private List<CardViewMensajes> msg_list;



    private Context context;

    public CardViewMensajes_Adapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat_panel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewMensajes_Adapter.ViewHolder holder, int position) {
        holder.mensaje.setText(msg_list.get(position).getMsg());
        holder.type_us.setText(msg_list.get(position).getUser_type());
        holder.img = msg_list.get(position).getImg_data();
        Log.e("imagen","url imagen: "+holder.img);
        if(holder.img.matches("null")){
            holder.captura.setVisibility(View.GONE);
        }else {
            Picasso.get().load(holder.img).into(holder.captura);
        }
    }

    @Override
    public int getItemCount() {
        return msg_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mensaje;
        private ImageView captura;
        private TextView type_us;
        private String img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mensaje = itemView.findViewById(R.id.res_mensaje);
            type_us = itemView.findViewById(R.id.type_user);
            captura = itemView.findViewById(R.id.img_respuesta);

        }
    }
}
