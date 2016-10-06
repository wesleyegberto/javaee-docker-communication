package com.github.wesleyegberto.customerverifier.control;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;


public class CpfValidatorUT {

	@Test
	public void testIsValid() throws Exception {
		String cpf = "";
		boolean result;
		
		result = CpfValidator.isValid(cpf);
		assertThat(result, is(false));
		
		cpf = "1";
		result = CpfValidator.isValid(cpf);
		assertThat(result, is(false));
		
		cpf = "0521100";
		result = CpfValidator.isValid(cpf);
		assertThat(result, is(false));
		
		cpf = "0521100526";
		result = CpfValidator.isValid(cpf);
		assertThat(result, is(false));

		cpf = "05211005260";
		result = CpfValidator.isValid(cpf);
		assertThat(result, is(true));

		cpf = "052110052612";
		result = CpfValidator.isValid(cpf);
		assertThat(result, is(false));
		
	}
}