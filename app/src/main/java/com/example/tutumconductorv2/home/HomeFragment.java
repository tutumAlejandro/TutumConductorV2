package com.example.tutumconductorv2.home;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

//import com.example.tutumconductorv2.Destino;
import com.example.tutumconductorv2.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Button btnMapas;
    private EditText txtDestino;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.activity_main_inicio_sesion, container, false);

//        btnMapas = (Button) root.findViewById(R.id.home_mapasDisponibles);
//        txtDestino = (EditText) root.findViewById(R.id.text_destinoDireccion);
//
//        btnMapas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Enviar a Vista de los mapas disponibles instalados en el telefono
//                Toast.makeText(getContext(), "Mapas disponibles",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        txtDestino.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Destino.class);
//                getActivity().startActivity(intent);
//            }
//        });

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

}