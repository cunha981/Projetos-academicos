package br.com.scnc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.scnc.bean.AreaAtuacao;
import br.com.scnc.bean.Disponibilidade;
import br.com.scnc.bean.Endereco;
import br.com.scnc.bean.Projeto;
import br.com.scnc.bean.Voluntario;
import br.com.scnc.factory.ConnectionFactory;

public class VoluntarioDAO {
	private Connection conn;

	public VoluntarioDAO() {
		this.conn = ConnectionFactory.conectar();
	}

	public Integer inserir(Voluntario voluntario) {
		String sqlInsert = "INSERT INTO tbVoluntario (nm_voluntario, tel, email, dt_nasc, cpf, genero, estado_civil, idEndereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		Integer idVoluntario = null;

		try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, voluntario.getNome());
			stm.setString(2, voluntario.getTel());
			stm.setString(3, voluntario.getEmail());
			stm.setDate(4, new java.sql.Date(voluntario.getDataNascimento().getTime()));
			stm.setString(5, voluntario.getCpf());
			stm.setString(6, Character.toString(voluntario.getGenero()));
			stm.setString(7, voluntario.getEstadoCivil());
			stm.setInt(8, voluntario.getEndereco().getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idVoluntario = findMaxId();
		inserirDisponibilidadeVoluntario(voluntario.getDisponibilidades(), idVoluntario);
		inserirAreaAtuacaoVoluntario(voluntario.getAreasAtuacao(), idVoluntario);
		return idVoluntario;
	}

	public void inserirDisponibilidadeVoluntario(List<Disponibilidade> disponibilidades, Integer idVoluntario) {
		String sql = "INSERT INTO tbDisponibilidadeVoluntario" + "(idVoluntario, idDisponibilidade)" + "VALUES(?,?)";
		// Integer idVoluntario = findMaxId();

		for (Disponibilidade d : disponibilidades) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setInt(1, idVoluntario);
				stmt.setInt(2, d.getId());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public Integer inserirAreaAtuacaoVoluntario(List<AreaAtuacao> areasAtuacao, Integer idVoluntario) {
		String sql = "INSERT INTO tbAreaAtuacaoVoluntario" + "(idVoluntario, idAreaAtuacao)" + "VALUES(?,?)";
		// Integer idVoluntario = findMaxId();
		for (AreaAtuacao a : areasAtuacao) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setInt(1, idVoluntario);
				stmt.setInt(2, a.getId());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return idVoluntario;
	}

	private Integer findMaxId() {
		String sql = "SELECT MAX(idVoluntario) as idVoluntario FROM tbVoluntario";
		Integer idVoluntario = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				idVoluntario = rs.getInt("idVoluntario");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idVoluntario;
	}

	public void atualizar(Voluntario voluntario) {
		String sqlUpdate = "UPDATE tbVoluntario SET nm_voluntario=?, tel=?, email=?, dt_nasc=?, genero=?, estado_civil=? WHERE idVoluntario=?";

		try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, voluntario.getNome());
			stm.setString(2, voluntario.getTel());
			stm.setString(3, voluntario.getEmail());
			stm.setDate(4, new java.sql.Date(voluntario.getDataNascimento().getTime()));
			stm.setString(5, Character.toString(voluntario.getGenero()));
			stm.setString(6, voluntario.getEstadoCivil());
			stm.setInt(7, voluntario.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		atualizarDisponibilidadeVoluntario(voluntario.getDisponibilidades(), voluntario.getId());
		atualizarAreasAtuacaoVoluntario(voluntario.getAreasAtuacao(), voluntario.getId());

	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM tbVoluntario WHERE idVoluntario = ?";

		try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Voluntario carregar(int id) {
		Voluntario voluntario = new Voluntario();
		voluntario.setId(id);
		String sqlSelect = "SELECT vo.nm_voluntario, vo.tel, vo.email, vo.dt_nasc, vo.cpf, vo.genero, vo.estado_civil, "
				+ "vo.status, e.idEndereco, e.logradouro, e.numero, e.cidade, e.cep, e.estado, COUNT(p.idProjeto) FROM tbVoluntario vo "
				+ "LEFT JOIN tbProjetoVoluntario p ON vo.idVoluntario = p.idVoluntario "
				+ "INNER JOIN tbEndereco e ON e.idEndereco = vo.idEndereco "
				+ "WHERE vo.idVoluntario = ?";

		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, voluntario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					voluntario.setNome(rs.getString(1));
					voluntario.setTel(rs.getString(2));
					voluntario.setEmail(rs.getString(3));
					voluntario.setDataNascimento(rs.getDate(4));
					voluntario.setCpf(rs.getString(5));
					String cast = rs.getString("genero");
					voluntario.setGenero(cast.charAt(0));
					voluntario.setEstadoCivil(rs.getString(7));
					voluntario.setStatus(rs.getBoolean(8));
					Endereco endereco = new Endereco();
					endereco.setId(rs.getInt(9));
					endereco.setLogradouro(rs.getString(10));
					endereco.setNumero(rs.getInt(11));
					endereco.setCidade(rs.getString(12));
					endereco.setCep(rs.getString(13));
					endereco.setEstado(rs.getString(14));
					voluntario.setQtdProjetos(rs.getInt(15));
					voluntario.setEndereco(endereco);
					voluntario.setDisponibilidades(findAvailability(voluntario.getId()));
					voluntario.setAreaAtuacoes(findAreas(voluntario.getId()));
				} else {
					voluntario.setId(-1);
					voluntario.setNome(null);
					voluntario.setTel(null);
					voluntario.setEmail(null);
					voluntario.setDataNascimento(rs.getDate(4));
					voluntario.setCpf(null);
					voluntario.setGenero(null);
					voluntario.setEstadoCivil(null);
					Endereco endereco = new Endereco();
					endereco.setId(-1);
					endereco.setLogradouro(null);
					endereco.setNumero(-1);
					endereco.setCep(rs.getString(null));
					endereco.setCidade(rs.getString(null));
					endereco.setEstado(rs.getString(null));
					voluntario.setEndereco(endereco);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return voluntario;
	}
	/*
	public Voluntario findById(int i) {
		String sql = "SELECT v.idVoluntario,v.nm_voluntario,v.tel," + "v.email,v.dt_nasc,v.cpf,v.genero,v.estado_civil,"
				+ "e.logradouro,e.numero,e.cidade,e.estado,e.cep, e.idEndereco, v.status FROM tbVoluntario v "
				+ "INNER JOIN tbEndereco e ON e.idEndereco = v.idEndereco " + "WHERE v.idVoluntario = ?";
		Voluntario voluntario = new Voluntario();
		Endereco endereco = new Endereco();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, i);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				voluntario.setId(rs.getInt(1));
				voluntario.setNome(rs.getString(2));
				voluntario.setTel(rs.getString(3));
				voluntario.setEmail(rs.getString(4));
				voluntario.setDataNascimento(new java.util.Date(rs.getDate(5).getTime()));
				voluntario.setCpf(rs.getString(6));
				voluntario.setGenero(rs.getString(7).charAt(0));
				voluntario.setEstadoCivil(rs.getString(8));
				endereco.setLogradouro(rs.getString(9));
				endereco.setNumero(rs.getInt(10));
				endereco.setCidade(rs.getString(11));
				endereco.setEstado(rs.getString(12));
				endereco.setCep(rs.getString(13));
				endereco.setId(rs.getInt(14));
				voluntario.setStatus(rs.getBoolean(15));
				voluntario.setEndereco(endereco);
				voluntario.setDisponibilidades(findAvailability(voluntario.getId()));
				voluntario.setAreaAtuacoes(findAreas(voluntario.getId()));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return voluntario;
	}
	*/
	public List<Voluntario> consultarFiltro(String nome, String cpf) {
		String sql = "SELECT v.idVoluntario, v.nm_voluntario, v.cpf, v.email, v.status FROM tbVoluntario v"
				+ " WHERE nm_voluntario LIKE '%" + nome + "%'";
		if (!cpf.isEmpty())
			sql = sql + " AND cpf LIKE '%"+ cpf +"%'";
		sql = sql + " GROUP BY v.idVoluntario";
		List<Voluntario> listaVoluntarios = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Voluntario voluntario = new Voluntario();

				voluntario.setId((rs.getInt(1)));
				voluntario.setNome(rs.getString(2));
				voluntario.setCpf(rs.getString(3));
				voluntario.setEmail(rs.getString(4));
				voluntario.setStatus(rs.getBoolean(5));

				listaVoluntarios.add(voluntario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaVoluntarios;
	}

	private List<Disponibilidade> findAvailability(Integer idVoluntario) {
		String sql = "SELECT d.periodo, d.dia, d.idDisponibilidade FROM tbVoluntario v "
				+ "INNER JOIN tbDisponibilidadeVoluntario dv ON v.idVoluntario = dv.idVoluntario "
				+ "INNER JOIN tbDisponibilidade d ON d.idDisponibilidade = dv.idDisponibilidade "
				+ "WHERE v.idVoluntario = ?;";
		List<Disponibilidade> disponibilidades = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVoluntario);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Disponibilidade disponibilidade = new Disponibilidade();
				disponibilidade.setPeriodo(rs.getString(1));
				disponibilidade.setDia(rs.getString(2));
				disponibilidade.setId(rs.getInt(3));

				disponibilidades.add(disponibilidade);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return disponibilidades;
	}

	private List<AreaAtuacao> findAreas(Integer idVoluntario) {
		String sql = "SELECT a.idAreaAtuacao, a.desc_areaAtuacao FROM tbVoluntario v "
				+ "INNER JOIN tbAreaAtuacaoVoluntario av ON v.idVoluntario = av.idVoluntario "
				+ "INNER JOIN tbAreaAtuacao a ON a.idAreaAtuacao = av.idAreaAtuacao " + "WHERE v.idVoluntario = ?";
		List<AreaAtuacao> areas = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVoluntario);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				AreaAtuacao area = new AreaAtuacao();
				area.setId(rs.getInt(1));
				area.setDescricao(rs.getString(1));

				areas.add(area);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return areas;
	}

	private void atualizarDisponibilidadeVoluntario(List<Disponibilidade> disponibilidades, Integer idVoluntario) {
		String sql = "DELETE FROM tbDisponibilidadeVoluntario WHERE idVoluntario = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVoluntario);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		inserirDisponibilidadeVoluntario(disponibilidades, idVoluntario);
	}

	private void atualizarAreasAtuacaoVoluntario(List<AreaAtuacao> areasAtuacao, Integer idVoluntario) {
		String sql = "DELETE FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVoluntario);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		inserirAreaAtuacaoVoluntario(areasAtuacao, idVoluntario);
	}

	public void suspenderVoluntario(Integer idVoluntario) {
		String sql = "UPDATE tbVoluntario v SET v.status = !v.status WHERE idVoluntario = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVoluntario);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Voluntario> voluntariosCompativeis(Projeto projeto) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < projeto.getDisponibilidades().size(); i++) {
			builder.append(projeto.getDisponibilidades().get(i).getId() + ",");
		}

		String sql = "SELECT v.idVoluntario, v.nm_voluntario, v.cpf, v.email, v.tel FROM tbVoluntario v "
				+ "INNER JOIN tbDisponibilidadeVoluntario dv ON v.idVoluntario = dv.idVoluntario "
				+ "INNER JOIN tbDisponibilidade d ON d.idDisponibilidade = dv.idDisponibilidade "
				+ "INNER JOIN tbAreaAtuacaoVoluntario av ON v.idVoluntario = av.idVoluntario "
				+ "INNER JOIN tbAreaAtuacao a ON a.idAreaAtuacao = av.idAreaAtuacao "
				+ "LEFT JOIN tbVoluntarioProjeto vp on v.idVoluntario = vp.idVoluntario "
				+ "WHERE dv.idDisponibilidade IN (" + builder.deleteCharAt(builder.length() - 1).toString()
				+ ") AND  av.idAreaAtuacao = ? " + "AND vp.idProjeto = ? AND vp.status = true " + "GROUP BY v.idVoluntario";

		List<Voluntario> listaVoluntarios = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, projeto.getAreaAtuacao().getId());
			stmt.setInt(2, projeto.getId());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Voluntario voluntario = new Voluntario();

				voluntario.setId((rs.getInt(1)));
				voluntario.setNome(rs.getString(2));
				voluntario.setCpf(rs.getString(3));
				voluntario.setEmail(rs.getString(4));
				voluntario.setTel(rs.getString(5));

				listaVoluntarios.add(voluntario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaVoluntarios;
	}

	public List<Voluntario> porIdProjeto(Integer idProjeto) {
		String sql = "SELECT v.idVoluntario, v.nm_voluntario, v.cpf, v.email, v.tel FROM tbVoluntario v "
				+ "INNER JOIN tbProjetoVoluntario pv on v.idVoluntario = pv.idVoluntario "
				+ "WHERE pv.idProjeto = ?";
		
		List<Voluntario> listaVoluntarios = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Voluntario voluntario = new Voluntario();

				voluntario.setId((rs.getInt(1)));
				voluntario.setNome(rs.getString(2));
				voluntario.setCpf(rs.getString(3));
				voluntario.setEmail(rs.getString(4));
				voluntario.setTel(rs.getString(5));

				listaVoluntarios.add(voluntario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaVoluntarios;
	}

	public String cadastraVoluntario(Integer idProjeto, Integer idVoluntario) {
		String sql = "INSERT INTO tbVoluntarioProjeto" + "(idProjeto, idVoluntario)" + "VALUES(?,?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);
			stmt.setInt(2, idVoluntario);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			return "O voluntário já está cadastrado neste projeto.";
		}
		return "O voluntário foi cadastro no projeto com sucesso, aguarde a aprovação.";
	}

}
