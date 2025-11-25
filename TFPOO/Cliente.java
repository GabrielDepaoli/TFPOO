package TFPOO;

public class Cliente extends Pessoa {
    private int rg;
    private int telefone;

    public Cliente(String nome, int rg, int telefone){
        super (nome);
        this.rg = rg;
        this.telefone = telefone;
    }

    public int getRg() {
        return rg;
    }

    public int getTelefone() {
        return telefone;
    }
}
