package com.example.projecterecum8.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.projecterecum8.Data.Repository;
import com.example.projecterecum8.Model.Contacto;

public class AddContactViewModel extends ViewModel {
    Repository repository;
    public AddContactViewModel(){
        repository= Repository.getRepository();
    }

    public void addNewContact(Contacto contacto) {
        repository.addNewCOntact(contacto);
    }
}
