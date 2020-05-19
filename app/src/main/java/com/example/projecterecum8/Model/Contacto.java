package com.example.projecterecum8.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Contacto {
    private String Nombre, Apellido, email, urlfoto;

    public Contacto(String nombre, String apellido, String email, String urlfoto) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.email = email;
        this.urlfoto = urlfoto;
    }
    public Contacto(){

    }

    public Contacto(String nombre, String apellido, String email) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.email = email;
        this.urlfoto = "";
    }

    public Contacto(JSONObject jsonObject){
        try {
            Nombre = jsonObject.getString("nombre");
            Apellido = jsonObject.getString("apellido");
            email = jsonObject.getString("email");
            urlfoto = jsonObject.getString("urlfoto");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean checkInpout() {
        if(Nombre.equalsIgnoreCase("") || Apellido.equalsIgnoreCase("") || email.equalsIgnoreCase("") ) {
            return false;
        }else return true;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}
