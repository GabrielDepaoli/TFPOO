package TFPOO;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner tec = new Scanner(System.in);
        Empresa op = new Empresa();

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
                    System.out.print("Nome: ");
                    String nome = tec.nextLine();
                    System.out.print("RG: ");
                    int rg = tec.nextInt();
                    System.out.print("Telefone: ");
                    int tel = tec.nextInt();
                    op.cadastrarCliente(nome, rg, tel);
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
                    System.out.println("Período do voo (1-Manhã / 2-Tarde / 3-Noite): ");
                    int p = tec.nextInt();
                    Periodo per;
                    if(p == 1) per = Periodo.MANHA;
                    else if(p == 2) per = Periodo.TARDE;
                    else per = Periodo.NOITE;
                    op.cadastrarVoo(codV, ori, des, hor, codAv, per);
                    break;

                case 4:
                    System.out.print("RG do cliente: ");
                    int rgc = tec.nextInt();
                    System.out.print("Código do voo: ");
                    int cv = tec.nextInt();
                    op.venderPassagem(rgc, cv);
                    break;

                case 5:
                    System.out.print("RG do cliente para relatório: ");
                    int rgr = tec.nextInt();
                    op.relatorioPorCliente(rgr);
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
