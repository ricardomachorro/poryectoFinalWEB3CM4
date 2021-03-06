<%-- 
    Document   : graficaBeneficiadosPorMunicipio
    Created on : 16 ene 2021, 10:54:51
    Author     : Ricardo Alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Grafica beneficiados por municipio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/navBarGeneral.css" >
        <link rel="stylesheet" href="css/generalCSS.css" >
        <link rel="stylesheet" href="css/principalAdmCSS.css" >
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" ></script>
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
            <canvas id="myChart" ></canvas>
            
           
        </div>
    </body>
     <script>

            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: [
                        <c:forEach items="${listaGrafica}" var="dao" varStatus="loop">
                            '${dao.nombre}'${!loop.last ? ',' : ''}
                        </c:forEach>
                        
                    ],
                    datasets: [{
                            label: 'Numero beneficiados',
                            data: [
                                <c:forEach items="${listaGrafica}" var="dao" varStatus="loop">
                                   '${dao.cantidad}'${!loop.last ? ',' : ''}
                               </c:forEach>
                            ],
                            backgroundColor: [
                                <c:forEach items="${listaGrafica}" var="dao" varStatus="loop">
                                   'rgba(255, 99, 132, 0.2)'${!loop.last ? ',' : ''}
                               </c:forEach>
                               
                            ],
                            borderColor: [
                                 <c:forEach items="${listaGrafica}" var="dao" varStatus="loop">
                                   'rgba(255, 99, 132, 1)'${!loop.last ? ',' : ''}
                               </c:forEach>
                                
                            ],
                            borderWidth: 1
                        }]
                },
                options: {
                    legend: { display: false },
                    title: {
                      display: true,
                      text: 'Numero de beneficiados por municipio'
                    },
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
</html>

