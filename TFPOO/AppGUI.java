package TFPOO;

import javax.swing.*;
import java.awt.*;
import java.io.*;
// classe AppGUI com interface gráfica para o sistema de vendas aéreas
public class AppGUI extends JFrame {

    private final Empresa empresa = new Empresa();
    private final GerenciadorArquivos arquivos = new GerenciadorArquivos();

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    // construtor da classe AppGUI
    public AppGUI() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Blink Airlines - Sistema de Passagens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 580);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        carregarDadosIniciais();

        add(criarHeader(), BorderLayout.NORTH);
        add(criarSidebar(), BorderLayout.WEST);
        add(criarMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private void carregarDadosIniciais() {
        arquivos.carregarClientes(empresa);
        arquivos.carregarAvioes(empresa);
        arquivos.carregarVoos(empresa);
        arquivos.carregarVendas(empresa);
    }
    // criar header da aplicação com título
    private JPanel criarHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 50));
        header.setBackground(new Color(20, 22, 36));

        JLabel titulo = new JLabel("BLINK AIRLINES — Sistema de Vendas Aéreas");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        header.add(titulo, BorderLayout.WEST);
        return header;
    }
    // criar sidebar com botões de navegação
    private JPanel criarSidebar() {
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setPreferredSize(new Dimension(220, 0));
        sideBar.setBackground(new Color(18, 19, 30));

        JLabel logo = new JLabel("BLINK AIRLINES", SwingConstants.CENTER);
        logo.setForeground(new Color(0, 180, 255));
        logo.setFont(new Font("SansSerif", Font.BOLD, 18));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton btn1 = menuButton("Dashboard");
        JButton btn2 = menuButton("Clientes");
        JButton btn3 = menuButton("Aviões");
        JButton btn4 = menuButton("Voos");
        JButton btn5 = menuButton("Vendas");
        JButton btn6 = menuButton("Relatórios");
        JButton btnSair = menuButton("Sair");

        btn1.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));
        btn2.addActionListener(e -> cardLayout.show(mainPanel, "CLIENTES"));
        btn3.addActionListener(e -> cardLayout.show(mainPanel, "AVIOES"));
        btn4.addActionListener(e -> cardLayout.show(mainPanel, "VOOS"));
        btn5.addActionListener(e -> cardLayout.show(mainPanel, "VENDAS"));
        btn6.addActionListener(e -> cardLayout.show(mainPanel, "REL"));
        btnSair.addActionListener(e -> System.exit(0));

        sideBar.add(logo);
        sideBar.add(btn1);
        sideBar.add(btn2);
        sideBar.add(btn3);
        sideBar.add(btn4);
        sideBar.add(btn5);
        sideBar.add(btn6);
        sideBar.add(Box.createVerticalGlue());
        sideBar.add(btnSair);
        sideBar.add(Box.createVerticalStrut(15));

        return sideBar;
    }

    private JButton menuButton(String txt) {
        JButton btn = new JButton(txt);
        btn.setMaximumSize(new Dimension(190, 38));
        btn.setBackground(new Color(34, 35, 52));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    private JPanel criarMainPanel() {

        mainPanel.add(criarTelaHome(), "HOME");
        mainPanel.add(criarTelaClientes(), "CLIENTES");
        mainPanel.add(criarTelaAvioes(), "AVIOES");
        mainPanel.add(criarTelaVoos(), "VOOS");
        mainPanel.add(criarTelaVendas(), "VENDAS");
        mainPanel.add(criarTelaRelatorios(), "REL");

        return mainPanel;
    }

    private JPanel base(String titulo) {
        JPanel p = new JPanel(null);
        p.setBackground(new Color(245, 246, 250));

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("SansSerif", Font.BOLD, 20));
        t.setBounds(20, 10, 500, 35);

        p.add(t);
        return p;
    }

    // parte inicial / home
    private JPanel criarTelaHome() {
        JPanel panel = base("Dashboard");

        JLabel texto = new JLabel(
            "<html><h2>Bem-vindo ao sistema da Blink Airlines</h2>"
            + "<p>Utilize o menu ao lado para gerenciar:</p>"
            + "<ul>"
            + "<li>Clientes</li>"
            + "<li>Aviões</li>"
            + "<li>Voos</li>"
            + "<li>Vendas de passagens</li>"
            + "<li>Relatórios (via arquivos TXT)</li>"
            + "</ul></html>"
        );
        texto.setFont(new Font("SansSerif", Font.PLAIN, 15));
        texto.setBounds(30, 60, 700, 300);

        panel.add(texto);
        return panel;
    }

    // clientes 
    private JPanel criarTelaClientes() {
        JPanel p = base("Cadastro de Clientes");

        JLabel l1 = new JLabel("Nome:");
        JLabel l2 = new JLabel("RG (7 a 9 dígitos):");
        JLabel l3 = new JLabel("Telefone (8 a 11 dígitos):");

        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();

        JButton salvar = new JButton("Salvar");

        l1.setBounds(40, 80, 200, 25);
        t1.setBounds(250, 80, 260, 25);

        l2.setBounds(40, 120, 200, 25);
        t2.setBounds(250, 120, 260, 25);

        l3.setBounds(40, 160, 200, 25);
        t3.setBounds(250, 160, 260, 25);

        salvar.setBounds(250, 210, 160, 35);

        p.add(l1); p.add(t1);
        p.add(l2); p.add(t2);
        p.add(l3); p.add(t3);
        p.add(salvar);

        salvar.addActionListener(e -> {
            String nome = t1.getText().trim();
            String rg   = t2.getText().trim();
            String tel  = t3.getText().trim();

            if (nome.isEmpty() || rg.isEmpty() || tel.isEmpty()) {
                erro("Preencha todos os campos.");
                return;
            }
            if (!rg.matches("\\d{7,9}")) {
                erro("RG deve ter 7 a 9 dígitos numéricos.");
                return;
            }
            if (!tel.matches("\\d{8,11}")) {
                erro("Telefone inválido.");
                return;
            }

            empresa.cadastrarCliente(nome, rg, tel);
            arquivos.salvarClientes(empresa.getClientes());

            info("Cliente cadastrado!");
            t1.setText(""); t2.setText(""); t3.setText("");
        });

        return p;
    }

    //  AVIÕES 
    private JPanel criarTelaAvioes() {
        JPanel p = base("Cadastro de Aviões");

        JLabel l1 = new JLabel("Código do Avião:");
        JLabel l2 = new JLabel("Nome:");
        JLabel l3 = new JLabel("Assentos (>0):");

        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();

        JButton salvar = new JButton("Salvar");

        l1.setBounds(40, 80, 200, 25);
        t1.setBounds(250, 80, 260, 25);

        l2.setBounds(40, 120, 200, 25);
        t2.setBounds(250, 120, 260, 25);

        l3.setBounds(40, 160, 200, 25);
        t3.setBounds(250, 160, 260, 25);

        salvar.setBounds(250, 210, 160, 35);

        p.add(l1); p.add(t1);
        p.add(l2); p.add(t2);
        p.add(l3); p.add(t3);
        p.add(salvar);

        salvar.addActionListener(e -> {
            String cod = t1.getText().trim();
            String nome= t2.getText().trim();
            String ass = t3.getText().trim();

            if (!cod.matches("\\d+")) { erro("Código inválido."); return; }
            if (!ass.matches("\\d+")) { erro("Assentos deve ser inteiro."); return; }

            int c = Integer.parseInt(cod);
            int a = Integer.parseInt(ass);

            if (a <= 0) { erro("Assentos deve ser > 0."); return; }

            empresa.cadastrarAviao(c, nome, a);
            arquivos.salvarAvioes(empresa.getAvioes());
            info("Avião cadastrado!");

            t1.setText(""); t2.setText(""); t3.setText("");
        });

        return p;
    }

    //  VOOS 
    private JPanel criarTelaVoos() {
        JPanel p = base("Cadastro de Voos");

        JLabel l1 = new JLabel("Código Voo:");
        JLabel l2 = new JLabel("Origem:");
        JLabel l3 = new JLabel("Destino:");
        JLabel l4 = new JLabel("Horário:");
        JLabel l5 = new JLabel("Código Avião:");
        JLabel l6 = new JLabel("Período:");

        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JTextField t5 = new JTextField();

        JComboBox<Periodo> cbPeriodo = new JComboBox<>(Periodo.values());

        JButton salvar = new JButton("Salvar");

        l1.setBounds(40, 60, 200, 25); t1.setBounds(250, 60, 260, 25);
        l2.setBounds(40, 100, 200, 25); t2.setBounds(250, 100, 260, 25);
        l3.setBounds(40, 140, 200, 25); t3.setBounds(250, 140, 260, 25);
        l4.setBounds(40, 180, 200, 25); t4.setBounds(250, 180, 260, 25);
        l5.setBounds(40, 220, 200, 25); t5.setBounds(250, 220, 260, 25);

        l6.setBounds(40, 260, 200, 25);
        cbPeriodo.setBounds(250, 260, 260, 25);

        salvar.setBounds(250, 310, 150, 35);

        p.add(l1); p.add(t1);
        p.add(l2); p.add(t2);
        p.add(l3); p.add(t3);
        p.add(l4); p.add(t4);
        p.add(l5); p.add(t5);
        p.add(l6); p.add(cbPeriodo);
        p.add(salvar);

        salvar.addActionListener(e -> {
            if (!t1.getText().matches("\\d+")) { erro("Código inválido."); return; }
            if (!t5.getText().matches("\\d+")) { erro("Código do avião inválido."); return; }

            int cod = Integer.parseInt(t1.getText());
            int codA = Integer.parseInt(t5.getText());

            empresa.cadastrarVoo(
                cod,
                t2.getText(),
                t3.getText(),
                t4.getText(),
                codA,
                (Periodo) cbPeriodo.getSelectedItem()
            );

            arquivos.salvarVoos(empresa.getVoos());
            info("Voo cadastrado!");

            t1.setText(""); t2.setText(""); t3.setText("");
            t4.setText(""); t5.setText("");
            cbPeriodo.setSelectedIndex(0);
        });

        return p;
    }

    //  VENDAS 
    private JPanel criarTelaVendas() {
        JPanel p = base("Venda de Passagens");

        JLabel l1 = new JLabel("RG do Cliente:");
        JLabel l2 = new JLabel("Código do Voo:");

        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();

        JButton vender = new JButton("Confirmar Venda");

        l1.setBounds(40, 80, 200, 25);
        t1.setBounds(250, 80, 260, 25);

        l2.setBounds(40, 120, 200, 25);
        t2.setBounds(250, 120, 260, 25);

        vender.setBounds(250, 170, 180, 35);

        p.add(l1); p.add(t1);
        p.add(l2); p.add(t2);
        p.add(vender);

        vender.addActionListener(e -> {

            if (!t1.getText().matches("\\d{7,9}")) { erro("RG inválido."); return; }
            if (!t2.getText().matches("\\d+")) { erro("Código inválido."); return; }

            String rg = t1.getText();
            int cod  = Integer.parseInt(t2.getText());

            empresa.venderPassagem(rg, cod);
            arquivos.salvarVendas(empresa.getVendas());

            info("Venda realizada!");
            t1.setText("");
            t2.setText("");
        });

        return p;
    }

    //  RELATÓRIOS 
    private JPanel criarTelaRelatorios() {
        JPanel p = base("Relatórios (arquivos TXT)");

        JLabel lbl = new JLabel("Selecione o arquivo:");
        JComboBox<String> cb = new JComboBox<>(
                new String[]{"clientes.txt", "avioes.txt", "voos.txt", "vendas.txt"}
        );

        JButton carregar = new JButton("Carregar");
        JButton gerar = new JButton("Gerar relatório de vendas");

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(area);

        lbl.setBounds(30, 80, 150, 25);
        cb.setBounds(180, 80, 200, 25);
        carregar.setBounds(400, 80, 120, 25);
        gerar.setBounds(540, 80, 230, 25);

        scroll.setBounds(30, 130, 750, 330);

        p.add(lbl); p.add(cb); p.add(carregar); p.add(gerar); p.add(scroll);

        carregar.addActionListener(e -> {
            String nome = (String) cb.getSelectedItem();
            area.setText( lerArquivo("arquivosTXT/" + nome) );
        });

        gerar.addActionListener(e -> {
            arquivos.gerarRelatorioVendas(empresa);
            area.setText( lerArquivo("arquivosTXT/relatorio_vendas.txt") );
            info("Relatório gerado em arquivosTXT/relatorio_vendas.txt");
        });

        return p;
    }

    private String lerArquivo(String caminho) {
        try {
            File f = new File(caminho);
            if (!f.exists()) return "Arquivo não encontrado: " + caminho;

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(f));

            String linha;
            while ((linha = br.readLine()) != null) {
                sb.append(linha).append("\n");
            }
            br.close();
            return sb.toString();

        } catch (Exception e) {
            return "Erro ao ler arquivo: " + e.getMessage();
        }
    }

    private void info(String m) {
        JOptionPane.showMessageDialog(this, m, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    private void erro(String m) {
        JOptionPane.showMessageDialog(this, m, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}
