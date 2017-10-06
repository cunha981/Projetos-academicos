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

import com.google.gson.Gson;

import br.com.scnc.bean.AreaAtuacao;
import br.com.scnc.bean.Disponibilidade;
import br.com.scnc.bean.OrganizacaoSocial;
import br.com.scnc.bean.Projeto;
import br.com.scnc.service.OSService;
import br.com.scnc.service.ProjetoService;

/**
 * Servlet implementation class ProjetoController
 */
@WebServlet("/proj.do")
public class ProjetoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjetoService projetoService = new ProjetoService();

	private OSService osService = new OSService();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String acao;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		acao = request.getParameter("acao");
		if (acao == null)
			acao = "";

		if (acao.equals("salvar")) {
			inserirProjeto(request);
			RequestDispatcher rd = request.getRequestDispatcher("os-detalhes.jsp");
			rd.forward(request, response);
		} else if (acao.equals("cadastrar")) {
			request.setAttribute("idOS", request.getParameter("idOS"));
			RequestDispatcher rd = request.getRequestDispatcher("proj-inserir.jsp");
			rd.forward(request, response);
		} else if (acao.equals("editar")) {
			editarProjeto(request);
			RequestDispatcher rd = request.getRequestDispatcher("proj-inserir.jsp");
			rd.forward(request, response);
		} else if (acao.equals("excluir")) {
			excluirProjeto(request);
			RequestDispatcher rd = request.getRequestDispatcher("os-detalhes.jsp");
			rd.forward(request, response);
		} else if (acao.equals("json")) {
			String json = new Gson()
					.toJson(projetoService.consultar(Integer.parseInt(request.getParameter("idProjeto"))));
			response.setContentType("application/json");
			response.getWriter().write(json);
		} else if (acao.equals("associar")) {
			associarVoluntario(request,response);
		} else {

		}

	}

	private void associarVoluntario(HttpServletRequest request, HttpServletResponse response) {
		Integer idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));
		Integer idProjeto = Integer.parseInt(request.getParameter("idProjeto"));

		projetoService.associarVoluntario(idProjeto, idVoluntario);
		try {
			response.sendRedirect("VoluntarioController.do?acao=vAssociados&idProjeto="+idProjeto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void excluirProjeto(HttpServletRequest request) {
		Integer idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		String msg = projetoService.excluir(idProjeto);
		Integer idOS = Integer.parseInt(request.getParameter("idOS"));
		OrganizacaoSocial os = osService.consultarDetalhes(idOS);
		request.setAttribute("os", os);
		request.setAttribute("msg", msg);
	}

	private void editarProjeto(HttpServletRequest request) {
		Integer idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		Projeto projeto = projetoService.consultar(idProjeto);
		request.setAttribute("projeto", projeto);
	}

	private void inserirProjeto(HttpServletRequest request) {
		projetoService.salvar(criaProjeto(request));
		Integer idOS = Integer.parseInt(request.getParameter("idOS"));
		OrganizacaoSocial os = osService.consultarDetalhes(idOS);
		request.setAttribute("os", os);
	}

	private Projeto criaProjeto(HttpServletRequest request) {
		Projeto projeto = new Projeto();
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		List<Disponibilidade> disponibilidades = new ArrayList<>();

		String[] listDisp = (request.getParameterValues("idDisponibilidade"));
		for (String disp : listDisp) {
			Disponibilidade disponibilidade = new Disponibilidade();
			disponibilidade.setId(Integer.parseInt(disp));
			disponibilidades.add(disponibilidade);
		}
		areaAtuacao.setId(Integer.parseInt(request.getParameter("idAreaAtuacao")));
		try {
			projeto.setId(Integer.parseInt(request.getParameter("idProjeto")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			projeto.setOs(Integer.parseInt(request.getParameter("idOS")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		projeto.setDescricao(request.getParameter("descricao"));
		projeto.setNome(request.getParameter("nomeProjeto"));
		try {
			projeto.setDataInicio(sdf.parse(request.getParameter("dataInicio")));
			projeto.setPeriodoInscricao(sdf.parse(request.getParameter("periodoInscricao")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		projeto.setQtdVoluntarios(Integer.parseInt(request.getParameter("qtdCandidatos")));
		// n�o precisa ter status na jsp
		projeto.setSituacao(Enum.valueOf(Projeto.Situacao.class, request.getParameter("situacao")));
		projeto.setAreaAtuacao(areaAtuacao);
		projeto.setDisponibilidades(disponibilidades);

		return projeto;
	}
}
