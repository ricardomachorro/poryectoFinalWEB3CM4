<%-- 
    Document   : actualizacionDatosBeneficiario
    Created on : 15 ene 2021, 18:20:22
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Actualización datos beneficiados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarGeneral.css" >
        <link rel="stylesheet" href="css/generalCSS.css" >
        <link rel="stylesheet" href="css/actualizacionDatosBeneficiarioCSS.css">
       
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
            <form class="formularioRegAdm" method="post" action="ControladorBeneficiario?accion=registroBeneficiario" 
                   enctype="multipart/form-data">
                <h2>Actualización datos beneficiados</h2>
                  <input type="hidden" class="form-control" id="txtIdBeneficiario" name="txtIdBeneficiario"
                         value="<c:out value="${dtoBeneficiario.entidad.IDBeneficiado}"/>">
                 <div class="form-group">
                    <label for="txtNombre">Nombre</label>
                    <input type="text" class="form-control" id="txtNombre" name="txtNombre"
                           value="<c:out value="${dtoBeneficiario.entidad.nombreUsuario}" />">
                </div>
                <div class="form-group">
                    <label for="txtEdad">Edad</label>
                    <input type="number" class="form-control" id="txtEdad" name="txtEdad"
                            value="<c:out value="${dtoBeneficiario.entidad.edad}" />">
                </div>
                <div class="form-group">
                    <label for="txtCalle">Calle</label>
                    <input type="text" class="form-control" id="txtCalle" name="txtCalle"
                            value="<c:out value="${dtoBeneficiario.entidad.calle}" />">
                </div>
                <div class="form-group">
                    <label for="txtMail">Correo</label>
                    <input type="mail" class="form-control" id="txtMail" name="txtMail" 
                            value="<c:out value="${dtoBeneficiario.entidad.correo}" />">
                </div>
               <!-- <div class="form-group">
                    <label for="txtFile">Imagen Usuario</label>
                    <input type="file" class="form-control" id="txtFile" name="txtFile" >
                </div>-->
                <div class="form-group">
                    <label for="txtPassword">Contraseña</label>
                    <input type="password" class="form-control" id="txtPassword" name="txtPassword">
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
                    <input type="text" class="form-control" id="txtCodigo" name="txtCodigo" >
                </div>
                <button type="submit" class="btn btn-primary">Actualizar</button>
                <c:out value="${mensaje}" />
            </form>
        </div>
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
    </body>
</html>

