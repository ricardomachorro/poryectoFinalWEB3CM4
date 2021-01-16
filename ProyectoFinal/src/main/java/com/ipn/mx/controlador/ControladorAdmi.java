/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.ApoyosDAO;
import com.ipn.mx.modelo.dao.BeneficiadosDAO;
import com.ipn.mx.modelo.dao.EstadoDAO;
import com.ipn.mx.modelo.dao.MunicipioDAO;
import com.ipn.mx.modelo.dao.VariantesApoyosDAO;
import com.ipn.mx.modelo.dto.ApoyosDTO;
import com.ipn.mx.modelo.dto.BeneficiadosDTO;
import com.ipn.mx.modelo.dto.EstadoDTO;
import com.ipn.mx.modelo.dto.MunicipioDTO;
import com.ipn.mx.modelo.dto.VariantesApoyosDTO;
import com.ipn.mx.modelo.entidades.Estado;
import com.ipn.mx.modelo.entidades.Municipio;
import com.ipn.mx.modelo.entidades.VariantesApoyos;
import com.ipn.mx.utilerias.EnviarEmail;
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
                   ingresoAdmi(request, response);
               case "nuevoApoyo":
                   nuevoApoyo(request, response);
               break;
                case "editarApoyo":
                   editarApoyo(request, response);
               break;
               case "almacenarApoyo":
                   almacenarApoyo(request, response);
               break;
               case "eliminarApoyo":
                   eliminarApoyo(request, response);
               break;
               case "cargarPanelAdmi":
                    cargarPanelAdmi(request, response);
               break;
               case "cerrarSesion":
                    cerrarSesion(request, response);
               break;
               case "verApoyo":
                    verApoyo(request, response);
               break;
               case "listaBeneficiados":
                    listaBeneficiados(request,response);
               break;
                case "eliminarBeneficiados":
                    eliminarBeneficiado(request,response);
               break;
                case "configuracionAdmi":
                    configuracionAdmi(request,response);
               break;
               case "guardarCambiosConfiguracionAdmi":
                    guardarCambiosConfiguracionAdmi(request,response);
               break;
               default :
               break;
        }
    }
    
     private void cargarPanelAdmi(HttpServletRequest request, HttpServletResponse response) {
     
         try{
            HttpSession session = request.getSession();
           EstadoDAO estadoDao= new EstadoDAO();
            EstadoDTO estadoDTO=(EstadoDTO)session.getAttribute("usuarioEstadosDatos");

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
           
               
                session.setAttribute("usuarioEstadosDatos", estadoDTO);
               request.setAttribute("listaVariantesApoyos",variantesApoyosSalida);
               request.setAttribute("listaApoyos",listaApoyos );
               request.setAttribute("listaMunicipios",listaMunicipios );
               request.setAttribute("listaVariantesApoyosSize",variantesApoyosSalida.size());
               request.setAttribute("listaApoyosSize",listaApoyos.size() );
               request.setAttribute("listaMunicipiosSize",listaMunicipios.size() );
               RequestDispatcher rd = request.getRequestDispatcher("principalAdmi.jsp");
               
               rd.forward(request, response);
         
         
         } catch (ServletException | IOException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
    
     private void ingresoAdmi(HttpServletRequest request, HttpServletResponse response) {
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
              /*  VariantesApoyosDAO varianteApoyosDao= new VariantesApoyosDAO();
                List<VariantesApoyosDTO> variantesApoyosLista=varianteApoyosDao.readAll();
                List<VariantesApoyosDTO> variantesApoyosSalida=varianteApoyosDao.readAll();
                variantesApoyosSalida.clear();
                MunicipioDAO municipioDao=new MunicipioDAO();
                List<MunicipioDTO> listaMunicipios=municipioDao.readAll();
                ApoyosDAO apoyoDao=new ApoyosDAO();
                List<ApoyosDTO> listaApoyos=apoyoDao.readAll();
                
              
               for(int i=0;i<variantesApoyosLista.size();i++){
                  int IDMunicipio=variantesApoyosLista.get(i).getEntidad().getIDMunicipio();
                  MunicipioDTO munEvaluar=new MunicipioDTO();
                  munEvaluar.getEntidad().setIDMunicipio(IDMunicipio);
                  munEvaluar=municipioDao.read(munEvaluar);
                  if(munEvaluar.getEntidad().getIDEstado()==estadoDTO.getEntidad().getIDEstado()){
                      variantesApoyosSalida.add(variantesApoyosLista.get(i));
                  }
               }*/
           
                HttpSession session = request.getSession();
                session.invalidate();
                session=request.getSession();
                session.setAttribute("usuarioEstadosDatos", estadoDTO);
              /* request.setAttribute("listaVariantesApoyos",variantesApoyosSalida);
               request.setAttribute("listaApoyos",listaApoyos );
               request.setAttribute("listaMunicipios",listaMunicipios );
               request.setAttribute("listaVariantesApoyosSize",variantesApoyosSalida.size());
               request.setAttribute("listaApoyosSize",listaApoyos.size() );
               request.setAttribute("listaMunicipiosSize",listaMunicipios.size() );
               RequestDispatcher rd = request.getRequestDispatcher("principalAdmi.jsp");
               
               rd.forward(request, response);*/
              
                cargarPanelAdmi(request, response);
               
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

      private void almacenarApoyo(HttpServletRequest request, HttpServletResponse response) {
      
         VariantesApoyosDTO varApoyoEntrada=new VariantesApoyosDTO();
         varApoyoEntrada.getEntidad().setNombreComercial(request.getParameter("txtNombreCom"));
         varApoyoEntrada.getEntidad().setLaboratorio(request.getParameter("txtLab"));
         varApoyoEntrada.getEntidad().setIDMunicipio(Integer.parseInt(request.getParameter("selMunicipio")));
         varApoyoEntrada.getEntidad().setIDApoyo(Integer.parseInt(request.getParameter("selApoyo")));
         VariantesApoyosDAO dao=new VariantesApoyosDAO();
         try{
             
           if(request.getParameter("idVariacionApoyo").isBlank()){
            dao.create(varApoyoEntrada);
            }else{
             varApoyoEntrada.getEntidad().setIDVarianteApoyo(Integer.parseInt(request.getParameter("idVariacionApoyo")));
            dao.update(varApoyoEntrada);
            }
             /* HttpSession session = request.getSession();
              EstadoDTO estadoDTO=(EstadoDTO) session.getAttribute("usuarioEstadosDatos");
              VariantesApoyosDAO varianteApoyosDao= new VariantesApoyosDAO();
                List<VariantesApoyosDTO> variantesApoyosLista=varianteApoyosDao.readAll();
                List<VariantesApoyosDTO> variantesApoyosSalida=varianteApoyosDao.readAll();
                variantesApoyosSalida.clear();
                MunicipioDAO municipioDao=new MunicipioDAO();
                List<MunicipioDTO> listaMunicipios=municipioDao.readAll();
                ApoyosDAO apoyoDao=new ApoyosDAO();
                List<ApoyosDTO> listaApoyos=apoyoDao.readAll();
                for(int i=0;i<variantesApoyosLista.size();i++){
                  int IDMunicipio=variantesApoyosLista.get(i).getEntidad().getIDMunicipio();
                  MunicipioDTO munEvaluar=new MunicipioDTO();
                  munEvaluar.getEntidad().setIDMunicipio(IDMunicipio);
                  munEvaluar=municipioDao.read(munEvaluar);
                  if(munEvaluar.getEntidad().getIDEstado()==estadoDTO.getEntidad().getIDEstado()){
                      variantesApoyosSalida.add(variantesApoyosLista.get(i));
                  }
               }
               
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
               
               rd.forward(request, response);*/
             
              cargarPanelAdmi(request, response);
         
         } catch (SQLException ex) { 
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
         
      }
      
       private void eliminarApoyo(HttpServletRequest request, HttpServletResponse response) {
        try {
            int IDVarianteApoyo=Integer.parseInt(request.getParameter("idApoyoVariante"));
            VariantesApoyosDAO dao=new VariantesApoyosDAO();
            VariantesApoyosDTO dto =new VariantesApoyosDTO();
            dto.getEntidad().setIDVarianteApoyo(IDVarianteApoyo);
            dao.delete(dto);
           /* HttpSession session = request.getSession();
              EstadoDTO estadoDTO=(EstadoDTO) session.getAttribute("usuarioEstadosDatos");
              VariantesApoyosDAO varianteApoyosDao= new VariantesApoyosDAO();
                List<VariantesApoyosDTO> variantesApoyosLista=varianteApoyosDao.readAll();
                List<VariantesApoyosDTO> variantesApoyosSalida=varianteApoyosDao.readAll();
                variantesApoyosSalida.clear();
                MunicipioDAO municipioDao=new MunicipioDAO();
                List<MunicipioDTO> listaMunicipios=municipioDao.readAll();
                ApoyosDAO apoyoDao=new ApoyosDAO();
                List<ApoyosDTO> listaApoyos=apoyoDao.readAll();
                for(int i=0;i<variantesApoyosLista.size();i++){
                  int IDMunicipio=variantesApoyosLista.get(i).getEntidad().getIDMunicipio();
                  MunicipioDTO munEvaluar=new MunicipioDTO();
                  munEvaluar.getEntidad().setIDMunicipio(IDMunicipio);
                  munEvaluar=municipioDao.read(munEvaluar);
                  if(munEvaluar.getEntidad().getIDEstado()==estadoDTO.getEntidad().getIDEstado()){
                      variantesApoyosSalida.add(variantesApoyosLista.get(i));
                  }
               }
               
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
               
               rd.forward(request, response);*/
               cargarPanelAdmi(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
       }
       
       private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
         RequestDispatcher rd = request.getRequestDispatcher("index.html");
               
        try {
             HttpSession session = request.getSession();
             session.invalidate();
            rd.forward(request, response);
            
        } catch (ServletException | IOException ex) {
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

    private void verApoyo(HttpServletRequest request, HttpServletResponse response) {
        try {
            int IDVarianteApoyo=Integer.parseInt(request.getParameter("idApoyoVariante"));
            VariantesApoyosDAO dao=new VariantesApoyosDAO();
            VariantesApoyosDTO dto =new VariantesApoyosDTO();
            ApoyosDAO apoDao=new ApoyosDAO();
            ApoyosDTO apoDto=new ApoyosDTO();
            MunicipioDAO munDao=new MunicipioDAO();
            MunicipioDTO munDto=new MunicipioDTO();
         
            dto.getEntidad().setIDVarianteApoyo(IDVarianteApoyo);
            dto=dao.read(dto);
            apoDto.getEntidad().setIDApoyo(dto.getEntidad().getIDApoyo());
            apoDto=apoDao.read(apoDto);
            munDto.getEntidad().setIDMunicipio(dto.getEntidad().getIDMunicipio());
            munDto=munDao.read(munDto);
            request.setAttribute("municipio", munDto.getEntidad().getNombre());
            request.setAttribute("apoyo", apoDto.getEntidad().getComponente());
            request.setAttribute("varianteApoyoDto", dto);
             RequestDispatcher rd = request.getRequestDispatcher("verVarianteApoyo.jsp");
             rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    private void listaBeneficiados(HttpServletRequest request, HttpServletResponse response) {
       //To change body of generated methods, choose Tools | Templates.
        try {
            BeneficiadosDAO dao=new  BeneficiadosDAO();
            List<BeneficiadosDTO> listaBeneficiados=dao.readAll();
             List<BeneficiadosDTO> listaBeneficiadosSalida=dao.readAll();
             listaBeneficiadosSalida.clear();
            MunicipioDAO munDao=new MunicipioDAO();
            List<MunicipioDTO> listaMunicipios=munDao.readAll();
            HttpSession session = request.getSession();
            EstadoDTO usuarioEstado= (EstadoDTO) session.getAttribute("usuarioEstadosDatos");
            for(int i=0;i<listaBeneficiados.size();i++){
              MunicipioDTO munDto=new MunicipioDTO();
              int IDMunBen=listaBeneficiados.get(i).getEntidad().getIDMunicipio();
              munDto.getEntidad().setIDMunicipio(IDMunBen);
              munDto=munDao.read(munDto);
              if(munDto.getEntidad().getIDEstado()==usuarioEstado.getEntidad().getIDEstado()){
                 listaBeneficiadosSalida.add(listaBeneficiados.get(i));
              }
            }
         
          request.setAttribute("listaMunicipio", listaMunicipios);
          request.setAttribute("listaBeneficiados",listaBeneficiadosSalida);
         RequestDispatcher rd = request.getRequestDispatcher("listaBeneficiados.jsp");
       
            rd.forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarBeneficiado(HttpServletRequest request, HttpServletResponse response) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            
            BeneficiadosDAO dao=new BeneficiadosDAO();
            BeneficiadosDTO dto=new BeneficiadosDTO();
            dto.getEntidad().setIDBeneficiado(Integer.parseInt(request.getParameter("idBeneficiado")));
            dto=dao.read(dto);
            EnviarEmail mail =new EnviarEmail();
            String asunto="Borrado decuenta sistema de apoyos";
            String mensaje="Usuario "+dto.getEntidad().getNombreUsuario()+ " su cuenta ha sido eliminada del sistema de apoyos junto con todos su pedidios";
            mail.enviarCorreo(dto.getEntidad().getCorreo(), asunto, mensaje);
             dao.delete(dto);
            listaBeneficiados(request, response);
           
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configuracionAdmi(HttpServletRequest request, HttpServletResponse response) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            HttpSession session = request.getSession();
            EstadoDTO usuarioEstado= (EstadoDTO) session.getAttribute("usuarioEstadosDatos");
            request.setAttribute("dtoEstado",usuarioEstado );
            RequestDispatcher rd = request.getRequestDispatcher("configuracionAdmi.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    private void guardarCambiosConfiguracionAdmi(HttpServletRequest request, HttpServletResponse response) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            HttpSession session = request.getSession();
            EstadoDTO usuarioEstado= (EstadoDTO) session.getAttribute("usuarioEstadosDatos");
            EstadoDAO dao=new EstadoDAO();
            usuarioEstado.getEntidad().setNombreUsuarioEncargado(request.getParameter("txtNombre"));
            usuarioEstado.getEntidad().setContra(request.getParameter("txtPassword"));
            dao.update(usuarioEstado);
            session.removeAttribute("usuarioEstadosDatos");
            session.setAttribute("usuarioEstadosDatos",usuarioEstado);
            cargarPanelAdmi(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

}
