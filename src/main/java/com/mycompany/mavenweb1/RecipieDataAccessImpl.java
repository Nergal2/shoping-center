/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1;

import com.mycompany.mavenweb1.entity.Cart;
import com.mycompany.mavenweb1.entity.CartItem;
import com.mycompany.mavenweb1.entity.Cartdb;
import com.mycompany.mavenweb1.entity.Cartrecipedb;
import com.mycompany.mavenweb1.entity.Recipe;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Администратор
 */
@Stateless
public class RecipieDataAccessImpl {
    @PersistenceContext(unitName = "example2PU")
    EntityManager em;
    
    public List<Recipe> getRecipies () {
        List<Recipe> lst=new ArrayList<Recipe>();
        if (em != null){
        Query q = em.createQuery("select recipe from Recipe recipe");
        lst = q.getResultList();
        }
        return lst;
    }
    
    public String storeAllRecipiesDb(List<Recipe> recipies){
     
    //    em.getTransaction().begin(); 
        if (em != null){
            Query q = em.createQuery("Delete from Recipe recipe");
            q.executeUpdate();
            for (Recipe recp: recipies) {
                em.persist(recp);
            }                
        }
            
    //    em.getTransaction().commit();
    
//    String result = "none";
//    if (em != null){
//        em.persist(rec);
        
     
//        Query q = em.createQuery("select recipe from Recipe recipe where recipe.name = :name");
//        q.setParameter("name", "Fruit");
//        result = q.getResultList().get(0).toString();
    
//          Query q = em.createQuery("select employee from Employee employee where employee.name = :name");
//  	  q.setParameter("name", "Sarah");
//          result= q.getResultList().toString(); 
    //      result =rec.toString();
  //  }
        return "Database: recipies stored";        
    }

    public List<Cartdb> getCarts() {
        List<Cartdb> lst=new ArrayList<Cartdb>();
        if (em != null){
        Query q = em.createQuery("select cartdb from Cartdb cartdb");
        lst = q.getResultList();
        }
        return lst;
    }

    public String storeCartdb(Cartdb cartdb) {
        if (em != null){     
            em.persist(cartdb);           
        }
        return "Database: cart stored";   
    }

    public String storeCartRecipedb(Cartrecipedb cartRecTemp) {
        if (em != null){     
            em.persist(cartRecTemp);           
        } 
       return "Database: recipies cart stored";  
    }
    public CartItem[] getCartRecipiesId(int id){
        Query q = em.createQuery("select cartrecipedb from Cartrecipedb cartrecipedb where cartrecipedb.orderid = :orderid");
	q.setParameter("orderid", id);
        List<Cartrecipedb> result= q.getResultList();
        List<CartItem> cram= new ArrayList<CartItem>();
      //  CartItem[] cram = new CartItem[];
        for (Cartrecipedb res: result) {
            CartItem ci = new CartItem();            
            Recipe rec= new Recipe(res.getId(),res.getName(),res.getPrice());
            rec.setDescription(res.getDescription());
            rec.setImagepath(res.getImagepath());
            
            ci.setNumb(res.getNumb()); 
            ci.setRec(rec);
            cram.add(ci);
        }
        CartItem[] y = cram.toArray(new CartItem[cram.size()]);
        return y;
    }
    
    public String storeAllCartsDb(List<Cart> carts) {
        if (em != null){
           Query q = em.createQuery("select cartdb.orderid from Cartdb cartdb");
           List<Integer> oldIds= q.getResultList();  
           List<Integer> newtids = new ArrayList<Integer>();  
           
//        Query q = em.createQuery("select cartrecipedb from Cartrecipedb cartrecipedb where cartrecipedb.orderid = :orderid");
            for (Cart cart: carts){
                newtids.add(cart.getOrderId());
            }
            for (Integer id: oldIds){
                if (!newtids.contains(id)) {
                    System.out.println("Order is being removed: id= "+ id);
                    Query q1 = em.createQuery("Delete from Cartdb cartdb where cartdb.orderid = :orderid");
                    q1.setParameter("orderid",id);
                    Query q2 = em.createQuery("Delete from Cartrecipedb cartrecipedb where cartrecipedb.orderid = :orderid");
                    q2.setParameter("orderid",id);
                    q1.executeUpdate();
                    q2.executeUpdate();
                } 
            } 
        }
        return "Database: carts updated"; 
    }
    
}
