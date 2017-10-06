<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Sistema de Organizações Sociais</title>

	<c:import url="header.jsp"/>
	
  </head>

  <body class="overflow-hidden">
	<!-- Overlay Div -->
	<div id="overlay" class="transparent"></div>
	
	<div id="wrapper" class="sidebar-mini">
		<c:import url="navbar.jsp"/>
		
		<c:import url="sidebar.jsp"/>

		<div id="main-container">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					 <li class="active"><i class="fa fa-home"></i><a href="index.jsp"> Home</a></li>
				</ul>
			</div><!-- breadcrumb -->
			<div class="padding-md logo-center">
				<img src="resources/img/sos_logo.png" alt="Sistema de Organizações Sociais" class="logo-img">
				<!-- <h1 class="text-center text-success">Sistema de Organizações Sociais</h1>
				<h3 class="text-center">Sem Chave Não Compila</h3> -->
			</div>
			<div class="logo-center">
				<div class="btn-group home-buttons">
					<button class="btn btn-home dropdown-toggle" data-toggle="dropdown"> 
						<i class="fa fa fa-handshake-o fa-lg" aria-hidden="true"></i> 
						Organização Social <span class="caret"> </span>
					</button>
                    <ul class="dropdown-menu slidedown dropdown-menu-left">
	                    <li><a href="os-inserir.jsp"><span class="submenu-label"> Cadastrar Organização Social</span></a></li>
						<li><a href="os-consultar.jsp"><span class="submenu-label"> Consultar Organização Social</span></a></li>
                    </ul>
				</div><!-- /btn-group -->
				
				<div class="btn-group home-buttons">
					<button class="btn btn-home dropdown-toggle" data-toggle="dropdown"> 
						<i class="fa fa fa-user fa-lg" aria-hidden="true"></i> 
						Voluntário <span class="caret"> </span>
					</button>
                    <ul class="dropdown-menu slidedown dropdown-menu-left">
	                    <li><a href="vol-inserir.jsp"><span class="submenu-label"> Cadastrar Voluntário</span></a></li>
						<li><a href="vol-consultar.jsp"><span class="submenu-label"> Consultar Voluntário</span></a></li>
                    </ul>
				</div><!-- /btn-group -->				
			</div>
		</div>
	</div>
	
    <c:import url="scripts.jsp"/>
	
  </body>
</html>