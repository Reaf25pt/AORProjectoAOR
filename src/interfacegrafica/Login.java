package interfacegrafica;

import programa.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;


/**
 * Classe Interface grafica, para proceder ao login da aplicacao
 */
public class Login extends JPanel implements ActionListener {

    private Aor_Autocarro aor_autocarro;

    private String loginEmail;
    private PainelFundo painelFundo;
    private TextField emailField;
    private JPasswordField palavraChaveField;
    private JButton botaoautenticar;
    private JButton botaoregistar;


    /** Constroi a interface grafica
     * @param painelfundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public Login(PainelFundo painelfundo, Aor_Autocarro aor_autocarro) {
        this.aor_autocarro = aor_autocarro;
        this.painelFundo = painelfundo;
        this.setLayout(null);


        //Painel do cabeçalho
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(-10, 30, 900, 60);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel tituloPrincipal = new JLabel("Aor-Autocarros");
        tituloPrincipal.setBounds(10, 30, 900, 30);
        tituloPrincipal.setHorizontalAlignment(JLabel.CENTER);
        tituloPrincipal.setVerticalAlignment(JLabel.CENTER);
        mainPanel.add(tituloPrincipal);
        this.add(mainPanel);


        //Painel do Login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(275, 125, 350, 200);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Label do login
        JLabel login = new JLabel("Login:");
        login.setBounds(10, 0, 50, 30);

        //Label do email
        JLabel email = new JLabel("Email");
        email.setBounds(30, 50, 80, 30);

        //Label da palavra chave
        JLabel palavraChave = new JLabel("Palavra-Chave");
        palavraChave.setBounds(30, 100, 100, 30);

        // Textofield do email
        emailField = new TextField();
        emailField.setBounds(150, 50, 170, 30);

        // Textofield da palavrachave
        palavraChaveField = new JPasswordField();
        palavraChaveField.setBounds(150, 100, 170, 30);

        //Botão de auntenticar
        botaoautenticar = new JButton("Autenticar");
        botaoautenticar.setBounds(20, 160, 300, 30);

        //Botao de Registar programa.Utilizador
        botaoregistar = new JButton("Registar Novo Utilizador");
        botaoregistar.setBounds(275, 400, 350, 50);
        this.add(botaoregistar);

        // Adicionar componentes ao painel
        loginPanel.add(login);
        loginPanel.add(email);
        loginPanel.add(palavraChave);
        loginPanel.add(emailField);
        loginPanel.add(palavraChaveField);
        loginPanel.add(botaoautenticar);
        this.add(loginPanel);


        //Adicionar paineis ao frame


        // Adicionar ao Listener
        botaoregistar.addActionListener(this);
        botaoautenticar.addActionListener(this);


    }

    //====================================

    /** Metodo retorna um email do login
     * Gets login email.
     *
     * @return loginEmail String - retorna o email no login
     */
    public String getLoginEmail() {
        return loginEmail;
    }

    /**Metodo atualiza o email do login
     *
     *
     * @param loginEmail String - Altera o email login
     */
    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    /** Coloca em branco os campos quando o utilizador sai da aplicacao
     *
     */
    public void sair() {
        emailField.setText("");
        palavraChaveField.setText("");
    }

    //*******************************************
    //Eventos

    @Override
    public void actionPerformed(ActionEvent ae) {

        String email = emailField.getText();
        String password = new String(palavraChaveField.getPassword());//para transformar em string
        Utilizador utilizadorLogado;
        Cliente logado;
        int opção=0;
        ArrayList<Reserva> reservasEsperaCopia = new ArrayList<>(aor_autocarro.getReservasemEspera());

        if (ae.getActionCommand().equals("Autenticar")) {
            if ((Utilizador.validarEmail(email)) && (aor_autocarro.validarRegisto(email, password))) {
                JOptionPane.showMessageDialog(null, "Login efetuado com Sucesso! " + email);
                utilizadorLogado = aor_autocarro.utilizadorLogado(email);

                if (aor_autocarro.verificarTipoUtilizador(email, password).equals("cliente")) {
                    logado = (Cliente) aor_autocarro.getUserLogado();
                    ((ReservaViagem) (painelFundo.mapaPaineis.get("ReservaViagem"))).nomeLogado();
                    if (logado.getNotificações().size() == 0) {
                        painelFundo.mudaEcra("ReservaViagem");
                    } else {
                        for (Notificação rc : logado.getNotificações()) {
                            if (rc.getLido() == false) {
                                if (rc.getTipoNotificação().equals("Cancelamento")) {
                                    JOptionPane.showMessageDialog(null, rc.getDescrição());
                                    rc.setLido(true);
                                    opção=1;
                                    FicheiroDeObjectos.escreveObjeto(aor_autocarro);

                                }
                                if (logado.getTipoCliente().equals("Premium")) {
                                    if (rc.getTipoNotificação().equals("Subscrição")) {
                                        if (rc.getData().isBefore(LocalDate.now())) {
                                            rc.setLido(true);
                                            int resultado = JOptionPane.showConfirmDialog(null, rc.getDescrição() +
                                                    ".Pretende manter plano Subscrição Premium?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                                            if (resultado == JOptionPane.YES_OPTION) {
                                                LocalDate dataExpiração = rc.getData().plusDays(35);
                                                LocalDate dataPreAvisoNotificação = rc.getData().plusDays(30);
                                                Notificação subscrição = new Notificação(logado.getEmail(), "Subscrição", "O pagamento da" +
                                                        "sua mensalidade expira em " + dataExpiração + ".Caso não proceda ao seu pagamento, deixará de" +
                                                        " aceder às vantagens da subscrição Premium.", dataPreAvisoNotificação, false);
                                                //Adiciona notificação à lista das notificaçóes do cliente
                                                logado.getNotificações().add(subscrição);
                                                opção=1;
                                            } else {
                                                JOptionPane.showMessageDialog(null, "A partir desta data ficou abrangido pelo plano normal");
                                                logado.setTipoCliente("Normal");
                                                opção=1;
                                            }
                                            FicheiroDeObjectos.escreveObjeto(aor_autocarro);
                                        } else opção=1;
                                    }
                                }
                                if (rc.getTipoNotificação().equals("ListaEspera")) {
                                    rc.setLido(true);
                                    int resultado = JOptionPane.showConfirmDialog(null, rc.getDescrição() +
                                            ".Pretende prosseguir?", "Escolha uma opção", JOptionPane.YES_NO_OPTION);
                                    if (resultado == JOptionPane.YES_OPTION) {
                                        Reserva reservaAtribuida = aor_autocarro.atribuirReservaListaEspera(email);
                                        reservaAtribuida.setDataReserva(LocalDate.now());
                                        opção=2;
                                    } else {
                                        for (Reserva emEspera :reservasEsperaCopia) {
                                            if (emEspera.getCliente().equals(logado)) {
                                                aor_autocarro.getReservasemEspera().remove(emEspera);
                                                aor_autocarro.getReservasCanceladas().add(emEspera);
                                                opção=1;
                                            } else if (emEspera.getDataPartida().isBefore(LocalDate.now())) {
                                                aor_autocarro.getReservasemEspera().remove(emEspera);
                                                aor_autocarro.getReservasCanceladas().add(emEspera);
                                                opção=1;
                                            }
                                        }
                                    }

                                }
                                if (rc.getTipoNotificação().equals("ClienteRemovido")) {
                                    JOptionPane.showMessageDialog(null, rc.getDescrição());
                                    rc.setLido(true);
                                    logado.setEmail("inativo");
                                    opção=3;
                                    FicheiroDeObjectos.escreveObjeto(aor_autocarro);
                                }

                            }else opção=1;
                        }
                        if (opção == 1) {
                            painelFundo.mudaEcra("ReservaViagem");
                        }else if (opção == 2) {
                            painelFundo.mudaEcra("Pagamentos");
                        }else if (opção ==3){
                            painelFundo.mudaEcra("Login");
                        }

                    }
                } else if (aor_autocarro.verificarTipoUtilizador(email, password).equals("administrador")) {
                    ((RegistarNovoAdministrador) (painelFundo.mapaPaineis.get("RegistarNovoAdministrador"))).nomeLogado();
                    painelFundo.mudaEcra("RegistarNovoAdministrador");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Login Invalido!");
            }
            FicheiroDeObjectos.escreveObjeto(aor_autocarro);
        }

        if (ae.getActionCommand().equals("Registar Novo Utilizador")) {
            painelFundo.mudaEcra("RegistarUtilizador");
        }
    }
}
