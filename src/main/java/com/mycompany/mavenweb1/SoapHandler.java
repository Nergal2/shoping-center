/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenweb1;

import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Nergal
 */
public class SoapHandler implements SOAPHandler {
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outbound = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            try {
                context.getMessage().setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        } catch (SOAPException ex) {}
    }
    return true;
}

    @Override
    public Set getHeaders() {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean handleMessage(MessageContext c) {
        System.out.println("HandlerValidator on server side");
        Boolean outbound = (Boolean)c.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            try {
                System.out.println("Server : doing stuff");
              //  ((SOAPMessageContext)c).getMessage().setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
                SOAPMessage soapm = ((SOAPMessageContext)c).getMessage();
              //  soapm.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
              //  soapm.getSOAPBody().setEncodingStyle("UTF-8");
                String s = new String(soapm.getSOAPBody().getTextContent().getBytes(),"UTF-8");
                String s2 = StringEscapeUtils.unescapeXml(soapm.getSOAPBody().getTextContent());
                System.out.println("orig "+soapm.getSOAPBody().getTextContent());
                System.out.println("S "+s);
                System.out.println("S2 "+s2);
            //    soapm.getSOAPBody().setTextContent(s);
            //    ((SOAPMessageContext)c).setMessage(soapm);
            } catch (SOAPException ex) {
          //      Logger.getLogger(SoapHandler.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageContext c) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close(MessageContext mc) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
