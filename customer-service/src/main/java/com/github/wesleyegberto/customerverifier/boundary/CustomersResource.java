package com.github.wesleyegberto.customerverifier.boundary;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.wesleyegberto.customerverifier.control.BusinessException;
import com.github.wesleyegberto.customerverifier.control.CustomerVerifier;

/**
 *
 * @author wesley
 */
@Path("/customers")
public class CustomersResource {

    @Inject
    private CustomerVerifier verifier;

    public CustomersResource() {
	}
    
    public CustomersResource(CustomerVerifier verifier) {
        this.verifier = verifier;
    }
    
    @GET
    @Path("/{cpf}/verify")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyCustomer(@PathParam("cpf") String cpf) {
        Response response = null;
        
        try {
            boolean isClear = verifier.isCustomerClear(cpf);
            
            if(isClear) {
                response = Response.noContent().build();
            } else {
                response = Response.ok(Json.createObjectBuilder().add("status", isClear).build()).build();
            }
        } catch(BusinessException be) {
            response = Response.status(BAD_REQUEST).entity(be.getMessage()).build();
        }
        return response;
    }
}
