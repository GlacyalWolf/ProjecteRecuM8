package com.example.projecterecum8.Data;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projecterecum8.Model.Contacto;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class Repository {
    private static Repository srepository;
    private Context context;
    private String departamentoactual;

    public String getDepartamentoactual() {
        return departamentoactual;
    }

    private MutableLiveData<ArrayList<Contacto>> listacontactosdept;

    private Repository(Context context){
        this.context = context;
        listacontactosdept = new MutableLiveData<>();

    }
    public static Repository get(Context context){
        if(srepository == null){
            srepository = new Repository(context);
        }
        return srepository;
    }
    public static Repository getRepository(){return srepository;}

    public void getContactosDepartamento(String departamento) {
        //thread de consulta
        myThread thread = new myThread();
        thread.execute("https://projecterecum8.firebaseio.com/JDA/"+departamento+"/value.json");
        departamentoactual = departamento;



    }
    public LiveData<ArrayList<Contacto>> getLiveContactos(){
        return listacontactosdept;
    }

    public void addNewCOntact(Contacto contacto) {


        ArrayList<Contacto> arraycontactos = listacontactosdept.getValue();
        arraycontactos.add(contacto);
        FirebaseDatabase.getInstance().getReference().child("JDA").child(departamentoactual).child("value").setValue(listacontactosdept);



    }

    public void uploadURIfoto(Uri uri) {

        UUID nombreAleat = UUID.randomUUID();
        final StorageReference ref = FirebaseStorage.getInstance().getReference().child("images").child(nombreAleat+",jpg");
        UploadTask uploadTask = ref.putFile(uri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    writeURLFoto(downloadUri.toString());

                } else {
                    // Handle failures
                    // ...
                }
            }
        });

    }

    private void writeURLFoto(String uri) {

        int posicion = listacontactosdept.getValue().size()-1;
        FirebaseDatabase.getInstance().getReference().child("JDA").child(departamentoactual).child("value")
                .child(String.valueOf(posicion)).child("urlfoto").setValue(uri);
    }

    public class myThread extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection;
            URL url;
            String result;
            result ="";

            try{

                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while(data != -1){
                    result += (char) data;
                    data = inputStream.read();
                }

            }catch (Exception e){

                e.printStackTrace();

            }

            Log.i("RESULT", result);



            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Contacto> listcontact= new ArrayList<>();
            // tratamiento del json
            try {
                JSONArray jsonArray = new JSONArray(s);
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);
                    // passar a arraylist de Contactos

                    Contacto contacto = new Contacto(jsonObject);
                    listcontact.add(contacto);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listacontactosdept.postValue(listcontact);
        }
    }
}
