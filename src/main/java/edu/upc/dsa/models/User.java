package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String nombre;
    private String id;
    public int numPuntos;
    public List<PuntoInteres> puntoInteresList = new ArrayList<>();

    public User(String nom, String id, List<PuntoInteres> list){
        this.id=id;
        this.nombre=nom;
        this.puntoInteresList=list;
    }

    public User(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PuntoInteres> getPuntoInteresList() {
        return puntoInteresList;
    }

    public void setPuntoInteresList(List<PuntoInteres> puntoInteresList) {
        this.puntoInteresList = puntoInteresList;
    }

    public int getNumPuntos(){
        return puntoInteresList.size();
    }

    public void setNumPuntos(int numPuntos) {
        this.numPuntos = numPuntos;
    }
}
