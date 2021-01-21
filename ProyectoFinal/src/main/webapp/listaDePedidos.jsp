<%-- 
    Document   : listaDePedidos
    Created on : 14 ene 2021, 16:02:59
    Author     : Ricardo Alberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro de pedidos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarGeneral.css" >
        <link rel="stylesheet" href="css/generalCSS.css" >
        <link rel="stylesheet" href="css/registroPedidosCSS.css" >
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
                    
                    <a class="nav-link textoNavBarGeneral" href="" >Cerrar sesión</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="card cartaOpccionesExtra">
                <div class="card-body">
                    <h5 class="card-title">Lista de pedidos </h5>
                    <img class="imgOpccionesLista" src="D:\5semestreDocsGrandes\WebAplicationDocGrandes\ProyectoFinalSemestre\ArchivosDeGitNormal\poryectoFinalWEB3CM4\ProyectoFinal\target\ProyectoFinal-1.0-SNAPSHOT\uploadFiles\imagenEdmodo.png"  >
               <!--     <img class="imgOpccionesLista" src="img/medicamento.png">-->
                    <p class="card-text">Nombre usuario:<c:out value="${nombreUsuario}"/></p>
                    <p class="card-text">Edad usuario:<c:out value="${edadUsuario}"/></p>
                    <p class="card-text">Calle usuario:<c:out value="${calleUsuario}"/></p>
                    <p class="card-text">Municipio usuario:<c:out value="${municipioUsuario}"/></p>
                    <p class="card-text">Estado usuario:<c:out value="${estadoUsuario}"/></p>
                     <a href="ControladorBeneficiario?accion=reportePedidos" class="card-link">Hacer reporte/comprobante de pedidos hechos hasta la fecha</a>
               <!--     <p class="card-text">Aqui hay unas opcciones extra para las clases de los pedidos</p>
                   
                    <a href="" class="card-link">Generar comprobante de pedidos hechos hasta la fecha</a>-->
                </div>
            </div>
             <table class="table table-striped tablaPer">
                <thead>
                    <tr>
                        <th>ID Pedido</th>
                        <th>Nombre comercial</th>
                      <!--  <th>Laboratorio</th>
                        <th>Cantidad</th>
                        <th>Fecha del pedidio</th>-->
                        <th>Mes entrega</th>
                        <th  colspan="4">Opcciones</th>
                       
                    </tr>
                </thead>
                <tbody>
                <c:forEach 
                    var="dtoPedidos"
                    items="${listaPedidos}">
                    <tr>
                        <td><c:out value="${dtoPedidos.entidad.IDPedido}"/></td>
                        <td><c:out value="${dtoPedidos.entidad.nombreComercial}"/></td>
                      <!--  <td>Laboratorio</td>
                        <td>Cantidad</td>
                        <td>Fecha del pedidio</td>-->
                        <td><c:out value="${dtoPedidos.entidad.mesEntrega}"/></td>
                        <td><a type="button"  class="btn btn-dark"  href="ControladorBeneficiario?accion=verPedido&idApoyo=<c:out value="${dtoPedidos.entidad.IDPedido}"/>">Ver pedido</a></td>
                     <!--   <td><a type="button" class="btn btn-primary">Comprobante de pedido</a></td>-->
                        <td><a type="button" class="btn btn-success" href="ControladorBeneficiario?accion=actualizarPedido&idApoyo=<c:out value="${dtoPedidos.entidad.IDPedido}"/>">Editar pedido</a></td>
                        <td><a  class="btn btn-danger" href="ControladorBeneficiario?accion=eliminarPedido&idApoyo=<c:out value="${dtoPedidos.entidad.IDPedido}"/>" >Cancelar pedido</a></td>
                        </tr>   
                </c:forEach>
                    
                  
                </tbody>
            </table>  
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>