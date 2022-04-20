package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Livro;

public class LivroDAO {
	private Conexao minhaConexao;
	private final String RELATORIO = "select * from livro";
	private final String INCLUIR = "insert into livro (isbn, nome_l, autor, preco, genero) values (?, ?, ?, ?, ?)";
	private final String BUSCAR = "select * from livro where isbn =?";
	private final String EXCLUIR = "delete from livro where isbn =?";

	public LivroDAO() {

		minhaConexao = new Conexao();
	}

	public ArrayList<Livro> relatorio() {
		ArrayList<Livro> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				Livro l = new Livro(rs.getString("nome_l"), rs.getString("autor"),
						rs.getFloat("preco"), rs.getString("genero"), rs.getInt("isbn"),
						rs.getInt("volume"));
				lista.add(l);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public void inclusao(Livro l) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setInt(1, l.getIsbn());
			instrucao.setString(2, l.getNome());
			instrucao.setString(3, l.getAutor());
			instrucao.setFloat(4, l.getPreco());
			instrucao.setString(5, l.getGenero());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}
	}

	public int buscar(int isbn) {
		int s = 0;

		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setInt(1, isbn);
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				s = rs.getInt("isbn");

			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro na busca: " + e.getMessage());
		}
		return s;
	}

	public void exclusao(int isbn) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(EXCLUIR);
			instrucao.setInt(1, isbn);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclus�o: " + e.getMessage());
		}
	}
}
