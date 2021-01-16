<%-- 
    Document   : verVarianteApoyo
    Created on : 14 ene 2021, 23:07:28
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Principal administrador</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarGeneral.css" >
        <link rel="stylesheet" href="css/generalCSS.css" >
        <link rel="stylesheet" href="css/principalAdmCSS.css" >
    </head>
    <body>
        <nav class="navbar navbar-expand-lg  navBarGeneral">
            <a class="navbar-brand textoNavBarGeneral" >Sistema de apoyos</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span ><img class="imagenNavBarInicio" src="img/lista.png"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                <a class="nav-link active textoNavBarGeneral" href="ControladorAdmi?accion=cargarPanelAdmi">Pagina principal administrador<span class="sr-only">(current)</span></a>
                    <a class="nav-link textoNavBarGeneral" href="ControladorAdmi?accion=nuevoApoyo" >Hacer nuevo apoyo</a>
                     <a class="nav-link textoNavBarGeneral" href="ControladorAdmi?accion=listaBeneficiados" >Lista Beneficiados</a>
                     <a class="nav-link textoNavBarGeneral" href="ControladorAdmi?accion=configuracionAdmi" >Cambiar datos administrador</a>
                    <a class="nav-link textoNavBarGeneral" href="ControladorAdmi?accion=cerrarSesion" >Cerrar sesión</a>
                    
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Variante apoyo</h5>
                    <p class="card-text">ID Variante Apoyo:<c:out value="${varianteApoyoDto.entidad.IDVarianteApoyo}" /></p>
                    <p class="card-text">Laboratorio:<c:out value="${varianteApoyoDto.entidad.laboratorio}" /></p>
                    <p class="card-text">Nombre Comercial:<c:out value="${varianteApoyoDto.entidad.nombreComercial}" /></p>
                    <p class="card-text">Municipio:<c:out value="${municipio}" /></p>
                    <p class="card-text">Apoyo(Medicamento):<c:out value="${apoyo}" /></p>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>

