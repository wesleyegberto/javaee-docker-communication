package com.github.wesleyegberto.order.control;

import com.github.wesleyegberto.customer.control.CustomerManager;
import com.github.wesleyegberto.order.entity.Order;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class OrderProcessor {

    @Inject 
    private CustomerManager customerManager;

    public Order createOrderForCustomer(String cpf) {
        
        if(!customerManager.verifyCustomer(cpf)) {
            throw new BusinessException("The customer can't create an order");
        }
        return new Order(generateOrderNumber(), cpf);
    }

    private int generateOrderNumber() {
        return 1000 + (int) Math.ceil(Math.random() * 1000);
    }
}
