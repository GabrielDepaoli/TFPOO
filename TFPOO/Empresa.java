package TFPOO;

import java.util.*;

public class Empresa implements Cadastro<Cliente> {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Aviao> avioes = new ArrayList<>();
    private List<Voo> voos = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();
    private Set<String> destinos = new HashSet<>();
    private Map<Integer, Voo> mapaVoos = new HashMap<>();
    
    // gets e sets para arquivos
    public List<Cliente> getClientes() { return clientes; }
    public List<Aviao> getAvioes() { return avioes; }
    public List<Voo> getVoos() { return voos; }
    public Map<Integer, Voo> getMapaVoos() { return mapaVoos; }
    public Set<String> getDestinos() { return destinos; }

    @Override
    public void adicionar(Cliente c) {
        clientes.add(c);
    }

    @Override
    public void listar() {
        for (Cliente c : clientes) {
            System.out.println("Nome: " + c.getNome() + " | RG: " + c.getRg());
        }
    }
    
    public void exibirMenu(){
        System.out.println("\n==== BLINK AIRLINES ====");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Avião");
        System.out.println("3 - Cadastrar Voo");
        System.out.println("4 - Vender Passagem");
        System.out.println("5 - Gerar Relatório do Sistema");
        System.out.println("====== Digite 0 para Sair e Salvar ======");
    }
 
    public void cadastrarCliente(String nome, String rg, String telefone){
        boolean existe = clientes.stream().anyMatch(c -> c.getRg().equals(rg)); 

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


    public void venderPassagem(String rgCliente, int codVoo){
        Cliente cli = clientes.stream()
                        .filter(c -> c.getRg().equals(rgCliente))
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
        Venda novaVenda = new Venda(cli, voo);
        vendas.add(novaVenda);
        
        GerenciadorArquivos.registrarVenda(novaVenda);

        System.out.println("Passagem vendida!");
    }


    public void relatorioPorCliente(String rg){
        System.out.println("--- Passagens compradas pelo RG " + rg + " ---");
        vendas.stream()
              .filter(v -> v.getCliente().getRg().equals(rg))
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

}