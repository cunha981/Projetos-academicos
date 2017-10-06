package br.com.scnc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.scnc.bean.AreaAtuacao;
import br.com.scnc.factory.ConnectionFactory;

public class AreaAtuacaoDAO {
	private Connection conn;

	public AreaAtuacaoDAO() {
		this.conn = ConnectionFactory.conectar();
	}

	public List<AreaAtuacao> listaAreaAtucaoVoluntario(Integer idVoluntario) {
		String sql = "SELECT a.idAreaAtuacao, a.desc_areaAtuacao from tbAreaAtuacao a "+
				"INNER JOIN tbAreaAtuacaoVoluntario av on av.idAreaAtuacao = a.idAreaAtuacao "+
				"WHERE av.idVoluntario = ?;";
		List<AreaAtuacao> listaAreaAtuacao = new ArrayList<>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idVoluntario);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				AreaAtuacao areaAtuacao = new AreaAtuacao();
				
				areaAtuacao.setId(rs.getInt(1));
				areaAtuacao.setDescricao(rs.getString(2));
				
				listaAreaAtuacao.add(areaAtuacao);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return listaAreaAtuacao;
	}

}
