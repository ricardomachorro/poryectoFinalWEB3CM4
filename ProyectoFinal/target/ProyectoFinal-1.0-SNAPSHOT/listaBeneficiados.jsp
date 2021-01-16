<%-- 
    Document   : listaBeneficiados
    Created on : 15 ene 2021, 15:00:57
    Author     : Ricardo Alberto
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <th>ID Beneficiado</th>
                        <th>Nombre usuario</th>
                        <th>Calle</th>
                        <th>Edad</th>
                        <th>Correo</th>
                        <th>Municipio</th>
                        <th>Opcciones</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dtoBen" items="${listaBeneficiados}">
                        <tr>
                            <td><c:out value="${dtoBen.entidad.IDBeneficiado}"/></td>
                            <td><c:out value="${dtoBen.entidad.nombreUsuario}"/></td>
                            <td><c:out value="${dtoBen.entidad.calle}"/></td>
                            <td><c:out value="${dtoBen.entidad.edad}"/></td>
                             <td><c:out value="${dtoBen.entidad.correo}"/></td>
                             <c:forEach var="dtoMun" items="${listaMunicipio}">
                                 <c:if test="${dtoBen.entidad.IDMunicipio==dtoMun.entidad.IDMunicipio}">
                                     <td><c:out value="${dtoMun.entidad.nombre}"/></td>
                                 </c:if>
                             </c:forEach>
                            <td><a  class="btn btn-danger" href="ControladorAdmi?accion=eliminarBeneficiados&idBeneficiado=<c:out value="${dtoBen.entidad.IDBeneficiado}"/>" >Eliminar</a></td>
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


