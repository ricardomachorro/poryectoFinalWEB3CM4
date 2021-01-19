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
import com.ipn.mx.modelo.dto.EstadoDTO;
import com.ipn.mx.modelo.dto.MunicipioDTO;
import com.ipn.mx.modelo.dto.PedidosDTO;
import com.ipn.mx.modelo.dto.VariantesApoyosDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
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
               case "formularioActualizarDatosBeneficiario":
                   formularioActualizarDatosBeneficiario(request, response);
               break;
               case "reportePedidos":
                   reportePedidos(request, response);
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
        List listaPedidosSalida=dao.readAll();
        listaPedidosSalida.clear();
        
         RequestDispatcher rd = request.getRequestDispatcher("listaDePedidos.jsp");
           for(int i=0;i<listaPedidos.size();i++){
              PedidosDTO pedidosdto=(PedidosDTO) listaPedidos.get(i);
              if(pedidosdto.getEntidad().getIDBeneficiado()==((Integer) session.getAttribute("idUsuarioBeneficirio") )){
                 listaPedidosSalida.add(listaPedidos.get(i));
              }
              
           }
            BeneficiadosDAO benDao=new BeneficiadosDAO();
            BeneficiadosDTO benDat=new BeneficiadosDTO();
            MunicipioDAO munDao=new MunicipioDAO();
            MunicipioDTO munDto=new MunicipioDTO();
            EstadoDAO estDao=new EstadoDAO();
            EstadoDTO estDto=new EstadoDTO();
            benDat.getEntidad().setIDBeneficiado((Integer) session.getAttribute("idUsuarioBeneficirio"));
            benDat=benDao.read(benDat);
            munDto.getEntidad().setIDMunicipio(benDat.getEntidad().getIDMunicipio());
            munDto=munDao.read(munDto);
            estDto.getEntidad().setIDEstado(munDto.getEntidad().getIDEstado());
            estDto=estDao.read(estDto);
            request.setAttribute("listaPedidos",listaPedidosSalida);
            request.setAttribute("nombreUsuario", benDat.getEntidad().getNombreUsuario());
            request.setAttribute("edadUsuario", benDat.getEntidad().getEdad());
            request.setAttribute("calleUsuario", benDat.getEntidad().getCalle());
            request.setAttribute("municipioUsuario", munDto.getEntidad().getNombre());
            request.setAttribute("estadoUsuario", estDto.getEntidad().getNombre());
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
                boolean nombreIgualAnterior=false;
                for(int i=0;i<listaBen.size();i++){
                    BeneficiadosDTO usuariAna=(BeneficiadosDTO)listaBen.get(i);
                    if(usuariAna.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombre"))){
                      usuarioUnico=false;
                    }
                }
                if(!(request.getParameter("txtIdBeneficiario").isBlank())){
                   HttpSession session = request.getSession();
                   dto.getEntidad().setIDBeneficiado((Integer) session.getAttribute("idUsuarioBeneficirio") );
                   dto=dao.read(dto);
                   if(dto.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombre"))){
                        nombreIgualAnterior=true;
                   }else{
                       nombreIgualAnterior=false;
                   }
                }
                if(usuarioUnico || nombreIgualAnterior){
                    dto.getEntidad().setNombreUsuario(request.getParameter("txtNombre"));
                    dto.getEntidad().setEdad(Integer.parseInt(request.getParameter("txtEdad")));
                    dto.getEntidad().setCalle(request.getParameter("txtCalle"));
                    dto.getEntidad().setCorreo(request.getParameter("txtMail"));
                    dto.getEntidad().setContra(request.getParameter("txtPassword"));
                    dto.getEntidad().setIDMunicipio(Integer.parseInt(request.getParameter("selectMunicipio")));
                   
                    if(request.getParameter("txtIdBeneficiario").isBlank()){
                       dao.create(dto);
                   /*  listaBen=dao.readAll();
                      for(int i=0;i<listaBen.size();i++){
                        BeneficiadosDTO usuariAna=(BeneficiadosDTO)listaBen.get(i);
                        if(usuariAna.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombre"))){
                            dto.getEntidad().setIDBeneficiado(usuariAna.getEntidad().getIDBeneficiado());
                        }
                    }
                    HttpSession session = request.getSession();
                     session.setAttribute("idUsuarioBeneficirio",dto.getEntidad().getIDBeneficiado());
                     session.setAttribute("idMunBeneficirio",dto.getEntidad().getIDMunicipio());
                    request.setAttribute("mensaje",dto.getEntidad().getIDBeneficiado());
                  cargarPanelPrinBen(request,response);*/
                 
                   RequestDispatcher rd = request.getRequestDispatcher("ingresoBeneficiados.jsp");
                 rd.forward(request, response);
                  //  request.setAttribute("mensaje",dto.getEntidad());
                  //   cargarDatosSignUpBene(request, response);
                    }else{
                        dto.getEntidad().setIDBeneficiado(Integer.parseInt(request.getParameter("txtIdBeneficiario")));
                        dao.update(dto);
                        HttpSession session = request.getSession();
                         session.removeAttribute("idUsuarioBeneficirio");
                         session.removeAttribute("idMunBeneficirio");
                     session.setAttribute("idUsuarioBeneficirio",Integer.parseInt(request.getParameter("txtIdBeneficiario")));
                     session.setAttribute("idMunBeneficirio",dto.getEntidad().getIDMunicipio());
                        cargarPanelPrinBen(request, response);
                    }
                    
                     
                }else{
                    request.setAttribute("mensaje","Nombre de Usuario ya usado");
                    if(request.getParameter("txtIdBeneficiario").isBlank()){
                       cargarDatosSignUpBene(request, response);
                    }else{
                       formularioActualizarDatosBeneficiario(request, response);
                    }
                      
                }
              
               
            }else{
                 request.setAttribute("mensaje","codigo incorrecto");
                if(request.getParameter("txtIdBeneficiario").isBlank()){
                       cargarDatosSignUpBene(request, response);
                    }else{
                        formularioActualizarDatosBeneficiario(request, response);
                    }
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
        } catch (ServletException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
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

    private void formularioActualizarDatosBeneficiario(HttpServletRequest request, HttpServletResponse response) {
        try {
            //To change body of generated methods, choose Tools | Templates.
             EstadoDAO estadosDao = new EstadoDAO();
          MunicipioDAO municipioDao=new MunicipioDAO();
      
            List listaEstados = estadosDao.readAll();
            List listaMunicipios= municipioDao.readAll();
            request.setAttribute("listaEstados", listaEstados);
             request.setAttribute("listaMunicipios", listaMunicipios);
            BeneficiadosDAO dao=new BeneficiadosDAO();
            BeneficiadosDTO dto=new BeneficiadosDTO();
            HttpSession session = request.getSession();
            dto.getEntidad().setIDBeneficiado((Integer)session.getAttribute("idUsuarioBeneficirio"));
            dto=dao.read(dto);
            request.setAttribute("dtoBeneficiario", dto);
            RequestDispatcher rd = request.getRequestDispatcher("actualizacionDatosBeneficiario.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void reportePedidos(HttpServletRequest request, HttpServletResponse response) {
        //To change body of generated methods, choose Tools | Templates.
         response.setContentType("application/pdf");
        try {
            Font bfBold24 = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, new BaseColor(0, 0, 0));
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            HttpSession session = request.getSession();
            int idBeneficiario=(Integer)session.getAttribute("idUsuarioBeneficirio");
            BeneficiadosDAO benDao=new BeneficiadosDAO();
            BeneficiadosDTO benDto=new BeneficiadosDTO();
            benDto.getEntidad().setIDBeneficiado(idBeneficiario);
            benDto=benDao.read(benDto);
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.addAuthor(benDto.getEntidad().getNombreUsuario());
            document.addCreationDate();
            document.addSubject("Using iText");
            document.addKeywords("Java, PDF, iText");
            document.addCreator("Proyecto sistema de apoyos 3CM4");
            document.addTitle("Reporte pedidos PDF");
            document.open();
            document.add(new Paragraph("Lista de Pedidos \n", bfBold24));
            document.add(new Paragraph("Beneficiado solicitante: " + benDto.getEntidad().getNombreUsuario() + " \n", bfBold12));
            Date date = new Date();
            document.add(new Paragraph("Fecha del reporte:"+date.toString(), bfBold12));
            document.add(new Paragraph("\n \n \n", bfBold12));
            PedidosDAO dao = new PedidosDAO();
            List listaPedidos = dao.readAll();
             List listaPedidosBeneficiado = dao.readAll();
             listaPedidosBeneficiado.clear();
            for(int i=0;i<listaPedidos.size();i++){
              PedidosDTO pedDTO=(PedidosDTO)listaPedidos.get(i);
              if(pedDTO.getEntidad().getIDBeneficiado()==idBeneficiario){
                listaPedidosBeneficiado.add(listaPedidos.get(i));
              }
            }
            PdfPTable table = new PdfPTable(5);
            table.addCell("IDPedido");
            table.addCell("Nombre comercial");
            table.addCell("Laboratorio");
            table.addCell("Cantidad");
            table.addCell("Mes de entrega");
            for (int i = 0; i < listaPedidosBeneficiado.size(); i++) {
                PedidosDTO pedTabla = (PedidosDTO) listaPedidosBeneficiado.get(i);
                table.addCell(String.valueOf(pedTabla.getEntidad().getIDPedido()));
                table.addCell(pedTabla.getEntidad().getNombreComercial());
                table.addCell(pedTabla.getEntidad().getLaboratorio());
               table.addCell(String.valueOf(pedTabla.getEntidad().getCantidad()));
               table.addCell(pedTabla.getEntidad().getMesEntrega());
            }
            document.add(table);
            document.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBeneficiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
