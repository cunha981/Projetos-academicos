<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Consultar OS</title>

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
					 <li class="active">Consultar OS</li>	 
				</ul>
			</div><!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->
                <!--  <a href="" id="theme-setting-icon" class="tooltip-test" data-toggle="tooltip" data-placement="left" title="Incluir nova organização social"><i class="fa fa-plus fa-lg"></i></a> -->
                <div class="panel panel-default">
                        <div class="panel-heading">
                            Organizações sociais
                        </div>
                        <form action="os.do" method="get" data-validate="parsley">
                        	<input type="hidden" name="acao" value="filtro">
	                        <div class="panel-body">
	                            <div class="row">
	                                <div class="col-md-6 col-sm-6">
	                                    <label>Nome da instituição</label>
	                                    <input name="nome" type="text" class="form-control inline input-sm" data-required="true">
	                                </div><!-- /.col -->
	                                <div class="col-md-3 col-sm-3">
	                                    <label>CNPJ</label>
	                                    <input name="cnpj" type="text" id="cnpj" class="form-control inline input-sm">
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
                        <c:if test="${not empty listaOS }">
	                        <table class="table table-striped" id="responsiveTable">
	                            <thead>
	                                <tr>
	                                    <th class="text-center">Nome da instituição</th>
	                                    <th class="text-center">CNPJ</th>
	                                    <th class="text-center">Endereço</th>
	                                    <th class="text-center">Projetos</th>
	                                    <th class="text-center">Status</th>
	                                    <th></th>
	                                </tr>
	                            </thead>
	                            <tbody class="text-center">
	                            <c:forEach items="${listaOS }" var="os">
	                                <tr>
	                                    <td>${os.nome}</td>
	                                    <td>${os.cnpj}</td>
	                                    <td>${os.endereco.logradouro}, Nº ${os.endereco.numero} - ${os.endereco.cidade }, ${os.endereco.estado }</td>
	                                    <td>${os.qtdProjetos }</td>
	                                    <td>
	                                    	<c:choose>
					                        	<c:when test="${os.status }">
					                        		<label class="label label-success">Ativo</label>
					                        	</c:when>
					                        	<c:otherwise>
					                        		<label class="label label-danger">Inativo</label>
					                        	</c:otherwise>
					                        </c:choose>
	                                    </td>
	                                    <td>
	                                        <div class="btn-group">
								                <button class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown"><span class="fa fa-cogs"> </span> <span class="caret"></span></button>
	                                            <ul class="dropdown-menu slidedown dropdown-menu-right">
	                                                <li><a href="os.do?acao=detalhes&idOS=${os.id }"><i class="fa fa-eye"></i> Detalhes</a></li>
	                                                <li><a href="os.do?acao=editar&idOS=${os.id }"><i class="fa fa-edit"></i> Editar</a></li>	
	                                                <li><a href="#" onclick="excluirOS(${os.id})"><i class="fa fa-refresh fa-spin"></i> Alterar status</a></li>
	                                                <c:if test="${os.status }">
	                                                	<li class="divider"></li>
	                                                	<li><a href="proj.do?acao=cadastrar&idOS=${os.id }"><i class="fa fa-file-text"></i> Cadastrar projeto</a></li>
	                                                </c:if>
	                                            </ul>
							                </div><!-- /btn-group -->
	                                    </td>
	                                </tr>
	                            </c:forEach>
	                            
	                            </tbody>
	                        </table>
                        </c:if>
                        <div class="panel-footer clearfix">
                        </div>
                    </div><!-- /panel -->
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

    <!--modal excluir projeto-->
	  <div class="modal fade" id="modalExcluirOS" role="dialog">
	  	<div class="modal-dialog modal-md">
	
	    <div class="modal-content">
	      <div class="modal-body">
	            <h4 class="text-center"> Deseja alterar o status desta organização?</h4>
	      </div>
	      <div class="modal-footer">
	      	<form action="os.do" method="post">
	      		<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="idOS" id="idOS-excluir">
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
	    	$('#cnpj').mask('00.000.000/0000-00', {reverse: true});
	    });
	    
	    function excluirOS(id){
	    	$('#idOS-excluir').val(id);
	    	$('#modalExcluirOS').modal()
	    }
	    $(document).ready(function(){
	    	$('.parsley-validate').parsley();
    	});
    </script>
	
  </body>
</html>