package br.com.scnc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.scnc.bean.Endereco;
import br.com.scnc.bean.OrganizacaoSocial;
import br.com.scnc.factory.ConnectionFactory;

public class OSDAO {
	private Connection conn;

	public OSDAO() {
		this.conn = ConnectionFactory.conectar();
	}

	public Integer insert(OrganizacaoSocial os) {
		String sql = "INSERT INTO tbOS" + "(nm_instituicao,idEndereco,nm_fantasia,cnpj,tel,email,dt_inicio)"
				+ "VALUES(?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, os.getNome());
			stmt.setInt(2, os.getEndereco().getId());
			stmt.setString(3, os.getNomeFantasia());
			stmt.setString(4, os.getCnpj());
			stmt.setString(5, os.getTel());
			stmt.setString(6, os.getEmail());
			stmt.setDate(7, new java.sql.Date(os.getDataInicio().getTime()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return findMaxId();

	}

	private Integer findMaxId() {
		String sql = "SELECT MAX(idOS) as idOS FROM tbOS";
		Integer idOS = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				idOS = rs.getInt("idOS");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idOS;
	}

	public OrganizacaoSocial findById(int i) {
		String sql = "SELECT os.idOS,os.nm_instituicao,os.nm_fantasia,os.cnpj,os.tel,os.email,os.dt_inicio,e.logradouro,e.numero,e.cidade,e.estado,e.cep,e.idEndereco, os.status, COUNT(p.idProjeto) FROM tbOS os"
				+ " LEFT JOIN tbProjeto p ON os.idOS = p.idOS"
				+ " INNER JOIN tbEndereco e ON e.idEndereco = os.idEndereco" + " WHERE os.idOS = ?";
		OrganizacaoSocial os = new OrganizacaoSocial();
		Endereco endereco = new Endereco();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, i);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				os.setId(Integer.parseInt(rs.getString(1)));
				os.setNome(rs.getString(2));
				os.setNomeFantasia(rs.getString(3));
				os.setCnpj(rs.getString(4));
				os.setTel(rs.getString(5));
				os.setEmail(rs.getString(6));
				os.setDataInicio(new java.util.Date(rs.getDate(7).getTime()));
				endereco.setLogradouro(rs.getString(8));
				endereco.setNumero(rs.getInt(9));
				endereco.setCidade(rs.getString(10));
				endereco.setEstado(rs.getString(11));
				endereco.setCep(rs.getString(12));
				endereco.setId(rs.getInt(13));
				os.setStatus(rs.getBoolean(14));
				os.setQtdProjetos(rs.getInt(15));
				os.setEndereco(endereco);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return os;
	}
	public List<OrganizacaoSocial> consultaFiltro(String nome, String cnpj) {
		String sql = "SELECT os.idOS, os.nm_instituicao, os.cnpj, e.logradouro, e.cidade, e.estado, e.numero, COUNT(p.idProjeto), os.status FROM tbOS os"
				+ " INNER JOIN tbEndereco e ON os.idEndereco = e.idEndereco"
				+ " LEFT JOIN tbProjeto p ON os.idOS = p.idOS"
				+ " WHERE nm_instituicao LIKE '%"+ nome +"%'";
				if(!cnpj.isEmpty()) 
					sql = sql + " AND cnpj LIKE '%"+ cnpj +"%'";
				sql = sql + " GROUP BY os.idOS";
		List<OrganizacaoSocial> listaOS = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				OrganizacaoSocial os = new OrganizacaoSocial();
				Endereco endereco = new Endereco();
				
				os.setId((rs.getInt(1)));
				os.setNome(rs.getString(2));
				os.setCnpj(rs.getString(3));
				endereco.setLogradouro(rs.getString(4));
				endereco.setCidade(rs.getString(5));
				endereco.setEstado(rs.getString(6));
				endereco.setNumero(rs.getInt(7));
				os.setQtdProjetos(rs.getInt(8));
				os.setStatus(rs.getBoolean(9));
				os.setEndereco(endereco);
				
				listaOS.add(os);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaOS;
	}

	public void atualizar(OrganizacaoSocial os) {
		String sql = "UPDATE tbOS SET"+
				" nm_instituicao = ?, nm_fantasia = ?, cnpj = ?, tel = ?, email = ?, dt_inicio = ?"+
				" WHERE idOS = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, os.getNome());
			stmt.setString(2, os.getNomeFantasia());
			stmt.setString(3, os.getCnpj());
			stmt.setString(4, os.getTel());
			stmt.setString(5, os.getEmail());
			stmt.setDate(6, new java.sql.Date(os.getDataInicio().getTime()));
			stmt.setInt(7, os.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Integer idOS) {
		String sql = "UPDATE tbOS o SET o.status = !o.status WHERE idOS = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idOS);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			
		}
	}

	public List<OrganizacaoSocial> listaOSVoluntario(Integer idVoluntario) {
		String sql = "SELECT o.idOS, o.nm_instituicao FROM tbOS o "+
				"INNER JOIN tbProjeto p ON o.idOS = p.idOS "+
				"INNER JOIN tbDisponibilidadeProjeto dp ON dp.idProjeto = p.idProjeto "+
				"INNER JOIN tbAreaAtuacao a ON a.idAreaAtuacao = p.idAreaAtuacao "+
				"WHERE dp.idDisponibilidade IN(SELECT idDisponibilidade FROM tbDisponibilidadeVoluntario WHERE idVoluntario = ?) AND "+
				"a.idAreaAtuacao IN(SELECT idAreaAtuacao FROM tbAreaAtuacaoVoluntario WHERE idVoluntario = ?) AND "+
				"p.status = 'BuscaDeVoluntario' AND p.dt_periodo_inscricao >= NOW() "+
				"GROUP BY o.idOS;";
		List<OrganizacaoSocial> listaOS = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idVoluntario);
			stmt.setInt(2, idVoluntario);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				OrganizacaoSocial os = new OrganizacaoSocial();
				
				os.setId(rs.getInt(1));
				os.setNome(rs.getString(2));
				
				listaOS.add(os);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return listaOS;
	}
}
