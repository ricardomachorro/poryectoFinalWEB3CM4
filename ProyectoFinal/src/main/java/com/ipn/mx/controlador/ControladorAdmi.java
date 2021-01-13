/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.ApoyosDAO;
import com.ipn.mx.modelo.dao.EstadoDAO;
import com.ipn.mx.modelo.dao.MunicipioDAO;
import com.ipn.mx.modelo.dao.VariantesApoyosDAO;
import com.ipn.mx.modelo.dto.ApoyosDTO;
import com.ipn.mx.modelo.dto.EstadoDTO;
import com.ipn.mx.modelo.dto.MunicipioDTO;
import com.ipn.mx.modelo.dto.VariantesApoyosDTO;
import com.ipn.mx.modelo.entidades.Estado;
import com.ipn.mx.modelo.entidades.Municipio;
import com.ipn.mx.modelo.entidades.VariantesApoyos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricardo Alberto
 */
@WebServlet(name = "ControladorAdmi", urlPatterns = {"/ControladorAdmi"})
public class ControladorAdmi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch(accion){
               case "ingresoAdmi":
                   ingresoPanelAdmi(request, response);
               case "nuevoApoyo":
                   nuevoApoyo(request, response);
               break;
                case "editarApoyo":
                   editarApoyo(request, response);
               break;
               default :
               break;
        }
    }
    
     private void ingresoPanelAdmi(HttpServletRequest request, HttpServletResponse response) {
            EstadoDAO estadoDao= new EstadoDAO();
            EstadoDTO estadoDTO=new EstadoDTO();
            boolean usuarioPermitido=false;
        try {
            List<EstadoDTO> estadoLista=estadoDao.readAll();
            for(int i=0;i<estadoLista.size();i++){
                 if(estadoLista.get(i).getEntidad().getClave().equals((String) request.getParameter("txtClave"))){
                    if((estadoLista.get(i).getEntidad().getNombreUsuarioEncargado().equals((String) request.getParameter("txtNombre")))
                           && (estadoLista.get(i).getEntidad().getContra().equals((String) request.getParameter("txtPassword")))){
                        estadoDTO=estadoLista.get(i);
                        usuarioPermitido=true;
                        break;
                    }
                   
                 }
            }
            if(usuarioPermitido){
                VariantesApoyosDAO varianteApoyosDao= new VariantesApoyosDAO();
                List<VariantesApoyosDTO> variantesApoyosLista=varianteApoyosDao.readAll();
                List<VariantesApoyosDTO> variantesApoyosSalida=varianteApoyosDao.readAll();
                variantesApoyosSalida.clear();
                MunicipioDAO municipioDao=new MunicipioDAO();
                List<MunicipioDTO> listaMunicipios=municipioDao.readAll();
                ApoyosDAO apoyoDao=new ApoyosDAO();
                List<ApoyosDTO> listaApoyos=apoyoDao.readAll();
             /*   List<MunicipioDTO> municipioLista=municipioDao.readAll();
               List<MunicipioDTO> municipiosDelEstado;*/   
              
               for(int i=0;i<variantesApoyosLista.size();i++){
                  int IDMunicipio=variantesApoyosLista.get(i).getEntidad().getIDMunicipio();
                  MunicipioDTO munEvaluar=new MunicipioDTO();
                  munEvaluar.getEntidad().setIDMunicipio(IDMunicipio);
                  munEvaluar=municipioDao.read(munEvaluar);
                  if(munEvaluar.getEntidad().getIDEstado()==estadoDTO.getEntidad().getIDEstado()){
                      variantesApoyosSalida.add(variantesApoyosLista.get(i));
                  }
               }
           
                HttpSession session = request.getSession();
                session.invalidate();
                session=request.getSession();
                session.setAttribute("usuarioEstadosDatos", estadoDTO);
               request.setAttribute("listaVariantesApoyos",variantesApoyosSalida);
               request.setAttribute("listaApoyos",listaApoyos );
               request.setAttribute("listaMunicipios",listaMunicipios );
               request.setAttribute("listaVariantesApoyosSize",variantesApoyosSalida.size());
               request.setAttribute("listaApoyosSize",listaApoyos.size() );
               request.setAttribute("listaMunicipiosSize",listaMunicipios.size() );
               RequestDispatcher rd = request.getRequestDispatcher("principalAdmi.jsp");
               
               rd.forward(request, response);
               
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("ingresoAdmi.jsp");
                request.setAttribute("messageList","Datos erroneos");
                rd.forward(request, response);
            }
       
            
          
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
         

    }
     
     private void nuevoApoyo(HttpServletRequest request, HttpServletResponse response) {
         MunicipioDAO municipioDao=new MunicipioDAO();
         ApoyosDAO apoyosDao=new ApoyosDAO();
        try {
            List<MunicipioDTO> listaMunicipios=municipioDao.readAll();
            List<ApoyosDTO> listaApoyos=apoyosDao.readAll();
            HttpSession session = request.getSession();
            EstadoDTO estadoUsuario=(EstadoDTO) session.getAttribute("usuarioEstadosDatos");
             List<MunicipioDTO> listaMunicipiosSalida=municipioDao.readAll();
             listaMunicipiosSalida.clear();
             for(int i=0;i<listaMunicipios.size();i++){
                 if(listaMunicipios.get(i).getEntidad().getIDEstado()==estadoUsuario.getEntidad().getIDEstado()){
                     listaMunicipiosSalida.add(listaMunicipios.get(i));
                 }
             }
             request.setAttribute("listaMunicipios",listaMunicipiosSalida);
              request.setAttribute("listaApoyos",listaApoyos);
               RequestDispatcher rd = request.getRequestDispatcher("datosApoyo.jsp");
               
               rd.forward(request, response);
            
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     
     private void editarApoyo(HttpServletRequest request, HttpServletResponse response) {
         MunicipioDAO municipioDao=new MunicipioDAO();
         ApoyosDAO apoyosDao=new ApoyosDAO();
         VariantesApoyosDAO varApoyosDAO=new VariantesApoyosDAO();
         VariantesApoyosDTO varApoyosDTO=new VariantesApoyosDTO();
         try {
             varApoyosDTO.getEntidad().setIDVarianteApoyo(Integer.parseInt(request.getParameter("idApoyoVariante")));
            varApoyosDTO=varApoyosDAO.read(varApoyosDTO);
         
        
            List<MunicipioDTO> listaMunicipios=municipioDao.readAll();
            List<ApoyosDTO> listaApoyos=apoyosDao.readAll();
            HttpSession session = request.getSession();
            EstadoDTO estadoUsuario=(EstadoDTO) session.getAttribute("usuarioEstadosDatos");
             List<MunicipioDTO> listaMunicipiosSalida=municipioDao.readAll();
             listaMunicipiosSalida.clear();
             for(int i=0;i<listaMunicipios.size();i++){
                 if(listaMunicipios.get(i).getEntidad().getIDEstado()==estadoUsuario.getEntidad().getIDEstado()){
                     listaMunicipiosSalida.add(listaMunicipios.get(i));
                 }
             }
             request.setAttribute("varianteApoyo",varApoyosDTO);
             request.setAttribute("listaMunicipios",listaMunicipiosSalida);
              request.setAttribute("listaApoyos",listaApoyos);
               RequestDispatcher rd = request.getRequestDispatcher("datosApoyo.jsp");
               
               rd.forward(request, response);
            
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
