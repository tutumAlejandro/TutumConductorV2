package com.example.tutumconductorv2.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardViewMensajes_Adapter2 extends RecyclerView.Adapter<CardViewMensajes_Adapter2.ViewHolder> {

    public CardViewMensajes_Adapter2(List<CardViewMensajes> msg_list) {
        this.msg_list = msg_list;
    }

    private List<CardViewMensajes> msg_list;

    public CardViewMensajes_Adapter2(Context context) {
        this.context = context;
    }

    private Context context;


    @NonNull
    @Override
    public CardViewMensajes_Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat_panel_2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewMensajes_Adapter2.ViewHolder holder, int position) {
        holder.mensaje.setText(msg_list.get(position).getMsg());
        holder.type_us.setText(msg_list.get(position).getUser_type());
        holder.img = msg_list.get(position).getImg_data();
        Log.e("imagen","url imagen: "+holder.img);
        if(holder.img.matches("null")){
            holder.captura.setVisibility(View.GONE);
        }else {
            holder.captura.setVisibility(View.VISIBLE);
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
