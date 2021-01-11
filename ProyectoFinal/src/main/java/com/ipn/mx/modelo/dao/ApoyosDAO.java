/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ApoyosDTO;
import com.ipn.mx.modelo.entidades.Apoyos;
import com.ipn.mx.utilerias.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Ricardo Alberto
 */
public class ApoyosDAO {
    
    public void create(ApoyosDTO dto) throws SQLException {
      
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

     public void update(ApoyosDTO dto) throws SQLException {
       
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


   public void delete(ApoyosDTO dto) throws SQLException {
      
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

    public ApoyosDTO read(ApoyosDTO dto) throws SQLException {
      
       Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
              
                dto.setEntidad(sesion.get(dto.getEntidad().getClass(),dto.getEntidad().getIDApoyo()));
             
              
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
               Query q= sesion.createQuery("from Apoyos a order by a.IDApoyo");
                for(Apoyos e:(List<Apoyos>) q.list()){
                   ApoyosDTO dto=new ApoyosDTO();
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
    
}
