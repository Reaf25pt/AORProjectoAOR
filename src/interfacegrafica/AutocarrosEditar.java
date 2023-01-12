package interfacegrafica;

import programa.Aor_Autocarro;
import programa.Autocarro;
import programa.FicheiroDeObjectos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Classe Interface grafica para editar Autocarros

 */
public class AutocarrosEditar extends JPanel implements ActionListener {

    private final PainelFundo painelFundo;
    private final Aor_Autocarro aor_autocarro;
    private Autocarro autocarro;

    private final JTextField matriculaField;

    private final JTextField marcaField;

    private final JTextField modeloField;

    private final JTextField lotacaoField;

    private final JLabel matriculaPreenchida;


    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public AutocarrosEditar(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
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
        JLabel clienteNome = new JLabel("Nome do Admin");
        clienteNome.setBounds(700, 0, 100, 30);
        cabecalho.add(clienteNome);

        // Botao para sair para o login
        JButton sairBotao = new JButton("Sair");
        sairBotao.setBounds(810, 1, 70, 28);
        cabecalho.add(sairBotao);
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
        JLabel segundoTitulo = new JLabel("Autocarros ");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        //======================================================================
        //Formulario

        JPanel formulario = new JPanel();
        formulario.setLayout(null);
        formulario.setBounds(10, 100, 350, 250);


        //Labels
        JLabel matriculaEditarLabel = new JLabel("Matricula");
        matriculaEditarLabel.setBounds(50, 40, 200, 30);
        matriculaPreenchida = new JLabel();
        matriculaPreenchida.setBounds(150, 40, 200, 30);

        JLabel matriculaLabel = new JLabel("Matricula:");
        matriculaLabel.setBounds(50, 90, 200, 30);
        JLabel marcaLabel = new JLabel("Marca:");
        marcaLabel.setBounds(50, 130, 200, 30);
        JLabel modeloLabel = new JLabel("Modelo:");
        modeloLabel.setBounds(50, 170, 200, 30);
        JLabel lotacaoLabel = new JLabel("Lotação:");
        lotacaoLabel.setBounds(50, 210, 200, 30);

        //Fields
        matriculaField = new JTextField();
        matriculaField.setBounds(150, 90, 200, 30);
        marcaField = new JTextField();
        marcaField.setBounds(150, 130, 200, 30);
        modeloField = new JTextField();
        modeloField.setBounds(150, 170, 200, 30);
        lotacaoField = new JTextField();
        lotacaoField.setBounds(150, 210, 200, 30);

        //Adicionar ao formulario
        formulario.add(matriculaLabel);
        formulario.add(matriculaPreenchida);
        formulario.add(marcaLabel);
        formulario.add(modeloLabel);
        formulario.add(lotacaoLabel);
        formulario.add(matriculaEditarLabel);
        formulario.add(matriculaField);
        formulario.add(marcaField);
        formulario.add(modeloField);
        formulario.add(lotacaoField);
        this.add(formulario);

        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(275, 375, 100, 30);
        this.add(editarButton);

        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        opcao6.addActionListener(this);
        sairBotao.addActionListener(this);
        editarButton.addActionListener(this);
    }


    /**Metodo atualiza os dados do painel referentes ao Autocarro
     *
     *
     * @param autocarro the autocarro
     */
    public void setAutocarro(Autocarro autocarro) {

        if (!(autocarro == null)) {
            this.autocarro = autocarro;
            matriculaPreenchida.setText(autocarro.getMatricula());
            matriculaField.setText(autocarro.getMatricula());
            marcaField.setText(autocarro.getMarca());
            modeloField.setText(autocarro.getModelo());
            lotacaoField.setText(String.valueOf(autocarro.getLotacao()));
            revalidate();
            repaint();
        }
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

        if (e.getActionCommand().equals("Editar")) {
            this.autocarro.setMatricula(matriculaField.getText());
            this.autocarro.setMarca(marcaField.getText());
            this.autocarro.setModelo(modeloField.getText());
            this.autocarro.setLotacao(lotacaoField.getText());
            FicheiroDeObjectos.escreveObjeto(aor_autocarro);

            matriculaField.setText("");
            marcaField.setText("");
            modeloField.setText("");
            lotacaoField.setText("");
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
            ((Autocarros) (painelFundo.mapaPaineis.get("Autocarros"))).atualizar();
            painelFundo.mudaEcra("Autocarros");
        }
    }
}
