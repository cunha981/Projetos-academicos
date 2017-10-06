<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS |  Detalhes Voluntário</title>    
    <meta name="description" content="">
    <meta name="author" content="">

    <c:import url="header.jsp"/>
	
  </head>

  <body class="overflow-hidden">
	<!-- Overlay Div -->
	<div id="overlay" class="transparent"></div>
	
	<!--  <div id="wrapper" class="preload"> -->
	<div id="wrapper" class="sidebar-mini">
		<c:import url="navbar.jsp"/>
		
		<c:import url="sidebar.jsp"/>

		<div id="main-container">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					 <li><i class="fa fa-home"></i><a href="index.jsp"> Home</a></li>
					 <!-- <li>Consultar Voluntário</li>	-->
					 <li class="active">Perfil Voluntário</li>	 
				</ul>
			</div><!--breadcrumb-->
			
			<div class="padding-md">
				<div class="row">
					<div class="col-md-3 col-sm-3">
						<div class="row">
							<div class="col-xs-6 col-sm-12 col-md-6 text-center">
								<a href="#">
									<img src="resources/img/user.jpg" alt="User Avatar" class="img-thumbnail">
								</a>
								<div class="seperator"></div>
								<div class="seperator"></div>
							</div><!-- /.col -->
							<div class="col-xs-6 col-sm-12 col-md-6">
								<strong class="font-14">${voluntario.nome}</strong>
								<small class="block text-muted">
									${voluntario.email}
								</small> 
								<div class="seperator"></div>
								<c:choose>
									<c:when test="${voluntario.status }">
										<label class="label label-success label-scnc"> Ativo</label>
									</c:when>
									<c:otherwise>
										<label class="label label-danger label-scnc"> Inativo</label>
									</c:otherwise>
								</c:choose>
								<!-- <a class="btn btn-success btn-xs m-bottom-sm">Follow</a> -->
								<div class="seperator"></div>
								<!-- <a href="#" class="social-connect tooltip-test facebook-hover pull-left m-right-xs" data-toggle="tooltip" data-original-title="Facebook"><i class="fa fa-facebook"></i></a>
								<a href="#" class="social-connect tooltip-test twitter-hover pull-left m-right-xs" data-toggle="tooltip" data-original-title="Twitter"><i class="fa fa-twitter"></i></a>
								<a href="#" class="social-connect tooltip-test google-plus-hover pull-left" data-toggle="tooltip" data-original-title="Google Plus"><i class="fa fa-google-plus"></i></a>  -->
								<div class="seperator"></div>
								<div class="seperator"></div>
							</div><!-- /.col -->
						</div><!-- /.row -->
						
						<div class="panel m-top-md">
							<div class="panel-body">
								<div class="row">
									<div class="col-xs-12 text-center">
										<c:if test="${voluntario.qtdProjetos == 0}">
											<span class="block font-14">0</span>
											<small class="text-muted">Projetos</small>
										</c:if>
										<c:if test="${voluntario.qtdProjetos == 1}">
											<span class="block font-14">${voluntario.qtdProjetos}</span>
											<small class="text-muted">Projeto</small>
										</c:if>
										<c:if test="${voluntario.qtdProjetos > 1}"> 
											<span class="block font-14">${voluntario.qtdProjetos}</span>
											<small class="text-muted">Projetos</small>
										</c:if>										
									</div><!-- /.col -->
									<!-- 
									<div class="col-xs-6 text-center">
										<span class="block font-14">5</span>
										<small class="text-muted">OS</small>
									</div> --><!-- /.col -->
								</div><!-- /.row -->
							</div>
						</div><!-- /panel -->
					</div><!-- /.col -->
					
					<div class="col-md-9 col-sm-9">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="overview">
								<!-- <div class="row">
									<div class="col-md-6">
									</div>
									<div class="col-md-6">
									</div>
								</div>--><!-- /.row -->
								<c:if test="${not empty msg }">
					                <div class="alert alert-warning">
					                	<p class="text-center">${msg }</p>
					                </div>
				                </c:if>
								<div class="panel panel-default">
									<form class="form-horizontal form-border">
										<div class="panel-heading">
											Dados Pessoais
										</div>
										<div class="panel-body">
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Nome</label>
				                                <div class="col-md-9">
				                                    <p class="voluntario-detalhe">${voluntario.nome}</p>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Endereço</label>
				                                <div class="col-md-9">
				                                    <p class="voluntario-detalhe">${voluntario.endereco.cep}</p><br>
				                                    <p class="voluntario-detalhe">${voluntario.endereco.logradouro}, ${voluntario.endereco.numero}</p><br>
				                                    <p class="voluntario-detalhe">${voluntario.endereco.cidade} - ${voluntario.endereco.estado}</p>
				                                </div>
				                                <!-- /.col -->                
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Telefone</label>
				                                <div class="col-md-9">
				                                    <p class="voluntario-detalhe">${voluntario.tel}</p>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">E-mail</label>
				                                <div class="col-md-9">
				                                    <p class="voluntario-detalhe">${voluntario.email}</p>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Data de nascimento</label>
				                                <div class="col-md-9">
				                                	<fmt:formatDate value="${voluntario.dataNascimento}" pattern="dd/MM/yyyy" var="dataNascimento"/>
				                                    <p class="voluntario-detalhe">${dataNascimento}</p>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Gênero</label>
				                                <div class="col-md-9">
				                                	<p class="voluntario-detalhe">				                                	
				                                		<c:if test="${voluntario.genero.toString() == 'm'}">Masculino</c:if>
				                                		<c:if test="${voluntario.genero.toString() == 'f'}">Feminino</c:if>
				                                	</p>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Estado Civil</label>
				                                <div class="col-md-9">
					                               <p class="voluntario-detalhe">${voluntario.estadoCivil}</p>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group row">
				                            	<label class="control-label col-md-2" title="Marque os dias e horários nos quais você está disponível para os projetos">Períodos disponíveis</label>
				                            	<div class="col-md-9">
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
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="1" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia4" value="4" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia7" value="7" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia10" value="10" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia13" value="13" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia16" value="16" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia19" value="19" disabled><span class="custom-checkbox"></span></td>
				                            				</tr>
				                            				<tr class="text-center">
				                            					<td>Tarde</td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia2" value="2" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia5" value="5" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia8" value="8" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia11" value="11" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia14" value="14" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia17" value="17" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia20" value="20" disabled><span class="custom-checkbox"></span></td>
				                            				</tr>
				                            				<tr class="text-center">
				                            					<td>Noite</td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia3" value="3" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia6" value="6" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia9" value="9" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia12" value="12" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia15" value="15" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia18" value="18" disabled><span class="custom-checkbox"></span></td>
				                            					<td><input name="idDisponibilidade" type="checkbox" id="dia21" value="21" disabled><span class="custom-checkbox"></span></td>
				                            				</tr>
				                            			</tbody>
				                            		</table>
				                            	</div>
				                            </div>
				                            <!-- /form-group -->
				                            <div class="form-group">
				                                <label class="control-label col-md-2">Áreas de atuação</label>
				                                <div class="col-md-9">
				                                	<input name="idAreaAtuacao" type="checkbox" id="area1" value="1" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area1">Saúde</label><br>
				                                    <input name="idAreaAtuacao" type="checkbox" id="area2" value="2" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area2">Cultura</label><br>
				                                    <input name="idAreaAtuacao" type="checkbox" id="area3" value="3" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area3">Esporte</label><br>
				                                    <input name="idAreaAtuacao" type="checkbox" id="area4" value="4" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area4">Atendimento ou promoção dos direitos das pessoas com deficiência</label><br>
				                                    <input name="idAreaAtuacao" type="checkbox" id="area5" value="5" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area5">Atendimento ou promoção dos direitos de crianças e adolescentes</label><br>
				                                    <input name="idAreaAtuacao" type="checkbox" id="area6" value="6" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area6">Proteção e conservação do meio ambiente</label><br>
				                                    <input name="idAreaAtuacao" type="checkbox" id="area7" value="7" disabled><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area7">Promoção de investimentos, de competitividade e de desenvolvimento</label><br>
				                                </div>
				                                <!-- /.col -->
				                            </div>
				                            <!-- /.form-group -->
				                        </div><!-- /.panel-body -->
										<div class="panel-footer">
											<div class="text-right">
												<a href="VoluntarioController.do?acao=editar&idVoluntario=${voluntario.id }" class="btn btn-primary hidden-print"><i class="fa fa-pencil"></i> Editar dados do Voluntário</a>
												<c:choose>
								                	<c:when test="${voluntario.status }">
								                    	<a href="#" data-toggle="modal" data-target="#modalExcluirVoluntario" class="btn btn-danger"><i class="fa fa-toggle-on"></i> Desativar Voluntário</a>
								                    </c:when>
								                    <c:otherwise>
								                    	<a href="#" data-toggle="modal" data-target="#modalExcluirVoluntario" class="btn btn-success"><i class="fa fa-toggle-off"></i> Ativar Voluntário</a>
								                    </c:otherwise>
								               	</c:choose>
											</div>
										</div>
									</form>
								</div><!-- /panel -->
								
								<div class="panel panel-default table-responsive">
									<div class="panel-heading">
										Projetos										
										<c:if test="${voluntario.qtdProjetos > 0}">
											<span class="label label-danger pull-right">
												<c:if test="${voluntario.qtdProjetos == 1}"> ${voluntario.qtdProjetos} Projeto</c:if>
												<c:if test="${voluntario.qtdProjetos > 1}"> ${voluntario.qtdProjetos} Projetos</c:if>
											</span>
										</c:if>
									</div>
									<table class="table table-bordered table-condensed table-hover table-striped table-vertical-center">
										<thead>
											<tr>
												<th></th>
												<th class="text-center">Nome</th>
												<th class="text-center">Área de Atuação</th>
												<th class="text-center">Data de Início</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
										<c:if test="${ voluntario.qtdProjetos > 0 }">
											<c:forEach items="${voluntario.projetos }" var="p">
												<tr>
													<td class="text-center hidden-xs">
														<img data-src="holder.js/60x60" alt="Product Image" src=".">
													</td>
													<td class="text-center">
														${p.nome }
													</td>
													<td class="text-center">
														${p.descricao }
													</td>
													<td class="text-center">
														<fmt:formatDate pattern="dd/MM/yyyy" value="${p.periodoInscricao }"/>
													</td>
													<td class="text-center">
													<a href="#" onclick="detalheProjeto(${p.id})" class="btn btn-sm btn-success"><i class="fa fa-eye"></i> Detalhes</a>
														<!--  <a href="VoluntarioController.do?acao=detalhes&idVoluntario=${voluntario.id }" class="btn btn-sm btn-success">Ver</a> -->
													</td>
												</tr>
											</c:forEach>
										</c:if>
										</tbody>
										<c:if test="${ voluntario.qtdProjetos == 0 || voluntario.qtdProjetos == null }">
				                           	<tr>
				                           		<td></td>
				                           		<td>Nenhum registro encontrado</td>
				                           		<td></td>
				                           		<td></td>
				                           		<td></td>
				                           	</tr>
			                            </c:if>
									</table>
								</div><!-- /panel -->
							</div><!-- /tab1 -->
							
							<c:forEach items="${voluntario.disponibilidades }" var="disp">
								<input class="disp" value="${disp.id }" type="hidden">
							</c:forEach>
							<c:forEach items="${voluntario.areasAtuacao }" var="at">
								<input class="at" value="${at.id }" type="hidden">
							</c:forEach>
							
						</div><!-- /tab-content -->
					</div><!-- /.col -->
				</div><!-- /.row -->			
			</div><!-- /.padding-md -->
		</div><!-- /main-container -->
	</div><!-- /wrapper -->

	<!-- 
	<a href="" id="scroll-to-top" class="hidden-print"><i class="fa fa-chevron-up"></i></a>
	-->
	
	<!-- Logout confirmation -->
	<!-- 
	<div class="custom-popup width-100" id="logoutConfirm">
		<div class="padding-md">
			<h4 class="m-top-none"> Do you want to logout?</h4>
		</div>

		<div class="text-center">
			<a class="btn btn-success m-right-sm" href="login.html">Logout</a>
			<a class="btn btn-danger logoutConfirm_close">Cancelar</a>
		</div>
	</div>
	 -->
	 
	<!--modal excluir-->
  	<div class="modal fade" id="modalExcluirVoluntario" role="dialog">
	  	<div class="modal-dialog modal-md">
	    <div class="modal-content">
	      <div class="modal-body">
	            <h4 class="text-center"> Deseja alterar o status deste voluntário?</h4>
	      </div>
	      <div class="modal-footer">
	      	<form action="VoluntarioController.do" method="post">
	      		<input type="hidden" name="acao" value="excluir">
	      		<input type="hidden" name="idVoluntario" value="${voluntario.id }">
	        	<button type="submit" class="btn btn-primary" id="delete">Sim</button>
	            <button type="button" data-dismiss="modal" class="btn btn-default">Não</button>
			</form>	      
	      </div>
	    </div>
	  </div>
	</div>
	
    <c:import url="scripts.jsp"/>
    
    <script>    
    	$('.disp').each(function(i,item){
			$('#dia'+$(item).val()).prop('checked', true);
		})
		$('.at').each(function(i,item){
			$('#area'+$(item).val()).prop('checked', true);
		})
    
	    $('#lightCustomModal').popup({
			pagecontainer: '.container',
			transition: 'all 0.3s'
		});
    </script>
	
  </body>
</html>
