package br.com.scnc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.scnc.bean.Disponibilidade;
import br.com.scnc.factory.ConnectionFactory;

public class DisponibilidadeDAO {
	private Connection conn;

	public DisponibilidadeDAO() {
		this.conn = ConnectionFactory.conectar();
	}

	public List<Disponibilidade> listaDisponibilidadeVoluntario(Integer idVoluntario) {
		String sql = "SELECT d.idDisponibilidade, d.dia, d.periodo FROM tbDisponibilidade d "+
				"INNER JOIN tbDisponibilidadeVoluntario dv on dv.idDisponibilidade = d.idDisponibilidade "+
				"WHERE dv.idVoluntario = ?;";
		List<Disponibilidade> listaDisponibilidade = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idVoluntario);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Disponibilidade disponibilidade = new Disponibilidade();
				
				disponibilidade.setId(rs.getInt(1));
				disponibilidade.setDia(rs.getString(2));
				disponibilidade.setPeriodo(rs.getString(3));
				
				listaDisponibilidade.add(disponibilidade);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return listaDisponibilidade;
	}
}
