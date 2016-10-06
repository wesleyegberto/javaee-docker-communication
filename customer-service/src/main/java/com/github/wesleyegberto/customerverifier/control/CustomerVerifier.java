package com.github.wesleyegberto.customerverifier.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wesley
 */
public class CustomerVerifier {
    
    private static List<String> DIRTY_CUSTOMERS;
    
    static {
        DIRTY_CUSTOMERS = Collections.unmodifiableList(Arrays.asList(new String[] {
            "05211005260", "64831368628", "47612469790", "71925263479"
        }));
    }
    
    public boolean isCustomerClear(String cpf) {
        if(!CpfValidator.isValid(cpf))
            throw new BusinessException("Invalid CPF");
        
        return !DIRTY_CUSTOMERS.contains(cpf);
    }
}
