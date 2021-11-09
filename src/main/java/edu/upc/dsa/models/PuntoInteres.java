package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class PuntoInteres {
    private String punto;
    public List<User> userList = new ArrayList<>();

    public PuntoInteres(){}
    public PuntoInteres(String p, List<User> list){
        this.punto = p;
        this.userList = list;
    }

    public void addUserToPuntoInteres(User user){
        this.userList.add(user);
    }

    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
