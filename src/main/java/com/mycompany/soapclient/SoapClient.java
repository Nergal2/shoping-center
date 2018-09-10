/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapclient;

import com.mycompany.mavenweb1.entity.Recipe;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import wsdlsaved.Item;
import wsdlsaved.MySOAPServer;
import wsdlsaved.SOAPServer;
 
/**
 *
 * @author Nergal
 */
public class SoapClient {
 /**
 * Отправляет soap запросы получает дополнительный список товаров
 * @author Администратор
 */ 
    public List<Recipe> getRecipiesSoap() {
        List<Recipe> lst = new ArrayList<Recipe>();
            System.out.print("calling getRecipiesSoap: ");
        try {    
    // send a request fnd check if the server is avaliable
            MySOAPServer soap = new MySOAPServer();
            SOAPServer sei = soap.getSOAPServerPort();
                if (sei!=null){
                    List<Item> itemLst = sei.getAllItems();
                    for (Item i: itemLst){
    // loop through all recieved elements
    // use jaxb to transform item to xml as ByteArrayOutputStream
                        JAXBContext context = JAXBContext.newInstance(Item.class);
                        Marshaller marshaller = context.createMarshaller();

                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        marshaller.marshal(i,bos);
        //restruct the xml to byte array             
                        byte[] array = bos.toByteArray();
                        for (byte b: array) {
                            System.out.print((char)b);
                        }
        // create ByteArrayInputStream from the incoming transformed xml
                        ByteArrayInputStream bs = new ByteArrayInputStream(array);
        // perform XSLT transformation
        // classLoader - для получения доступа к текущим ресурсам проекта
        // classLoader.getResourceAsStream("/Item2Recipe.xsl") вернёт поток из файла
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        StreamSource xlscode = new StreamSource(classLoader.getResourceAsStream("/Item2Recipe.xsl"));
                      //  StreamSource xlscode = new StreamSource(new File("Item2Recipe.xsl"));
                        TransformerFactory tf = TransformerFactory.newInstance();
                        Transformer trans = tf.newTransformer(xlscode);
                        StreamSource input = new StreamSource(bs);
                        ByteArrayOutputStream bs2 = new ByteArrayOutputStream();
                        StreamResult output = new StreamResult(bs2);
                        trans.transform(input, output);                       
                        System.out.println(output.toString());
          // transform the result of the XSLT to byte array
                        byte array2[] = bs2.toByteArray();
                        ByteArrayInputStream bs3 = new ByteArrayInputStream(array2);
          // create instance Recipe from ByteArrayInputStream using jaxb
          //prepare Unmarshaller for Recipe.class use JAXB to 
                        JAXBContext contextr = JAXBContext.newInstance(Recipe.class);
                        Unmarshaller unmarshaller = contextr.createUnmarshaller();
                        Recipe r1 = (Recipe)unmarshaller.unmarshal(bs3);
                        System.out.println(r1.getName()+r1.getPrice());
                        lst.add(r1);
                        
                   //     Recipe r = new Recipe(i.getId(),i.getName(),i.getPrice());
                    //    r.setDescription(i.getDescription());
                    //    r.setImagepath(i.getImagepath());
                   //     lst.add(r);
                    }
                    System.out.println("SOAP is requested");
                }
                return lst;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return lst;
        }   
    }
    
}
