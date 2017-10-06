<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Cadastrar Voluntário</title>

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
                    <li class="active">Cadastrar Voluntário</li>
                </ul>
            </div>
            <!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->

                <div class="panel panel-default">
                    <form class="form-horizontal form-border" method="POST" action="VoluntarioController.do?acao=salvar">
                        <div class="panel-heading">
                            Cadastrar Voluntário
                        </div>
                        <div class="panel-body">
                        	<input type="hidden" name="idVoluntario" value="${voluntairo.id }">
                            <div class="form-group">
                                <label class="control-label col-md-2">Nome</label>
                                <div class="col-md-7">
                                    <input name="nomeVoluntario" type="text" id="nome" class="form-control input-sm" placeholder="Nome" value="" required>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">CPF</label>
                                <div class="col-md-7">
                                    <input name="cpf" type="text" id="cpf" class="form-control input-sm" placeholder="CPF" value="" required>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <!-- 
                            <div class="form-group">
                                <label class="control-label col-md-2">Situação</label>
                                <div class="col-md-10">
                                    <label class="label-radio inline">
										<input type="radio" name="inline-radio" checked>
										<span class="custom-radio"></span>
										Ativo
									</label>
                                    <label class="label-radio inline">
										<input type="radio" name="inline-radio">
										<span class="custom-radio"></span>
										Inativo
									</label>
                                </div> -->
                                <!-- /.col -->
                            <!-- </div> -->
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Endereço</label>
                                <div class="col-md-7">
                                    <input id="cep" name="cep" type="text" class="form-control input-sm" placeholder="CEP" value="" required><br>
                                    <input id="logradouro" name="logradouro" type="text" class="form-control input-sm" placeholder="Logradouro" value="" required><br>
                                    <input id="numero" name="numero" type="number" min="0" class="form-control input-sm" placeholder="Número" value="" required><br>
                                    <input id="uf" name="uf" type="text" class="form-control input-sm" placeholder="Estado" value="" required><br>
                                    <input id="cidade"  name="cidade" type="text" class="form-control input-sm" placeholder="Cidade" value="" required>
                                </div>
                                <!-- /.col -->                
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Telefone</label>
                                <div class="col-md-7">
                                    <input name="tel" type="tel" id="telefone" class="form-control input-sm" placeholder="Telefone" value="" required>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">E-mail</label>
                                <div class="col-md-7">
                                    <input name="email" type="email" id="email" class="form-control input-sm" placeholder="E-mail" value="" required>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Data de nascimento</label>
                                <div class="col-md-7">
                                    <input name="dataNasc" type="date" id="dataNasc" class="form-control input-sm" required>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Gênero</label>
                                <div class="col-md-7">
                                	<input name="genero" type="radio" id="masculino" value="m">
                                    <span class="custom-radio"></span><label class="label-radio-inline" for="masculino"> Masculino </label>
                                    <input name="genero" type="radio" id="feminino" value="f">
                                    <span class="custom-radio"></span><label class="label-radio-inline" for="feminino"> Feminino</label>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Estado Civil</label>
                                <div class="col-md-7">
	                                <select class="form-control" name="estCivil" required>
										<option value="Solteiro">Solteiro</option>
									  	<option value="Casado">Casado</option>
									  	<option value="Divorciado">Divorciado</option>
									  	<option value="Viúvo">Viúvo</option>
									  	<option value="Separado">Separado</option>
									</select>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group row">
                            	<label class="control-label col-md-2" title="Marque os dias e horários nos quais você está disponível para os projetos">Períodos disponíveis</label>
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
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="4"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="7"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="10"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="13"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="16"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="19"><span class="custom-checkbox"></span></td>
                            				</tr>
                            				<tr class="text-center">
                            					<td>Tarde</td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="2"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="5"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="8"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="11"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="14"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="17"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="20"><span class="custom-checkbox"></span></td>
                            				</tr>
                            				<tr class="text-center">
                            					<td>Noite</td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="3"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="6"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="9"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="12"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="15"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="18"><span class="custom-checkbox"></span></td>
                            					<td><input name="idDisponibilidade" type="checkbox" id="dia1" value="21"><span class="custom-checkbox"></span></td>
                            				</tr>
                            			</tbody>
                            		</table>
                            	</div>
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Áreas de atuação</label>
                                <div class="col-md-7">
                                	<input name="idAreaAtuacao" type="checkbox" id="area1" value="1"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area1">Saúde</label><br>
                                    <input name="idAreaAtuacao" type="checkbox" id="area2" value="2"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area2">Cultura</label><br>
                                    <input name="idAreaAtuacao" type="checkbox" id="area3" value="3"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area3">Esporte</label><br>
                                    <input name="idAreaAtuacao" type="checkbox" id="area4" value="4"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area4">Atendimento ou promoção dos direitos das pessoas com deficiência</label><br>
                                    <input name="idAreaAtuacao" type="checkbox" id="area5" value="5"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area5">Atendimento ou promoção dos direitos de crianças e adolescentes</label><br>
                                    <input name="idAreaAtuacao" type="checkbox" id="area6" value="6"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area6">Proteção e conservação do meio ambiente</label><br>
                                    <input name="idAreaAtuacao" type="checkbox" id="area7" value="7"><span class="custom-checkbox"></span><label class="label-checkbox-inline" for="area7">Promoção de investimentos, de competitividade e de desenvolvimento</label><br>
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.form-group -->
                        </div><!-- /.panel-body -->
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
            <h4 class="m-top-none"> Deseja excluir este voluntário?</h4>
        </div>

        <div class="text-center">
            <a href="#" class="btn btn-success m-right-sm lightCustomModal_close">Sim</a>
            <a href="#" class="btn btn-danger lightCustomModal_close">Nâo</a>
        </div>
    </div>

    <c:import url="navbar.jsp"/>
		
	<c:import url="scripts.jsp"/>

    <script>
    	$(document).ready(function () {
    		$('#cpf').mask('000.000.000-00', {reverse: true});
    		$('#cep').mask('00000-000');
    		$('#uf').mask('SS');
    		$("#telefone").mask("(00) 0000-0000");
    	});
    	
	    $(document).ready(function () {
	        $('#checkBtn').click(function() {
	          checkedDisp = $("input[name='idDisponibilidade'][type='checkbox']:checked").length;
	          checkedArea = $("input[name='idAreaAtuacao'][type='checkbox']:checked").length;
	          checkedGenero = $("input[name='genero'][type='radio']:checked").length;
	
	          if(!checkedGenero) {
			      alert("Você deve selecionar seu gênero!");
			      return false;
		      }
	          if(!checkedDisp) {
	            alert("Você deve selecionar ao menos um dia e um período!");
	            return false;
	          }
	          if(!checkedArea) {
	       	  	alert("Você deve selecionar ao menos uma área de atuação!");
	        	return false;
	          }
	
	        });
	    });
    
        $('#lightCustomModal').popup({
            pagecontainer: '.container',
            transition: 'all 0.3s'
        });
        
        $(document).ready( function() {
     	   /* Executa a requisição quando o campo CEP perder o foco */
     	   $('#cep').blur(function(){
     	           /* Configura a requisição AJAX */
     	           $.ajax({
     	                url : 'http://cep.republicavirtual.com.br/web_cep.php?formato=json', /* URL que será chamada */ 
     	                type : 'POST', /* Tipo da requisição */ 
     	                data: 'cep=' + $('#cep').val(), /* dado que será enviado via POST */
     	                dataType: 'json', /* Tipo de transmissão */
     	                success: function(data){
     	                    if(data.resultado == 1){
     	                        $('#logradouro').val(data.tipo_logradouro+" "+data.logradouro);
     	                        $('#bairro').val(data.bairro);
     	                        $('#cidade').val(data.cidade);
     	                        $('#uf').val(data.uf);
     	 
     	                        $('#numero').focus();
     	                    }
     	                }
     	           });   
     	   return false;    
     	   })
     	});
    </script>

</body>

</html>