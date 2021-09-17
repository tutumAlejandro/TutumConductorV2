package com.example.tutumconductorv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.models.Model_Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {


    private Context context;
    private List<Model_Test> model_testList;

    public TestAdapter(Context context,List<Model_Test> model_tests ){

        this.context=context;
        model_testList=model_tests;

    }




    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.card_history_booking , parent,false);
        return  new TestHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {

        Model_Test model_test=model_testList.get(position);

        holder.destination.setText(model_test.getDestination());
        holder.origin.setText(model_test.getOrigin());
        holder.date.setText( model_test.getDate().toString());
        holder.price.setText(String.valueOf(model_test.getPrice()));
        holder.id_viaje.setText(String.valueOf(model_test.getIdHistoryBooking()));


    }

    @Override
    public int getItemCount() {
        return model_testList.size();
    }

    public class TestHolder extends RecyclerView.ViewHolder {


        TextView  destination,origin,date,price,id_viaje;


        public TestHolder(@NonNull View itemView) {
            super(itemView);

            destination=itemView.findViewById(R.id.txtDestino2);
            origin=itemView.findViewById(R.id.txtEncuentro);
            price=(TextView) itemView.findViewById(R.id.txtPrecio);
            date=(TextView) itemView.findViewById(R.id.txtFecha2);
            id_viaje=(TextView) itemView.findViewById(R.id.txt_id_viajes);




        }
    }
}



