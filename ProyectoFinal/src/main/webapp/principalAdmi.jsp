<%-- 
    Document   : principalAdmi
    Created on : 12 ene 2021, 12:35:19
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
                    <a class="nav-link textoNavBarGeneral" href="ControladorAdmi?accion=cerrarSesion" >Cerrar sesión</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="card cartaOpccionesExtra">
                <div class="card-body">
                    <h5 class="card-title">Visón general </h5>
                    <img class="imgOpccionesLista" src="img/mexico.png">
                    <img class="imgOpccionesLista" src="img/mujer.png">
                    <p class="card-text">Informe de los apoyos en los municipios</p>
                     <p><c:out value="${listaVariantesApoyosSize}"/></p>
            <p><c:out value="${listaApoyosSize}"/></p>
            <p><c:out value="${listaMunicipiosSize}"/></p>
                    <a href="" class="card-link">Ver los apoyos mas solicitados por municipio</a>
                    <a href="" class="card-link">Generar reporte de beneficiados por municipio</a>
                </div>
            </div>
            <table class="table table-striped tablaPer">
                <thead>
                    <tr>
                        <th>ID Variante Apoyo</th>
                        <th>Municipio</th>
                        <th>Apoyo</th>
                        <th>Laboratorio</th>
                        <th>Nombre comercial</th>
                        <!-- <th>Editar datos del apoyo</th>
                         <th>Eliminar apoyo</th>-->
                        <th colspan="3">Opcciones</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach
                            var="dtoVariantesApoyos"
                            items="${listaVariantesApoyos}">
                            <tr>
                                <td><c:out value="${dtoVariantesApoyos.entidad.IDVarianteApoyo}"/></td>
                            <c:forEach
                            var="dtoMumicipios"
                            items="${listaMunicipios}">
                                <c:if test = "${dtoVariantesApoyos.entidad.IDMunicipio==dtoMumicipios.entidad.IDMunicipio}">
                                    <td><c:out value="${dtoMumicipios.entidad.nombre}"/></td>
                                </c:if>
                                
                             </c:forEach>
                              <c:forEach
                            var="dtoApoyos"
                            items="${listaApoyos}">
                                <c:if test = "${dtoVariantesApoyos.entidad.IDApoyo==dtoApoyos.entidad.IDApoyo}">
                                    <td><c:out value="${dtoApoyos.entidad.componente}"/></td>
                                </c:if>
                                
                             </c:forEach>
                       
                    
                    <td><c:out value="${dtoVariantesApoyos.entidad.laboratorio}"/></td>
                    <td><c:out value="${dtoVariantesApoyos.entidad.nombreComercial}"/></td>
                       <td><a   class="btn btn-dark" href="ControladorAdmi?accion=verApoyo&idApoyoVariante=<c:out value="${dtoVariantesApoyos.entidad.IDVarianteApoyo}"/>" >Ver datos apoyo</a></td>
                        <td><a  class="btn btn-success" href="ControladorAdmi?accion=editarApoyo&idApoyoVariante=<c:out value="${dtoVariantesApoyos.entidad.IDVarianteApoyo}"/>" >Editar</a></td>
                        <td><a  class="btn btn-danger" href="ControladorAdmi?accion=eliminarApoyo&idApoyoVariante=<c:out value="${dtoVariantesApoyos.entidad.IDVarianteApoyo}"/>" >Eliminar</a></td></tr>
                    </c:forEach>
                    
               

                </tbody>
            </table>  
            
           
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>

