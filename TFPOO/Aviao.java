package TFPOO;
// classe Aviao com atributos, construtor e getters
public class Aviao {
    private int codigo;
    private String nome;
    private int assentos;

    public Aviao(int codigo, String nome, int assentos){
        this.codigo = codigo;
        this.nome = nome;
        this.assentos = assentos;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getAssentos() {
        return assentos;
    }

    public void diminuirAssento() {
        if (assentos > 0) {
            assentos--;
        }
    }
}
