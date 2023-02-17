package org.acme.controller;

import org.acme.dto.CustomerDto;
import org.acme.service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    @POST
    @Transactional
    public Response saveCustomer(CustomerDto dto) {
        try {
            customerService.createCustomer(dto);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, CustomerDto dto) {
        try {
            customerService.updateCustomer(id, dto);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
