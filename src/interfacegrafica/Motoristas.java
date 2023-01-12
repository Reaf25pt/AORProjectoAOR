package interfacegrafica;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe Interface grafica, para proceder adesao, remocao e edicao dos motoristas por um administrador
 */
public class Motoristas extends JPanel implements ActionListener {

    private final PainelFundo painelFundo;
    private final Aor_Autocarro aor_autocarro;
    private JScrollPane sp;


    private JTable tabela;
    private final JLabel adminNome;
    private final TextField nomeField;

    private final TextField emailField;
    private final JTextField removerField;

    private final JTextField emailEditarField;

    private final JTextField nomeeditarField;


    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public Motoristas(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
        this.aor_autocarro = aor_autocarro;
        this.painelFundo = painelFundo;
        this.setLayout(null);

        //===================================================
        //Painel do Cabeçalho
        JPanel cabecalho = new JPanel();
        cabecalho.setLayout(null);
        cabecalho.setBounds(0, 0, 900, 30);

        //Label do nome da empresa
        JLabel empresaNome = new JLabel("Aor-Autocarros");
        empresaNome.setBounds(5, 0, 100, 30);
        cabecalho.add(empresaNome);

        // Nome do cliente
        adminNome = new JLabel();
        adminNome.setBounds(700, 0, 100, 30);
        cabecalho.add(adminNome);

        // Botao para sair para o login
        JButton sairBotao = new JButton("Sair");
        sairBotao.setBounds(810, 1, 70, 28);
        cabecalho.add(sairBotao);
        this.add(cabecalho);


        //===========================================================
        //Painel de escolhas do cliente
        JPanel opcaoPainel = new JPanel();
        opcaoPainel.setLayout(new GridLayout(1, 5, 15, 0));
        opcaoPainel.setBounds(0, 35, 900, 50);
        opcaoPainel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JButton opcao1 = new JButton("Adminstradores");
        JButton opcao2 = new JButton("Motoristas");
        JButton opcao3 = new JButton("Autocarros");
        JButton opcao4 = new JButton("Clientes");
        JButton opcao5 = new JButton("Estatistica");
        JButton opcao6 = new JButton("Dados Pessoais");

        opcaoPainel.add(opcao1);
        opcaoPainel.add(opcao2);
        opcaoPainel.add(opcao3);
        opcaoPainel.add(opcao4);
        opcaoPainel.add(opcao5);
        opcaoPainel.add(opcao6);

        this.add(opcaoPainel);

        //=====================================================================
        //Segundo titulo
        JLabel segundoTitulo = new JLabel("Motoristas ");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        //Painel do Login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(10, 150, 300, 100);

        //Label do nomeLabel
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(0, 0, 80, 30);

        //Label da palavra chave
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(0, 50, 80, 30);

        // Textofield do nomeLabel
        nomeField = new TextField();
        nomeField.setBounds(100, 0, 170, 30);

        emailField = new TextField();
        emailField.setBounds(100, 50, 170, 30);
        ;
        // Adicionar componentes ao painel
        loginPanel.add(nomeLabel);
        loginPanel.add(emailLabel);
        loginPanel.add(nomeField);
        loginPanel.add(emailField);
        this.add(loginPanel);


        JPanel removerPanel = new JPanel();
        removerPanel.setLayout(null);
        removerPanel.setBounds(10, 300, 300, 100);
        JLabel removerLabel = new JLabel("Email:");
        removerLabel.setBounds(0, 0, 80, 30);
        removerField = new JTextField();
        removerField.setBounds(100, 0, 170, 30);
        removerPanel.add(removerLabel);
        removerPanel.add(removerField);
        this.add(removerPanel);


        JPanel editarPanel = new JPanel();
        editarPanel.setLayout(null);
        editarPanel.setBounds(10, 450, 300, 100);
        JLabel emailEditarLabel = new JLabel("Email:");
        emailEditarLabel.setBounds(0, 0, 80, 30);
        emailEditarField = new JTextField("Altere o nome associado ao email");
        emailEditarField.setBounds(100, 0, 170, 30);
        JLabel nomeeditarLabel = new JLabel("Nome:");
        nomeeditarLabel.setBounds(0, 50, 80, 30);
        nomeeditarField = new JTextField();
        nomeeditarField.setBounds(100, 50, 170, 30);
        editarPanel.add(emailEditarLabel);
        editarPanel.add(emailEditarField);
        editarPanel.add(nomeeditarLabel);
        editarPanel.add(nomeeditarField);
        this.add(editarPanel);


        //==========================================
        // Painel botoes


        JButton adicionarButton = new JButton("Adicionar");
        adicionarButton.setBounds(350, 200, 100, 30);
        JButton removerButton = new JButton("Remover");
        removerButton.setBounds(350, 300, 100, 30);
        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(350, 450, 100, 30);


        this.add(adicionarButton);
        this.add(removerButton);
        this.add(editarButton);

        //========================================
        // Tabela


        String[] colunas = {"Nome", "Email"};
        String[][] data = new String[aor_autocarro.getMotoristas().size()][2];

        for (int i = 0; i < aor_autocarro.getMotoristas().size(); i++) {
            data[i][0] = aor_autocarro.getMotoristas().get(i).getNome();
            data[i][1] = aor_autocarro.getMotoristas().get(i).getEmail();

        }

        tabela = new JTable(data, colunas);
        sp = new JScrollPane(tabela);
        sp.setBounds(550, 150, 300, 400);
        this.add(sp);


        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        opcao6.addActionListener(this);
        sairBotao.addActionListener(this);
        adicionarButton.addActionListener(this);
        removerButton.addActionListener(this);
        editarButton.addActionListener(this);


    }

    /** Atualizar a tabela automaticamente quando houver alguma alteracao
     *
     */
//========================================
    //Metodo para atualizar tabela
    public void atualizar() {
        FicheiroDeObjectos.escreveObjeto(aor_autocarro);
        String[] colunas = {"Nome", "Email"};
        String data[][] = new String[aor_autocarro.getMotoristas().size()][2];
        this.remove(sp);
        for (int i = 0; i < aor_autocarro.getMotoristas().size(); i++) {
            data[i][0] = aor_autocarro.getMotoristas().get(i).getNome();
            data[i][1] = aor_autocarro.getMotoristas().get(i).getEmail();

        }
        tabela = new JTable(data, colunas);
        sp = new JScrollPane(tabela);
        sp.setBounds(550, 150, 300, 400);
        this.add(sp);
        revalidate();
        repaint();
    }


    //=======================================================

    /**Atualiza JLabel nomeLabel = new JLabel("Nome:") com nome do utilizador logado
     *
     */
    public void nomeLogado() {


        if (aor_autocarro.getUserLogado() == null) {
            adminNome.setText("");
        } else
            adminNome.setText(aor_autocarro.getUserLogado().getNome());
        revalidate();
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Adminstradores")) {
            painelFundo.mudaEcra("RegistarNovoAdministrador");
        }

        if (e.getActionCommand().equals("Motoristas")) {
            painelFundo.mudaEcra("Motoristas");
        }

        if (e.getActionCommand().equals("Autocarros")) {
            painelFundo.mudaEcra("Autocarros");
        }

        if (e.getActionCommand().equals("Clientes")) {
            painelFundo.mudaEcra("AdicionarClientes");
        }
        if (e.getActionCommand().equals("Estatistica")) {
            ((Estatistica) (painelFundo.mapaPaineis.get("Estatistica"))).nomeLogado();
            painelFundo.mudaEcra("Estatistica");
        }
        if (e.getActionCommand().equals("Dados Pessoais")) {
            ((DadosPessoaisAdmin) (painelFundo.mapaPaineis.get("DadosPessoaisAdmin"))).nomeLogado();
            painelFundo.mudaEcra("DadosPessoaisAdmin");
        }

        if (e.getActionCommand().equals("Sair")) {
            ((Login) painelFundo.mapaPaineis.get("Login")).sair();
            painelFundo.mudaEcra("Login");
        }

        if (e.getActionCommand().equals("Adicionar")) {
            if ((Utilizador.validarNome(nomeField.getText())) &&
                    (Utilizador.validarEmail(emailField.getText())) &&
                    (!aor_autocarro.verificarDuplicaçãoEmailMotorista(emailField.getText()))) {
                aor_autocarro.getMotoristas().add(new Motorista(nomeField.getText(), emailField.getText()));
                JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");
                atualizar();
                emailField.setText("");
                nomeField.setText("");

            } else if (aor_autocarro.verificarDuplicaçãoEmailMotorista(emailField.getText())) {
                JOptionPane.showMessageDialog(null, "Email duplicado!");

            } else if (!Utilizador.validarNome(nomeField.getText())) {
                JOptionPane.showMessageDialog(null, "Nome com carateres inválidos");
            } else if (!Utilizador.validarEmail(emailField.getText())) {
                JOptionPane.showMessageDialog(null, "Email inválido");
            }
        }

        if (e.getActionCommand().equals("Remover")) {
            if (aor_autocarro.removerMotorista(removerField.getText()) == null) {
                JOptionPane.showMessageDialog(null, "Não existe nenhum motorista");
                removerField.setText("");
            } else {
                try {
                    Motorista removido = aor_autocarro.removerMotorista(removerField.getText());
                    JOptionPane.showMessageDialog(null, "Removido com sucesso!");
                    aor_autocarro.getMotoristas().remove(removido);
                    aor_autocarro.cancelarReservasporMotorista(emailField.getText());
                    atualizar();
                    removerField.setText("");
                } catch (NullPointerException n) {
                    JOptionPane.showMessageDialog(null, "Não existe nenhum motorista com esse email");
                }
                //FicheiroDeObjectos.escreveObjeto(aor_autocarro);

            }


        }
        if (e.getActionCommand().equals("Editar")) {
            if (aor_autocarro.getMotorista(emailEditarField.getText()).getEmail().equals(emailEditarField.getText())) {
                aor_autocarro.getMotorista(emailEditarField.getText()).setNome(nomeeditarField.getText());
                JOptionPane.showMessageDialog(null, "Editado com sucesso!");
                atualizar();
                emailEditarField.setText("");
                nomeeditarField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Não existe motorista com esse email");

            }


        }


    }
}

