/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libInterface;

/**
 *
 * @author STEINACOZ-PC
 */
public interface SenderInterface {
    
    public String toAddress(String toAdd);
    public String fromAddress(String fromAdd);
    
    public String emailUser(String username);
    public String emailPass(String password);
    
    public String emailHost(String host);
    public String emailHost();
    
    public String sendEmail(String subject, String message);
    
}
