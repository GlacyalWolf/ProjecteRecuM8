package com.example.projecterecum8.ViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecterecum8.Data.Repository;
import com.example.projecterecum8.Model.Contacto;
import com.example.projecterecum8.R;
import com.example.projecterecum8.View.Recycler;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {
    ArrayList<Contacto> listc= new ArrayList<>();





    public recyclerViewAdapter(ArrayList<Contacto> listc) {
        this.listc = listc;

    }
    public void setContactos(ArrayList<Contacto> cont){
        this.listc = cont;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_recycler,viewGroup,false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int i) {
        String nombre= listc.get(i).getNombre();
        String email= listc.get(i).getEmail();

        vh.nombre.setText(nombre);
        vh.email.setText(email);



    }

    @Override
    public int getItemCount() {
        return listc.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre,email;




        public ViewHolder(@NonNull View itemView) {


            super(itemView);

            final Repository repo= Repository.getRepository();
            nombre= itemView.findViewById(R.id.holder_name);
            email= itemView.findViewById(R.id.holder_correo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("POSICION",getAdapterPosition());
                    bundle.putString("DEPARTAMENTO", repo.getDepartamentoactual());
                    Navigation.findNavController(v).navigate(R.id.contactFragment, bundle);

                }
            });
        }
    }
}
