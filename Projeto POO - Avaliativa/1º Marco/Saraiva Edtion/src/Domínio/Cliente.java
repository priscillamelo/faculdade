package Domínio;

import java.util.ArrayList;

public final class Cliente extends Pessoa implements Informacoes {
    private String numeroTel;
    private ArrayList<Livro> minhasCompras = new ArrayList<>();

    public void comprar(Livro livro) {
        minhasCompras.add(livro);
    }
    
    public ArrayList<Livro> getMinhasCompras() {
        return minhasCompras;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
    
	@Override
    public String informarDados() {
        return "Cliente{" +
                "Nome= " + getNome() +
                ", CPF= " + getCpf() +
                ", Telefone= " + numeroTel +                
                '}';
    }
}
