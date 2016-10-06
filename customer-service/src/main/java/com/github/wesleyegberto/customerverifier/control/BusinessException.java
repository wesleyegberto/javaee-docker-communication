package com.github.wesleyegberto.customerverifier.control;

/**
 *
 * @author wesley
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1395370594071743206L;

	public BusinessException(String message) {
        super(message);
    }
    
}
