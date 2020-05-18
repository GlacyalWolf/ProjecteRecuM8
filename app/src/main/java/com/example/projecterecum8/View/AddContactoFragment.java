package com.example.projecterecum8.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projecterecum8.Data.Repository;
import com.example.projecterecum8.Model.Contacto;
import com.example.projecterecum8.R;
import com.example.projecterecum8.ViewModel.AddContactViewModel;
import com.example.projecterecum8.ViewModel.GalleryViewModel;
import com.example.projecterecum8.ViewModel.RecyclerViewModel;

public class AddContactoFragment extends Fragment {
    private EditText nombre,email,departamento,apellido;
    private ImageView fotoperfil;
    private Button save,botonfoto;

    AddContactViewModel addContactViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Repository repo= Repository.getRepository();
        // Inflate the layout for this fragment
        addContactViewModel =
                ViewModelProviders.of(this).get(AddContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_contacto, container, false);
        nombre= root.findViewById(R.id.nombreedittext);
        email= root.findViewById(R.id.emailedittext);
        departamento= root.findViewById(R.id.cargoedittext);
        departamento.setText(getArguments().getString("DEPARTAMENTO"));
        apellido= root.findViewById(R.id.apellidoedittext);
        save= root.findViewById(R.id.guardarContacto);
        botonfoto= root.findViewById(R.id.escojerimagen);
        fotoperfil = root.findViewById(R.id.fotoadd);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_home);


            }
        });

        botonfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto = new Contacto(nombre.getText().toString(),apellido.getText().toString(),
                        email.getText().toString());

                if(contacto.checkInpout()){

                    addContactViewModel.addNewContact(contacto);
                }


            }
        });






        return root;
    }
}
