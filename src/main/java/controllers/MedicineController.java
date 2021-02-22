package controllers;

import entities.Medicine;
import repositories.interfaces.IMedicineRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("medicines")
public class MedicineController {
    @Inject
    private IMedicineRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicine() {
        List<Medicine> medicines;
        try {
            medicines = repo.getAllMedicine();
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicine(Medicine medicine) {
        boolean created;
        try {
            created = repo.createMedicine(medicine);
        } catch (ServerErrorException ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Medicine cannot be created!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Medicine created successfully!")
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") int id) {
        Medicine medicine;
        try {
            medicine = repo.getByID(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (medicine == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Medicine does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicine)
                .build();
    }
    @GET
    @Path("/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) {
        Medicine medicine;
        try {
            medicine = repo.getByName(name);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (medicine == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Medicine does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicine)
                .build();
    }
    @GET
    @Path("/get/company/{manufacturer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByManufacturer(@PathParam("manufacturer") String manufacturer) {
        List<Medicine> medicine;
        try {
            medicine = repo.getByManufacturer(manufacturer);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (medicine == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Medicine does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicine)
                .build();
    }
    @GET
    @Path("/expired")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExpired() {
        List<Medicine> medicines;
        try {
            medicines = repo.getExpired();
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }
    @GET
    @Path("/checkorder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkOrder(@PathParam("id") int id) {
        boolean checker;
        try {
            checker = repo.checkOrder(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(checker)
                .build();
    }
    @GET
    @Path("/removeByID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeByID(@PathParam("id") int id) {
        boolean deleted;
        try {
            deleted = repo.removeByID(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (deleted == false) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Medicine does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(deleted ? "Medicine was removed!" : "Medicine creation was failed! Such Medicine already exists!")
                .build();
    }
}