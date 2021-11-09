package edu.upc.dsa;

import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerImpl implements GameManager{

    HashMap<String, User> listausers = new HashMap<String,User>();
    List<PuntoInteres> listapuntosinteres = new LinkedList<>();
    static final Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    private static GameManagerImpl manager;

    //Singleton
    public static GameManagerImpl getInstance(){
        if(manager==null){
            manager=new GameManagerImpl();
        }
        return manager;
    }
    public GameManagerImpl(){}


    @Override
    public void addUser(String nom, String id, List<PuntoInteres> p) {
        User user = new User(nom,id,p);
        listausers.put(id,user);
        logger.info("S'ha afegit un nou usuari: "+user.getNombre()+" "+user.getId()+" "+user.getPuntoInteresList());
    }

    @Override
    public List<User> listaUsersOrdenados() {
        List<User> r = new LinkedList<>(listausers.values());
        Collections.sort(r, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
        logger.info("Llista ordenada alfabeticament: "+r.toString());
        return r;
    }

    @Override
    public User consultarUser(String name) {
        User user = getUserByName(name);
        logger.info("Nom: "+user.getNombre()+" ID: "+user.getId()+" Punto d'Interes: "+user.getPuntoInteresList());
        return user;
    }

    @Override
    public void addPuntoInteresToUser(String user, String pI) {
        User u = getUserByName(user);
        PuntoInteres punto = getPuntoInteresByName(pI);
        List<PuntoInteres> lista = u.getPuntoInteresList();
        if(u!=null){
            logger.info("Usuari Trobat: "+user);
            lista.add(punto);
            punto.addUserToPuntoInteres(u);
            u.setPuntoInteresList(lista);
        }
        logger.info("Usuari no Trobat");
    }

    @Override
    public List<PuntoInteres> getlistaPuntoInteres(String user) {
        List<PuntoInteres> lista = getlistaPuntoInteres(user);
        return lista;
    }

    @Override
    public List<User> getUsers(String pI) {
        PuntoInteres puntoInteres = getPuntoInteresByName(pI);
        return puntoInteres.getUserList();
    }

    @Override
    public List<User> getUsersOrdenados() {
        List<User> list = new LinkedList<>(listausers.values());
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getNumPuntos()-o2.getNumPuntos();
            }
        });
        logger.info("Llista usuaris ordenats per numero de punts d'interes: "+list.toString());
        return list;
    }

    @Override
    public void clear() {
        listapuntosinteres.clear();
        listausers.clear();
    }

    @Override
    public User getUserByName(String n) {
        User user = this.listausers.get(n);
        return user;
    }

    @Override
    public PuntoInteres getPuntoInteresByName(String pI) {
        PuntoInteres pIt=null;
        for(PuntoInteres puntoInteres: this.listapuntosinteres){
            if(puntoInteres.getPunto().compareTo(pI)==0){
                pIt=puntoInteres;
            }
        }
        return pIt;
    }
}
