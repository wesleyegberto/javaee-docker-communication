package com.github.wesleyegberto.order.boundary;

import com.github.wesleyegberto.order.control.BusinessException;
import com.github.wesleyegberto.order.control.OrderProcessor;
import com.github.wesleyegberto.order.entity.Order;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.*;

/**
 * @author wesley
 */
@Path("/orders")
public class OrdersResource {
    
    @Inject
    private OrderProcessor orderProcessor;
    
    @POST
    @Path("/prepare/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prepareOrderForCustomer(@PathParam("cpf") String cpf) {
        Response response = null;
        
        try {
            Order order = orderProcessor.createOrderForCustomer(cpf);
        
            if(order != null)
                response = Response.ok(order).build();
            else
                response = Response.noContent().build();
        } catch(BusinessException bex) {
            response = Response.status(FORBIDDEN).entity(bex.getMessage()).build();
        } catch(IllegalArgumentException ex) {
            response = Response.status(BAD_REQUEST).entity(ex.getMessage()).build();
        }
        return response;
    }
}
