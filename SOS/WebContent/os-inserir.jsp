<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Cadastrar OS</title>

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
                    <li class="active">Cadastrar OS</li>
                </ul>
            </div>
            <!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->

                <div class="panel panel-default">
                    <form class="form-horizontal form-border" method="POST" action="os.do?acao=salvar" data-validate="parsley">
                        <div class="panel-heading">
                            Cadastrar organização social
                        </div>
                        <input type="hidden" name="idOS" value="${os.id }">
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="control-label col-md-2">Nome (razão social)</label>
                                <div class="col-md-5">
                                    <input name="nomeOS" type="text" id="nomeOS" class="form-control input-sm" placeholder="Nome" value="${os.nome }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Nome fantasia</label>
                                <div class="col-md-5">
                                    <input name="nomeFantasia" type="text" id="nomeFantasia" class="form-control input-sm" placeholder="Nome fantasia" value="${ os.nomeFantasia}" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">CNPJ</label>
                                <div class="col-md-5">
                                    <input name="cnpj" type="text" id="cnpj" class="form-control input-sm" placeholder="CNPJ" value="${os.cnpj }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Endereço</label>
                                <div class="col-md-5">
                                	<input type="hidden" name="idEndereco" value="${os.endereco.id }">
                                    <input id="cep" name="cep" type="text" class="form-control input-sm" placeholder="CEP" value="${os.endereco.cep }" data-required="true"><br>
                                    <input id="logradouro" name="logradouro" type="text" class="form-control input-sm" placeholder="Logradouro" value="${os.endereco.logradouro }" data-required="true"><br>
                                    <input id="numero" name="numero" type="number" min="0" class="form-control input-sm" placeholder="Número" value="${os.endereco.numero }" data-required="true"><br>
                                    <input id="uf" name="uf" type="text" class="form-control input-sm" placeholder="Estado" value="${os.endereco.estado }" data-required="true"><br>
                                    <input id="cidade"  name="cidade" type="text" class="form-control input-sm" placeholder="Cidade" value="${os.endereco.cidade }" data-required="true">
                                </div>
                                <!-- /.col -->                
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Telefone</label>
                                <div class="col-md-5">
                                    <input name="tel" type="tel" id="telefone" class="form-control input-sm" placeholder="Telefone" value="${os.tel }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">E-mail</label>
                                <div class="col-md-5">
                                    <input name="email" type="email" id="email" class="form-control input-sm" placeholder="E-mail" value="${os.email }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-md-2">Data de início das atividades</label>
                                <div class="col-md-5">
                                	<fmt:formatDate value="${os.dataInicio }" pattern="yyyy-MM-dd" var="dataInicio"/>
                                    <input name="dataInicio" type="date" class="form-control input-sm" value="${dataInicio }" data-required="true">
                                </div>
                                <!-- /.col -->
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="text-right">
								<button class="btn btn-sm btn-primary" type="submit">Salvar</button>
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
            <h4 class="m-top-none"> Deseja excluir esta organização?</h4>
        </div>

        <div class="text-center">
            <a href="#" class="btn btn-success m-right-sm lightCustomModal_close">Sim</a>
            <a href="#" class="btn btn-danger lightCustomModal_close">Nâo</a>
        </div>
    </div>
		
	<c:import url="scripts.jsp"/>

    <script>
	    $(document).ready(function () {
	    	$('#cnpj').mask('00.000.000/0000-00', {reverse: true});
	    	$('#cep').mask('00000-000');
	    	$('#uf').mask('SS');
    		$("#telefone").mask("(00) 0000-0000");
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