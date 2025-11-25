package TFPOO;

public class Cliente extends Pessoa {
    private String rg;
    private String telefone;

    public Cliente(String nome, String rg, String telefone){
        super (nome);
        this.rg = rg;
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }
}
