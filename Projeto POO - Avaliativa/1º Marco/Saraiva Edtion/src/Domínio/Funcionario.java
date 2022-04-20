package Domínio;

public class Funcionario extends Pessoa implements Informacoes{
    private int id;

    public Funcionario(){
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String informarDados() {
        return "Funcionario {" +
                "id= " + id +
                ", Nome= " + getNome() +
                ", CPF= " + getCpf() +
                '}';
    }
}
