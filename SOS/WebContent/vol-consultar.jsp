<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Consultar Voluntário</title>

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
					 <li><i class="fa fa-home"></i><a href="index.jsp"> Home</a></li>
					 <li class="active">Consultar Voluntário</li>	 
				</ul>
			</div><!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->
                <div class="panel panel-default">
                        <div class="panel-heading">
                            Consultar Voluntário
                            <!-- <span class="label label-info pull-right">790 Voluntários</span> -->
                        </div>
                        <form action="VoluntarioController.do" method="get" data-validate="parsley">
                        	<input type="hidden" name="acao" value="filtro">
	                        <div class="panel-body">
	                            <div class="row">
	                                <div class="col-md-6 col-sm-6">
	                                    <label>Nome</label>
	                                    <input name="nome" type="text" class="form-control inline input-sm" data-required="true">
	                                </div><!-- /.col -->
	                                <div class="col-md-3 col-sm-3">
	                                    <label>CPF</label>
	                                    <input name="cpf" type="text" id="cpf" class="form-control inline input-sm">
	                                </div><!-- /.col -->
	                                <div class="col-md-3 col-sm-3">
	                                    <button class="btn btn-primary btn-sm" style="margin-top: 21px;">Pesquisar</button>
	                                </div><!-- /.col -->
	                            </div><!-- /.row -->
	                        </div>
                        </form>
                        
                        <c:if test="${not empty vazia }">
	                        <div class="odd text-center alert alert-default">
	                        	<p>Nenhum registro encontrado</p>
	                        </div>
                        </c:if>
                        
                        <c:if test="${not empty listaVoluntarios }">
	                        <table class="table table-striped" id="responsiveTable">
	                            <thead>
	                                <tr>
	                                    <th class="text-center">Nome do Voluntário</th>
	                                    <th class="text-center">CPF</th>
	                                    <th class="text-center">E-mail</th>
	                                    <th class="text-center">Status</th>
	                                    <th></th>
	                                </tr>
	                            </thead>
	                            <tbody class="text-center">
	                            	<c:forEach items="${listaVoluntarios }" var="voluntario">
	                                <tr>
	                                    <td>${voluntario.nome}</td>
	                                    <td>${voluntario.cpf}</td>
	                                    <td>${voluntario.email }</td>
	                                    <td>
	                                    	<c:choose>
					                        	<c:when test="${voluntario.status }">
					                        		<label class="label label-success">Ativo</label>
					                        	</c:when>
					                        	<c:otherwise>
					                        		<label class="label label-danger">Inativo</label>
					                        	</c:otherwise>
					                        </c:choose>
	                                    </td>
	                                    <!-- <td>Ativo</td> -->
	                                    <td>
	                                        <div class="btn-group">
								                <button class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown"><span class="fa fa-cogs"> </span> <span class="caret"></span></button>
	                                            <ul class="dropdown-menu slidedown dropdown-menu-right">
	                                                <li><a href="VoluntarioController.do?acao=detalhes&idVoluntario=${voluntario.id }"><i class="fa fa-eye"></i> Detalhes</a></li>
	                                                <li><a href="VoluntarioController.do?acao=editar&idVoluntario=${voluntario.id }"><i class="fa fa-edit"></i> Editar</a></li>	
	                                                <li><a href="#" onclick="excluirVoluntario(${voluntario.id})"><i class="fa fa-refresh"></i> Alterar status</a></li>
	                                                <li class="divider"></li>
	                                                <li><a href="VoluntarioController.do?acao=pvCompativel&idVoluntario=${voluntario.id }"><i class="fa fa-file-text"></i> Voluntariar-se</a></li>
	                                            </ul>
							                </div><!-- /btn-group -->
	                                    </td>
	                                </tr>
	                                </c:forEach>
	                            </tbody>
	                        </table>
                        </c:if>
                        <div class="panel-footer clearfix"></div><!-- /panel -->
                <!--Corpo da pagina-->
            </div>
		</div><!-- /main-container -->
	</div><!-- /wrapper -->
	
	<!-- Logout confirmation -->
	<!-- 
	<div class="custom-popup width-100" id="logoutConfirm">
		<div class="padding-md">
			<h4 class="m-top-none"> Do you want to logout?</h4>
		</div>
		<div class="text-center">
			<a class="btn btn-success m-right-sm" href="login.html">Logout</a>
			<a class="btn btn-danger logoutConfirm_close">Cancel</a>
		</div>
	</div>
	 -->

    <!--Modal all-->

    <!--modal excluir-->
    <div class="custom-popup light width-100" id="lightCustomModal">
		<div class="padding-md">
			<h4 class="m-top-none"> Deseja excluir este voluntário?</h4>
		</div>

		<div class="text-center">
			<a href="#" class="btn btn-success m-right-sm lightCustomModal_close">Sim</a>
			<a href="#" class="btn btn-danger lightCustomModal_close">Nâo</a>
		</div>
	</div>
	
	<div class="modal fade" id="modalExcluirVoluntario" role="dialog">
	  	<div class="modal-dialog modal-md">
	
	    <div class="modal-content">
	      <div class="modal-body">
	            <h4 class="text-center"> Deseja alterar o status deste voluntário?</h4>
	      </div>
	      <div class="modal-footer">
	      	<form action="VoluntarioController.do" method="post">
	      		<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="idVoluntario" id="idVoluntario-excluir">
	        	<button type="submit" class="btn btn-primary" id="delete">Sim</button>
	            <button type="button" data-dismiss="modal" class="btn btn-default">Não</button>
			</form>	      
	      </div>
	    </div>
	  </div>
	</div>
	
    <c:import url="scripts.jsp"/>

    <script>
    $(document).ready(function () {
		$('#cpf').mask('000.000.000-00', {reverse: true});
	});
    
    function excluirVoluntario(id){
    	$('#idVoluntario-excluir').val(id);
    	$('#modalExcluirVoluntario').modal()
    }
    
        	$('#lightCustomModal').popup({
				pagecontainer: '.container',
				 transition: 'all 0.3s'
			});
    </script>
	
  </body>
</html>