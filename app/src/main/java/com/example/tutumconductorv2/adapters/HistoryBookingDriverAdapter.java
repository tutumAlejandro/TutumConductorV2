package com.example.tutumconductorv2.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.models.HistoryBooking;

import java.util.List;

public class HistoryBookingDriverAdapter extends RecyclerView.Adapter<HistoryBookingDriverAdapter.Holder> {

    Context context;
    List<HistoryBooking> lista;

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
        holder.getPrice().setText(lista.get(i).getOrigin());

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






/*

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.activity_historial_viajes;
import com.example.tutumconductorv2.models.HistoryBooking;
import com.example.tutumconductorv2.providers.ClientProvider;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HistoryBookingDriverAdapter  extends FirebaseRecyclerAdapter<HistoryBooking, HistoryBookingDriverAdapter.ViewHolder> {

    private ClientProvider mClientProvider;
    private Context mContext;

    public HistoryBookingDriverAdapter(FirebaseRecyclerOptions<HistoryBooking> options, Context context) {
        super(options);
        mClientProvider = new ClientProvider();
        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, int position, @NonNull HistoryBooking historyBooking) {

        final String id = getRef(position).getKey();


        holder.textViewOrigin.setText(historyBooking.getOrigin());
        holder.textViewDestination.setText(historyBooking.getDestination());
        holder.textViewCalification.setText(String.valueOf(historyBooking.getCalificationDriver()));
        mClientProvider.getClient(historyBooking.getIdClient()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    holder.textViewName.setText(name);
                    if (dataSnapshot.hasChild("image")) {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.with(mContext).load(image).into(holder.imageViewHistoryBooking);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, activity_historial_viajes.class);
                intent.putExtra("idHistoryBooking", id);
                mContext.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history_booking, parent, false);
        return new  ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewOrigin;
        private TextView textViewDestination;
        private TextView textViewCalification;
        private ImageView imageViewHistoryBooking;
        private View mView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewName = view.findViewById(R.id.textViewName);
            textViewOrigin = view.findViewById(R.id.textViewOrigin);
            textViewDestination = view.findViewById(R.id.textViewDestination);
            textViewCalification = view.findViewById(R.id.textViewCalification);
            imageViewHistoryBooking = view.findViewById(R.id.imageViewHistoryBooking);
        }

    }
}
*/
