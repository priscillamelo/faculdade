package Dominio;

public class Funcionario extends Pessoa implements Informacoes{
	private int id;

	public Funcionario(int id, String nome, String cpf) {
		this.id = id;
		setNome(nome);
		setCpf(cpf);

	}

	public Funcionario() {
		id = 1;
		setCpf("000000000");
		setNome("Fulano");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String informarDados() {
		return "Funcionario{" + "id= " + id + " Nome= " + getNome() + " CPF= " + getCpf() + '}';
	}
}
