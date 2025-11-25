package TFPOO;

public class Voo {
    private int codigo;
    private String origem;
    private String destino;
    private String horario;
    private Aviao aviao;
    private Periodo periodo;


    public Voo(int codigo, String origem, String destino, String horario, Aviao aviao, Periodo periodo){
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.aviao = aviao;
        this.periodo = periodo;
    }


    public int getCodigo() {
        return codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getHorario() {
        return horario;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

}
