package com.example.projecterecum8.ViewModel;

import android.text.style.TtsSpan;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.projecterecum8.Data.Repository;
import com.example.projecterecum8.Model.Contacto;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class RecyclerViewModel extends ViewModel {
    Repository repo;
    ArrayList<Contacto> listacont= new ArrayList<>();
    MutableLiveData<ArrayList<Contacto>> livedatalistacont;

    public RecyclerViewModel() {
        repo = Repository.getRepository();
        listacont = new ArrayList<>();
        livedatalistacont= new MutableLiveData<ArrayList<Contacto>>();

    }

   public void getContactosDepartamento(String departamento) {
        repo.getContactosDepartamento(departamento);
        repo.getLiveContactos().observeForever(new Observer<ArrayList<Contacto>>() {
            @Override
            public void onChanged(ArrayList<Contacto> contactos) {
                listacont = contactos;
                livedatalistacont.postValue(listacont);

            }
        });
    }
    public LiveData<ArrayList<Contacto>> getContactolist(){
        return livedatalistacont;
    }

    public ArrayList<Contacto> getListacont() {
        return listacont;
    }
    public Contacto getContactoPosicion(int position){
        return listacont.get(position);
    }
}
