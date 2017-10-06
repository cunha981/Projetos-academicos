<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Detalhes OS</title>

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
                    <li class="active">Detalhes OS</li>
                </ul>
            </div>
            <!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->
                <div class="clearfix">
                    <div class="pull-left">
						<span class="fa-stack fa-2x fa-pull-left">
							<i class="fa fa-square fa-stack-2x"></i>
                        	<i class="fa fa-users fa-stack-1x fa-inverse" aria-hidden="true"></i>
                        </span>
                        <div class="pull-left m-left-sm">
                            <h4 class="m-bottom-xs m-top-xs">${os.nome }</h4>
                            <span class="text-muted"><abbr title="Telefone"><strong>Tel: </strong></abbr> ${os.tel } |</span>
                            <span class="text-muted"><strong>E-mail: </strong>${os.email }</span>
                        </div>
                    </div>
                    <div class="pull-right">
                        <h5 class="text-center"><strong>#${os.id}</strong></h5>
                        <strong>Desde <fmt:formatDate pattern="dd/MM/yyyy" value="${os.dataInicio }"/></strong>
                    </div>
                </div>
                <hr>
                <c:if test="${not empty msg }">
	                <div class="alert alert-warning">
	                	<p class="text-center">${msg }</p>
	                </div>
                </c:if>
                <div class="clearfix">
                    <div class="pull-left">
                        <h4>Dados da organizão social</h4>
                        <address class="text-justify" style="font-size: 14px;"> 
							<strong>Razão social: </strong>${os.nome }<br> 
							<strong>Nome fantasia: </strong>${os.nomeFantasia }<br> 
							<abbr title="Telefone"><strong>CNPJ: </strong></abbr>${os.cnpj }<br>
							<strong>Endereço: </strong>${os.endereco.logradouro }, Nº ${os.endereco.numero }, ${os.endereco.cidade } - ${os.endereco.estado }<br> 
							<strong>CEP: </strong>${os.endereco.cep }<br>
						</address>
                    </div>
                    <div class="pull-right text-right">
						<h5>STATUS</h5>
						<c:choose>
                        	<c:when test="${os.status }">
                        		<label class="label label-success label-scnc">Ativo</label>
                        	</c:when>
                        	<c:otherwise>
                        		<label class="label label-danger label-scnc">Inativo</label>
                        	</c:otherwise>
                        </c:choose> 
					</div>
                </div>
                <hr>
				<div class="panel-heading">
                    Projetos cadastrados:
                    <c:if test="${os.qtdProjetos > 0}"> 
	                    <span class="label label-info pull-right">
		                    <c:if test="${os.qtdProjetos == 1}"> ${os.qtdProjetos} Projeto</c:if>
							<c:if test="${os.qtdProjetos > 1}"> ${os.qtdProjetos} Projetos</c:if>
	                    </span>
                    </c:if>
                </div>
                <table class="table table-striped" id="responsiveTable">
                    <thead>
                        <tr class="bg-theme">
                            <th class="text-center">Projeto</th>
                            <th class="text-center">Descrição</th>
                            <th class="text-center">Área de atuação</th>
                            <th class="text-center">Data de início</th>
                            <th class="text-center">Período de inscrição</th>
                            <th class="text-center">Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${os.projetos }" var="p">
                    		<tr class="text-center">
                    			<td>${p.nome }</td>
                    			<td>${p.descricao }</td>
                    			<td>${p.areaAtuacao.descricao }</td>
                    			<td class="hidden-xs"><fmt:formatDate pattern="dd/MM/yyyy" value="${p.dataInicio}"/></td>
                    			<td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.dataCadastro }"/> até <fmt:formatDate pattern="dd/MM/yyyy" value="${p.periodoInscricao }"/></td>
                    			<td>${p.situacao }</td>
                    			<td>
                                    <div class="btn-group">
			                			<button class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown"><span class="fa fa-cogs"> </span> <span class="caret"></span></button>
                                        <ul class="dropdown-menu slidedown dropdown-menu-right">
                                            <li><a href="#" onclick="detalheProjeto(${p.id})"><i class="fa fa-eye"></i> Detalhes</a></li>
                                            <c:if test="${os.status }">
	                                            <li><a href="proj.do?acao=editar&idProjeto=${p.id }"><i class="fa fa-edit"></i> Editar</a></li>	
	                                            <c:if test="${p.situacao == 'BuscaDeVoluntario' }">
	                                            	<li><a href="#" onclick="excluirProjeto(${p.id})"><i class="fa fa-trash-o"></i> Excluir</a></li>
                                            	</c:if>
                                            </c:if>
                                        </ul>
		                			</div><!-- /btn-group -->
                                </td>          
                    		</tr>
                    	</c:forEach>
                    </tbody>
                </table>
                <br>
                <div class="pull-right">
					<c:if test="${os.status }">	
                    	<a href="proj.do?acao=cadastrar&idOS=${os.id }" class="btn btn-default"><i class="fa fa-file-text"></i> Cadastrar projeto</a>
                    </c:if>
                    <a href="os.do?acao=editar&idOS=${os.id }" class="btn btn-primary hidden-print"><i class="fa fa-pencil"></i> Editar dados da OS</a>
                    <c:choose>
	                	<c:when test="${os.status }">
	                    	<a href="#" data-toggle="modal" data-target="#modalExcluirOS" class="btn btn-danger"><i class="fa fa-toggle-on"></i> Desativar OS</a>
	                    </c:when>
	                    <c:otherwise>
	                    	<a href="#" data-toggle="modal" data-target="#modalExcluirOS" class="btn btn-success"><i class="fa fa-toggle-off"></i> Ativar OS</a>
	                    </c:otherwise>
	               	</c:choose>
                </div>
                
                <!--Corpo da pagina-->
            </div>
        </div>
        <!-- /main-container -->
    </div>
    <!-- /wrapper -->

    <!--Modal all-->
	
    <!--modal excluir-->
    <div class="modal fade" id="modalExcluirOS" role="dialog">
	  	<div class="modal-dialog modal-md">
	    <div class="modal-content">
	      <div class="modal-body">
	            <h4 class="text-center"> Deseja alterar o status desta organização?</h4>
	      </div>
	      <div class="modal-footer">
	      	<form action="os.do" method="post">
	      		<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="idOS" value="${os.id }">
	        	<button type="submit" class="btn btn-primary" id="delete">Sim</button>
	            <button type="button" data-dismiss="modal" class="btn btn-default">Não</button>
			</form>	      
	      </div>
	    </div>
	  </div>
	</div>

	<!--modal excluir projeto-->
	  <div class="modal fade" id="modalExcluirProjeto" role="dialog">
	  	<div class="modal-dialog modal-md">
	
	    <div class="modal-content">
	      <div class="modal-body">
	            <h4 class="text-center"> Deseja excluir este projeto?</h4>
	      </div>
	      <div class="modal-footer">
	      	<form action="proj.do" method="post">
	      		<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="idProjeto" id="idProjeto-excluir">
	      		<input type="hidden" name="idOS" value="${os.id }">
	        	<button type="submit" class="btn btn-danger" id="delete">Sim</button>
	            <button type="button" data-dismiss="modal" class="btn btn-default">Não</button>
			</form>	      
	      </div>
	    </div>
	  </div>
	</div>
    <c:import url="scripts.jsp"/>
    <script>
    
    function excluirProjeto(id){
    	$('#idProjeto-excluir').val(id);
    	$('#modalExcluirProjeto').modal();
    }
    
    </script>

</body>
</html>