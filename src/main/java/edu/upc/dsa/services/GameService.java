package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Game", description = "Endpoint to Text Service")
@Path("/users")
public class GameService {
    private final GameManager manager;

    public GameService(){
        this.manager  = GameManagerImpl.getInstance();
        if(true){
            this.manager.addUser("Joel","1",null);
            this.manager.addUser("Maria", "2", null);
            manager.addPuntoInteresToUser("Joel","Puente");
            manager.addPuntoInteresToUser("Maria", "Porta1");
        }
    }

    //Afegir un usuari
    @POST
    @ApiOperation(value = "Afegir un Usuari", notes = "Afegir un Usuari")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")
    })

    @Path("/addUser/{User}{id}{Punto de Interes}")
    public Response addUser(@PathParam("User") String user, @PathParam("id") String id, @PathParam("Punto de Interes") List<PuntoInteres> punto) {

        User u = manager.getUserByName(user);
        manager.addUser(user,id,punto);
        if (u == null || id == null){
            return Response.status(500).build();
        }else{
            return Response.status(201).build();
        }
    }

    //Usuaris Ordenats
    @GET
    @ApiOperation(value = "Donam els Usuaris Ordenats Alfabeticament", notes = "Usuaris Ordenats")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "List Not Found")
    })
    @Path("/getUsersOrdenats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersOrdenats() {
        List<User> users = this.manager.listaUsersOrdenados();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    //Consulta un Usuari
    @GET
    @ApiOperation(value = "Consultar un Usuari", notes = "Consultar Usuari")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User Not Found")
    })
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("Nom") String name) {
        User t = this.manager.getUserByName(name);
        if (t == null){
            return Response.status(404).build();
        }
        else{
            return Response.status(201).entity(t).build();
        }
    }

    //Punts d'Interes d'un usuari
    @GET
    @ApiOperation(value = "Donam els punts d'interes d'un Usuari", notes = "Punts d'Interes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class),
            @ApiResponse(code = 404, message = "List Not Found")
    })
    @Path("/getPuntInteresByUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntInteresByUser(@PathParam("Nom") String name) {
        User t = this.manager.getUserByName(name);
        List<PuntoInteres> list = t.getPuntoInteresList();
        if (list.size() == 0){
            return Response.status(404).build();
        }
        else {
            GenericEntity<List<PuntoInteres>> entity = new GenericEntity<List<PuntoInteres>>(list){};
            return Response.status(201).entity(entity).build();
        }
    }

    //Usuaris que han passat per un punt d'interés .
    @GET
    @ApiOperation(value = "Donam els Usuaris que ha passat per un Punt d'interes", notes = "Usuaris per punt d'interes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class),
            @ApiResponse(code = 404, message = "List Not Found")
    })
    @Path("/getUsersByPuntInteres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByPuntInteres(@PathParam("puntInteres") String p) {
        PuntoInteres puntoInteres = this.manager.getPuntoInteresByName(p);
        List<User> list = puntoInteres.getUserList();
        if (list.size()== 0){
            return Response.status(404).build();
        }
        else {
            GenericEntity<List<User>> entity = new GenericEntity<List<User>>(list) {};
            return Response.status(201).entity(entity).build();
        }
    }

    //Usuaris ordenats per punts d'interés .
    @GET
    @ApiOperation(value = "Donam els Usuaris ordenats per el numero de punts d'interes", notes = "Usuaris ordenats")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class),
            @ApiResponse(code = 404, message = "List Not Found")
    })
    @Path("/getUsersOrdenatsByPuntInteres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersOrdenatsByPuntInteres(){
        List<User> userList = this.manager.getUsersOrdenados();
        if (userList.size()== 0){
            return Response.status(404).build();
        }
        else {
            GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userList) {};
            return Response.status(201).entity(entity).build();
        }
    }
}
