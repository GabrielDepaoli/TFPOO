package TFPOO;

import java.util.*;
import java.io.*;

public class Empresa implements Cadastro<Cliente> {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aviao> avioes = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();
    private Set<String> destinos = new HashSet<>();
    private Map<Integer, Voo> mapaVoos = new HashMap<>();

    
    public void adicionar(Cliente c) {
        clientes.add(c);
    }

    public void listar() {
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
    
    // exibir menu da empresa 
    public void exibirMenu(){
        System.out.println("\n==== BLINK AIRLINES ====");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Avião");
        System.out.println("3 - Cadastrar Vo0");
        System.out.println("4 - Vender Passagem");
        System.out.println("5 - Gerar Relatório do Sistema");
        System.out.println("====== Digite 0 para Sair ======");
    }
    
    // cadastrar cliente com verificação de RG único
    public void cadastrarCliente(String nome, String rg, String telefone){
        boolean existe = clientes.stream().anyMatch(c -> c.getRg() == rg); // verificar existência

        if(existe){
            System.out.println("Cliente já existe.");
            return;
        }

        clientes.add(new Cliente(nome, rg, telefone));
        System.out.println("Cliente cadastrado!");
    }

    public void cadastrarAviao(int codigo, String nome, int assentos){
        if(assentos <= 0){ // verificar assentos 
            System.out.println("Quantidade de assentos inválida.");
            return;
        }

        avioes.add(new Aviao(codigo, nome, assentos));
        System.out.println("Avião cadastrado!");
    }

    // cadastrar voo usando código do avião
    public void cadastrarVoo(int codigo, String origem, String destino, String horario, int codAviao, Periodo periodo){
        Aviao av = avioes.stream() // buscar avião pelo código
                        .filter(a -> a.getCodigo() == codAviao)
                        .findFirst()
                        .orElse(null);

        if(av == null){
            System.out.println("Avião não encontrado.");
            return;
        }

        Voo novo = new Voo(codigo, origem, destino, horario, av, periodo);

        voos.add(novo);
        destinos.add(destino);        
        mapaVoos.put(codigo, novo);   

        System.out.println("Voo cadastrado!");
    }

    // vender passagem para cliente e voo específicos
    public void venderPassagem(String rgCliente, int codVoo) {

        Cliente cli = clientes.stream() // buscar cliente
                .filter(c -> c.getRg().trim().equalsIgnoreCase(rgCliente.trim()))
                .findFirst()
                .orElse(null);

        Voo voo = voos.stream() // Buscar voo
                .filter(v -> v.getCodigo() == codVoo)
                .findFirst()
                .orElse(null);

        if (cli == null || voo == null) {
            System.out.println("Cliente ou voo não encontrado.");
            return;
        }

        long vendidos = vendas.stream() // Contar assentos já vendidos
                .filter(v -> v.getVoo().getCodigo() == codVoo)
                .count();

        int assentosRestantes = voo.getAviao().getAssentos() - (int) vendidos;

        if (assentosRestantes <= 0) {
            System.out.println("Voo lotado. Não é possível vender mais passagens.");
            return;
        }

        Venda venda = new Venda(cli, voo); // Registrar venda
        vendas.add(venda);

        System.out.println("Passagem vendida!");
    }

    // gerar relatório de vendas por cliente com RG específico
    public void relatorioPorCliente(String rg){
        vendas.stream()
              .filter(v -> v.getCliente().getRg() == rg)
              .forEach(v -> {
                  System.out.println("- Voo: " + v.getVoo().getCodigo() 
                      + " | Origem: " + v.getVoo().getOrigem()
                      + " | Destino: " + v.getVoo().getDestino());
              });
    }
    // gerar relatório de voos por origem
    public void relatorioVoosPorOrigem(String origem){
        voos.stream()
            .filter(v -> v.getOrigem().equalsIgnoreCase(origem))
            .forEach(v -> System.out.println("- Voo " + v.getCodigo() + " para " + v.getDestino()));
    }

    // getters para listas privadas
    public List<Cliente> getClientes() {
    return clientes;
    }

    public List<Aviao> getAvioes() {
        return avioes;
    }

    public List<Voo> getVoos() {
        return voos;
    }

    public List<Venda> getVendas() {
        return vendas;
    }


}
