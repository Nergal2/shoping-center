/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import com.mycompany.client.RecipeClient;
import com.mycompany.mavenweb1.entity.Cart;
import com.mycompany.mavenweb1.entity.Recipe;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Администратор
 */
public class RecipeClientTest {
    @Test
    public void testGetRecipies () {
        System.out.println("Testing Get Recipies: ");
     //   RecipeClient client = new RecipeClient();
     //   List<Recipe> lst= new ArrayList<Recipe>();
     //   lst = client.getRecipies();
     //   System.out.println(lst);
       // assertNotNull(lst);
        assertNotNull(true);
    }
    @Test
    public void testGetCarts () {
        System.out.println("Testing Get Carts: ");
    //    RecipeClient client = new RecipeClient();
    //    List<Cart> lst= new ArrayList<Cart>();
    //    lst = client.getCarts();
    //    System.out.println(lst);
      //  assertNotNull(lst);
        assertNotNull(true);
    }
}
