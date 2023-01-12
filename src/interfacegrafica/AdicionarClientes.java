package interfacegrafica;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  Classe da Interface grafica para proceder adesao, remocao e edicao de Clientes
 */
public class AdicionarClientes extends JPanel implements ActionListener {

    private final Aor_Autocarro aor_autocarro;
    private final PainelFundo painelFundo;
    private final JLabel clienteNome;
    private JScrollPane sp;

    private final JTextField nomeField;
    private final JTextField nifField;
    private final JTextField moradaField;
    private final JTextField telefoneField;
    private final JTextField emailField;
    private final JLabel palavraChaveField;
    private final JTextField nifRemoverField;
    private final JTextField nifEditarField;

    private String nifEditavel;
    private JTable tabela;

    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public AdicionarClientes(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
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
        clienteNome = new JLabel();
        clienteNome.setBounds(700, 0, 100, 30);
        cabecalho.add(clienteNome);

        // Botao para sair para o login
        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(810, 1, 70, 28);
        cabecalho.add(sairButton);
        this.add(cabecalho);

        //===========================================================
        //Painel de escolhas do Admin
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
        JLabel segundoTitulo = new JLabel("Clientes ");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        //======================================================================
        //Formulario

        JPanel formulario = new JPanel();
        formulario.setLayout(null);
        formulario.setBounds(0, 100, 350, 800);


        //Labels
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 50, 200, 30);
        JLabel nifLabel = new JLabel("NIF:");
        nifLabel.setBounds(50, 90, 200, 30);
        JLabel moradaLabel = new JLabel("Morada:");
        moradaLabel.setBounds(50, 130, 200, 30);
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(50, 170, 200, 30);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 210, 200, 30);
        JLabel palavraChaveLabel = new JLabel("Palavra-Chave");
        palavraChaveLabel.setBounds(50, 250, 200, 30);
        JLabel nifRemoverLabel = new JLabel("NIF:");
        nifRemoverLabel.setBounds(50, 425, 200, 30);
        JLabel nifEditarLabel = new JLabel("NIF:");
        nifEditarLabel.setBounds(50, 500, 200, 30);


        //Fields
        nomeField = new JTextField();
        nomeField.setBounds(150, 50, 200, 30);
        nifField = new JTextField();
        nifField.setBounds(150, 90, 200, 30);
        moradaField = new JTextField();
        moradaField.setBounds(150, 130, 200, 30);
        telefoneField = new JTextField();
        telefoneField.setBounds(150, 170, 200, 30);
        emailField = new JTextField();
        emailField.setBounds(150, 210, 200, 30);
        palavraChaveField = new JLabel("AOR2022");
        palavraChaveField.setBounds(150, 250, 200, 30);
        nifRemoverField = new JTextField();
        nifRemoverField.setBounds(150, 425, 200, 30);
        nifEditarField = new JTextField();
        nifEditarField.setBounds(150, 500, 200, 30);

        //Adicionar ao formulario
        formulario.add(nomeLabel);
        formulario.add(nifLabel);
        formulario.add(moradaLabel);
        formulario.add(telefoneLabel);
        formulario.add(emailLabel);
        formulario.add(palavraChaveLabel);
        formulario.add(nifRemoverLabel);
        formulario.add(nifEditarLabel);
        formulario.add(nomeField);
        formulario.add(nifField);
        formulario.add(moradaField);
        formulario.add(telefoneField);
        formulario.add(emailField);
        formulario.add(palavraChaveField);
        formulario.add(nifRemoverField);
        formulario.add(nifEditarField);
        this.add(formulario);

        JButton adicionarButton = new JButton("Adicionar");
        adicionarButton.setBounds(375, 150, 100, 30);


        JButton removerButton = new JButton("Remover");
        removerButton.setBounds(375, 525, 100, 30);


        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(375, 600, 100, 30);

        this.add(adicionarButton);
        this.add(removerButton);
        this.add(editarButton);


        //========================================
        // Tabela

        String[] colunas = {"Id", "Nome", "NIF", "Morada", "Telefone", "Email"};

        ArrayList<String[]> data = new ArrayList<>();
        for (Utilizador utilizador : aor_autocarro.getUtilizadores()) {
            if (utilizador instanceof Cliente) {
                String[] utilizadorInfo = new String[6];
                utilizadorInfo[0] = utilizador.getId();
                utilizadorInfo[1] = utilizador.getNome();
                utilizadorInfo[2] = utilizador.getNif();
                utilizadorInfo[3] = utilizador.getMorada();
                utilizadorInfo[4] = utilizador.getTelefone();
                utilizadorInfo[5] = utilizador.getEmail();
                data.add(utilizadorInfo);
            }

        }
        String[][] dataArray = data.toArray(new String[0][0]);
        tabela = new JTable(dataArray, colunas);
        sp = new JScrollPane(tabela);
        sp.setBounds(500, 150, 375, 400);
        this.add(sp);

        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        opcao6.addActionListener(this);
        sairButton.addActionListener(this);
        adicionarButton.addActionListener(this);
        removerButton.addActionListener(this);
        editarButton.addActionListener(this);


    }


    /**Metodo atualiza a tabela dos clientes, em cada alteracao feita pelo utilizador
     */
    public void atualizar() {
        FicheiroDeObjectos.escreveObjeto(aor_autocarro);
        String[] colunas = {"Id", "Nome", "NIF", "Morada", "Telefone", "Email"};

        ArrayList<String[]> data = new ArrayList<>();

        this.remove(sp);
        for (Utilizador utilizador : aor_autocarro.getUtilizadores()) {
            if (utilizador instanceof Cliente) {
                String[] utilizadorInfo = new String[6];
                utilizadorInfo[0] = utilizador.getId();
                utilizadorInfo[1] = utilizador.getNome();
                utilizadorInfo[2] = utilizador.getNif();
                utilizadorInfo[3] = utilizador.getMorada();
                utilizadorInfo[4] = utilizador.getTelefone();
                utilizadorInfo[5] = utilizador.getEmail();
                data.add(utilizadorInfo);
            }

        }


        String[][] dataArray = data.toArray(new String[0][0]);
        tabela = new JTable(dataArray, colunas);
        sp = new JScrollPane(tabela);
        sp.setBounds(500, 150, 375, 400);
        this.add(sp);
        revalidate();
        repaint();

    }


    /** Metodo altera o nif.
     * @param nifEditavel para alterar
     */
    public void setNifEditavel(String nifEditavel) {
        this.nifEditavel = nifEditavel;
    }

    /**Metodo Identifica utilizador logado no aplicacao e altera o JtextField
     *
     */
    public void nomeLogado() {
        if (aor_autocarro.getUserLogado() == null) {
            clienteNome.setText("");
        } else
            clienteNome.setText(aor_autocarro.getUserLogado().getNome());
        revalidate();
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean validar = true;
        if (e.getActionCommand().equals("Adicionar")) {
            //verificar se todos os campos estão preenchidos
            if (nomeField.getText().equals("") || nifField.getText().equals("") ||
                    moradaField.getText().equals("") || telefoneField.getText().equals("") ||
                    emailField.getText().equals("") || palavraChaveField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Há campos de preenchimento obrigatório que não foram preenchidos");
                validar = false;
            }
            //Verificar se email é válido
            if (!Utilizador.validarEmail(emailField.getText())) {
                JOptionPane.showMessageDialog(null, "Email inválido");
                validar = false;
            }
            //Verificar se o nome é constituído só por letras
            if (!Utilizador.validarNome(nomeField.getText())) {
                JOptionPane.showMessageDialog(null, "Nome com carateres inválidos");
                validar = false;
            }
            //Verificar se o nif é constituído por 9 números
            if (!Utilizador.validarTlfeNif(nifField.getText())) {
                JOptionPane.showMessageDialog(null, "Nif inválido");
                validar = false;
            }
            //Verificar se o telefone é constituído por 9 números
            if (!Utilizador.validarTlfeNif(telefoneField.getText())) {
                JOptionPane.showMessageDialog(null, "Número de telefone inválido");
                validar = false;
            }
            //Verificar se existe já algum Cliente registado com o nif registado
            if (aor_autocarro.verificarDuplicacaoNif(nifField.getText())) {
                JOptionPane.showMessageDialog(null, "Já existe um cliente registado com esse nif");
                validar = false;
            }
            //Verificar se existe já algum Cliente registado com o email registado
            if (aor_autocarro.verificarDuplicaçãoEmail(emailField.getText())) {
                JOptionPane.showMessageDialog(null, "Já existe um cliente registado com esse email");
                validar = false;
            }
            if (validar == false) {
            } else {
                String id = "cl".concat(String.valueOf(aor_autocarro.contarCliente()));
                aor_autocarro.getUtilizadores().add(new Cliente(id, emailField.getText(), palavraChaveField.getText(), nomeField.getText(), nifField.getText(),
                        moradaField.getText(), telefoneField.getText(), "Normal", LocalDate.now()));
                JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso.\n" +
                        "Será enviado para o email " + emailField.getText() + " uma password provisória, a qual deverá" +
                        "ser alterada.");
                atualizar();
                nomeField.setText("");
                nifField.setText("");
                emailField.setText("");
                moradaField.setText("");
                telefoneField.setText("");

            }
        }
        if (e.getActionCommand().equals("Remover")) {
            if (!aor_autocarro.removerCliente(nifRemoverField.getText())) {
                JOptionPane.showMessageDialog(null, "Não existe nenhum cliente " +
                        "com esse nif");
                nifRemoverField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "O cliente só irá ser removido da lista de clientes, " +
                        "após ter sido informado sobre encerramento da sua conta, ao efetuar login");
                if (aor_autocarro.getReservas().size() != 0) {
                    aor_autocarro.cancelarReservasdoClienteRemovido(nifRemoverField.getText());
                }
                atualizar();
                nifRemoverField.setText("");



            }
        }
        if (e.getActionCommand().equals("Editar")) {
            if (!(aor_autocarro.getCliente(nifEditarField.getText()) == null)) {
                Utilizador cliente = aor_autocarro.getCliente(nifEditarField.getText());
                setNifEditavel(nifEditarField.getText());
                ((ClientesEditar) (painelFundo.mapaPaineis.get("ClientesEditar"))).setCliente(cliente);
                nifEditarField.setText("");
                painelFundo.mudaEcra("ClientesEditar");
            } else {
                nifEditarField.setText("");
                JOptionPane.showMessageDialog(null, "Não existe nenhum cliente com esse nif");
            }
        }
        if (e.getActionCommand().equals("Adminstradores")) {
            painelFundo.mudaEcra("RegistarNovoAdministrador");
        }
        if (e.getActionCommand().equals("Motoristas")) {
            ((Motoristas) (painelFundo.mapaPaineis.get("Motoristas"))).nomeLogado();
            painelFundo.mudaEcra("Motoristas");
        }
        if (e.getActionCommand().equals("Autocarros")) {
            ((Autocarros) (painelFundo.mapaPaineis.get("Autocarros"))).nomeLogado();
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
    }

}
