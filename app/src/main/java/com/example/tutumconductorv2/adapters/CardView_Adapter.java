package com.example.tutumconductorv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;

import java.util.List;

public class CardView_Adapter extends RecyclerView.Adapter<CardView_Adapter.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener; //ok

    public CardView_Adapter(List<CardViewDatos> heads_list)
    {
        this.heads_list = heads_list;
    }

    private List<CardViewDatos> heads_list;
    private Context context;

    public CardView_Adapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_reportes,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView_Adapter.ViewHolder holder, int position) {
        holder.titulo_reporte.setText(heads_list.get(position).getHeader());
    }

    @Override
    public int getItemCount() {
        return heads_list.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo_reporte;
        private int id_reporte=0;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            titulo_reporte = itemView.findViewById(R.id.head_report);
        }
    }
}
