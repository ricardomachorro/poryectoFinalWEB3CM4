/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ricardo Alberto
 */
public class EnviarEmail {
    public void enviarCorreo(String destinatario,String asunto,String mensaje){
    Properties propiedades=new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.user", "ricardocorreoejemplo@gmail.com");
        propiedades.setProperty("mail.smtp.auth", "true");
        
        Session session=Session.getDefaultInstance(propiedades);
        MimeMessage elMensaje=new MimeMessage(session);
        try {
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);
            Transport t= session.getTransport("smtp");
            t.connect(propiedades.getProperty("mail.smtp.user"),"ramv1357");
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
            
            //myaccount.google.com/lessecureapp
        } catch (AddressException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String [] args){
        EnviarEmail mandarCorreo=new EnviarEmail();
        String destinatario="ricardoalbeto.machorrovences@gmail.com";
        String asunto="Enviando correo desde Java";
        String texto="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec quam efficitur, tincidunt lacus a, mattis mi. Vivamus ut arcu vitae libero suscipit volutpat. In hac habitasse platea dictumst. Nunc efficitur, ex eu faucibus viverra, sapien dolor rutrum erat, a commodo metus justo eu urna. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis ullamcorper velit quis sollicitudin rutrum. Morbi suscipit faucibus nisl, ut tempor sapien efficitur sed. Vestibulum faucibus viverra risus. Cras imperdiet nisl tortor, a tristique ipsum efficitur bibendum. Praesent ultricies dictum tellus id dapibus. In tincidunt sapien a quam mattis rutrum. Quisque in vestibulum urna, ut elementum metus. Aliquam sed turpis condimentum velit auctor pharetra. Nulla rhoncus vestibulum eros, pretium volutpat nibh pretium at.";
        mandarCorreo.enviarCorreo(destinatario,asunto, texto);
    }
}
