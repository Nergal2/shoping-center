/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import com.mycompany.mavenweb1.entity.Cart;
import com.mycompany.mavenweb1.entity.Recipe;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Администратор
 */
public class RecipeClient {
    private Client client;
    public RecipeClient(){
        client = ClientBuilder.newClient();
    }
    
    public List<Recipe> getRecipies (){
        WebTarget target = client.target("http://localhost:8080/mavenweb1-1.0-SNAPSHOT/");

        String response2 = target.path("rest/recipies/all").request(MediaType.APPLICATION_JSON)
                .get(String.class);         
        System.out.println(response2);
        
     //   List<Recipe> response = target.path("rest/recipies/all").request(MediaType.APPLICATION_JSON)
     //           .get(new GenericType<List<Recipe>>(){});
          Response response = target.path("rest/recipies/all").request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        if (response.getStatus()!=200){
            throw new RuntimeException(response.getStatus()+ " There was an error ");        
        }
        
        return response.readEntity(new GenericType<List<Recipe>>(){});
    }
    
        public List<Cart> getCarts (){
        WebTarget target = client.target("http://localhost:8080/mavenweb1-1.0-SNAPSHOT/");
        List<Cart> response = target.path("rest/cart/all").request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Cart>>(){});
        String response2 = target.path("rest/cart/all").request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println(response2);
        return response;
    }
        
}
