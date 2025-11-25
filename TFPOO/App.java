package TFPOO;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner tec = new Scanner(System.in);
        
        // 1. Instancia a Empresa (lógica de negócio)
        Empresa op = new Empresa(); 
        
        // 2. Instancia o Gerenciador de Arquivos (persistência)
        GerenciadorArquivos gerenciador = new GerenciadorArquivos();

        // 3. Carrega os dados dos arquivos para dentro da Empresa
        gerenciador.carregarDados(op);

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
                String nome; 
                String rg;
                String telefone;
                // ... validações (código igual ao anterior) ...
                while (true) {
                    try {
                        System.out.print("Nome: ");
                        nome = tec.nextLine();
                        Validador.validarNome(nome);
                        break;
                    } catch (DadoInvalidoException e) { System.out.println("Erro: " + e.getMessage()); }
                }
                while (true) {
                    try {
                        System.out.print("RG: ");
                        rg = tec.nextLine();
                        Validador.validarRg(rg);
                        break;
                    } catch (DadoInvalidoException e) { System.out.println("Erro: " + e.getMessage()); }
                }
                while (true) {
                    try {
                        System.out.print("Telefone: ");
                        telefone = tec.nextLine();
                        Validador.validarTelefone(telefone);
                        break;
                    } catch (DadoInvalidoException e) { System.out.println("Erro: " + e.getMessage()); }
                }
                op.cadastrarCliente(nome, rg, telefone);
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
                    System.out.println("Período (1-Manhã / 2-Tarde / 3-Noite): ");
                    int p = tec.nextInt();
                    Periodo per = (p == 1) ? Periodo.MANHA : (p == 2) ? Periodo.TARDE : Periodo.NOITE;
                    op.cadastrarVoo(codV, ori, des, hor, codAv, per);
                    break;

                case 4:
                    System.out.print("RG do cliente: ");
                    String rgc = tec.nextLine();
                    System.out.print("Código do voo: ");
                    int cv = tec.nextInt();
                    op.venderPassagem(rgc, cv);
                    break;

                case 5:
                    System.out.print("RG do cliente: ");
                    String rgr = tec.nextLine();
                    op.relatorioPorCliente(rgr);
                    break;

                case 0:
                    // 4. Salva tudo antes de sair
                    gerenciador.salvarDados(op);
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while(esc != 0);

        tec.close();
    }
}