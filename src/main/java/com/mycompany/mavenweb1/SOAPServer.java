/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1;

import com.mycompany.logger.LoggerAnotation;
import static com.mycompany.mavenweb1.RecipieResource.base64encode;
import com.mycompany.mavenweb1.entity.Cart;
import com.mycompany.mavenweb1.entity.CartItem;
import com.mycompany.mavenweb1.entity.Cartdb;
import com.mycompany.mavenweb1.entity.Cartrecipedb;
import com.mycompany.mavenweb1.entity.Recipe;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
 
/**
 *
 * @author Nergal
 */
@WebService(serviceName = "mySOAPServer")
@HandlerChain(file = "../../../mySOAPServer_handler.xml")
public class SOAPServer {
    /** Поле токен */
    String token="token key valueadmin@admin.ruadmin male";
    
/** Поле логин и пароль */    
    String admin="admin@admin.ruadmin male";   
    
    @Inject
    private RecipieDataAccessImpl rdai;
    
/** Логгер */    
    @Inject
    @LoggerAnotation
    private Logger logger;
    
    @WebMethod (exclude = false)
    public String testServerreturn(int test){
        String s  = "Тест"+String.valueOf(test+69);
        System.out.println("s= "+s);
        return s;
    }
    
    @WebMethod
    @WebResult( name = "Recipies")
    public List<Recipe> getAllRecipies(){
        List<Recipe> lst= rdai.getRecipies(); 
        logger.log( Level.INFO, "Recipies list's been requested");
        return lst;
    } 

    @WebMethod
    public Cart storeCart(Cart cart){    
        logger.log( Level.INFO, "Request to storeCart");
        final int rnd=100 + (int) (Math.random() * 10000);  // номер заказа сгенерить
        // подготовка элемента для записи в бд Cartdb    
        Cartdb cartTemp= new Cartdb(cart.getName(),cart.getPrice(),cart.getEmail(),cart.getSex(),rnd);
        CartItem[] cartTempArr = cart.getCart();  // достать массив покупок
        
        Set<Cartrecipedb> cartRecipeTempDb = new HashSet<>();
        for (CartItem cartItemTemp: cartTempArr) {
            Cartrecipedb cartRecTemp= new Cartrecipedb(); //  подготовка элемента для записи в бд Cartrecipedb
            cartRecTemp.setOrderid(rnd);
            Recipe cartRec = cartItemTemp.getRec();  // достать recipe из элемента массива и переписать поля
            cartRecTemp.setId(cartRec.getId()); 
            cartRecTemp.setName(cartRec.getName());
            cartRecTemp.setPrice(cartRec.getPrice());
            cartRecTemp.setDescription(cartRec.getDescription());
            cartRecTemp.setImagepath(cartRec.getImagepath());
            cartRecTemp.setNumb(cartItemTemp.getNumb());
            
            cartRecipeTempDb.add(cartRecTemp);      
            System.out.println(cartRecTemp.getName());
        }
        cartTemp.setCartrecipedb(cartRecipeTempDb);
        String s1= rdai.storeCartdb(cartTemp);        
        System.out.println(s1);
         
        return cart;
    }
 
    @WebMethod
    @WebResult(name = "Carts")
    public List<Cart> getAllCarts(@WebParam(name = "auth") String tokenAuth){
        logger.log( Level.INFO, "Operation getAllCarts with key: "+tokenAuth);
        if (this.token.equals(tokenAuth)) {
            List<Cart> cartArr= new ArrayList<Cart>();
            List<Cartdb> cartdb= rdai.getCarts();
            for (Cartdb cdb : cartdb){
               String idTemp= base64encode(cdb.getOrderid());
               Cart cart=new Cart(cdb.getName(),cdb.getEmail(),cdb.getSex(),cdb.getPrice(),idTemp);
               cart.setCart(rdai.getCartRecipiesId(cdb.getOrderid()));
               cartArr.add(cart);
            }              
            return cartArr;   
            } else {
                return null;            
        }
    }
    
    @WebMethod
    public String storeAllCarts(@WebParam(name = "auth") String tokenAuth, @WebParam(name = "carts") List<Cart> carts){
        logger.log( Level.INFO, "Operation storeAllCarts with key: "+tokenAuth);
        if (this.token.equals(tokenAuth)){      
            String s= rdai.storeAllCartsDb(carts);
            logger.log( Level.INFO, s);           
            return "Ok"; 
        } else {
            return null; 
        }    
    }
    
}
