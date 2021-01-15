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
                    <a class="nav-link active textoNavBarGeneral" href="index.html">Inicio <span class="sr-only">(current)</span></a>
                    <a class="nav-link textoNavBarGeneral" href="listaDePedidos.html" >Registro pedidos de beneficios(medicamentos)</a>
                    <a class="nav-link textoNavBarGeneral" href="PedidoApoyo.html" >Hacer nuevo pedido de medicamento</a>
                    <a class="nav-link textoNavBarGeneral" href="actualizacionDatosBeneficiario.html" >Actualizar datos del beneficiado</a>
                    <a class="nav-link textoNavBarGeneral" href="" >Eliminar cuenta</a>
                    <a class="nav-link textoNavBarGeneral" href="" >Cerrar sesión</a>
                </div>
            </div>
        </nav>
        <div class="container">
             <form class="formularioNuevoPed">
                <h2>Nuevo pedido</h2>
                <div class="form-group">
                    <label for="selMed">Medicamento</label>
                    <select class="form-control">
                        <option>Seleccione medicamento</option>
                        <c:forEach var="dtoVA" 
                                   items="${listaVarianteApoyos}">
                            <option value="<c:out value="${dtoVA.entidad.IDVarianteApoyo}" />" >
                                Nombre comercial:<c:out value="${dtoVA.entidad.nombreComercial}" />  Laboratorio: <c:out value="${dtoVA.entidad.laboratorio}" /> </option>
                        </c:forEach>
                        
                    </select>
                </div>
                <div class="form-group">
                    <label for="txtCantidad">Cantidad</label>
                    <input type="number" class="form-control" id="txtCantidad" >
                </div>
                  <div class="form-group">
                    <label for="txtCantidad">Fecha de entrega solicitada</label>
                    <input type="date" class="form-control" id="txtFechaEntrega" >
                </div>
                <button type="submit" class="btn btn-primary">Enviar pedido</button>
            </form>
        </div>
    </body>
       <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>

