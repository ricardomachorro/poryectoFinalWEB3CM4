/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.BeneficiadosDAO;
import com.ipn.mx.modelo.dao.EstadoDAO;
import com.ipn.mx.modelo.dao.MunicipioDAO;
import com.ipn.mx.modelo.dao.PedidosDAO;
import com.ipn.mx.modelo.dao.VariantesApoyosDAO;
import com.ipn.mx.modelo.dto.BeneficiadosDTO;
import com.ipn.mx.modelo.dto.MunicipioDTO;
import com.ipn.mx.modelo.dto.PedidosDTO;
import com.ipn.mx.modelo.dto.VariantesApoyosDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
               case "formularioRegistroBeneficiario":
                     cargarDatosSignUpBene(request, response);
               break;
               case "ingresoBeneficiario":
                     ingresarBeneficiario(request, response);
               break;
               case "cargarPanelPrinBen":
                     cargarPanelPrinBen(request, response);
               break;
               case "nuevoPedido":
                     nuevoPedido(request, response);
               break;
               case "actualizarPedido":
                     actualizarPedido(request, response);
               break;
               case "guardarPedido":
                     guardarPedido(request, response);
               break;
               case "eliminarPedido":
                     eliminarPedido(request, response);
               break;
               case "verPedido":
                    verPedido(request, response);
               break;
               default :
               break;
        }
    }
    
    private void cargarDatosSignUpBene(HttpServletRequest request, HttpServletResponse response) { 
          EstadoDAO estadosDao = new EstadoDAO();
          MunicipioDAO municipioDao=new MunicipioDAO();
        try {
            List listaEstados = estadosDao.readAll();
            List listaMunicipios= municipioDao.readAll();
            request.setAttribute("listaEstados", listaEstados);
             request.setAttribute("listaMunicipios", listaMunicipios);
             //  request.setAttribute("mensaje", "");
            RequestDispatcher rd = request.getRequestDispatcher("registroBeneficiados.jsp");
            rd.forward(request, response);
            
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     private void ingresarBeneficiario(HttpServletRequest request, HttpServletResponse response) {
          BeneficiadosDAO dao=new BeneficiadosDAO();
          BeneficiadosDTO dto=new BeneficiadosDTO();
          boolean usuarioAceptado=false;
        try {
            List<BeneficiadosDTO> listaBen=dao.readAll();
             for(int i=0;i<listaBen.size();i++){
                    BeneficiadosDTO usuariAna=(BeneficiadosDTO)listaBen.get(i);
                    if((usuariAna.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombre")))
                            && (usuariAna.getEntidad().getContra().equals(request.getParameter("txtPassword"))) ){
                         usuarioAceptado=true;
                         dto=usuariAna;
                         break;
                          
                    }
                }
             
             if(usuarioAceptado){
                HttpSession session = request.getSession();
                session.setAttribute("idUsuarioBeneficirio",dto.getEntidad().getIDBeneficiado());
                session.setAttribute("idMunBeneficirio",dto.getEntidad().getIDMunicipio());
                cargarPanelPrinBen(request,response);
             }else{
                request.setAttribute("mensaje", "datos incorrectos");
                 RequestDispatcher rd = request.getRequestDispatcher("ingresoBeneficiados.jsp");
                 rd.forward(request, response);
             }
            
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }

    private void cargarPanelPrinBen(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PedidosDAO dao=new PedidosDAO();
        try {
        List listaPedidos=dao.readAll();
        
         RequestDispatcher rd = request.getRequestDispatcher("listaDePedidos.jsp");
           for(int i=0;i<listaPedidos.size();i++){
              PedidosDTO pedidosdto=(PedidosDTO) listaPedidos.get(i);
              if(pedidosdto.getEntidad().getIDBeneficiado()!=((Integer) session.getAttribute("idUsuarioBeneficirio") )){
                 listaPedidos.remove(i);
              }
              
           }
            request.setAttribute("listaPedidos",listaPedidos);
            rd.forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void registroBene(HttpServletRequest request, HttpServletResponse response) {
        try {
            BeneficiadosDTO dto=new BeneficiadosDTO();
            BeneficiadosDAO dao=new BeneficiadosDAO();
            MunicipioDAO munDao=new MunicipioDAO();
            MunicipioDTO munDto=new MunicipioDTO();
           
            munDto.getEntidad().setIDMunicipio(Integer.parseInt(request.getParameter("selectMunicipio")));
            munDto=munDao.read(munDto);
            
            if(munDto.getEntidad().getCodigo().equals(request.getParameter("txtCodigo"))){
                List<BeneficiadosDTO> listaBen=dao.readAll();
                boolean usuarioUnico=true;
                for(int i=0;i<listaBen.size();i++){
                    BeneficiadosDTO usuariAna=(BeneficiadosDTO)listaBen.get(i);
                    if(usuariAna.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombre"))){
                      usuarioUnico=false;
                    }
                }
                if(usuarioUnico){
                    dto.getEntidad().setNombreUsuario(request.getParameter("txtNombre"));
                    dto.getEntidad().setEdad(Integer.parseInt(request.getParameter("txtEdad")));
                    dto.getEntidad().setCalle(request.getParameter("txtCalle"));
                    dto.getEntidad().setCorreo(request.getParameter("txtMail"));
                    dto.getEntidad().setContra(request.getParameter("txtPassword"));
                    dto.getEntidad().setIDMunicipio(Integer.parseInt(request.getParameter("selectMunicipio")));
                    dao.create(dto);
                     listaBen=dao.readAll();
                      for(int i=0;i<listaBen.size();i++){
                        BeneficiadosDTO usuariAna=(BeneficiadosDTO)listaBen.get(i);
                        if(usuariAna.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombre"))){
                            dto.getEntidad().setIDBeneficiado(usuariAna.getEntidad().getIDBeneficiado());
                        }
                    }
                     HttpSession session = request.getSession();
                     session.setAttribute("idUsuarioBeneficirio",dto.getEntidad().getIDBeneficiado());
                     session.setAttribute("idMunBeneficirio",dto.getEntidad().getIDMunicipio());
                  //   request.setAttribute("mensaje",dto.getEntidad().getIDBeneficiado());
                  cargarPanelPrinBen(request,response);
                  //  request.setAttribute("mensaje",dto.getEntidad());
                  //   cargarDatosSignUpBene(request, response);
                     
                }else{
                    request.setAttribute("mensaje","Nombre de Usuario ya usado");
                      cargarDatosSignUpBene(request, response);
                }
              
               
            }else{
                 request.setAttribute("mensaje","codigo incorrecto");
                
                 cargarDatosSignUpBene(request, response);
            }
            /*  try {
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
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
     }
    
      private void nuevoPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            VariantesApoyosDAO dao=new VariantesApoyosDAO();
            VariantesApoyosDTO dto=new VariantesApoyosDTO();
             HttpSession session = request.getSession();
             List<VariantesApoyosDTO> listaVariantesApoyos=dao.readAll();
             List<VariantesApoyosDTO> listaVariantesApoyosSalida=dao.readAll();
           listaVariantesApoyosSalida.clear();
             for(int i=0;i<listaVariantesApoyos.size();i++){
               if(listaVariantesApoyos.get(i).getEntidad().getIDMunicipio()==(Integer)session.getAttribute("idMunBeneficirio")){
                   listaVariantesApoyosSalida.add(listaVariantesApoyos.get(i));
               }
             }
            RequestDispatcher rd = request.getRequestDispatcher("PedidoApoyo.jsp");
            request.setAttribute("listaVarianteApoyos",listaVariantesApoyosSalida);
            rd.forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
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

    private void actualizarPedido(HttpServletRequest request, HttpServletResponse response) {
         try {
            VariantesApoyosDAO dao=new VariantesApoyosDAO();
            VariantesApoyosDTO dto=new VariantesApoyosDTO();
            PedidosDAO pedioDao=new PedidosDAO();
            PedidosDTO pedidoDto=new PedidosDTO();
            pedidoDto.getEntidad().setIDPedido(Integer.parseInt(request.getParameter("idApoyo")));
            pedidoDto=pedioDao.read(pedidoDto);
             HttpSession session = request.getSession();
             List<VariantesApoyosDTO> listaVariantesApoyos=dao.readAll();
             List<VariantesApoyosDTO> listaVariantesApoyosSalida=dao.readAll();
           listaVariantesApoyosSalida.clear();
             for(int i=0;i<listaVariantesApoyos.size();i++){
               if(listaVariantesApoyos.get(i).getEntidad().getIDMunicipio()==(Integer)session.getAttribute("idMunBeneficirio")){
                   listaVariantesApoyosSalida.add(listaVariantesApoyos.get(i));
               }
             }
            RequestDispatcher rd = request.getRequestDispatcher("PedidoApoyo.jsp");
            request.setAttribute("listaVarianteApoyos",listaVariantesApoyosSalida);
            request.setAttribute("pedidoDto",pedidoDto);
            rd.forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            PedidosDAO pedDao=new PedidosDAO();
            PedidosDTO pedDto=new PedidosDTO();
            VariantesApoyosDAO varApoDao=new VariantesApoyosDAO();
            VariantesApoyosDTO varApoDto=new VariantesApoyosDTO();
            varApoDto.getEntidad().setIDVarianteApoyo(Integer.parseInt(request.getParameter("selVarApoyo")));
            varApoDto=varApoDao.read(varApoDto);
            HttpSession session = request.getSession();
            pedDto.getEntidad().setIDBeneficiado((Integer)session.getAttribute("idUsuarioBeneficirio"));
            pedDto.getEntidad().setLaboratorio(varApoDto.getEntidad().getLaboratorio());
            pedDto.getEntidad().setNombreComercial(varApoDto.getEntidad().getNombreComercial());
            pedDto.getEntidad().setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
            pedDto.getEntidad().setMesEntrega(request.getParameter("selMesEntrega"));
            if(request.getParameter("idPedido").isBlank()){
              pedDao.create(pedDto);
            }else{
               pedDto.getEntidad().setIDPedido(Integer.parseInt(request.getParameter("idPedido")));
               pedDao.update(pedDto);
            }
            cargarPanelPrinBen(request, response);
                    } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            PedidosDAO pedDao=new PedidosDAO();
            PedidosDTO pedDto=new PedidosDTO();
            pedDto.getEntidad().setIDPedido(Integer.parseInt(request.getParameter("idApoyo")));
            pedDao.delete(pedDto);
             cargarPanelPrinBen(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verPedido(HttpServletRequest request, HttpServletResponse response) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            PedidosDAO pedDao=new PedidosDAO();
            PedidosDTO pedDto=new PedidosDTO();
            pedDto.getEntidad().setIDPedido(Integer.parseInt(request.getParameter("idApoyo")));
            pedDto=pedDao.read(pedDto);
            request.setAttribute("pedidoDTO", pedDto);
            RequestDispatcher rd = request.getRequestDispatcher("verPedido.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
