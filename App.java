package TFPOO;
import TFPOO.Empresa;

import java.util.*;

public class App {

    public static void main(String Args[]){
        Scanner tec = new Scanner(System.in);
        Empresa op = new Empresa();
        int esc;

        do {
            op.exibirMenu();
            System.out.println("Escolha uma opção");
            esc = tec.nextInt();
            tec.nextLine();

            switch (esc){
                case 1:
                    // cadastrar cliente
                    break;

                case 2:
                    // cadastrar avião
                    break;

                case 3:
                    // cadastrar voo
                    break;

                case 4:
                    // vender passagem
                    break;

                case 5:
                    // gerar relatório
                    break;

                default:
                    System.out.println("Opção Inválida!");
            }

        } while(esc != 0);
            System.out.println("Encerrando Sistema");


    }
    
}
