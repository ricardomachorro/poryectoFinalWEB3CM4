/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.dto.MunicipioDTO;
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
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;

/**
 *
 * @author Ricardo Alberto
 */
public class GraficaDAO {
    
    public List datosGraficaBeneficiadosPorMunicipio(int IDEstado){
    
     Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
       
        MunicipioDAO munDao=new MunicipioDAO();
        
        try {
            List<MunicipioDTO> listaMunicipio=munDao.readAll();
            transaction.begin();
            //select * from usuario as u order by u.idUsuario;
            //int x;
            //Usuario u
            //
           
         
            Query q = sesion.createSQLQuery(" select IDMunicipio,count(IDBeneficiado) as conteo from beneficiados group by idmunicipio").
                    addScalar("conteo",new IntegerType()).addScalar("IDMunicipio",new IntegerType());
           
          
            for (Object item : q.list()) {
             
              
                Object[] element = (Object[]) item;
                int IDMunicipio=(Integer)element[1];
            MunicipioDTO munDto=new MunicipioDTO();
             for(int i=0;i<listaMunicipio.size();i++){
                if(listaMunicipio.get(i).getEntidad().getIDMunicipio()==IDMunicipio){
                   munDto=listaMunicipio.get(i);
                }
             }
            
                if(munDto.getEntidad().getIDEstado()==IDEstado){
                   GraficaDTO dto=new GraficaDTO();
                   dto.setNombre(munDto.getEntidad().getNombre());
                   dto.setCantidad((Integer)element[0]);
                   lista.add(dto);
                }
             
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GraficaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static void main(String args[]){
    
       GraficaDAO dao=new GraficaDAO();
       dao.datosGraficaBeneficiadosPorMunicipio(32);
    }
}
