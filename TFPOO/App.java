package TFPOO;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner tec = new Scanner(System.in);
        Empresa op = new Empresa();

        GerenciadorArquivos ger = new GerenciadorArquivos();

        ger.carregarClientes(op);
        ger.carregarAvioes(op);
        ger.carregarVoos(op);
        ger.carregarVendas(op);

        int esc;

        do {
            op.exibirMenu();
            System.out.print("Escolha: ");

            try {
                esc = tec.nextInt();
            } catch (Exception e){
                System.out.println("Digite um número válido.");
                tec.nextLine();
                esc = -1;
            }

            tec.nextLine(); 

            switch(esc){

                case 1:
                String nome; // Utilizamos String em todos os dados para realizar a validação utilizando Char
                String rg;
                String telefone;

                while (true) {
                    try {
                        System.out.print("\nNome: ");
                        nome = tec.nextLine();
                        Validador.validarNome(nome);
                        break;

                    } catch (DadoInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                while (true) {
                    try {
                        System.out.print("\nRG: ");
                        rg = tec.nextLine();
                        Validador.validarRg(rg);
                        break;

                    } catch (DadoInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                while (true) {
                    try {
                        System.out.print("\nTelefone (DD999999999): ");
                        telefone = tec.nextLine();
                        Validador.validarTelefone(telefone);
                        break;

                    } catch (DadoInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                op.cadastrarCliente(nome, rg, telefone); // conclui cadastro
                ger.salvarClientes(op.getClientes()); // salva automaticamente
                break;

                case 2:
                    System.out.print("Código: ");
                    int codA = tec.nextInt();
                    tec.nextLine();
                    System.out.print("Nome avião: ");
                    String nomA = tec.nextLine();
                    System.out.print("Assentos: ");
                    int as = tec.nextInt();
                    op.cadastrarAviao(codA, nomA, as);
                    ger.salvarAvioes(op.getAvioes());
                    break;

                case 3:
                    System.out.print("Código do voo: ");
                    int codV = tec.nextInt();
                    tec.nextLine();
                    System.out.print("Origem: ");
                    String ori = tec.nextLine();
                    System.out.print("Destino: ");
                    String des = tec.nextLine();
                    System.out.print("Horário: ");
                    String hor = tec.nextLine();
                    System.out.print("Código do avião: ");
                    int codAv = tec.nextInt();
                    System.out.println("Período do voo (1-Manhã / 2-Tarde / 3-Noite): ");
                    int p = tec.nextInt();
                    Periodo per;
                    if(p == 1) per = Periodo.MANHA;
                    else if(p == 2) per = Periodo.TARDE;
                    else per = Periodo.NOITE;
                    op.cadastrarVoo(codV, ori, des, hor, codAv, per);
                    ger.salvarVoos(op.getVoos());
                    break;

                case 4:
                    System.out.println("\n=== CLIENTES CADASTRADOS ===");
                    if (op.getClientes().isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado."); // lista os clientes cadastrados
                        break;
                    }
                    for (Cliente c : op.getClientes()) {
                        System.out.println("Nome: " + c.getNome() +
                                        " | RG: " + c.getRg() +
                                        " | Telefone: " + c.getTelefone());
                    }

                    System.out.print("RG do cliente: ");
                    String rgc = tec.nextLine();

                    System.out.println("\n=== VOOS CADASTRADOS ==="); // lista os voos cadastrados
                    if (op.getVoos().isEmpty()) {
                        System.out.println("Nenhum voo cadastrado.");
                        break;
                    }
                    for (Voo v : op.getVoos()) {
                        System.out.println(
                            "Código: " + v.getCodigo() +
                            " | " + v.getOrigem() + " -> " + v.getDestino() +
                            " | Horário: " + v.getHorario() +
                            " | Período: " + v.getPeriodo()
                        );
                    }
                    System.out.print("Código do voo: ");
                    int cv = tec.nextInt();
                    op.venderPassagem(rgc, cv);
                    ger.salvarVendas(op.getVendas());
                    break;

                case 5:
                    ger.gerarRelatorioVendas(op);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while(esc != 0);

        tec.close();
    }
}
