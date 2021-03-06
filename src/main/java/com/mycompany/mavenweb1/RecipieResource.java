/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1;

import com.mycompany.logger.LoggerAnotation;
import com.mycompany.mavenweb1.entity.Cart;
import com.mycompany.mavenweb1.entity.CartItem;
import com.mycompany.mavenweb1.entity.Cartdb;
import com.mycompany.mavenweb1.entity.Cartrecipedb;
import com.mycompany.mavenweb1.entity.Recipe;
import com.mycompany.soapclient.SoapClient;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Обрабатывает rest запросы
 *
 * @author Администратор
 */
@Path("/rest")
@RequestScoped
public class RecipieResource {

    @Inject
    private RecipieDataAccessImpl rdai;

    @Inject
    private SoapClient soapclient;

    /**
     * Логгер
     */
    @Inject
    @LoggerAnotation
    private Logger logger;
    //  private Logger logger=LogManager.getLogger();

    /**
     * Шифровальщики
     */
    public static final String DEFAULT_ENCODING = "UTF-8";
    static BASE64Encoder enc = new BASE64Encoder();
    static BASE64Decoder dec = new BASE64Decoder();

    /**
     * Поле токен
     */
    String token = "token key valueadmin@admin.ruadmin male";

    /**
     * Поле логин и пароль
     */
    String admin = "admin@admin.ruadmin male";

    /**
     * Метод вызывается кодировании ID.
     */
    public static String base64encode(Integer text) {
        try {
            return enc.encode(text.toString().getBytes(DEFAULT_ENCODING));

        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Метод вызывается декодировании ID.
     */
    public static int base64decode(String text) {
        try {
            return Integer.parseInt(new String(dec.decodeBuffer(text), DEFAULT_ENCODING));
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Метод вызывается при запросе списка товаров.
     */
    @GET
    @Path("/recipies/all")
    //@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recipe> getAllRecipies() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        List<Recipe> lst = rdai.getRecipies();
        lst.addAll(soapclient.getRecipiesSoap());
        logger.log(Level.INFO, "Recipies list's been requested");
        return lst;
    }

    /**
     * Метод вызывается при вводе логина и пароля. Возвращает токен.
     *
     * @param info - логин и пароль
     */
    @POST
    @Path("/login")
    @Produces({MediaType.TEXT_PLAIN})
    public Response login(String info) {
        logger.log(Level.INFO, "login with " + info);
        String resp = "";
        if (info.equals(admin)) {
            resp = this.token;
        }
        return Response.ok(" ")
                .header("Authorization", resp)
                .build();
    }

    /**
     * Метод вызывается при сохранении отредактированого списка товаров.
     *
     * @param tokenAuth - токен
     * @param recipies - список отредактированых товаров
     */
    @PUT
    @Path("/recipies/all")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response storeAllRecipiesUpd(@QueryParam(value = "auth") String tokenAuth, List<Recipe> recipies) {
        logger.log(Level.INFO, "Operation storeAllRecipiesUpd with key: " + tokenAuth);
        if (this.token.equals(tokenAuth)) {
            String s = rdai.storeAllRecipiesDb(recipies);
            System.out.println(s);
            return Response.ok(" ").build();
        } else {
            return Response.status(403).build();
        }
    }

    /**
     * Метод вызывается при получении списка заказов.
     *
     * @param tokenAuth - токен
     */
    @GET
    @Path("/cart/all")
//    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    //  public List<Cart> getAllCarts(){
    public Response getAllCarts(@QueryParam(value = "auth") String tokenAuth) {
        logger.log(Level.INFO, "Operation getAllCarts with key: " + tokenAuth);
        if (this.token.equals(tokenAuth)) {
            List<Cart> cartArr = new ArrayList<Cart>();
            List<Cartdb> cartdb = rdai.getCarts();
            for (Cartdb cdb : cartdb) {
                String idTemp = base64encode(cdb.getOrderid());

                Cart cart = new Cart(cdb.getName(), cdb.getEmail(), cdb.getSex(), cdb.getPrice(), idTemp);
                cart.setCart(rdai.getCartRecipiesId(cdb.getOrderid()));
                cartArr.add(cart);
            }
            return Response.ok(cartArr).build();
        } else {
            return Response.status(403).build();
        }
    }

    /**
     * Метод вызывается при добавлении нового заказа.
     *
     * @param cart - корзина с товарами, ценой и данными о заказчике
     */
    @POST
    @Path("/cart/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cart storeCart(Cart cart) {
        logger.log(Level.INFO, "Request to storeCart");
        final int rnd = 100 + (int) (Math.random() * 10000);  // номер заказа сгенерить
        // подготовка элемента для записи в бд Cartdb    
        Cartdb cartTemp = new Cartdb(cart.getName(), cart.getPrice(), cart.getEmail(), cart.getSex(), rnd);

        CartItem[] cartTempArr = cart.getCart();  // достать массив покупок

        Set<Cartrecipedb> cartRecipeTempDb = new HashSet<>();
        for (CartItem cartItemTemp : cartTempArr) {
            Cartrecipedb cartRecTemp = new Cartrecipedb(); //  подготовка элемента для записи в бд Cartrecipedb
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
        String s1 = rdai.storeCartdb(cartTemp);
        System.out.println(s1);

        return cart;
    }

    /**
     * Метод вызывается при сохранении списка заказов.
     *
     * @param tokenAuth - токен
     * @param carts - массив корзин заказов
     */
    @PUT
    @Path("/cart/all")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response storeAllCarts(@QueryParam(value = "auth") String tokenAuth, List<Cart> carts) {
        logger.log(Level.INFO, "Operation storeAllCarts with key: " + tokenAuth);
        if (this.token.equals(tokenAuth)) {
            String s = rdai.storeAllCartsDb(carts);
            logger.log(Level.INFO, s);
            return Response.ok("ok").build();
        } else {
            return Response.status(403).build();
        }
    }

}
