package org.example.jersey.jersry;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Path("/hello")
@Component
public class JerseyController {


    private final SecondService service;

    public JerseyController(SecondService service) {
        this.service = service;
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String sayHello() {
        return "Hello, Jersey!";
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public ResponseEntity<?> listOfString() {
        List<String> strings = List.of("1", "2", "3", "4");
        return ResponseEntity.ok(strings);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public String createString(@QueryParam("name") String name) {
        return "Hello my name is " + name;
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateString(@QueryParam("name") String name) {
        String oldName = "Hello";
        oldName = name;
        return oldName;
    }

    @POST
    @Path("/create/dto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Dto createDto(Dto dto) {
        return dto;
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> deleteIntegerByIndex(@QueryParam("id") Integer id) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
        list.remove(id);
        System.out.println(("deleted this index : " + id));
        return list;
    }

    @GET
    @Path("/injectString")
    @Produces(MediaType.TEXT_PLAIN)
    public String injectString(@QueryParam("s") String s) {
        return service.getString(s);
    }

    @GET
    @Path("/getDto")
    @Produces(MediaType.APPLICATION_JSON)
    public Dto getDto() {
        Dto dto = new Dto();
        dto.setId(1);
        dto.setName("Jersey");
        dto.setUsername("Username");
        return dto;
    }
}

