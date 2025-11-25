package TFPOO;

import java.util.*;
import java.io.*;

public class Empresa {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aviao> avioes = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();
    private Set<String> destinos = new HashSet<>();
    private Map<Integer, Voo> mapaVoos = new HashMap<>();


    
    public void exibirMenu(){
        System.out.println("==== BLINK AIRLINES ====");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Avião");
        System.out.println("3 - Cadastrar Vo0");
        System.out.println("4 - Vender Passagem");
        System.out.println("5 - Gerar Relatório do Sistema");
        System.out.println("====== Digite 0 para Sair ======");
    }
 
    public void cadastrarCliente(String nome, int rg, int telefone){
        boolean existe = clientes.stream().anyMatch(c -> c.getRg() == rg);

        if(existe){
            System.out.println("Cliente já existe.");
            return;
        }

        clientes.add(new Cliente(nome, rg, telefone));
        System.out.println("Cliente cadastrado!");
    }

    public void cadastrarAviao(int codigo, String nome, int assentos){
        if(assentos <= 0){
            System.out.println("Quantidade de assentos inválida.");
            return;
        }

        avioes.add(new Aviao(codigo, nome, assentos));
        System.out.println("Avião cadastrado!");
    }

    public void cadastrarVoo(int codigo, String origem, String destino, String horario, int codAviao, Periodo periodo){
        Aviao av = avioes.stream()
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


    public void venderPassagem(int rgCliente, int codVoo){
        Cliente cli = clientes.stream()
                        .filter(c -> c.getRg() == rgCliente)
                        .findFirst()
                        .orElse(null);

        Voo voo = voos.stream()
                        .filter(v -> v.getCodigo() == codVoo)
                        .findFirst()
                        .orElse(null);

        if(cli == null || voo == null){
            System.out.println("Cliente ou voo não encontrado.");
            return;
        }

        if(voo.getAviao().getAssentos() <= 0){
            System.out.println("Sem assentos disponíveis.");
            return;
        }

        voo.getAviao().diminuirAssento();
        vendas.add(new Venda(cli, voo));
        salvarVendaArquivo(new Venda(cli, voo));


        System.out.println("Passagem vendida!");
    }


    public void relatorioPorCliente(int rg){
        vendas.stream()
              .filter(v -> v.getCliente().getRg() == rg)
              .forEach(v -> {
                  System.out.println("- Voo: " + v.getVoo().getCodigo() 
                      + " | Origem: " + v.getVoo().getOrigem()
                      + " | Destino: " + v.getVoo().getDestino());
              });
    }

    public void relatorioVoosPorOrigem(String origem){
        voos.stream()
            .filter(v -> v.getOrigem().equalsIgnoreCase(origem))
            .forEach(v -> System.out.println("- Voo " + v.getCodigo() + " para " + v.getDestino()));
    }

    private void salvarVendaArquivo(Venda venda){
        try {
            FileWriter fw = new FileWriter("vendas.txt", true);
            fw.write("Cliente: " + venda.getCliente().getNome()
                    + "  Voo: " + venda.getVoo().getCodigo()
                    + "  Destino: " + venda.getVoo().getDestino()
                    + "  Data: " + venda.getData().toString()
                    + "\n");
            fw.close();
        } catch (IOException e){
            System.out.println("Erro ao salvar venda no arquivo.");
        }
    }

}
