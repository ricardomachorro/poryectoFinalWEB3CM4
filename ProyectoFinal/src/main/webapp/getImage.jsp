<%-- 
    Document   : getImage
    Created on : 22 ene 2021, 16:21:59
    Author     : Ricardo Alberto
--%>


<%@page import="java.io.OutputStream"%>
<%@page import="com.ipn.mx.modelo.dao.BeneficiadosDAO"%>
<%@page import="com.ipn.mx.modelo.dto.BeneficiadosDTO"%>
<%
 BeneficiadosDAO dao=new BeneficiadosDAO();
 BeneficiadosDTO dto=new BeneficiadosDTO();
 dto.getEntidad().setIDBeneficiado(Integer.parseInt(request.getParameter("id")));
 dto=dao.read(dto);
 byte byteArray[] =dto.getEntidad().getImagen();
 response.setContentType("image/gif");
        OutputStream os = response.getOutputStream();
        os.write(byteArray);
        os.flush();
        os.close();
%>