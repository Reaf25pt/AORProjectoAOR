package interfacegrafica;

import programa.Aor_Autocarro;
import programa.Cliente;
import programa.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe Interface grafica, para consultar os dados pessoais do Cliente
 */
public class DadosPessoaisCliente extends JPanel implements ActionListener {

    private final PainelFundo painelFundo;
    private final Aor_Autocarro aor_autocarro;

    private final JLabel nomeField;

    private final JLabel nifField;
    private final JLabel moradaField;

    private final JLabel telefoneField;

    private final JLabel emailField;

    private final JLabel palavraChaveField;
    private final JLabel clienteNome;
    private final JLabel tipoSubscricao;

    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public DadosPessoaisCliente(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
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


        JButton opcao1 = new JButton("Reserva Autocarro");
        JButton opcao2 = new JButton("Histórico Reservas");
        JButton opcao3 = new JButton("Consultar Reservas");
        JButton opcao4 = new JButton("Cancelar Reservas");
        JButton opcao5 = new JButton("Dados Pessoais");

        opcaoPainel.add(opcao1);
        opcaoPainel.add(opcao2);
        opcaoPainel.add(opcao3);
        opcaoPainel.add(opcao4);
        opcaoPainel.add(opcao5);

        this.add(opcaoPainel);

        //=====================================================================
        //Segundo titulo
        JLabel segundoTitulo = new JLabel("Dados Pessoais");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        //Painel Formulario

        JPanel formulario = new JPanel();
        formulario.setLayout(null);
        formulario.setBounds(25, 100, 400, 350);


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
        JLabel palavraChaveLabel = new JLabel("Palavra Chave:");
        palavraChaveLabel.setBounds(50, 250, 200, 30);
        JLabel tipoSubscricaoLabel = new JLabel("Tipo de subscrição: ");
        tipoSubscricaoLabel.setBounds(25, 290, 200, 30);

        //Fields
        nomeField = new JLabel();
        nomeField.setBounds(150, 50, 200, 30);
        nifField = new JLabel();
        nifField.setBounds(150, 90, 200, 30);
        moradaField = new JLabel();
        moradaField.setBounds(150, 130, 200, 30);
        telefoneField = new JLabel();
        telefoneField.setBounds(150, 170, 200, 30);
        emailField = new JLabel();
        emailField.setBounds(150, 210, 200, 30);
        palavraChaveField = new JLabel();
        palavraChaveField.setBounds(150, 250, 200, 30);
        tipoSubscricao = new JLabel();
        tipoSubscricao.setBounds(150, 290, 200, 30);


        formulario.add(nomeLabel);
        formulario.add(nifLabel);
        formulario.add(moradaLabel);
        formulario.add(telefoneLabel);
        formulario.add(emailLabel);
        formulario.add(palavraChaveLabel);
        formulario.add(nomeField);
        formulario.add(nifField);
        formulario.add(moradaField);
        formulario.add(telefoneField);
        formulario.add(emailField);
        formulario.add(palavraChaveField);
        formulario.add(tipoSubscricaoLabel);
        formulario.add(tipoSubscricao);
        this.add(formulario);

        //===============================================
        //Painel de botoes

        JPanel botoesPainel = new JPanel(new GridLayout(2, 1, 0, 10));
        botoesPainel.setBounds(450, 200, 200, 100);

        JButton alterarPalavraChave = new JButton("Alterar palavra chave");
        JButton alterarPlanoSubscricao = new JButton("Alterar plano subsrição");
        botoesPainel.add(alterarPalavraChave);
        botoesPainel.add(alterarPlanoSubscricao);
        this.add(botoesPainel);


        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        sairBotao.addActionListener(this);
        alterarPalavraChave.addActionListener(this);
        alterarPlanoSubscricao.addActionListener(this);


    }

    /** Metodo para atualizar o JTextField da interface relativa aos dados do Cliente logado
     *
     * @return the utilizador
     */
    public Utilizador nomeLogado() {
        if (aor_autocarro.getUserLogado() == null) {
            nomeField.setText("");
        } else {
            clienteNome.setText(aor_autocarro.getUserLogado().getNome());
            nomeField.setText(aor_autocarro.getUserLogado().getNome());
            nifField.setText(aor_autocarro.getUserLogado().getNif());
            moradaField.setText(aor_autocarro.getUserLogado().getMorada());
            telefoneField.setText(aor_autocarro.getUserLogado().getTelefone());
            emailField.setText(aor_autocarro.getUserLogado().getEmail());
            palavraChaveField.setText(aor_autocarro.getUserLogado().getPalavraChave());
            Cliente logado = (Cliente) aor_autocarro.getUserLogado();
            tipoSubscricao.setText(logado.getTipoCliente());
        }
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Reserva Autocarro")) {
            painelFundo.mudaEcra("ReservaViagem");
        }


        if (e.getActionCommand().equals("Histórico Reservas")) {
            ((HistoricoReservas) (painelFundo.mapaPaineis.get("HistoricoReservas"))).listagemPorMesReservasEfetuadas();
            ((HistoricoReservas) (painelFundo.mapaPaineis.get("HistoricoReservas"))).listagemPorMesReservasCanceladas();
            painelFundo.mudaEcra("HistoricoReservas");
        }

        if (e.getActionCommand().equals("Consultar Reservas")) {
            ((ConsultarReservas) (painelFundo.mapaPaineis.get("ConsultarReservas"))).listagemPorMes();
            painelFundo.mudaEcra("ConsultarReservas");
        }

        if (e.getActionCommand().equals("Cancelar Reservas")) {
            ((CancelarReserva) (painelFundo.mapaPaineis.get("CancelarReserva"))).nomeLogado();
            painelFundo.mudaEcra("CancelarReserva");
        }
        if (e.getActionCommand().equals("Dados Pessoais")) {
            ((DadosPessoaisCliente) (painelFundo.mapaPaineis.get("DadosPessoaisCliente"))).nomeLogado();
            painelFundo.mudaEcra("DadosPessoaisCliente");
        }
        if (e.getActionCommand().equals("Sair")) {
            ((Login) painelFundo.mapaPaineis.get("Login")).sair();
            painelFundo.mudaEcra("Login");
        }
        if (e.getActionCommand().equals("Alterar palavra chave")) {
            ((AlterarPalavraChaveCliente) painelFundo.mapaPaineis.get("AlterarPalavraChave")).nomeLogado();
            painelFundo.mudaEcra("AlterarPalavraChave");
        }
        if (e.getActionCommand().equals("Alterar plano subsrição")) {
            painelFundo.mudaEcra("PlanoSubscrição");

        }

    }
}