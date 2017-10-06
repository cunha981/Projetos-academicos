package br.com.scnc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.scnc.bean.Endereco;
import br.com.scnc.factory.ConnectionFactory;

public class EnderecoDAO {
	private Connection conn;

	public EnderecoDAO() {
		this.conn = ConnectionFactory.conectar();
	}

	public Endereco insert(Endereco endereco) {
		String sql = "INSERT INTO tbEndereco" + "(logradouro,numero,cidade,cep,estado)" + "VALUES(?,?,?,?,?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, endereco.getLogradouro());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getCidade());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getEstado());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return findByMaxId();

	}

	private Endereco findByMaxId() {
		String sql = "SELECT MAX(idEndereco) as idEndereco FROM tbEndereco";
		Endereco endereco = new Endereco();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				endereco.setId(rs.getInt("idEndereco"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return endereco;
	}

	public Endereco atualizar(Endereco endereco) {
		String sql = "UPDATE tbEndereco SET"+
				" logradouro = ?, numero = ?, cidade = ?, cep = ?, estado = ?"+
				" WHERE idEndereco = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, endereco.getLogradouro());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getCidade());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getEstado());
			stmt.setInt(6, endereco.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return endereco;
	}

}
