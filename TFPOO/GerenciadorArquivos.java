package TFPOO;

import java.io.*;
import java.util.*;

public class GerenciadorArquivos {

    private static final String ARQ_CLIENTES = "clientes.txt";
    private static final String ARQ_AVIOES = "avioes.txt";
    private static final String ARQ_VOOS = "voos.txt";
    private static final String ARQ_VENDAS = "vendas.txt";

    public void salvarDados(Empresa empresa) {
        System.out.println("Salvando dados no sistema...");
        salvarClientes(empresa.getClientes());
        salvarAvioes(empresa.getAvioes());
        salvarVoos(empresa.getVoos());
    }

    public void carregarDados(Empresa empresa) {
        System.out.println("Carregando dados do sistema...");
        carregarClientes(empresa.getClientes());
        carregarAvioes(empresa.getAvioes());
        carregarVoos(empresa.getVoos(), empresa.getAvioes(), empresa.getMapaVoos(), empresa.getDestinos());
    }

    private void salvarClientes(List<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQ_CLIENTES))) {
            for (Cliente c : clientes) {
                pw.println(c.getNome() + ";" + c.getRg() + ";" + c.getTelefone());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private void carregarClientes(List<Cliente> clientes) {
        File arquivo = new File(ARQ_CLIENTES);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    clientes.add(new Cliente(dados[0], dados[1], dados[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler clientes: " + e.getMessage());
        }
    }

    private void salvarAvioes(List<Aviao> avioes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQ_AVIOES))) {
            for (Aviao a : avioes) {
                pw.println(a.getCodigo() + ";" + a.getNome() + ";" + a.getAssentos());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar aviões: " + e.getMessage());
        }
    }

    private void carregarAvioes(List<Aviao> avioes) {
        File arquivo = new File(ARQ_AVIOES);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    int codigo = Integer.parseInt(dados[0]);
                    int assentos = Integer.parseInt(dados[2]);
                    avioes.add(new Aviao(codigo, dados[1], assentos));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler aviões: " + e.getMessage());
        }
    }

    private void salvarVoos(List<Voo> voos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQ_VOOS))) {
            for (Voo v : voos) {
                pw.println(v.getCodigo() + ";" + v.getOrigem() + ";" + v.getDestino() + ";" 
                         + v.getHorario() + ";" + v.getAviao().getCodigo() + ";" + v.getPeriodo());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar voos: " + e.getMessage());
        }
    }

    private void carregarVoos(List<Voo> voos, List<Aviao> avioes, Map<Integer, Voo> mapaVoos, Set<String> destinos) {
        File arquivo = new File(ARQ_VOOS);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 6) {
                    int codVoo = Integer.parseInt(dados[0]);
                    int codAviao = Integer.parseInt(dados[4]);
                    
                    Aviao aviaoVinculado = avioes.stream()
                            .filter(a -> a.getCodigo() == codAviao)
                            .findFirst()
                            .orElse(null);

                    if (aviaoVinculado != null) {
                        Periodo per = Periodo.valueOf(dados[5]);
                        Voo v = new Voo(codVoo, dados[1], dados[2], dados[3], aviaoVinculado, per);
                        voos.add(v);
                        mapaVoos.put(codVoo, v);
                        destinos.add(dados[2]);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler voos: " + e.getMessage());
        }
    }

    public static void registrarVenda(Venda venda) {
        try (FileWriter fw = new FileWriter(ARQ_VENDAS, true)) {
            fw.write("Cliente: " + venda.getCliente().getNome()
                    + "  Voo: " + venda.getVoo().getCodigo()
                    + "  Destino: " + venda.getVoo().getDestino()
                    + "  Data: " + venda.getData().toString()
                    + "\n");
        } catch (IOException e){
            System.out.println("Erro ao salvar venda no log.");
        }
    }
}