/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.BeneficiadosDTO;
import com.ipn.mx.modelo.entidades.Beneficiados;
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
public class BeneficiadosDAO {
    
    public void create(BeneficiadosDTO dto) throws SQLException {
      
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

     public void update(BeneficiadosDTO dto) throws SQLException {
       
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


   public void delete(BeneficiadosDTO dto) throws SQLException {
      
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

    public BeneficiadosDTO read(BeneficiadosDTO dto) throws SQLException {
      
       Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
              
                dto.setEntidad(sesion.get(dto.getEntidad().getClass(),dto.getEntidad().getIDBeneficiado()));
             
              
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
               Query q= sesion.createQuery("from Beneficiados b order by b.IDBeneficiado");
                for(Beneficiados e:(List<Beneficiados>) q.list()){
                   BeneficiadosDTO dto=new BeneficiadosDTO();
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
        try {
            BeneficiadosDTO dto=new BeneficiadosDTO();
            BeneficiadosDAO dao=new BeneficiadosDAO();
            dto.getEntidad().setNombreUsuario("rick");
            dto.getEntidad().setEdad(12);
            dto.getEntidad().setCalle("sdsads");
            dto.getEntidad().setCorreo("fdsdsdfs");
            dto.getEntidad().setContra("1234");
            dto.getEntidad().setIDMunicipio(126);
            
            dao.create(dto);
        } catch (SQLException ex) {
            Logger.getLogger(BeneficiadosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
}
