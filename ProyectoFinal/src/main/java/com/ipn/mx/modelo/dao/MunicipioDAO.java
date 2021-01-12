/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.MunicipioDTO;
import com.ipn.mx.modelo.entidades.Municipio;
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
public class MunicipioDAO {
    
    public void create(MunicipioDTO dto) throws SQLException {
      
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

     public void update(MunicipioDTO dto) throws SQLException {
       
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


   public void delete(MunicipioDTO dto) throws SQLException {
      
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

    public MunicipioDTO read(MunicipioDTO dto) throws SQLException {
      
       Session sesion=HibernateUtil.getSessionFactory().getCurrentSession();
           Transaction transaction=sesion.getTransaction();
           try{
               transaction.begin();
              
                dto.setEntidad(sesion.get(dto.getEntidad().getClass(),dto.getEntidad().getIDMunicipio()));
             
              
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
               Query q= sesion.createQuery("from Municipio m order by m.IDMunicipio");
                for(Municipio e:(List<Municipio>) q.list()){
                   MunicipioDTO dto=new MunicipioDTO();
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
         MunicipioDTO mun=new MunicipioDTO();
         MunicipioDAO dao=new MunicipioDAO();
         mun.getEntidad().setIDMunicipio(30);
        try {
            int i=dao.read(mun).getEntidad().getIDEstado();
            System.out.println(i);
        } catch (SQLException ex) {
            Logger.getLogger(MunicipioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      
      }
      
}
