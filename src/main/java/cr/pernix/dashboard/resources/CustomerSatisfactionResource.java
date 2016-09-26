package cr.pernix.dashboard.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cr.pernix.dashboard.models.CustomerSatisfaction;
import cr.pernix.dashboard.services.CustomerSatisfactionService;

@Path("costumerSatisfaction")
public class CustomerSatisfactionResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() throws Exception {
        List<CustomerSatisfaction> result = CustomerSatisfactionService.getInstance().get();
        GenericEntity<List<CustomerSatisfaction>> list = new GenericEntity<List<CustomerSatisfaction>>(result) {
        };
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") int id) throws Exception {
        CustomerSatisfaction customerSatisfaction = CustomerSatisfactionService.getInstance().get(id);
        return Response.ok(customerSatisfaction).build();
    }

    @POST
    public Response create(CustomerSatisfaction customerSatisfaction) throws Exception {
        CustomerSatisfactionService.getInstance().save(customerSatisfaction);
        return Response.ok().build();
    }

    @PUT
    public Response update(CustomerSatisfaction customerSatisfaction) throws Exception {
        CustomerSatisfactionService.getInstance().save(customerSatisfaction);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) throws Exception {
        CustomerSatisfactionService.getInstance().delete(id);
        return Response.ok().build();
    }
}