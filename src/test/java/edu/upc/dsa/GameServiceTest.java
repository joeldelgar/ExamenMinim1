package edu.upc.dsa;


import java.util.logging.Logger;

public class GameServiceTest {
    private static Logger logger = Logger.getLogger(String.valueOf(GameServiceTest.class));
    GameManager manager = GameManagerImpl.getInstance();

    public void SetUp(){
        manager.addUser("Joel","1",null);
        manager.addUser("Maria", "2",null);
        manager.addPuntoInteresToUser("Joel","Puente");
        manager.addPuntoInteresToUser("Maria", "Porta1");

    }

    public void tearDown(){
        manager.clear();
    }

    public void testPuntoInteres(){
        manager.addPuntoInteresToUser("Joel","Porta1");
    }

}
