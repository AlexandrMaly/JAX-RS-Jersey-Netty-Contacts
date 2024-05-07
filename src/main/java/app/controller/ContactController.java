package app.controller;

import app.domain.Contact;
import app.network.ResponseEntity;
import app.network.ResponseEntityList;
import app.service.impl.ContactService;
import app.utils.Constants;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("/api/v1/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactController {

    ContactService service = new ContactService();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Contact contact) {
        Contact contactCreated = service.create(contact);
        if (contactCreated != null) {
            return Response.ok(new ResponseEntity<>(201, "Created",
                            true, contactCreated).toString())
                    .status(Response.Status.CREATED).build();
        } else {
            return Response.ok(new ResponseEntity<>(204, "No Content",
                            false, Constants.TEXT_NO_CONTENT).toString())
                    .status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    public Response fetchAll() {
        List<Contact> list = service.fetchAll();
        if (!list.isEmpty()) {
            return Response.ok(new ResponseEntityList<>(200, "OK",
                            true, list).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntityList<>(404, "Not Found",
                            false, Collections.emptyList()).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id: [0-9]+}")
    public Response fetchById(@PathParam("id") Long id) {
        Contact contact = service.fetchById(id);
        if (contact != null) {
            return Response.ok(new ResponseEntity<>(200, "OK",
                            true, contact.toString()).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntity<>(404, "Not Found",
                            true, Constants.TEXT_NOT_FOUND).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Contact contact) {
        Contact contactToUpdate = service.fetchById(id);
        if (contactToUpdate != null) {
            Contact contactUpdated = service.update(id, contact);
            return Response.ok(new ResponseEntity<>(200, "OK",
                            true, contactUpdated).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntity<>(404, "Not Found",
                            true, Constants.TEXT_NOT_FOUND).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response delete(@PathParam("id") Long id) {
        if (service.delete(id)) {
            return Response.ok(new ResponseEntity<>(200, "OK",
                            true, Constants.TEXT_DELETED).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntity<>(404, "Not Found",
                            true, Constants.TEXT_NOT_FOUND).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

}
