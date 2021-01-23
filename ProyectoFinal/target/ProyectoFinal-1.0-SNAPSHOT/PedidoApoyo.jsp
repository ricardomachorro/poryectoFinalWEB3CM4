<%-- 
    Document   : PedidoApoyo
    Created on : 14 ene 2021, 17:59:33
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Nuevo pedido</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarGeneral.css" >
        <link rel="stylesheet" href="css/generalCSS.css" >
        <link rel="stylesheet" href="css/nuevoPedidoCSS.css" >
    </head>
    <body>
        <nav class="navbar navbar-expand-lg  navBarGeneral">
            <a class="navbar-brand textoNavBarGeneral" >Sistema de apoyos</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                   <span ><img class="imagenNavBarInicio" src="img/lista.png"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                     <!--   <a class="nav-link active textoNavBarGeneral" href="index.html">Inicio <span class="sr-only">(current)</span></a>-->
                    <a class="nav-link textoNavBarGeneral" href="ControladorBeneficiario?accion=cargarPanelPrinBen" >Registro pedidos de beneficios(medicamentos)</a>
                    <a class="nav-link textoNavBarGeneral" href="ControladorBeneficiario?accion=nuevoPedido" >Hacer nuevo pedido de medicamento</a>
                    <a class="nav-link textoNavBarGeneral" href="ControladorBeneficiario?accion=formularioActualizarDatosBeneficiario" >Actualizar datos del beneficiado</a>
                    
                    <a class="nav-link textoNavBarGeneral" href="ControladorBeneficiario?accion=cerrarSesion" >Cerrar sesión</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <form class="formularioNuevoPed" method="post" action="ControladorBeneficiario?accion=guardarPedido">
                <h2>Nuevo pedido</h2>
                <input type="hidden" value="<c:out value="${pedidoDto.entidad.IDPedido}" />" 
                       name="idPedido" id="idPedido">
                <div class="form-group">
                    <label for="selMed">Medicamento</label>
                    <select class="form-control" id="selVarApoyo" name="selVarApoyo" required>
                        <option disabled>Seleccione medicamento</option>
                        <c:forEach var="dtoVA" 
                                   items="${listaVarianteApoyos}">
                            <option value="<c:out value="${dtoVA.entidad.IDVarianteApoyo}" />" >
                                Nombre comercial:<c:out value="${dtoVA.entidad.nombreComercial}" />  Laboratorio: <c:out value="${dtoVA.entidad.laboratorio}" /> </option>
                        </c:forEach>
                        
                    </select>
                </div>
                <div class="form-group">
                    <label for="txtCantidad">Cantidad</label>
                    <input required type="number" class="form-control" id="txtCantidad" name="txtCantidad" 
                           value="<c:out value="${pedidoDto.entidad.cantidad}" />">
                </div>
                  <div class="form-group">
                    <label for="txtMesEntrega">Mes Entrega</label>
                    <select required class="form-control" id="selMesEntrega" name="selMesEntrega">
                        <option disabled>Seleccione mes de entrega</option>
                        <option value="Enero" <c:if test="${pedidoDto.entidad.mesEntrega=='Enero'}"> 
                                    <c:out value="selected" /></c:if>>Enero</option>
                        <option value="Febrero" <c:if test="${pedidoDto.entidad.mesEntrega=='Febrero'}">
                           <c:out value="selected" />      
                        </c:if>>Febrero</option>
                        <option value="Marzo" <c:if test="${pedidoDto.entidad.mesEntrega=='Marzo'}">
                            <c:out value="selected" />
                        </c:if>>Marzo</option>
                        <option value="Abril" <c:if test="${pedidoDto.entidad.mesEntrega=='Abril'}">
                             <c:out value="selected" />
                        </c:if>>Abril</option>
                        <option value="Mayo"  <c:if test="${pedidoDto.entidad.mesEntrega=='Mayo'}">
                              <c:out value="selected" />
                        </c:if>>Mayo</option>
                        <option value="Junio" <c:if test="${pedidoDto.entidad.mesEntrega=='Junio'}">
                               <c:out value="selected" />
                        </c:if>>Junio</option>
                        <option value="Julio" <c:if test="${pedidoDto.entidad.mesEntrega=='Julio'}">
                                 <c:out value="selected" />
                        </c:if> >Julio</option>
                        <option value="Agosto" <c:if test="${pedidoDto.entidad.mesEntrega=='Agosto'}">
                                 <c:out value="selected" />
                        </c:if> >Agosto</option>
                        <option value="Septiembre" <c:if test="${pedidoDto.entidad.mesEntrega=='Septiembre'}">
                                 <c:out value="selected" />
                        </c:if>>Septiembre</option>
                        <option value="Octubre" <c:if test="${pedidoDto.entidad.mesEntrega=='Octubre'}">
                                 <c:out value="selected" />
                        </c:if>>Octubre</option>
                        <option value="Noviembre" <c:if test="${pedidoDto.entidad.mesEntrega=='Noviembre'}">
                                 <c:out value="selected" />
                        </c:if>>Noviembre</option>
                        <option value="Diciembre" <c:if test="${pedidoDto.entidad.mesEntrega=='Diciembre'}">
                                <c:out value="selected" />
                        </c:if >>Diciembre</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Enviar pedido</button>
            </form>
        </div>
    </body>
       <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>

