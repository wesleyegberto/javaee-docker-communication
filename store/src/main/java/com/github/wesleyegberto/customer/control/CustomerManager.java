package com.github.wesleyegberto.customer.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

/**
 *
 * @author wesley
 */
public class CustomerManager {
    private final WebTarget target;

    public CustomerManager() {
    	// customerservice is the name of docker container
        target = ClientBuilder.newClient().target("http://customerservice:8080/customer-service");
    }
    
    public boolean verifyCustomer(String cpf) {
        Response response = target.path("/resources/customers/{cpf}/verify")
                            .resolveTemplate("cpf", cpf)
                            .request().get();
        
        // 204 - Customer clear (without any record)
        if(response.getStatus() == NO_CONTENT.getStatusCode()) {
            return true;
        // 200 - Customer not clear
        } else if(response.getStatus() == ACCEPTED.getStatusCode()) {
            String rawResponse = response.readEntity(String.class);
            // Could access the returned entity to obtain more information about the records
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<String, Object> map = mapper.readValue(
                        rawResponse,
                        new TypeReference<Map<String, String>>() {});
                System.out.println("Customer status: " + map.get("status"));
                return "true".equals(map.get("status"));
            } catch(IOException ex) {
                throw new IllegalArgumentException("An error has occurred during the checking.", ex);
            }
        } else if(response.getStatus() == BAD_REQUEST.getStatusCode()) {
            throw new IllegalArgumentException(response.readEntity(String.class));
        }
        
        return false;
    }
}
