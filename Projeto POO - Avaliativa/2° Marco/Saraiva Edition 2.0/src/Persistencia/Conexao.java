package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private String usuario;
	private String senha;
	private String caminho;
	private Connection con;

	public Conexao() {
		caminho = "jdbc:mysql://localhost:3360/Livraria";
		senha = "1234";
		usuario = "root";
	}

	public void conectar() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(caminho, usuario, senha);
		} catch (Exception e) {
			System.out.println("Erro na conexão: " + e.getMessage());
		}
	}

	public void desconectar() {
		try {
			con.close(); // fecha a conexão com o PostgreSQL
		} catch (Exception e) {
			System.out.println("Erro na desconexão: " + e.getMessage());
		}
	}

	public Connection getConexao() {
		return con;
	}
}
