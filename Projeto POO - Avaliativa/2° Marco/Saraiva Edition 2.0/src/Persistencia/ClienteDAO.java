package Persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Cliente;
import Dominio.Livro;

public class ClienteDAO {
	private Conexao minhaConexao;
	private final String RELATORIO = "select * from cliente";
	private final String INCLUIR = "insert into cliente (cpf_c, numerotel, nome) values (?, ?, ?)";
	private final String BUSCAR = "select * from cliente where cpf_c =?";
	private final String EXCLUIR = "delete from cliente where cpf_c =?";
	private final String ALTERARNOME = "update cliente set nome =? where cpf_c =?";
	private final String ALTERARTEL = "update cliente set numerotel =? where cpf_c =?";
	private final String COMPRAR = "insert into compras (cpf_c, isbn_c, nome_c, autor_c, preco_c, genero_c) values (?, ?, ?, ?, ?, ?)";
	private final String MINHASCOMPRAS = "select isbn_c, nome_c, autor_c, preco_c, genero_c, volume_c from compras where cpf_c =? ";

	public ClienteDAO() {

		minhaConexao = new Conexao();
	}

	public ArrayList<Cliente> relatorio() {
		ArrayList<Cliente> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				Cliente l = new Cliente(rs.getString("cpf_c"), rs.getString("numerotel"), rs.getString("nome"));
				lista.add(l);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public void inclusao(Cliente c) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setString(1, c.getCpf());
			instrucao.setString(2, c.getNúmeroTel());
			instrucao.setString(3, c.getNome());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}
	}

	public Cliente buscar(Cliente c) {

		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setString(1, c.getCpf());
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				c = new Cliente(rs.getString("cpf_c"), rs.getString("numeroTel"), rs.getString("nome"));

			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro na busca: " + e.getMessage());
		}
		return c;
	}

	public void exclusao(String cpf_c) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(EXCLUIR);
			instrucao.setString(1, cpf_c);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

	public void alterarNome(String nome, String cpf) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERARNOME);
			instrucao.setString(1, nome);
			instrucao.setString(2, cpf);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na alteração: " + e.getMessage());
		}
	}

	public void alterarTelefone(String telefone, String cpf) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ALTERARTEL);
			instrucao.setString(1, telefone);
			instrucao.setString(2, cpf);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na alteração: " + e.getMessage());
		}
	}

	public void comprar(Livro livro, String cpf) {

		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(COMPRAR);
			instrucao.setString(1, cpf);
			instrucao.setInt(2, livro.getIsbn());
			instrucao.setString(3, livro.getNome());
			instrucao.setString(4, livro.getAutor());
			instrucao.setFloat(5, livro.getPreco());
			instrucao.setString(6, livro.getGenero());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclusão: " + e.getMessage());
		}

	}

	public ArrayList<Livro> minhasCompras(String cpf) {
		ArrayList<Livro> lista = new ArrayList<>();
		Livro l = null;
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(MINHASCOMPRAS);
			instrucao.setString(1, cpf);
			ResultSet rs = instrucao.executeQuery();
			while (rs.next()) {
				l = new Livro(rs.getString("nome_c"), rs.getString("autor_c"), rs.getFloat("preco_c"),
						rs.getString("genero_c"), rs.getInt("isbn_c"), rs.getInt("volume_c"));
				lista.add(l);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro na solicitação: " + e.getMessage());
		}
		return lista;

	}
}
