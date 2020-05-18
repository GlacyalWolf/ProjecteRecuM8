package com.example.projecterecum8.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projecterecum8.Model.Contacto;
import com.example.projecterecum8.R;
import com.example.projecterecum8.ViewModel.RecyclerViewModel;

public class ContactFragment extends Fragment {
    private RecyclerViewModel recyclerViewModel;
    private TextView nombre,email,departamento;


    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        nombre = view.findViewById(R.id.nombre_text);
        email = view.findViewById(R.id.email_text);
        departamento = view.findViewById(R.id.departamento_text);
        cargar_datos_contacto(recyclerViewModel.getContactoPosicion(getArguments().getInt("POSICION")));

        return view;



    }
    public void cargar_datos_contacto(Contacto contacto){
        nombre.setText(contacto.getNombre()+" "+contacto.getApellido());
        email.setText(contacto.getEmail());

        departamento.setText(getArguments().getString("DEPARTAMENTO"));

    }
}
