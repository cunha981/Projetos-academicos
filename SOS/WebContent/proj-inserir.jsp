<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Cadastrar Projeto</title>

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
                    <li class="active">Cadastrar Projeto</li>
                </ul>
            </div>
            <!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->
				
				<c:forEach items="${projeto.disponibilidades }" var="disp">
					<input class="disp" value="${disp.id }" type="hidden">
				</c:forEach>
				<input id="at" value="${projeto.areaAtuacao.id }" type="hidden">
				
                <div class="panel panel-default">
                    <form class="form-horizontal form-border" method="POST" action="proj.do?acao=salvar" id="form-projeto" data-validate="parsley">
                    	<input type="hidden" name="idOS" value="${idOS }${projeto.os}">
						<input type="hidden" name="idProjeto" value="${projeto.id }" >
                        <div class="panel-heading">
                            Cadastrar projeto
                        </div>
                        <div class="panel-body">
                        
                            <div class="form-group">
                                <label class="control-label col-md-2">Nome</label>
                                <div class="col-md-7">
                                    <input name="nomeProjeto" type="text" class="form-control input-sm" placeholder="Nome do projeto" value="${projeto.nome }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Descrição</label>
                                <div class="col-md-7">
                                    <textarea name="descricao" class="form-control input-sm" placeholder="Descrição do projeto" data-required="true">${projeto.descricao }</textarea>
                                </div>
                                <!-- /.col -->
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">Data limite de inscrição</label>
                                <div class="col-md-7">
                                	<fmt:formatDate value="${projeto.periodoInscricao }" pattern="yyyy-MM-dd" var="periodoInscricao"/>
                                    <input name="periodoInscricao" type="date" class="form-control input-sm" placeholder="" value="${periodoInscricao }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">Data de início do projeto</label>
                                <div class="col-md-7">
                                <fmt:formatDate value="${projeto.dataInicio }" pattern="yyyy-MM-dd" var="dataInicio"/>
                                    <input name="dataInicio" type="date" class="form-control input-sm" placeholder="" value="${dataInicio }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">Quantidade de voluntários</label>
                                <div class="col-md-7">
                                    <input name="qtdCandidatos" type="number" min="1" class="form-control input-sm" placeholder="" value="${projeto.qtdVoluntarios }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group row">
                            	<label class="control-label col-md-2">Dias de projeto</label>
                            	<div class="col-md-7">
                            		<table class="table">
                            			<thead>
                            				<tr>
                            					<th class="text-center"></th>
                            					<th class="text-center">Segunda-Feira</th>
                            					<th class="text-center">Terça-Feira</th>
                            					<th class="text-center">Quarta-Feira</th>
                            					<th class="text-center">Quinta-Feira</th>
                            					<th class="text-center">Sexta-Feira</th>
                            					<th class="text-center">Sábado</th>
                            					<th class="text-center">Domingo</th>
                            				</tr>
                            			</thead>
                            			<tbody>
                            				<tr class="text-center">
                            					<td>Manhã</td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="1"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia4" value="4"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia7" value="7"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia10" value="10"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia13" value="13"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia16" value="16"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia19" value="19"><span class="custom-checkbox"></span></td>
                            				</tr>
                            				<tr class="text-center">
                            					<td>Tarde</td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia2" value="2"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia5" value="5"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia8" value="8"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia11" value="11"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia14" value="14"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia17" value="17"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia20" value="20"><span class="custom-checkbox"></span></td>
                            				</tr>
                            				<tr class="text-center">
                            					<td>Noite</td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia3" value="3"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia6" value="6"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia9" value="9"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia12" value="12"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia15" value="15"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia18" value="18"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia21" value="21"><span class="custom-checkbox"></span></td>
                            				</tr>
                            			</tbody>
                            		</table>
                            	</div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">Áreas de atuação</label>
                                <div class="col-md-7 area-check">
                                	<input name="idAreaAtuacao" type="radio" id="area1" value="1" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area1">Saúde</label><br>
                                    <input name="idAreaAtuacao" type="radio" id="area2" value="2" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area2">Cultura</label><br>
                                    <input name="idAreaAtuacao" type="radio" id="area3" value="3" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area3">Esporte</label><br>
                                    <input name="idAreaAtuacao" type="radio" id="area4" value="4" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area4">Atendimento ou promoção dos direitos das pessoas com deficiência</label><br>
                                    <input name="idAreaAtuacao" type="radio" id="area5" value="5" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area5">Atendimento ou promoção dos direitos de crianças e adolescentes</label><br>
                                    <input name="idAreaAtuacao" type="radio" id="area6" value="6" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area6">Proteção e conservação do meio ambiente</label><br>
                                    <input name="idAreaAtuacao" type="radio" id="area7" value="7" class="fieldValid"><span class="custom-radio"></span><label class="label-radio-inline" for="area7">Promoção de investimentos, de competitividade e de desenvolvimento</label><br>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <c:choose>
	                            <c:when test="${not empty projeto.id }">
	                            	<input type="hidden" id="situacao-js" value="${projeto.situacao }">
	                            	<div class="form-group">
		                                <label class="control-label col-md-2">Situação</label>
		                                <div class="col-md-7">
											<input type="radio" name="situacao" id="BuscaDeVoluntario" value="BuscaDeVoluntario">
											<span class="custom-radio"></span>
											<label class="label-radio inline" for="ativo">Busca de voluntários</label>
											
		                                    <input type="radio" name="situacao" id="Ativo" value="Ativo">
		                                    <span class="custom-radio"></span>
		                                    <label class="label-radio inline" for="suspenso">Ativo</label>
											
											<input type="radio" name="situacao" id="Suspenso" value="Suspenso">
		                                    <span class="custom-radio"></span>
		                                    <label class="label-radio inline" for="extinto">Suspenso</label>	
															
		                                    <input type="radio" name="situacao" id="Extinto" value="Extinto">
		                                    <span class="custom-radio"></span>
		                                    <label class="label-radio inline" for="extinto">Extinto</label>														
		                                </div>
		                                <!-- /.col -->
		                            </div>
		                            <!-- /form-group -->
	                            </c:when>
	                            <c:otherwise>
	                            	<div class="hide">
		                            	<input type="radio" name="situacao" id="buscaDeVoluntario" value="BuscaDeVoluntario" checked="checked">
										<span class="custom-radio"></span>
										<label class="label-radio inline" for="ativo">Busca de voluntários</label>
									</div>
	                            </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="panel-footer">
                            <div class="text-right">
                                <button class="btn btn-sm btn-primary" type="submit" id="checkBtn">Salvar</button>
                                <button class="btn btn-sm btn-danger" type="reset">Cancelar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /panel -->
                <!--Corpo da pagina-->
            </div>
        </div>
        <!-- /main-container -->
    </div>
    <!-- /wrapper -->

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
            <h4 class="m-top-none"> Deseja excluir este projeto?</h4>
        </div>

        <div class="text-center">
            <a href="#" class="btn btn-success m-right-sm lightCustomModal_close">Sim</a>
            <a href="#" class="btn btn-danger lightCustomModal_close">Nâo</a>
        </div>
    </div>

    <c:import url="scripts.jsp"/>

    <script>
	 	
    
    	$('.disp').each(function(i,item){
    		$('#dia'+$(item).val()).prop('checked', true);
    	})
    	$('#area'+$('#at').val()).attr('checked', true);
    
	    $(document).ready(function () {
	        $('#checkBtn').click(function() {
	          checkedDisp = $("input[type=checkbox]:checked").length;
	          checkedArea = $("input[type=radio].fieldValid:checked").length;
	          console.log("Área de Atuação: " + checkedArea);
			
	          if(!checkedDisp || !checkedArea){
		          if(!checkedDisp) {
		            //alert("Você deve selecionar ao menos um dia e um período!");
		            $('.table').addClass('alert alert-danger');
		          }
		          if(!checkedArea){
		        	//alert("Você deve selecionar ao menos uma área de atuação!");
		        	$('.area-check').addClass('alert alert-danger');
		          }
		          
		          return false;  
	          }
	        });
	        var situacao = $('#situacao-js').val();
		    $("#"+situacao).prop('checked', true);
	    });
	    
        $('#lightCustomModal').popup({
            pagecontainer: '.container',
            transition: 'all 0.3s'
        });
    </script>

</body>

</html>