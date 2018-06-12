/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Nergal
 */
public class LoggerProducer {
    
    @Produces
    @LoggerAnotation
    private Logger producerLogger(InjectionPoint ip){
        return LogManager.getLogger(ip.getMember().getDeclaringClass().getName());
    } 
    
}