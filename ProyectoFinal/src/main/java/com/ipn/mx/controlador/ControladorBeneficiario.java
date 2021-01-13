/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dto.BeneficiadosDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricardo Alberto
 */
@WebServlet(name = "ControladorBeneficiario", urlPatterns = {"/ControladorBeneficiario"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, 
maxFileSize=1024*1024*10, 
maxRequestSize=1024*1024*50)
public class ControladorBeneficiario extends HttpServlet {

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
               case "registroBeneficiario":
                     registroBene(request, response);
               break;
               default :
               break;
        }
    }

     private void registroBene(HttpServletRequest request, HttpServletResponse response) {
         BeneficiadosDTO dao=new BeneficiadosDTO();
         dao.getEntidad().setNombreUsuario(request.getParameter("txtNombre"));
         dao.getEntidad().setEdad(Integer.parseInt(request.getParameter("txtEdad")));
         dao.getEntidad().setCalle(request.getParameter("txtCalle"));
         dao.getEntidad().setCorreo(request.getParameter("txtMail"));
         dao.getEntidad().setContra(request.getParameter("txtPassword"));
         dao.getEntidad().setIDMunicipio(Integer.parseInt(request.getParameter("selectEstado")));
          RequestDispatcher rd = request.getRequestDispatcher("ingresoAdmi.jsp");
                request.setAttribute("messageList",dao.getEntidad().getNombreUsuario());
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
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
