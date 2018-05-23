/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Администратор
 */
@Entity //(name = "cartdb")
@Table(name = "cartdb")
public class Cartdb implements Serializable{
    
    @NotNull
    @Column(name = "name")
    private String name; 
    
    @NotNull
    @Column(name = "email")
    private String email;     

    @NotNull
    @Column(name = "sex")
    private String sex;    
    
    @NotNull
    @Column(name = "price")
    private int price; 

    @Id
    @NotNull
    @Column(name = "orderid")
    private int orderid;
    
    @NotNull
    @OneToMany(targetEntity = Cartrecipedb.class, cascade = CascadeType.PERSIST)
    
    private Set<Cartrecipedb> cartrecipedb=new HashSet<>();
    
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    
    public Cartdb() {
    }
    
    public Cartdb(String name, int price, String email, String sex,int orderid) {
        this.name=name;
        this.price=price;
        this.email=email;
        this.sex=sex;
        this.orderid=orderid;
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
        if (!(object instanceof Cartdb)) {
            return false;
        }
        Cartdb other = (Cartdb) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }  

    public Set<Cartrecipedb> getCartrecipedb() {
        return cartrecipedb;
    }

    public void setCartrecipedb(Set<Cartrecipedb> cartrecipedb) {
        this.cartrecipedb = cartrecipedb;
    }
}
