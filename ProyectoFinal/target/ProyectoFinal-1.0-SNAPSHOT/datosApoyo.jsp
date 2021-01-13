<%-- 
    Document   : datosApoyo
    Created on : 12 ene 2021, 21:39:18
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
           <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarGeneral.css" >
        <link rel="stylesheet" href="css/generalCSS.css" >
        <link rel="stylesheet" href="css/datosApoyoCSS.css" >
    </head>
    <body>
        <nav class="navbar navbar-expand-lg  navBarGeneral">
            <a class="navbar-brand textoNavBarGeneral" >Sistema de apoyos</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span ><img class="imagenNavBarInicio" src="img/lista.png"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active textoNavBarGeneral" href="principalAdmi.html">Pagina principal administrador<span class="sr-only">(current)</span></a>
                    <a class="nav-link textoNavBarGeneral" href="datosApoyo.html" >Hacer nuevo apoyo</a>
                    <a class="nav-link textoNavBarGeneral" href="" >Cerrar sesión</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <form class="formularioDatosApoyo">
                <h2>Datos Apoyo</h2>
                <input type="hidden" class="form-control"
                       id="idVariacionApoyo" name="idVariacionApoyo" 
                       value="<c:out value="${varianteApoyo.entidad.IDVarianteApoyo}"/>">
                
                <div class="form-group">
                    <label for="txtNombreCom">Nombre Comercial</label>
                    <input type="text" class="form-control" id="txtNombreCom"
                           name="txtNombreCom"
                      value="<c:out value="${varianteApoyo.entidad.nombreComercial}"/>"     >
                </div>
                 <div class="form-group">
                    <label for="txtLab">Laboratorio</label>
                    <input type="text" class="form-control" id="txtLab" 
                           name="txtLab" 
                        value="<c:out value="${varianteApoyo.entidad.laboratorio}"/>" >
                </div>
                <div class="form-group">
                    <label for="selMunicipio">Municipio</label>
                    <select class="form-control" 
                            name="selMunicipio" 
                            id="selMunicipio">
                        <option>Seleccione municipio</option>
                        <c:forEach
                            var="dtoMunicipio"
                            items="${listaMunicipios}">
                            <option value="<c:out value="${dtoMunicipio.entidad.IDMunicipio}" />"><c:out value="${dtoMunicipio.entidad.nombre}" /></option>
                            </c:forEach>
                        
                    </select>
                </div>
                <div class="form-group">
                    <label for="selApoyo">Apoyo</label>
                    <select class="form-control"
                            name="selApoyo" 
                            id="selApoyo">
                        <option>Seleccione apoyo</option>
                       <c:forEach
                            var="dtoApoyos"
                            items="${listaApoyos}">
                            <option value="<c:out value="${dtoApoyos.entidad.IDApoyo}" />"><c:out value="${dtoApoyos.entidad.componente}" /></option>
                            </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </form>
        </div>
    </body>
       <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>
