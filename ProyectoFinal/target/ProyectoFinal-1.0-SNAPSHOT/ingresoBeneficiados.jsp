<%-- 
    Document   : ingresoBeneficiados
    Created on : 2 ene 2021, 14:42:27
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro beneficiados</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarInicioCSS.css">
        <link rel="stylesheet" href="css/ingresoBeneficiadosCSS.css">

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
                    <a class="nav-link textoNavBarInicio" href="ingresoBeneficiados.html" >Ingreso beneficiados</a>
                    <a class="nav-link textoNavBarInicio" href="registroBeneficiados.html">Registro beneficiados</a>
                    <a class="nav-link textoNavBarInicio" href="ingresoAdmi.html" >Ingreso administradores</a>
                    <a class="nav-link textoNavBarInicio" href="registroAdmi.html">Registro administradores</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <form class="formularioIngBenef">
                <h2>Ingreso beneficiados</h2>
                <div class="form-group">
                    <label for="txtNombre">Nombre</label>
                    <input type="text" class="form-control" id="txtNombre" >

                </div>
                <div class="form-group">
                    <label for="txtPassword">Contraseña</label>
                    <input type="password" class="form-control" id="txtPassword">
                </div>

                <button type="submit" class="btn btn-primary">Ingresar</button>
            </form>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>

