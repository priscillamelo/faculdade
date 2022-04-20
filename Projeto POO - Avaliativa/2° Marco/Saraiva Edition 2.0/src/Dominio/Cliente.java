package Dominio;

import java.util.ArrayList;

public class Cliente extends Pessoa implements Informacoes {
	private String numeroTel;
	private ArrayList<Livro> minhasCompras = new ArrayList<>();

	public Cliente() {

	}

	public Cliente(String cpf, String numeroTel, String nome) {
		setNome(nome);
		setCpf(cpf);
		this.numeroTel = numeroTel;
	}

	public void comprar(Livro livro) {
		minhasCompras.add(livro);

	}

	public ArrayList<Livro> getMinhasCompras() {
		return minhasCompras;
	}

	public String getNúmeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String númeroTel) {
		this.numeroTel = númeroTel;
	}

	@Override
	public String informarDados() {
		return "Cliente{" + " Nome= " + getNome() + " CPF= " + getCpf() + " Telefone= " + numeroTel + '}';
	}
}
