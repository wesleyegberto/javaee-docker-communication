package com.github.wesleyegberto.customerverifier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.github.wesleyegberto.customerverifier.control.CpfValidatorUT;
import com.github.wesleyegberto.customerverifier.control.CustomerVerifierUT;

@RunWith(Suite.class)
@Suite.SuiteClasses(

{ CustomerVerifierUT.class, CpfValidatorUT.class })
public class TestSuite { // nothing
}
