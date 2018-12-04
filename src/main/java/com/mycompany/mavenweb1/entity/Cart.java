/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1.entity;

import java.io.Serializable;
import java.util.HashMap;
import javax.json.Json;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Администратор
 */
@XmlRootElement
//@Entity
//@Table(name = "cart")
public class Cart implements Serializable {
//    @Id

    @Basic(optional = false)
//    @NotNull
//    @Column(name = "name")
    private String name;

//    @Id
    @Basic(optional = false)
//    @NotNull
//    @Column(name = "email")
    private String email;

    @Basic(optional = false)
//    @NotNull
//    @Column(name = "sex")
    private String sex;

    @Basic(optional = true)
//    @NotNull
//    @Column(name = "cart") 
    private CartItem[] cart;

    @Basic(optional = false)
//    @NotNull
//    @Column(name = "price")
    private int price;

    @Basic(optional = true)
    private String orderId;

    public Cart() {
    }

    public Cart(String name, String email, String sex, int price, String orderId) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.price = price;
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public CartItem[] getCart() {
        return cart;
    }

    public void setCart(CartItem[] cart) {
        this.cart = cart;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
