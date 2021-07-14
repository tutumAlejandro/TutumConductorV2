package com.example.tutumconductorv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;

import java.util.List;

public class CardViewMensajes_Adapter extends RecyclerView.Adapter<CardViewMensajes_Adapter.ViewHolder>
        implements View.OnClickListener{

    private List<CardViewMensajes> msg_list;
    private Context context;
    private View.OnClickListener listener;

    public CardViewMensajes_Adapter(List<CardViewMensajes> msg_list) {
        this.msg_list = msg_list;
    }

    public CardViewMensajes_Adapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat_panel,parent,false);
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewMensajes_Adapter.ViewHolder holder, int position) {
        holder.mensaje.setText(msg_list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return msg_list.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mensaje;
        private ImageView captura;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mensaje = itemView.findViewById(R.id.res_mensaje);


        }
    }
}
