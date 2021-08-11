package com.example.tutumconductorv2.adapters;


/*
public class HistoryBookingDriverAdapter extends RecyclerView.Adapter<HistoryBookingDriverAdapter.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener; //ok

    public HistoryBookingDriverAdapter(List<CardView_Datos_Ganancia> heads_list)
    {
        this.heads_list = heads_list;
    }

    private List<CardView_Datos_Ganancia> heads_list;
    private Context context;

    public HistoryBookingDriverAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history_booking,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull HistoryBookingDriverAdapter.ViewHolder holder, int position) {
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
}*/


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.models.HistoryBooking;

import java.util.ArrayList;
import java.util.List;

public class HistoryBookingDriverAdapter extends RecyclerView.Adapter<HistoryBookingDriverAdapter.Holder> {

    Context context;
   // List<HistoryBooking> lista;
    List<HistoryBooking> lista = new ArrayList<>();



    public HistoryBookingDriverAdapter(List<HistoryBooking> heads_list)

    {
        this.heads_list = heads_list;
    }

    private List<HistoryBooking> heads_list;

    public void AdaptadorRecycler(Context context, List<HistoryBooking> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_history_booking,viewGroup,false);
        return new Holder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.getOrigen().setText(lista.get(i).getOrigin());
        holder.getDestino().setText(lista.get(i).getDestination());
        holder.getDate().setText(lista.get(i).date().getDate());
        holder.getPrice().setText(lista.get(i).price());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        //ImageView imageView;
        TextView origen, destino, date, price;


        public Holder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imagenItem);
            origen = itemView.findViewById(R.id.txtEncuentro1);
            destino = itemView.findViewById(R.id.txtDestino2);
            date = itemView.findViewById(R.id.txtFecha3);
            price = itemView.findViewById(R.id.txtPrecio);
        }

/*public ImageView getImageView() {
    ImageView imageView;
    return imageView;
        }*/


        public TextView getOrigen() {
            return origen;
        }

        public TextView getDestino() {
            return destino;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getPrice() {
            return price;
        }
    }
}





