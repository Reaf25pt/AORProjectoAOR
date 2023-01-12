package interfacegrafica;

import programa.Aor_Autocarro;
import programa.Cliente;
import programa.FicheiroDeObjectos;
import programa.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe Interface grafica para proceder a edicao de clientes
 */
public class ClientesEditar extends JPanel implements ActionListener {

    private final Aor_Autocarro aor_autocarro;
    private final PainelFundo painelFundo;

    private  Utilizador utilizador;
    private final JTextField nameField;
    private final JLabel nifLabelPreenchido;
    private final JTextField moradaField;
    private final JTextField telefoneField;
    private final JTextField emailField;


    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    ClientesEditar(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
        this.aor_autocarro=aor_autocarro;
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
        JLabel clienteNome = new JLabel("Nome do Admin");
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


        //Fields
        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        nifLabelPreenchido = new JLabel();
        nifLabelPreenchido.setBounds(150, 90, 200, 30);
        moradaField = new JTextField();
        moradaField.setBounds(150, 130, 200, 30);
        telefoneField = new JTextField();
        telefoneField.setBounds(150, 170, 200, 30);
        emailField = new JTextField();
        emailField.setBounds(150, 210, 200, 30);


        //Adicionar ao formulario
        formulario.add(nomeLabel);
        formulario.add(nifLabel);
        formulario.add(moradaLabel);
        formulario.add(telefoneLabel);
        formulario.add(emailLabel);
        formulario.add(nameField);
        formulario.add(nifLabelPreenchido);
        formulario.add(moradaField);
        formulario.add(telefoneField);
        formulario.add(emailField);
        this.add(formulario);

        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(350, 400, 100, 30);
        this.add(editarButton);

        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        opcao6.addActionListener(this);
        sairButton.addActionListener(this);
        editarButton.addActionListener(this);


    }

    /**Metodo para atualizar o JTextFields da interface relativa aos dados do cliente
     *
     *
     * @param utilizador the utilizador
     */
    public void setCliente(Utilizador utilizador) {
        if(!(utilizador==null)) {
            this.utilizador=utilizador;
            nifLabelPreenchido.setText(utilizador.getNif());
            nameField.setText(utilizador.getNome());
            moradaField.setText(utilizador.getMorada());
            telefoneField.setText(utilizador.getTelefone());
            emailField.setText(utilizador.getEmail());
            revalidate();
            repaint();
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Editar")) {
            this.utilizador.setNome(nameField.getText());
            this.utilizador.setEmail(emailField.getText());
            this.utilizador.setMorada(moradaField.getText());
            this.utilizador.setTelefone(telefoneField.getText());


            nameField.setText("inativo");
            nifLabelPreenchido.setText("inativo");
            emailField.setText("inativo");
            moradaField.setText("inativo");
            telefoneField.setText("inativo");

            JOptionPane.showMessageDialog(null,"Editado com sucesso!");
            ((AdicionarClientes)(painelFundo.mapaPaineis.get("AdicionarClientes"))).atualizar();
            painelFundo.mudaEcra("AdicionarClientes");

            FicheiroDeObjectos.escreveObjeto(aor_autocarro);

        }
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
            painelFundo.mudaEcra("Estatistica");
        }
        if (e.getActionCommand().equals("Dados Pessoais")) {
            painelFundo.mudaEcra("DadosPessoaisAdmin");
        }

        if (e.getActionCommand().equals("Sair")) {
            ((Login)painelFundo.mapaPaineis.get("Login")).sair();
            painelFundo.mudaEcra("Login");
        }

    }
}
