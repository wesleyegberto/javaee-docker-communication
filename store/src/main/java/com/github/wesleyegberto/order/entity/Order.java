package com.github.wesleyegberto.order.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wesley
 */
@XmlRootElement
public class Order {
    private long id;
    private int orderNumber;
    private String cpf;

    public Order() {
    }

    public Order(int orderNumber, String cpf) {
        this.orderNumber = orderNumber;
        this.cpf = cpf;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
