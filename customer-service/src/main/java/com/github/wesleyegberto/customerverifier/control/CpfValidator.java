package com.github.wesleyegberto.customerverifier.control;

/**
 *
 * @author wesley
 */
public class CpfValidator {

    public static boolean isValid(String cpf) {
        return (cpf == null || cpf.length() != 11);
    }
    
}
