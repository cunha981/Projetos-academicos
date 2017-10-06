package br.com.scnc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			// Carrega o driver MySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection conectar() {
		String servidor = "localhost";
		String porta = "3306";
		String database = "Projeto2017";
		String usuario = "alunos";
		String senha = "alunos";

		Connection conn = null;
		// abrir uma conex�o atrav�s do m�todo getConnection
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + servidor + ":" + porta + "/" + database + "?user="
					+ usuario + "&password=" + senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
