package br.com.scnc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.scnc.bean.AreaAtuacao;
import br.com.scnc.bean.Disponibilidade;
import br.com.scnc.bean.Projeto;
import br.com.scnc.factory.ConnectionFactory;

public class ProjetoDAO {
	private Connection conn;

	public ProjetoDAO() {
		this.conn = ConnectionFactory.conectar();
	}

	public void insert(Projeto projeto) {
		String sql = "INSERT INTO tbProjeto"
				+ "(nm_projeto,idOS,desc_projeto,qtd_voluntario,status,dt_inicio,dt_periodo_inscricao,idAreaAtuacao)"
				+ "VALUES(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, projeto.getNome());
			stmt.setInt(2, projeto.getOs());
			stmt.setString(3, projeto.getDescricao());
			stmt.setInt(4, projeto.getQtdVoluntarios());
			stmt.setString(5, projeto.getSituacao().toString());
			stmt.setDate(6, new Date(projeto.getDataInicio().getTime()));
			stmt.setDate(7, new Date(projeto.getPeriodoInscricao().getTime()));
			stmt.setInt(8, projeto.getAreaAtuacao().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		insertDisponibilidadeProjeto(projeto.getDisponibilidades(), findMaxId());
	}

	private void insertDisponibilidadeProjeto(List<Disponibilidade> disponibilidades, Integer idProjeto) {
		String sql = "INSERT INTO tbDisponibilidadeProjeto" + "(idProjeto, idDisponibilidade)" + "VALUES(?,?)";

		for (Disponibilidade d : disponibilidades) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setInt(1, idProjeto);
				stmt.setInt(2, d.getId());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private Integer findMaxId() {
		String sql = "SELECT MAX(idProjeto) as idProjeto FROM tbProjeto";
		Integer idProjeto = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				idProjeto = rs.getInt("idProjeto");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idProjeto;
	}

	public List<Projeto> findByIdOS(Integer idOS) {
		String sql = "SELECT p.idProjeto,p.nm_projeto,p.desc_projeto,p.dt_periodo_inscricao,a.desc_areaAtuacao, p.status, p.dt_inicio, p.dt_cadastro FROM tbProjeto p"
				+ " INNER JOIN tbAreaAtuacao a ON p.idAreaAtuacao = a.idAreaAtuacao" + " WHERE p.idOS = ?";
		List<Projeto> projetos = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idOS);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Projeto projeto = new Projeto();
				AreaAtuacao areaAtuacao = new AreaAtuacao();

				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				projeto.setDescricao(rs.getString(3));
				projeto.setPeriodoInscricao(new java.util.Date(rs.getDate(4).getTime()));
				areaAtuacao.setDescricao(rs.getString(5));
				projeto.setSituacao(Enum.valueOf(Projeto.Situacao.class, rs.getString(6)));
				projeto.setAreaAtuacao(areaAtuacao);
				projeto.setDisponibilidades(findAvailability(projeto.getId()));
				projeto.setDataInicio(new java.util.Date(rs.getDate(7).getTime()));
				projeto.setDataCadastro(new java.util.Date(rs.getDate(8).getTime()));
				projetos.add(projeto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return projetos;
	}

	public List<Projeto> findByIdVol(Integer idVol) {
		String sql = "SELECT p.idProjeto,p.nm_projeto,p.desc_projeto,p.dt_inicio FROM tbProjeto p "
				+ "INNER JOIN tbProjetoVoluntario v ON p.idProjeto = v.idProjeto WHERE v.idVoluntario = ?";
		List<Projeto> projetos = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVol);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				projeto.setDescricao(rs.getString(3));
				projeto.setPeriodoInscricao(new java.util.Date(rs.getDate(4).getTime()));
				projeto.setDisponibilidades(findAvailability(projeto.getId()));

				projetos.add(projeto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return projetos;
	}

	private List<Disponibilidade> findAvailability(Integer idProjeto) {
		String sql = "SELECT d.periodo,d.dia, d.idDisponibilidade FROM tbProjeto p"
				+ " INNER JOIN tbDisponibilidadeProjeto dp ON p.idProjeto = dp.idProjeto"
				+ " INNER JOIN tbDisponibilidade d ON d.idDisponibilidade = dp.idDisponibilidade"
				+ " WHERE p.idProjeto = ?;";
		List<Disponibilidade> disponibilidades = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);

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

	public Projeto findById(Integer idProjeto) {
		String sql = "SELECT p.idProjeto,p.nm_projeto,p.desc_projeto,p.dt_periodo_inscricao,a.desc_areaAtuacao,"
				+ " p.qtd_voluntario, a.idAreaAtuacao, p.dt_inicio, p.idOS, p.status, p.dt_cadastro, os.nm_instituicao FROM tbProjeto p"
				+ " INNER JOIN tbAreaAtuacao a ON p.idAreaAtuacao = a.idAreaAtuacao" 
				+ " INNER JOIN tbOS os ON p.idOS = os.idOS"
				+ " WHERE p.idProjeto = ?";
		Projeto projeto = new Projeto();
		AreaAtuacao areaAtuacao = new AreaAtuacao();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				projeto.setDescricao(rs.getString(3));
				projeto.setPeriodoInscricao(new java.util.Date(rs.getDate(4).getTime()));
				areaAtuacao.setDescricao(rs.getString(5));
				projeto.setQtdVoluntarios(rs.getInt(6));
				areaAtuacao.setId(rs.getInt(7));
				projeto.setDataInicio(new java.util.Date(rs.getDate(8).getTime()));
				projeto.setOs(rs.getInt(9));
				projeto.setSituacao(Enum.valueOf(Projeto.Situacao.class, rs.getString(10)));
				projeto.setDataCadastro(new java.util.Date(rs.getDate(11).getTime()));
				projeto.setNmOS(rs.getString(12));
				projeto.setAreaAtuacao(areaAtuacao);
				projeto.setDisponibilidades(findAvailability(projeto.getId()));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return projeto;
	}

	public void atualizar(Projeto projeto) {
		String sql = "UPDATE tbProjeto SET"
				+ " nm_projeto = ?, desc_projeto = ?, qtd_voluntario = ?, status = ?, dt_inicio = ?, dt_periodo_inscricao = ?, idAreaAtuacao = ?"
				+ " WHERE idProjeto = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, projeto.getNome());
			stmt.setString(2, projeto.getDescricao());
			stmt.setInt(3, projeto.getQtdVoluntarios());
			stmt.setString(4, projeto.getSituacao().toString());
			stmt.setDate(5, new Date(projeto.getDataInicio().getTime()));
			stmt.setDate(6, new Date(projeto.getPeriodoInscricao().getTime()));
			stmt.setInt(7, projeto.getAreaAtuacao().getId());

			stmt.setInt(8, projeto.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		atualizarDisponibilidadeProjeto(projeto.getDisponibilidades(), projeto.getId());
	}

	private void atualizarDisponibilidadeProjeto(List<Disponibilidade> disponibilidades, Integer idProjeto) {
		String sql = "DELETE FROM tbDisponibilidadeProjeto WHERE idProjeto = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		insertDisponibilidadeProjeto(disponibilidades, idProjeto);
	}

	public String excluir(Integer idProjeto) {
		String sql = "DELETE FROM tbProjeto WHERE idProjeto = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			return "Não foi possível excluir o projeto pois há voluntários associados a ele.";
		}
		return null;
	}

	public void suspendeProjeto(Integer idOS) {
		String sql = "UPDATE tbProjeto p SET p.status = 'Suspenso' WHERE p.idOS = ? AND p.status != 'Extinto'";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idOS);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Projeto> listaProjetoVaga() {
		String sql = "SELECT p.idProjeto, p.nm_projeto FROM tbProjeto p "
				+ "WHERE (p.status = 'BuscaDeVoluntario' OR p.status = 'Ativo') and p.qtd_voluntario > (SELECT COUNT(*) FROM tbProjetoVoluntario pv WHERE p.idProjeto = pv.idProjeto)";
		List<Projeto> projetos = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));

				projetos.add(projeto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return projetos;
	}

	public void associarVoluntario(Integer idProjeto, Integer idVoluntario) {
		String sql = "INSERT INTO tbProjetoVoluntario" + "(idProjeto, idVoluntario)" + "VALUES(?,?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idProjeto);
			stmt.setInt(2, idVoluntario);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		removeVoluntarioProjeto(idProjeto,idVoluntario);
	}
	private void removeVoluntarioProjeto(Integer idProjeto, Integer idVoluntario){
		String sql = "UPDATE tbVoluntarioProjeto SET status = false WHERE idVoluntario = ? AND idProjeto = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idVoluntario);
			stmt.setInt(2, idProjeto);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Projeto> listaProjetos() {
		String sql = "SELECT p.idProjeto, p.nm_projeto FROM tbProjeto p ";
		List<Projeto> projetos = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Projeto projeto = new Projeto();
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));

				projetos.add(projeto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return projetos;
	}

	public List<Projeto> listaProjetoVoluntario(Integer idVoluntario) {
		String sql = "SELECT p.idProjeto, p.nm_projeto FROM tbProjeto p "+
				"INNER JOIN tbOS o ON o.idOS = p.idOS "+
				"INNER JOIN tbDisponibilidadeProjeto dp ON dp.idProjeto = p.idProjeto "+
				"INNER JOIN tbAreaAtuacao a ON a.idAreaAtuacao = p.idAreaAtuacao "+
				"WHERE dp.idDisponibilidade IN(SELECT idDisponibilidade FROM tbDisponibilidadeVoluntario WHERE idVoluntario = ?) AND "+
				"a.idAreaAtuacao IN(SELECT idAreaAtuacao FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = ?) AND "+
				"p.status = 'BuscaDeVoluntario' "+
				"GROUP BY p.idProjeto;";
		List<Projeto> listaProjeto = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idVoluntario);
			stmt.setInt(2, idVoluntario);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Projeto projeto = new Projeto();
				
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				
				listaProjeto.add(projeto);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return listaProjeto;
	}

	public List<Projeto> projetoCompativel(Integer id, Integer idVoluntario, String filtro) {
		String sql = "SELECT p.idProjeto, p.nm_projeto, p.desc_projeto, p.dt_inicio, a.desc_areaAtuacao, os.nm_instituicao FROM tbProjeto p "+
				"INNER JOIN tbOS o ON o.idOS = p.idOS "+
				"INNER JOIN tbDisponibilidadeProjeto dp ON dp.idProjeto = p.idProjeto "+
				"INNER JOIN tbAreaAtuacao a ON a.idAreaAtuacao = p.idAreaAtuacao "+
				"INNER JOIN tbOS os ON os.idOS = p.idOS ";

		switch (filtro) {
		case "1":
			sql = sql+"WHERE dp.idDisponibilidade IN(SELECT idDisponibilidade FROM tbDisponibilidadeVoluntario WHERE idVoluntario = "+idVoluntario+") AND "+
					"a.idAreaAtuacao IN(SELECT idAreaAtuacao FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = "+idVoluntario+") AND "+
					"p.idOS ="+id+" AND p.status = 'BuscaDeVoluntario' AND p.dt_periodo_inscricao >= NOW() GROUP BY p.idProjeto";
			break;
		case "2":
			sql = sql+"WHERE dp.idDisponibilidade IN(SELECT idDisponibilidade FROM tbDisponibilidadeVoluntario WHERE idVoluntario = "+idVoluntario+") AND "+
					"a.idAreaAtuacao IN(SELECT idAreaAtuacao FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = "+idVoluntario+") AND "+
					"p.idProjeto ="+id+" AND p.status = 'BuscaDeVoluntario' AND p.dt_periodo_inscricao >= NOW() GROUP BY p.idProjeto";
			break;
		case "3":
			sql = sql+"WHERE dp.idDisponibilidade IN(SELECT idDisponibilidade FROM tbDisponibilidadeVoluntario WHERE idVoluntario = "+idVoluntario+") AND "+
					"a.idAreaAtuacao IN("+id+") AND p.status = 'BuscaDeVoluntario' AND p.dt_periodo_inscricao >= NOW() GROUP BY p.idProjeto";
			break;
		case "4":
			sql = sql+"WHERE dp.idDisponibilidade IN("+id+") AND "+
					"a.idAreaAtuacao IN(SELECT idAreaAtuacao FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = "+idVoluntario+") AND"+
					" p.status = 'BuscaDeVoluntario' AND p.dt_periodo_inscricao >= NOW() GROUP BY p.idProjeto";
			break;
		default:
			sql = sql+"WHERE dp.idDisponibilidade IN(SELECT idDisponibilidade FROM tbDisponibilidadeVoluntario WHERE idVoluntario = "+idVoluntario+") AND "+
					"a.idAreaAtuacao IN(SELECT idAreaAtuacao FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = "+idVoluntario+") AND"+
					" p.status = 'BuscaDeVoluntario' AND p.dt_periodo_inscricao >= NOW() GROUP BY p.idProjeto";
			
		}
		
		List<Projeto> listaProjeto = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Projeto projeto = new Projeto();
				AreaAtuacao area = new AreaAtuacao();
				
				projeto.setId(rs.getInt(1));
				projeto.setNome(rs.getString(2));
				projeto.setDescricao(rs.getString(3));
				projeto.setDataInicio(new java.util.Date(rs.getDate(4).getTime()));
				area.setDescricao(rs.getString(5));
				projeto.setNmOS(rs.getString(6));
				projeto.setAreaAtuacao(area);
				
				listaProjeto.add(projeto);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return listaProjeto;
	}
	public int countVoluntarios(int idProjeto){
		String sql = "SELECT COUNT(*) FROM tbProjetoVoluntario pv WHERE pv.idProjeto = ?";
		
		int count = 0;
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idProjeto);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return count;
		
	}
}
