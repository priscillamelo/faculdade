package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Funcionario;

public class FuncionarioDAO {
	private Conexao minhaConexao;
	private final String RELATORIO = "select * from funcionario";
	private final String INCLUIR = "insert into funcionario (cpf, nome) values (?, ?)";
	private final String BUSCAR = "select * from funcionario where id_f =?";
	private final String EXCLUIR = "delete from funcionario where id_f =?";

	public FuncionarioDAO() {

		minhaConexao = new Conexao();
	}

	public ArrayList<Funcionario> relatorio() {
		ArrayList<Funcionario> lista = new ArrayList<>();
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				Funcionario s = new Funcionario(rs.getInt("id_f"), rs.getString("nome"),
						rs.getString("cpf"));
				lista.add(s);
			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}

	public void inclusao(Funcionario f) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(INCLUIR);
			instrucao.setString(1, f.getCpf());
			instrucao.setString(2, f.getNome());
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na inclus�o: " + e.getMessage());
		}
	}

	public int buscar(int id) {
		int s = 0;

		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(BUSCAR);
			instrucao.setInt(1, id);
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				s = rs.getInt("id_f");

			}
			minhaConexao.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro na busca: " + e.getMessage());
		}
		return s;
	}

	public void exclusao(int id_f) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(EXCLUIR);
			instrucao.setInt(1, id_f);
			instrucao.execute();
			minhaConexao.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclus�o: " + e.getMessage());
		}
	}
}
