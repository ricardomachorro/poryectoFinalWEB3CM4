<%-- 
    Document   : registroBeneficiados
    Created on : 2 ene 2021, 15:07:16
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de beneficiados</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarInicioCSS.css">
        <link rel="stylesheet" href="css/registroBeneCSS.css">

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navBarInicio">
            <a class="navbar-brand textoNavBarInicio" >Sistema de apoyos</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span ><img class="imagenNavBarInicio" src="img/lista.png"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                  <a class="nav-link active textoNavBarInicio" href="index.html">Inicio <span class="sr-only">(current)</span></a>
                    <a class="nav-link textoNavBarInicio" href="" >Ingreso beneficiados</a>
                    <a class="nav-link textoNavBarInicio" href="ControladorPrincipal?accion=ingresoBeneficiados">Registro beneficiados</a>
                    <a class="nav-link textoNavBarInicio" href="ingresoAdmi.jsp" >Ingreso administradores</a>
                   
                </div>
            </div>
        </nav>
        <div class="container">
            <form class="formularioRegBen" action="ControladorBeneficiario?accion=registroBeneficiario">
                <h2>Registro Beneficiados</h2>
                <div class="form-group">
                    <label for="txtNombre">Nombre</label>
                    <input type="text" class="form-control" id="txtNombre" >
                </div>
                <div class="form-group">
                    <label for="txtEdad">Edad</label>
                    <input type="number" class="form-control" id="txtEdad" >
                </div>
                <div class="form-group">
                    <label for="txtCalle">Calle</label>
                    <input type="text" class="form-control" id="txtCalle" >
                </div>
                <div class="form-group">
                    <label for="txtMail">Correo</label>
                    <input type="mail" class="form-control" id="txtMail" >
                </div>
                <div class="form-group">
                    <label for="txtFile">Imagen Usuario</label>
                    <input type="file" class="form-control" id="txtFile" >
                </div>
                <div class="form-group">
                    <label for="txtPassword">Contraseña</label>
                    <input type="password" class="form-control" id="txtPassword">
                </div>
                <div class="form-group">
                    <label for="selEstado">Estado</label>
                    <select value="0" 
                            name="selectEstado" 
                            id="selectEstado" class="form-control">
                        <option >Seleccione estado</option>
                        <c:forEach
                            var="dtoEstados"
                            items="${listaEstados}">
                            <option  value="<c:out value="${dtoEstados.entidad.IDEstado}"/>" ><c:out value="${dtoEstados.entidad.nombre}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selMunicipio">Municipio</label>
                    <select class="form-control"
                            id="selectMunicipio"
                            name="selectMunicipio" class="form-control">
                        <option IDEstado="0">Seleccione municipio</option>
                        <c:forEach
                            var="dtoMunicipios"
                            items="${listaMunicipios}">
                            <option hidden  value="<c:out value="${dtoMunicipios.entidad.IDMunicipio}"/>" 
                            IDEstado="<c:out value="${dtoMunicipios.entidad.IDEstado}"/>" >
                                <c:out value="${dtoMunicipios.entidad.nombre}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="txtCodigo">Código</label>
                    <input type="text" class="form-control" id="txtCodigo" >
                </div>
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </form>
        </div>
    </body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript">
     $(document).ready(function(){
         
          $('#selectEstado').on('change', function(){
               var valorEstado = $(this).val();
                $('#selectMunicipio option[IDEstado='+valorEstado+']').removeAttr('hidden');
                $('#selectMunicipio option[IDEstado!='+valorEstado+']').attr('hidden','hidden');
                $("#selectMunicipio option[IDEstado='0']").removeAttr('hidden');
          });
         
     });
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>
