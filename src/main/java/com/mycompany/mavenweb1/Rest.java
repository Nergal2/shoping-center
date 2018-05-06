/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1;

import com.mycompany.mavenweb1.entity.Employee;
import com.mycompany.mavenweb1.entity.Recipe;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Администратор
 */
@Path("/rest/gate")
@Stateless
public class Rest {
    
    @PersistenceContext(unitName = "example2PU")
    EntityManager em;
    
    @GET
    @Path("/echo")
    public String echo(@QueryParam("q") String original) {
        return original;
    }
    
   @GET
    @Path("/echo2")
    public String echo() {
        return "smth recipie";
    }  
    
    @POST
    @Path("/testEM")        
    public String testEm(){
        String result = "none";
        if (em != null){
           result = "not null";
            Employee e1=new Employee("Milana569",100);
            em.persist(e1);
            Query q = em.createQuery("select employee from Employee employee where employee.name = :name");
	    q.setParameter("name", "Sarah");
	    result= q.getResultList().toString();
        }
        return result;
    }
}
