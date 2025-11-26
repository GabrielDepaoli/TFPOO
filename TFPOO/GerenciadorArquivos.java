package TFPOO;

import java.io.*;
import java.util.List;

public class GerenciadorArquivos {

    private final String pasta = "TFPOO/arquivosTXT";

    public GerenciadorArquivos() {
        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    // ============================
    // SALVAR CLIENTES
    // ============================
    public void salvarClientes(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(pasta + "/clientes.txt"))) {

            for (Cliente c : clientes) {
                bw.write(c.getRg() + ";" + c.getNome() + ";" + c.getTelefone());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes.");
        }
    }

    // ============================
    // SALVAR AVIÕES
    // ============================
    public void salvarAvioes(List<Aviao> avioes) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(pasta + "/avioes.txt"))) {

            for (Aviao a : avioes) {
                bw.write(a.getCodigo() + ";" + a.getNome() + ";" + a.getAssentos());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar aviões.");
        }
    }

    // ============================
    // SALVAR VOOS
    // ============================
    public void salvarVoos(List<Voo> voos) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(pasta + "/voos.txt"))) {

            for (Voo v : voos) {
                bw.write(
                    v.getCodigo() + ";" +
                    v.getOrigem() + ";" +
                    v.getDestino() + ";" +
                    v.getHorario() + ";" +
                    v.getAviao().getCodigo() + ";" +
                    v.getPeriodo().toString()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar voos.");
        }
    }

    // ============================
    // SALVAR VENDAS
    // ============================
    public void salvarVendas(List<Venda> vendas) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(pasta + "/vendas.txt"))) {

            for (Venda v : vendas) {
                bw.write(
                    v.getCliente().getRg() + ";" +
                    v.getVoo().getCodigo()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar vendas.");
        }
    }

    // ============================
    // CARREGAR CLIENTES
    // ============================
    public void carregarClientes(Empresa empresa) {
        empresa.getClientes().clear();

        File arq = new File(pasta + "/clientes.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                empresa.getClientes().add(
                    new Cliente(d[1].trim(), d[0].trim(), d[2].trim())
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes.");
        }
    }

    // ============================
    // CARREGAR AVIÕES
    // ============================
    public void carregarAvioes(Empresa empresa) {
        empresa.getAvioes().clear();

        File arq = new File(pasta + "/avioes.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                int cod = Integer.parseInt(d[0].trim());
                String nome = d[1].trim();
                int assentos = Integer.parseInt(d[2].trim());

                empresa.getAvioes().add(new Aviao(cod, nome, assentos));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar aviões.");
        }
    }

    // ============================
    // CARREGAR VOOS
    // ============================
    public void carregarVoos(Empresa empresa) {
        empresa.getVoos().clear();

        File arq = new File(pasta + "/voos.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                int codigo = Integer.parseInt(d[0].trim());
                String origem = d[1].trim();
                String destino = d[2].trim();
                String horario = d[3].trim();

                int codAviao = Integer.parseInt(d[4].trim());
                Aviao av = empresa.getAvioes().stream()
                        .filter(x -> x.getCodigo() == codAviao)
                        .findFirst()
                        .orElse(null);

                if (av == null) continue;

                Periodo periodo = Periodo.valueOf(d[5].trim().toUpperCase());

                empresa.getVoos().add(new Voo(codigo, origem, destino, horario, av, periodo));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar voos.");
        }
    }

    // ============================
    // CARREGAR VENDAS
    // ============================
    public void carregarVendas(Empresa empresa) {
        empresa.getVendas().clear();

        File arq = new File(pasta + "/vendas.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                String rg = d[0].trim();
                int codVoo = Integer.parseInt(d[1].trim());

                Cliente cli = empresa.getClientes().stream()
                        .filter(c -> c.getRg().equals(rg))
                        .findFirst()
                        .orElse(null);

                Voo voo = empresa.getVoos().stream()
                        .filter(v -> v.getCodigo() == codVoo)
                        .findFirst()
                        .orElse(null);

                if (cli != null && voo != null) {
                    empresa.getVendas().add(new Venda(cli, voo));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar vendas.");
        }
    }

    public void gerarRelatorioVendas(Empresa empresa) {

        File arq = new File(pasta + "/relatorio_vendas.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq))) {

            bw.write("==== RELATORIO DE VENDAS ====");
            bw.newLine();
            bw.newLine();

            if (empresa.getVendas().isEmpty()) {
                bw.write("Não há vendas registradas.");
                bw.newLine();
                return;
            }

            for (Venda v : empresa.getVendas()) {

                Cliente c = v.getCliente();
                Voo voo = v.getVoo();

                bw.write("Cliente: " + c.getNome());
                bw.newLine();
                bw.write("RG: " + c.getRg());
                bw.newLine();
                bw.write("Telefone: " + c.getTelefone());
                bw.newLine();

                bw.write("Voo: " + voo.getCodigo());
                bw.newLine();
                bw.write("Origem: " + voo.getOrigem());
                bw.newLine();
                bw.write("Destino: " + voo.getDestino());
                bw.newLine();
                bw.write("Horario: " + voo.getHorario());
                bw.newLine();
                bw.write("Periodo: " + voo.getPeriodo());
                bw.newLine();

                bw.write("------------------------------------------");
                bw.newLine();
            }

            bw.write("\nTotal de vendas: " + empresa.getVendas().size());
            bw.newLine();

            System.out.println("Relatório gerado com sucesso em arquivosTXT/relatorio_vendas.txt");

        } catch (IOException e) {
            System.out.println("Erro ao gerar relatório de vendas.");
        }
    }

}
