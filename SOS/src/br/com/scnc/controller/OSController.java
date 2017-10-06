package br.com.scnc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.scnc.bean.Endereco;
import br.com.scnc.bean.OrganizacaoSocial;
import br.com.scnc.service.OSService;

@WebServlet("/os.do")
public class OSController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			inserirOS(request);
			if (request.getParameter("idOS").isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("proj-inserir.jsp");
				rd.forward(request, response);
			} else {
				consultarDetalhes(request);
				RequestDispatcher rd = request.getRequestDispatcher("os-detalhes.jsp");
				rd.forward(request, response);
			}
		} else if (acao.equals("detalhes")) {
			consultarDetalhes(request);
			RequestDispatcher rd = request.getRequestDispatcher("os-detalhes.jsp");
			rd.forward(request, response);
		} else if (acao.equals("filtro")) {
			consultarOS(request);
			RequestDispatcher rd = request.getRequestDispatcher("os-consultar.jsp");
			rd.forward(request, response);
		} else if (acao.equals("editar")) {
			editarOS(request);
			RequestDispatcher rd = request.getRequestDispatcher("os-inserir.jsp");
			rd.forward(request, response);
		} else if (acao.equals("excluir")) {
			excluirOS(request);
			consultarDetalhes(request);
			request.setAttribute("msg", "Operação realizada com sucesso.");
			RequestDispatcher rd = request.getRequestDispatcher("os-detalhes.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("os-consultar.jsp");
		}
	}

	private void excluirOS(HttpServletRequest request) {
		Integer idOS = Integer.parseInt(request.getParameter("idOS"));
		osService.excluir(idOS);

	}

	private void consultarDetalhes(HttpServletRequest request) {
		Integer idOS = Integer.parseInt(request.getParameter("idOS"));
		OrganizacaoSocial os = osService.consultarDetalhes(idOS);
		request.setAttribute("os", os);
	}

	private void consultarOS(HttpServletRequest request) {
		String nome = request.getParameter("nome");
		String cnpj = request.getParameter("cnpj");

		List<OrganizacaoSocial> listaOS = osService.consultarFiltro(nome, cnpj);
		request.setAttribute("listaOS", listaOS);
		if(listaOS == null || listaOS.size() < 1)
			request.setAttribute("vazia", "Nenhum registro encontrado.");

	}

	private void editarOS(HttpServletRequest request) {
		Integer idOS = Integer.parseInt(request.getParameter("idOS"));
		OrganizacaoSocial os = osService.consultar(idOS);
		request.setAttribute("os", os);
	}

	private void inserirOS(HttpServletRequest request) {
		Integer idOS = osService.salvar(criaOS(request));
		request.setAttribute("idOS", idOS);
	}

	private OrganizacaoSocial criaOS(HttpServletRequest request) {
		OrganizacaoSocial os = new OrganizacaoSocial();
		Endereco endereco = new Endereco();

		// TODO arrumar campo endereco na jsp
		try {
			endereco.setId(Integer.parseInt(request.getParameter("idEndereco")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		endereco.setCep(request.getParameter("cep"));
		endereco.setCidade(request.getParameter("cidade"));
		endereco.setEstado(request.getParameter("uf"));
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setNumero(Integer.parseInt(request.getParameter("numero")));

		try {
			os.setId(Integer.parseInt(request.getParameter("idOS")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		os.setNome(request.getParameter("nomeOS"));
		os.setNomeFantasia(request.getParameter("nomeFantasia"));
		os.setCnpj(request.getParameter("cnpj"));
		os.setEmail(request.getParameter("email"));
		os.setTel(request.getParameter("tel"));
		try {
			os.setDataInicio(sdf.parse(request.getParameter("dataInicio")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		os.setEndereco(endereco);

		return os;
	}

}
