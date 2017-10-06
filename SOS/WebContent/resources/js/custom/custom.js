 $(document).ready(function() {
        	$('#responsiveTable').DataTable( {
        		"sDom": '<"row view-filter"<"col-sm-12"<"pull-right"l><"pull-right"f><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-right"ip>>>',
                "pagingType": "full_numbers",
                "ordering": true,
                "info":     false,
                "searching": false,
                "lengthChange": false,
                "scrollCollapse": false,
                "autoWidth": false,
                columnDefs: [ {
                    targets: 1,
                    render: function ( data, type, row ) {
                        if(data.length >66){
                        	return data.substr( 0, 65 )+' ...';
                        }
                        return data;
                    }
                } ],
                "pageLength":5,
                "oLanguage":{
                    "sEmptyTable": "Nenhum registro encontrado",
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                    "sInfoPostFix": "",
                    "sInfoThousands": ".",
                    "sLengthMenu": "_MENU_ resultados por página",
                    "sLoadingRecords": "Carregando...",
                    "sProcessing": "Processando...",
                    "sZeroRecords": "Nenhum registro encontrado",
                    "sSearch": "Pesquisar",
                    "oPaginate": {
                        "sNext": "Próximo",
                        "sPrevious": "Anterior",
                        "sFirst": "Primeiro",
                        "sLast": "Último"
                    },
                    "oAria": {
                        "sSortAscending": ": Ordenar colunas de forma ascendente",
                        "sSortDescending": ": Ordenar colunas de forma descendente"
                    }
                }
            } );
        });
 
 function detalheProjeto(idProjeto){
 	var acao = 'json';
 	$.post('proj.do',{'acao': acao,'idProjeto':idProjeto},function(data){
 		console.log(data);
 		$('#nmProjeto').text(data.nome);
 		$('#nmOS').text(data.nmOS);
 		$('#descProjeto').text(data.descricao);
 		
 		var date = moment(data.periodoInscricao);
 		$('#periodoInscricao').val(date.format("YYYY-MM-DD"));
 		
 		date = moment(data.dataInicio);
 		$('#dtInicio').val(date.format("YYYY-MM-DD"))
 		
 		date = moment(data.dataCadastro);
 		$('#dtCadastro').val(date.format("YYYY-MM-DD"));
 		
 		$('#txtStatus').val(data.situacao);
 		$('#qtdVoluntarios').val(data.qtdVoluntarios)
 		
 		$('.liDisp').remove();
 		$(data.disponibilidades).each(function(i,item){
 			$('#disponibilidades').append("<li class='liDisp'>"+item.dia+", "+item.periodo+"</li>");
 		})
 		$('#areaAtuacao').text(data.areaAtuacao.descricao);
 		$('#vis-voluntarios').attr('href','VoluntarioController.do?acao=vAssociados&idProjeto='+data.id)
 		$('#vis-os').attr('href','os.do?acao=detalhes&idOS='+data.os)
 		
 		$('#modalDetalhesProjeto').modal();
 	});
 }