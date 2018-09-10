/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1;

import static com.mycompany.mavenweb1.RecipieResource.base64decode;
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
import javax.transaction.Transactional;

/**
 * Обрабатывает запросы в БД
 * @author Администратор
 */
//@Stateless
@Transactional // не нужна для EJB
public class RecipieDataAccessImpl {
    @PersistenceContext(unitName = "example2PU")
    EntityManager em;
    
    /**
     * Метод вызывается при запросе списка товаров.
     */
    public List<Recipe> getRecipies () {
        List<Recipe> lst=new ArrayList<Recipe>();
        if (em != null){
        Query q = em.createQuery("select recipe from Recipe recipe");
        lst = q.getResultList();
        }
        return lst;
    }

    /**
     * Метод вызывается при сохранении товаров из новой корзины.
     *
     * @param recipies - массив товаров
     */    
    public String storeAllRecipiesDb(List<Recipe> recipies){
        if (em != null){
            Query q = em.createQuery("Delete from Recipe recipe");
            q.executeUpdate();
            for (Recipe recp: recipies) {
                em.persist(recp);
            }                
        }
        return "Database: recipies stored";        
    }

    /**
     * Метод вызывается при загрузке данных о всех заказах.
     * @return список заказов
     */    
    public List<Cartdb> getCarts() {
        List<Cartdb> lst=new ArrayList<Cartdb>();
        if (em != null){
           Query q = em.createQuery("select cartdb from Cartdb cartdb");
           lst = q.getResultList();
        }
        return lst;
    }

    /**
     * Метод вызывается при сохранении данных о заказе.
     *
     * @param cartdb - данные о корзине
     */   
    public String storeCartdb(Cartdb cartdb) {
        if (em != null){     
            em.persist(cartdb);           
        }
        return "Database: cart stored";   
    }
    
     /**
     * Метод вызывается при загрузке данных о товарах для заказа с номером id.
     *
     * @param id - данные о товарах в корзине
     */ 
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
    
     /**
     * Метод вызывается при сохранении изменённого списка заказов администратором.
     * Заказы удаляются из БД, если они выполнены
     * @param carts - данные о товарах в списке
     */     
    public String storeAllCartsDb(List<Cart> carts) {
        if (em != null){
           Query q = em.createQuery("select cartdb.orderid from Cartdb cartdb");
           List<Integer> oldIds= q.getResultList();  
           List<Integer> newtids = new ArrayList<Integer>();  
           
//        Query q = em.createQuery("select cartrecipedb from Cartrecipedb cartrecipedb where cartrecipedb.orderid = :orderid");
            for (Cart cart: carts){
                newtids.add(base64decode(cart.getOrderId()));  // - декодирование ID
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
