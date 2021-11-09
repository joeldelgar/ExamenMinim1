package edu.upc.dsa;

import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.User;

import java.util.List;

public interface GameManager {

    public void addUser(String nom, String id, List<PuntoInteres> puntoInteres);
    //Añadir un usuario
    public List<User> listaUsersOrdenados();
    //Listado de usuarios ordenado alfabéticamente
    public User consultarUser(String name);
    //Consultar información de un usuario
    public void addPuntoInteresToUser(String user, String pI);
    //Informar que un usuario pasa por un punto de interés
    public List<PuntoInteres> getlistaPuntoInteres(String user);
    //Consultar los puntos de interés por los que un usuario pasa
    public List<User> getUsers(String pI);
    //Listado de usuarios que han pasado por un punto de interés
    public List<User> getUsersOrdenados();

    //Funcions Auxiliars
    public void clear();
    public User getUserByName(String n);
    public PuntoInteres getPuntoInteresByName(String pI);


}
