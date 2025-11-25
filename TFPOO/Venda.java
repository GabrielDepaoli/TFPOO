package TFPOO;

import java.time.LocalDateTime;

public class Venda {
    private Cliente cliente;
    private Voo voo;
    private LocalDateTime data;

    public Venda(Cliente cliente, Voo voo){
        this.cliente = cliente;
        this.voo = voo;
        this.data = LocalDateTime.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Voo getVoo() {
        return voo;
    }

    public LocalDateTime getData() {
        return data;
    }
}
