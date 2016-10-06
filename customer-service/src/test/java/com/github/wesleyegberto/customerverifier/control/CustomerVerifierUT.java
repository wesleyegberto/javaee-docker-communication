package com.github.wesleyegberto.customerverifier.control;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class CustomerVerifierUT {

	private CustomerVerifier cu;
	
	@Before
	public void setup() {
		cu = new CustomerVerifier();
	}
	
	@Test
	public void test_isCustomerClear() {
		assertThat(cu.isCustomerClear("05211005260"), is(false));
		assertThat(cu.isCustomerClear("64831368628"), is(false));
		assertThat(cu.isCustomerClear("47612469790"), is(false));
		assertThat(cu.isCustomerClear("71925263479"), is(false));
	}

}
