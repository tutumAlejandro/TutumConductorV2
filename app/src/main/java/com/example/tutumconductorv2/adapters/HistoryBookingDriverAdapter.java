package com.example.tutumconductorv2.adapters;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.models.ListItem;

import java.util.List;

public class HistoryBookingDriverAdapter extends RecyclerView.Adapter<HistoryBookingDriverAdapter.ViewHolder> {



    private  List<ListItem> listItems;
    private Context context;

    public HistoryBookingDriverAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_history_booking,parent,false);

        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
/*
        ListItem listItem = listItems.get(position);
        holder.tvPrice.setText(listItem.getPrice());
        holder.tvOrigin.setText(listItem.getOrigin());
        holder.tvDestination.setText(listItem.getDestination());

*/
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //public TextView tvName
        //public TextView tvDate;
        public TextView tvPrice;
        public TextView tvOrigin;
        public TextView tvDestination;
      //public TextView tvStatus;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

         // tvName=itemView.findViewById(R.id.textView_nombre);
            //tvOrigin=itemView.findViewById(R.id.txtEncuentro1);
            tvDestination=itemView.findViewById(R.id.txtDestino2);
            tvPrice=(TextView)itemView.findViewById(R.id.txtPrecio);



        }
    }


}





