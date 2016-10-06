package com.github.wesleyegberto.customerverifier.boundary;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.airhacks.JAXRSConfiguration;
import com.github.wesleyegberto.customerverifier.control.CustomerVerifier;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

@RunWith(Arquillian.class)
public class CustomersResourceIT {
	@Inject
	private CustomerVerifier customerVerifier;
	
	@ArquillianResource
	private URI deploymentUri;
	
	@Deployment
	public static Archive<?> deploy() {
		return ShrinkWrap.create(JavaArchive.class, "CustomerService_Test.jar")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addPackage(CustomerVerifier.class.getPackage())
				.addClass(JAXRSConfiguration.class)
				.addClass(CustomersResource.class);
	}
	
	@Test
	public void testIsDeployed() {
		Assert.assertNotNull(deploymentUri);
		Assert.assertNotNull(customerVerifier);
	}
	
	@Test
	// will executed outside of WAR so that will allow the injection of WebTarget
	@RunAsClient
	public void testDirtyCustomer(@ArquillianResteasyResource("resources") WebTarget webTarget) throws Exception {
		final Response response = webTarget.path("/customers/{cpf}/verify")
											.resolveTemplate("cpf", "05211005260")
									        .request(MediaType.APPLICATION_JSON)
									        .get();
		String rawBody = response.readEntity(String.class);
		
		assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
		assertThat(rawBody, is("{\"status\":false}"));
	}

	@Test
	@RunAsClient
	public void testCleanCustomer(@ArquillianResteasyResource("resources") WebTarget webTarget) throws Exception {
		final Response response = webTarget.path("/customers/{cpf}/verify")
											.resolveTemplate("cpf", "06355215899")
									        .request(MediaType.APPLICATION_JSON)
									        .get();
		String rawBody = response.readEntity(String.class);
		
		assertThat(response.getStatus(), is(Status.NO_CONTENT.getStatusCode()));
		assertThat(rawBody, is(""));
	}
	
	@Test
	// will executed outside of WAR so that will make a valid request
	@RunAsClient
	public void should_get_dirty_customer() {
		given()
			.pathParam("cpf", "05211005260")
		.when()
			.get("/test/resources/customers/{cpf}/verify")
		.then()
			.body("status", equalTo(false));
	}

	@Test
	@RunAsClient
	public void should_get_no_content_for_clean_customer() {
		given()
			.pathParam("cpf", "05201005225")
		.when()
			.get("/test/resources/customers/{cpf}/verify")
		.then()
			.statusCode(Status.NO_CONTENT.getStatusCode());
	}
}