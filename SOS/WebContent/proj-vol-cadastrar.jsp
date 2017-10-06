<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SOS | Cadastrar Voluntário Compatível</title>

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
					 <li class="active">Consultar voluntários</li>	 
					 <li class="active">Consultar projetos compatíveis</li>
				</ul>
			</div><!-- breadcrumb -->
            <div class="padding-md">
                <!--Corpo da pagina-->
                <!-- <a href="" id="theme-setting-icon" class="tooltip-test" data-toggle="tooltip" data-placement="left" title="Incluir novo projeto"><i class="fa fa-plus fa-lg"></i></a>  -->
                <div class="panel panel-default">
                        <div class="panel-heading">
                            Projetos compatíveis com você.
                        </div>
                        <div class="panel-body">
                        	<form action="VoluntarioController.do" id="filtro-js">
                        		<input name="acao" value="pCompativel" type="hidden">
                        		<input name="idVoluntario" value="${idVoluntario }" type="hidden">
	                            <div class="row">
	                                <div class="col-md-6 col-sm-6 hide chk" id="chk2">
	                                    <label>Nome do projeto</label>
										<select class="form-control input-sm filtro" name="">
											<option value="0">Selecione</option>
											<c:forEach items="${projetos }" var="p">
												<option value="${p.id }" ${idProjeto == p.id? 'selected':'' }>${p.nome }</option>
											</c:forEach>
										</select>
	                                </div><!-- /.col -->
	                                <div class="col-md-6 col-sm-6 chk" id="chk1">
	                                    <label>Organização social</label>
										<select class="form-control input-sm filtro" name="id">
											<option value="0">Selecione</option>
											<c:forEach items="${organizacoes }" var="os">
												<option value="${os.id }" ${idOS == os.id? 'selected':'' }>${os.nome }</option>
											</c:forEach>
										</select>
	                                </div><!-- /.col -->
	                                <div class="col-md-6 col-sm-6 hide chk" id="chk3">
	                                    <label>Área de atuação</label>
										<select class="form-control input-sm filtro" name="">
											<option value="0">Selecione</option>
											<c:forEach items="${areasAtuacao }" var="a">
												<option value="${a.id }" ${idAreaAtuacao == a.id? 'selected':'' }>${a.descricao }</option>
											</c:forEach>
										</select>
	                                </div><!-- /.col -->
	                                <div class="col-md-6 col-sm-6 hide chk" id="chk4">
	                                    <label>Disponibilidade</label>
										<select class="form-control input-sm filtro" name="">
											<option value="0">Selecione</option>
											<c:forEach items="${disponibilidades }" var="d">
												<option value="${d.id }" ${idDisponibilidade == d.id? 'selected':'' }>${d.dia }, ${d.periodo }</option>
											</c:forEach>
										</select>
	                                </div><!-- /.col -->
	                                <div class="col-md-3 col-sm-3">
	                                    <button class="btn btn-primary btn-sm" style="margin-top: 21px" onclick="enviar()"><i class="fa fa-search"></i></button>
	                                </div><!-- /.col -->
	                            </div><!-- /.row -->
	                            <br>
                            <div class="row">
                            	<div class="form-group">
								<label class="col-md-1 control-label">Filtrar por:</label>
									<div class="col-md-10">
										<label class="label-radio inline">
											<input type="radio" name="inline-radio" value="1" checked>
											<span class="custom-radio"></span>
											Organização social
										</label>
										<label class="label-radio inline">
											<input type="radio" name="inline-radio" value="3">
											<span class="custom-radio"></span>
											Área de atuação
										</label>
										<label class="label-radio inline">
											<input type="radio" name="inline-radio" value="4">
											<span class="custom-radio"></span>
											Disponibilidade
										</label>
									</div><!-- /.col -->
								</div><!-- /form-group -->
                            </div>
                            </form>
                        </div>
                        <c:if test="${not empty msg }">
                        	<div class="alert alert-warning">
                        		<p class="text-center">${msg }</p>
                        	</div>
                        </c:if>
                        <table class="table table-striped" id="responsiveTable">
                            <thead>
                                <tr>
                                    <th class="text-center col-md-2">Nome</th>
                                    <th class="text-center col-md-4">Descrição</th>
                                    <th class="text-center col-md-2">Área de atuação</th>
                                    <th class="text-center col-md-2">Organização social</th>
                                    <th class="col-md-1"></th>
                                </tr>
                            </thead>
                            <tbody class="text-center">
                                <c:forEach items="${projetosV }" var="v">
	                                <tr>
	                                    <td>${v.nome }</td>
	                                    <td>${v.descricao }</td>
	                                    <td nowrap="nowrap">${v.areaAtuacao.descricao }</td>
	                                    <td>${v.nmOS }</td>
	                                    <td>
	                                        <div class="btn-group">
								                <button class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown"><span class="fa fa-cogs"> </span> <span class="caret"></span></button>
	                                            <ul class="dropdown-menu slidedown dropdown-menu-right">
	                                                <li><a href="#" onclick="detalheProjeto(${v.id})"><i class="fa fa-eye"></i> Detalhes</a></li>
	                                                <li><a href="VoluntarioController.do?acao=cadastrar&idVoluntario=${idVoluntario }&idProjeto=${v.id}"><i class="fa fa-group"></i> Cadastrar</a></li>
	                                            </ul>
							                </div><!-- /btn-group -->
	                                    </td>
	                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="panel-footer clearfix">
                        	<p class="text-justify">
                            	<strong class="text-info">Suas disponibilidades </strong>
                            	<c:forEach items="${disponibilidades }" var="d">
									<span class="text-info fa fa-ellipsis-h "></span>  ${d.dia }, ${d.periodo } 
								</c:forEach>
                            </p>
                            <p class="text-justify">
                            	<strong class="text-info">Áreas de atuações </strong>
                            	<c:forEach items="${areasAtuacao }" var="a">
									<span class="text-info fa fa-ellipsis-h "></span>  ${a.descricao }
								</c:forEach>
                            </p>
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
        	  $('input[type="radio"]').click(function(){
        	    if ($(this).is(':checked'))
        	    {	
        	    	$('.chk').removeClass('hide');
        	    	$('.chk').addClass('hide');
        	    	$('.filtro').prop('name','');
        	      	$('#chk'+$(this).val()).removeClass('hide');
        	      	$('#chk'+$(this).val()).find('select').prop('name','id');
        	    }
        	  });
        	  $('.chk').change(function(){
        		  $('#filtro-js').submit();
          	  });
        	});
        function enviar(){
        	if($("select[name=id]").val() < 1){
        		$("input[name=inline-radio]").val('0');
        	}
        	 $('#filtro-js').submit();
        }
    </script>	
  </body>
</html>