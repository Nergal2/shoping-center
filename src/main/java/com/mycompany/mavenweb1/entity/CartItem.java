/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Администратор
 */
@XmlRootElement
public class CartItem implements Serializable {
 //   @Id
    @Basic(optional = false)
 //   @NotNull
//    @Column(name = "rec")
    private Recipe rec; 
    
 //   @Id
    @Basic(optional = false)
 //   @NotNull
//    @Column(name = "numb")
    private int numb;  
    
    public CartItem() {
    }

    public Recipe getRec() {
        return rec;
    }

    public void setRec(Recipe rec) {
        this.rec = rec;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
}
