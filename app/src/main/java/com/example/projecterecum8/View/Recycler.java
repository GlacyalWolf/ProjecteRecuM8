package com.example.projecterecum8.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecterecum8.Model.Contacto;
import com.example.projecterecum8.R;
import com.example.projecterecum8.ViewModel.RecyclerViewModel;
import com.example.projecterecum8.ViewModel.recyclerViewAdapter;

import java.util.ArrayList;


public class Recycler extends Fragment {
    private RecyclerViewModel recyclerViewModel;
    private RecyclerView miRecycler;
    private recyclerViewAdapter miAdapter;
    private ArrayList<Contacto> listaContactos= new ArrayList<>();
    private Spinner spinner;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        recyclerViewModel = ViewModelProviders.of(this).get(RecyclerViewModel.class);

        View root= inflater.inflate(R.layout.fragment_recycler, container, false);

        miRecycler=root.findViewById(R.id.recycleContactos);
        miRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        miAdapter = new recyclerViewAdapter(listaContactos);
        miRecycler.setAdapter(miAdapter);




        spinner= root.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.departamentos, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //listener para comucarmos con VM
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recyclerViewModel.getContactosDepartamento(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerViewModel.getContactolist().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contacto>>() {
            @Override
            public void onChanged(ArrayList<Contacto> contactos) {

                listaContactos= contactos;
                miAdapter.notifyDataSetChanged();
            }
        });






        return root;

    }







}
