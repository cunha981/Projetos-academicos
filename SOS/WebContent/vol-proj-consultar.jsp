<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Consultar Voluntários Associados</title>

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
					 <li><i class="fa fa-home"></i><a href="index.html"> Home</a></li>
					 <li class="active">Consultar voluntários associados</li>	 
				</ul>
			</div><!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->
                <!-- <a href="" id="theme-setting-icon" class="tooltip-test" data-toggle="tooltip" data-placement="left" title="Incluir novo projeto"><i class="fa fa-plus fa-lg"></i></a> -->
                <div class="panel panel-default">
                        <div class="panel-heading">
                            Voluntários associados
                        </div>
                        <div class="panel-body">
                        	<form action="VoluntarioController.do" method="get" id="filtro-js" data-validate="parsley">
                        	<input name="acao" value="vAssociados" type="hidden">
	                            <div class="row">
	                                <div class="col-md-6 col-sm-6">
	                                    <label>Nome do projeto</label>
										<select class="form-control input-sm" name="idProjeto" id="projeto-select" data-required="true">
											<option value="">Selecione</option>
											<c:forEach items="${projetos }" var="p">
												<option value="${p.id }" ${idProjeto == p.id? 'selected':'' }>${p.nome }</option>
											</c:forEach>
										</select>
	                                </div><!-- /.col -->
	                                <div class="col-md-3 col-sm-3">
	                                    <button class="btn btn-primary btn-sm" style="margin-top: 21px;"><i class="fa fa-search"></i></button>
	                                </div><!-- /.col -->
	                            </div><!-- /.row -->
                            </form>
                        </div>
                        <c:if test="${not empty vazia }">
	                        <div class="odd text-center alert alert-default">
	                        	<p>Nenhum registro encontrado</p>
	                        </div>
                        </c:if>
                        <c:if test="${not empty voluntarios }">
	                        <table class="table table-striped" id="responsiveTable">
	                            <thead>
	                                <tr>
	                                    <th class="text-center">Nome</th>
	                                    <th class="text-center">CPF</th>
	                                    <th class="text-center">Telefone</th>
	                                    <th class="text-center">E-mail</th>
	                                    <th></th>
	                                </tr>
	                            </thead>
	                            <tbody class="text-center">
	                                <c:forEach items="${voluntarios }" var="v">
		                                <tr>
		                                    <td>${v.nome }</td>
		                                    <td>${v.cpf }</td>
		                                    <td>${v.tel }</td>
		                                    <td>${v.email }</td>
		                                    <td>
		                                        <div class="btn-group">
									                <button class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown"><span class="fa fa-cogs"> </span> <span class="caret"></span></button>
		                                            <ul class="dropdown-menu slidedown dropdown-menu-right">
		                                                <li><a href="VoluntarioController.do?acao=detalhes&idVoluntario=${v.id }"><i class="fa fa-eye"></i> Detalhes</a></li>
		                                            </ul>
								                </div><!-- /btn-group -->
		                                    </td>
		                                </tr>
	                                </c:forEach>
	                            </tbody>
	                        </table>
                        </c:if>
                        <div class="panel-footer clearfix"></div>
                    </div><!-- /panel -->
                <!--Corpo da pagina-->
            </div>
		</div><!-- /main-container -->
	</div><!-- /wrapper -->
	
	<!-- Logout confirmation -->
	<div class="custom-popup width-100" id="logoutConfirm">
		<div class="padding-md">
			<h4 class="m-top-none"> Do you want to logout?</h4>
		</div>
		<div class="text-center">
			<a class="btn btn-success m-right-sm" href="login.html">Logout</a>
			<a class="btn btn-danger logoutConfirm_close">Cancel</a>
		</div>
	</div>

    <!--Modal all-->

    <!--modal excluir-->
    <div class="custom-popup light width-100" id="lightCustomModal">
		<div class="padding-md">
			<h4 class="m-top-none"> Deseja excluir esta organização?</h4>
		</div>

		<div class="text-center">
			<a href="#" class="btn btn-success m-right-sm lightCustomModal_close">Sim</a>
			<a href="#" class="btn btn-danger lightCustomModal_close">Nâo</a>
		</div>
	</div>
	
	<c:import url="scripts.jsp"/>

    <script>
    
        $('#lightCustomModal').popup({
				pagecontainer: '.container',
				 transition: 'all 0.3s'
			});
        $(function(){
	        $('#projeto-select').change(function(){
	  		  $('#filtro-js').submit();
	    	  });
        });
    </script>
	
  </body>
</html>