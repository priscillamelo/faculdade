package Domínio;

public class Livro implements Informacoes {
    private String nome;
    private String autor;
    private float preco;
    private String genero;
    private int isbn;
    private static int volume = 1;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String informarDados() {
        return "Livro{" +
                "nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", preco=" + preco +
                ", genero='" + genero + '\'' +
                ", isbn=" + isbn +
                '}';
    }
}
