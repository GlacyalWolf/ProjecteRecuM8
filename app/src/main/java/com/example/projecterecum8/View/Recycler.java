package com.example.projecterecum8.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
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
    private String departamento;
    private ArrayList<Contacto> listaContactos= new ArrayList<>();
    private Spinner spinner;
    private Button adduser;

    public String getDepartamento() {
        return departamento;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //recyclerViewModel = ViewModelProviders.of(this).get(RecyclerViewModel.class);
        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);
        View root= inflater.inflate(R.layout.fragment_recycler, container, false);

        miRecycler=root.findViewById(R.id.recycleContactos);
        miRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        miAdapter = new recyclerViewAdapter(listaContactos);
        miRecycler.setAdapter(miAdapter);
        adduser= root.findViewById(R.id.adduser);




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
                departamento = parent.getItemAtPosition(position).toString();
                recyclerViewModel.getContactosDepartamento(departamento);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerViewModel.getContactolist().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contacto>>() {
            @Override
            public void onChanged(ArrayList<Contacto> contactos) {

                miAdapter.setContactos(contactos);
                miAdapter.notifyDataSetChanged();
            }
        });
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                bundle.putString("DEPARTAMENTO",departamento);
                Navigation.findNavController(v).navigate(R.id.fragment_add_contacto,bundle);
            }
        });






        return root;

    }







}
