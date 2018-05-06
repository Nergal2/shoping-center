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
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.core.Response;

/**
 *
 * @author Администратор
 */
@Path("/rest")
public class RecipieResource {
    @Inject
    private RecipieDataAccessImpl rdai;
    
    String token="token key valueadmin@admin.ruadmin male";
    String admin="admin@admin.ruadmin male";
    
    @GET
    @Path("/recipies/all")
    //@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
//    public List<Recipe> getAllRecipies(){
    public Response getAllRecipies(){        
        List<Recipe> lst= rdai.getRecipies(); 
        
    return Response.ok(lst).header("Content-Type", "application/json")
           // .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")			
            .header("Access-Control-Allow-Origin", "http://localhost:4200")
        //    .header("Access-Control-Allow-Headers", "x-requested-with")
            .build();
    }
    
    @GET
    @Path("/echo")
    public String echo() {
        return "smth recipie";
    }
    
    @POST
    @Path("/login")
    public Response login(String info) {
        System.out.println("login with "+info);
        String resp= "";
        if (info.equals(admin)){
            resp= this.token;
        }
        return Response.ok("<!DOCTYPE html>")
            .header("Authorisation ", resp)
            .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    //        .header("Access-Control-Allow-Method", "POST")    
            .header("Access-Control-Expose-Headers", "Authorisation")
            .header("Access-Control-Allow-Origin", "http://localhost:4200")                
            .build();              
    }

    @POST
    @Path("/recipies/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Recipe> storeAllRecipies(List<Recipe> recipies){
    //    for (Recipe rec : recipies) {
    //    System.out.println(rec.getName());
    //    System.out.println(rec.getPrice());
    //    }
        
        String s= rdai.storeAllRecipiesDb(recipies);
        System.out.println(s);
        return recipies;
    }
    
    @OPTIONS
    @Path("/recipies/all") 
    public Response storeAllRecipiesUpdOptions(){
        return Response.ok().header("Content-Type", "application/json")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
            .header("Access-Control-Allow-Origin", "http://localhost:4200")
            .build();      
    }
    @PUT
    @Path("/recipies/all")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response storeAllRecipiesUpd(@QueryParam(value="auth") String tokenAuth, List<Recipe> recipies){
        System.out.println("Operation storeAllRecipiesUpd with key: "+tokenAuth);
        if (this.token.equals(tokenAuth)) 
            {      
           String s= rdai.storeAllRecipiesDb(recipies);
           System.out.println(s);
                return Response.ok().header("Content-Type", "application/json")
                 .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
                  .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                 .header("Access-Control-Allow-Origin", "http://localhost:4200")
                 .build(); 
            } else {
            return Response.status(403).header("Content-Type", "application/json")
                 .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
                  .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                 .header("Access-Control-Allow-Origin", "http://localhost:4200")
                 .build(); 
        }    
    }
    
    @OPTIONS
    @Path("/cart/all") 
    public Response getAllCartsOptions(){
        return Response.ok().header("Content-Type", "application/json")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
            .header("Access-Control-Allow-Origin", "http://localhost:4200")
            .build();      
    }
    @GET
    @Path("/cart/all")
//    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
  //  public List<Cart> getAllCarts(){
    public Response getAllCarts(@QueryParam(value="auth") String tokenAuth){
        System.out.println("Operation getAllCarts with key: "+tokenAuth);
        if (this.token.equals(tokenAuth)) 
            {
            List<Cart> cartArr= new ArrayList<Cart>();
            List<Cartdb> cartdb= rdai.getCarts();
            for (Cartdb cdb : cartdb){            
               Cart cart=new Cart(cdb.getName(),cdb.getEmail(),cdb.getSex(),cdb.getPrice(),cdb.getOrderid());
               cart.setCart(rdai.getCartRecipiesId(cdb.getOrderid()));
               cartArr.add(cart);
               }             
            return Response.ok(cartArr).header("Content-Type", "application/json")
           // .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")			
            .header("Access-Control-Allow-Origin", "http://localhost:4200")
        //    .header("Access-Control-Allow-Headers", "x-requested-with")
            .build();   
            } else {
            return Response.status(403).header("Content-Type", "application/json")
                 .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
                 .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                 .header("Access-Control-Allow-Origin", "http://localhost:4200")
                 .build();            
        }
    }
    
    @OPTIONS
    @Path("/cart/new") 
    public Response storeCartOptions(){
        return Response.ok().header("Content-Type", "application/json")
            .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")  
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
            .header("Access-Control-Allow-Origin", "http://localhost:4200")
            .build();      
    }
    
    @POST
    @Path("/cart/new")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)    
    public Response storeCart(Cart cart){    
        System.out.println("Request to storeCart");

        System.out.println(cart.getPrice());
        final int rnd=100 + (int) (Math.random() * 10000);  // номер заказа сгенерить
        Cartdb cartTemp= new Cartdb(); // подготовка элемента для записи в бд Cartdb
        cartTemp.setName(cart.getName());
        cartTemp.setPrice(cart.getPrice());
        cartTemp.setEmail(cart.getEmail());
        cartTemp.setSex(cart.getSex());
        cartTemp.setOrderid(rnd);
        
        CartItem[] cartTempArr = cart.getCart();  // достать массив покупок
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
            
            String s2= rdai.storeCartRecipedb(cartRecTemp);        
            System.out.println(cartRecTemp.getName());
        }
        
        String s1= rdai.storeCartdb(cartTemp);        
        System.out.println(s1);
         
        return Response.ok(cart).header("Content-Type", "application/json")
            .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    //        .header("Access-Control-Allow-Method", "POST")    
            .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
            .header("Access-Control-Allow-Origin", "http://localhost:4200")
            .build();      
    }    

    @PUT
    @Path("/cart/all")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response storeAllCarts(@QueryParam(value="auth") String tokenAuth, List<Cart> carts){
        System.out.println("Operation storeAllCarts with key: "+tokenAuth);
        if (this.token.equals(tokenAuth)) 
            {      
           String s= rdai.storeAllCartsDb(carts);
           System.out.println(s);
                return Response.ok().header("Content-Type", "application/json")
                 .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
                  .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                 .header("Access-Control-Allow-Origin", "http://localhost:4200")
                 .build(); 
            } else {
            return Response.status(403).header("Content-Type", "application/json")
                 .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS")  
                  .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                 .header("Access-Control-Allow-Origin", "http://localhost:4200")
                 .build(); 
        }    
    }
    
}
