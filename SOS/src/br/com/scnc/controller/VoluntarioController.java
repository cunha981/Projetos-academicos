package br.com.scnc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.scnc.bean.AreaAtuacao;
import br.com.scnc.bean.Disponibilidade;
import br.com.scnc.bean.Endereco;
import br.com.scnc.bean.OrganizacaoSocial;
import br.com.scnc.bean.Projeto;
import br.com.scnc.bean.Voluntario;
import br.com.scnc.service.AreaAtuacaoService;
import br.com.scnc.service.DisponibilidadeService;
import br.com.scnc.service.OSService;
import br.com.scnc.service.ProjetoService;
import br.com.scnc.service.VoluntarioService;

@WebServlet("/VoluntarioController.do")
public class VoluntarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VoluntarioService vService = new VoluntarioService();
	private ProjetoService projetoService = new ProjetoService();
	private OSService oSService = new OSService();
	private AreaAtuacaoService areaAtuacaoService = new AreaAtuacaoService();
	private DisponibilidadeService disponibilidadeService = new DisponibilidadeService();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String acao;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		acao = request.getParameter("acao");
		if (acao == null)
			acao = "";

		if (acao.equals("salvar")) {
			inserirVoluntario(request);
			if (request.getParameter("idVoluntario").isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("proj-vol-cadastrar.jsp");
				rd.forward(request, response);
			} else {
				consultarDetalhes(request);
				RequestDispatcher rd = request.getRequestDispatcher("vol-editar.jsp");
				rd.forward(request, response);
			}
			// TODO: criar JSP de sequência do fluxo de inserir voluntário
		} else if (acao.equals("detalhes")) {
			consultarDetalhes(request);
			RequestDispatcher rd = request.getRequestDispatcher("vol-detalhes.jsp");
			rd.forward(request, response);
		} else if (acao.equals("editar")) {
			editarVoluntario(request);
			RequestDispatcher rd = request.getRequestDispatcher("vol-editar.jsp");
			rd.forward(request, response);
		} else if (acao.equals("filtro")) {
			consultarVoluntario(request);
			RequestDispatcher rd = request.getRequestDispatcher("vol-consultar.jsp");
			rd.forward(request, response);			
		} else if(acao.equals("excluir")){
			excluirVoluntario(request);
			consultarDetalhes(request);
			request.setAttribute("msg", "Operação realizada com sucesso.");
			RequestDispatcher rd = request.getRequestDispatcher("vol-detalhes.jsp");
			rd.forward(request, response);
		} else if (acao.equals("compativel")) {
			request.setAttribute("projetos", projetoService.listaProjetoVaga());
			RequestDispatcher rd = request.getRequestDispatcher("proj-vol-consultar.jsp");
			rd.forward(request, response);
		} else if (acao.equals("vCompativel")) {
			voluntariosCompativeis(request);
			request.setAttribute("projetos", projetoService.listaProjetoVaga());
			RequestDispatcher rd = request.getRequestDispatcher("proj-vol-consultar.jsp");
			rd.forward(request, response);
		}else if(acao.equals("associados")){
			request.setAttribute("projetos", projetoService.listaProjeto());
			RequestDispatcher rd = request.getRequestDispatcher("vol-proj-consultar.jsp");
			rd.forward(request, response);
		}else if(acao.equals("vAssociados")){
			voluntariosAssociados(request);
			request.setAttribute("projetos", projetoService.listaProjeto());
			RequestDispatcher rd = request.getRequestDispatcher("vol-proj-consultar.jsp");
			rd.forward(request, response);
		}else if(acao.equals("pCompativel")){
			Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
			cadastraVoluntarioProjeto(idVoluntario,request);
			projetoCompativel(request);
			request.setAttribute("idVoluntario", idVoluntario);
			RequestDispatcher rd = request.getRequestDispatcher("proj-vol-cadastrar.jsp");
			rd.forward(request, response);
		}else if(acao.equals("pvCompativel")){
			Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
			cadastraVoluntarioProjeto(idVoluntario,request);
			request.setAttribute("idVoluntario", idVoluntario);
			RequestDispatcher rd = request.getRequestDispatcher("proj-vol-cadastrar.jsp");
			rd.forward(request, response);
		}else if(acao.equals("cadastrar")){
			cadastraVoluntario(request,response);
			
		}
		else {
			response.sendRedirect("vol-consultar.jsp");
		}
	}
	
	private void cadastraVoluntario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
		Integer idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		
		String msg = vService.cadastraVoluntario(idProjeto,idVoluntario);
		cadastraVoluntarioProjeto(idVoluntario,request);
		request.setAttribute("msg", msg);
		request.setAttribute("idVoluntario", idVoluntario);
		RequestDispatcher rd = request.getRequestDispatcher("proj-vol-cadastrar.jsp");
		rd.forward(request, response);
	}

	private void projetoCompativel(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
		String filtro = request.getParameter("inline-radio");
		List<Projeto>listaProjeto = projetoService.projetoCompativel(id,idVoluntario,filtro);
		
		request.setAttribute("projetosV", listaProjeto);
		
	}

	private void voluntariosAssociados(HttpServletRequest request) {
		Integer idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		List<Voluntario>voluntarios = vService.voluntariosAssociados(idProjeto);
		
		request.setAttribute("voluntarios", voluntarios);
		request.setAttribute("idProjeto", idProjeto);
		
		if(voluntarios == null || voluntarios.size() < 1)
			request.setAttribute("vazia", "Nenhum registro encontrado.");
	}

	private void excluirVoluntario(HttpServletRequest request) {
		Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
		vService.suspenderVoluntario(idVoluntario);
	}

	private void voluntariosCompativeis(HttpServletRequest request) {
		Integer idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		List<Voluntario> voluntarios = vService.voluntariosCompativeis(idProjeto);
		request.setAttribute("voluntarios", voluntarios);
		request.setAttribute("idProjeto", idProjeto);
	}

	private void consultarDetalhes(HttpServletRequest request) {
		Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
		Voluntario voluntario = vService.consultarDetalhes(idVoluntario);
		request.setAttribute("voluntario", voluntario);
	}

	private void consultarVoluntario(HttpServletRequest request) {
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");

		List<Voluntario> listaVoluntarios = vService.consultarFiltro(nome, cpf);
		request.setAttribute("listaVoluntarios", listaVoluntarios);
		if(listaVoluntarios == null || listaVoluntarios.size() < 1)
			request.setAttribute("vazia", "Nenhum registro encontrado.");
		/*
		 * Voluntario voluntario = vService.consultar();
		 * request.setAttribute("voluntario", voluntario);
		 */
	}

	private void editarVoluntario(HttpServletRequest request) {
		Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
		Voluntario voluntario = vService.consultarDetalhes(idVoluntario);
		request.setAttribute("voluntario", voluntario);
	}

	private void inserirVoluntario(HttpServletRequest request) {
		Integer idVoluntario = vService.salvar(criaVoluntario(request));
		request.setAttribute("idVoluntario", idVoluntario);
		if(request.getParameter("idVoluntario").isEmpty()){
			cadastraVoluntarioProjeto(idVoluntario,request);
		}
	}
	
	private void cadastraVoluntarioProjeto(Integer idVoluntario, HttpServletRequest request){
		List<OrganizacaoSocial>organizacoes = oSService.listaOSVoluntario(idVoluntario);
		List<Projeto>projetos = projetoService.listaProjetoVoluntario(idVoluntario);
		List<AreaAtuacao>areasAtuacao = areaAtuacaoService.listaAreaAtucaoVoluntario(idVoluntario);
		List<Disponibilidade>disponibilidades = disponibilidadeService.listaDisponibilidadeVoluntario(idVoluntario);
		
		List<Projeto>listaProjeto = projetoService.projetoCompativel(null,idVoluntario,"");
		
		request.setAttribute("projetosV", listaProjeto);
		request.setAttribute("organizacoes", organizacoes);
		request.setAttribute("projetos", projetos);
		request.setAttribute("areasAtuacao", areasAtuacao);
		request.setAttribute("disponibilidades", disponibilidades);
	}
	
	private Voluntario criaVoluntario(HttpServletRequest request) {
		Voluntario voluntario = new Voluntario();
		List<Disponibilidade> disponibilidades = new ArrayList<>();
		List<AreaAtuacao> areaAtuacoes = new ArrayList<>();
		Endereco endereco = new Endereco();

		try {
			endereco.setId(Integer.parseInt(request.getParameter("idEndereco")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			voluntario.setId(Integer.parseInt(request.getParameter("idVoluntario")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		endereco.setCep(request.getParameter("cep"));
		endereco.setCidade(request.getParameter("cidade"));
		endereco.setEstado(request.getParameter("uf"));
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setNumero(Integer.parseInt(request.getParameter("numero")));

		String[] listDisp = (request.getParameterValues("idDisponibilidade"));
		for (String disp : listDisp) {
			Disponibilidade disponibilidade = new Disponibilidade();
			disponibilidade.setId(Integer.parseInt(disp));
			disponibilidades.add(disponibilidade);
		}
		String[] listAreas = (request.getParameterValues("idAreaAtuacao"));
		for (String areas : listAreas) {
			AreaAtuacao areaAtuacao = new AreaAtuacao();
			areaAtuacao.setId(Integer.parseInt(areas));
			areaAtuacoes.add(areaAtuacao);
		}

		voluntario.setNome(request.getParameter("nomeVoluntario"));
		voluntario.setCpf(request.getParameter("cpf"));
		voluntario.setTel(request.getParameter("tel"));
		voluntario.setEmail(request.getParameter("email"));
		voluntario.setGenero(request.getParameter("genero").charAt(0));
		voluntario.setEstadoCivil(request.getParameter("estCivil"));

		try {
			voluntario.setDataNascimento(sdf.parse(request.getParameter("dataNasc")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		voluntario.setAreaAtuacoes(areaAtuacoes);
		voluntario.setDisponibilidades(disponibilidades);
		voluntario.setEndereco(endereco);

		return voluntario;
	}
}
