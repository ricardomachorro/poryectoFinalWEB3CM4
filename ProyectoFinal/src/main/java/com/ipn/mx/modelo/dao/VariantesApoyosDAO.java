/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.VariantesApoyosDTO;
import com.ipn.mx.modelo.entidades.VariantesApoyos;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Ricardo Alberto
 */
public class VariantesApoyosDAO {
    
    public void create(VariantesApoyosDTO dto) throws SQLException {
      
        Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
               sesion.save(dto.getEntidad());
               
               transaction.commit();
           }catch(HibernateException he){
               if(transaction!=null && transaction.isActive()){
                  transaction.rollback();
               }
           }
    }

     public void update(VariantesApoyosDTO dto) throws SQLException {
       
       Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
              
               sesion.update(dto.getEntidad());
               transaction.commit();
           }catch(HibernateException he){
               if(transaction!=null && transaction.isActive()){
                  transaction.rollback();
               }
           }
    }


   public void delete(VariantesApoyosDTO dto) throws SQLException {
      
       Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
               sesion.delete(dto.getEntidad());
               transaction.commit();
           }catch(HibernateException he){
               if(transaction!=null && transaction.isActive()){
                  transaction.rollback();
               }
           }
    }

    public VariantesApoyosDTO read(VariantesApoyosDTO dto) throws SQLException {
      
       Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
              
                dto.setEntidad(sesion.get(dto.getEntidad().getClass(),dto.getEntidad().getIDVarianteApoyo()));
             
              
             transaction.commit();
           }catch(HibernateException he){
               if(transaction!=null && transaction.isActive()){
                  transaction.rollback();
               }
           }
           return dto;
    }

      public List readAll() throws SQLException {
      
          Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
          Transaction transaction=sesion.getTransaction();
          List lista=new ArrayList();
           
           try{
               transaction.begin();
              //select * from usuario as u order by u.idUsuario;
              //int x;
              //Usuario u
              //
               Query q= sesion.createQuery("from VariantesApoyos v order by v.IDVarianteApoyo");
                for(VariantesApoyos e:(List<VariantesApoyos>) q.list()){
                   VariantesApoyosDTO dto=new VariantesApoyosDTO();
                   dto.setEntidad(e);
                   lista.add(dto);
                   
                }
               transaction.commit();
           }catch(HibernateException he){
               if(transaction!=null && transaction.isActive()){
                  transaction.rollback();
               }
           }
           return lista;
    }
      
       public static void main (String args[]){
         VariantesApoyosDTO mun=new VariantesApoyosDTO();
         VariantesApoyosDAO dao=new VariantesApoyosDAO();
        
        try {
            List<VariantesApoyosDTO> apo=dao.readAll();
            for(int i=0;i<apo.size();i++){
              System.out.println(apo.get(i).getEntidad());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VariantesApoyosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
      
      }
}
