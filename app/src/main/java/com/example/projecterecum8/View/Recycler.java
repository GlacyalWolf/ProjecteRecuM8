package com.example.projecterecum8.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
    private ArrayList<Contacto> listaContactos= new ArrayList<>();
    private Spinner spinner;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recyclerViewModel = ViewModelProviders.of(this).get(RecyclerViewModel.class);
        View root= inflater.inflate(R.layout.fragment_recycler, container, false);
        Contacto x= new Contacto("jose","manuel","asdasdasd","asdada");
        listaContactos.add(x);
        RecyclerView rv = root.findViewById(R.id.recycleContactos);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        recyclerViewAdapter rva= new recyclerViewAdapter(listaContactos);
        rv.setAdapter(rva);

        spinner= root.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.departamentos, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //listener para comucarmos con VM




        return root;

    }



}
