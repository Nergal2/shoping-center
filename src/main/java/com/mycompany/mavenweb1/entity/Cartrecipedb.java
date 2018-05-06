/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Администратор
 */
@Entity
@Table(name = "cartrecipedb")
public class Cartrecipedb implements Serializable{
    @Id
    @NotNull
    @Column(name = "orderid")
    private int orderid;
    
    @Id
    @NotNull
    @Column(name = "id")
    private int id;
    
    @NotNull
    @Column(name = "name")
    private String name; 
    
    @NotNull
    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String  description; 

    @Column(name = "imagepath")    
    private String imagepath;
    
    @NotNull
    @Column(name = "numb")
    private int numb;
    
    public Cartrecipedb() {
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartrecipedb)) {
            return false;
        }
        Cartrecipedb other = (Cartrecipedb) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

}
