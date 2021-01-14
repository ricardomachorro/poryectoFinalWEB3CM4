/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.BeneficiadosDAO;
import com.ipn.mx.modelo.dto.BeneficiadosDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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

    private void cargarPanelPrinBen(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BeneficiadosDTO dato=(BeneficiadosDTO) session.getAttribute("datosUsuario");
         RequestDispatcher rd = request.getRequestDispatcher("ingresoBeneficiados.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void registroBene(HttpServletRequest request, HttpServletResponse response) {
         BeneficiadosDTO dto=new BeneficiadosDTO();
         BeneficiadosDAO dao=new BeneficiadosDAO();
         dto.getEntidad().setNombreUsuario(request.getParameter("txtNombre"));
         dto.getEntidad().setEdad(Integer.parseInt(request.getParameter("txtEdad")));
         dto.getEntidad().setCalle(request.getParameter("txtCalle"));
         dto.getEntidad().setCorreo(request.getParameter("txtMail"));
         dto.getEntidad().setContra(request.getParameter("txtPassword"));
         dto.getEntidad().setIDMunicipio(Integer.parseInt(request.getParameter("selectEstado")));
        try {
            Part filePart = request.getPart("txtFile");
            OutputStream out = null;
             out = new FileOutputStream(new File(""));
            InputStream filecontent = null;
            filecontent = filePart.getInputStream();
            
             final byte[] bytes = new byte[1024];
             int read = 0;
        
            while ((read = filecontent.read(bytes)) != -1) {
               out.write(bytes, 0, read);
            }
            dto.getEntidad().setImagen(bytes);
          //  dao.create(dto);
            HttpSession session = request.getSession();
          //  session.setAttribute("datosUsuario", dto);
            cargarPanelPrinBen(request, response);
            
        } catch (IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }/* catch (IOException | ServletException | SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }*/
         
         
         
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
