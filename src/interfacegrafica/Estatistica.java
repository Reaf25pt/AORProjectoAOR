package interfacegrafica;

import programa.Aor_Autocarro;
import programa.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe Interface grafica para visualizar estatisticas da empresa por parte do Administrador
 */
public class Estatistica extends JPanel implements ActionListener {
    private final PainelFundo painelFundo;
    private final Aor_Autocarro aor_autocarro;

    private final JLabel clienteNome;

    private JTable tabela;
    private JScrollPane sp;

    private JTable canceladasTable;
    private JScrollPane spCanceladas;

    private JTable esperaTabela;
    private JScrollPane spEspera;

    private String[] colunasEspera;

    private final JComboBox mesCombobox1;
    private final JPanel autocarrosReservados;
    private final JComboBox mesCombobox2;
    private final JPanel autocarrosCanceladas;
    private final JPanel autocarrosEspera;
    private final JComboBox mesCombobox3;
    private final JLabel totalClientesPreenchidoLabel;
    private final JLabel totalMotoristasPreenchidoLabel;
    private final JLabel totalAutocarrosPreenchidoLabel;


    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public Estatistica(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
        this.painelFundo = painelFundo;
        this.aor_autocarro = aor_autocarro;
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
        clienteNome = new JLabel("Nome do Admin");
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
        JLabel segundoTitulo = new JLabel("Estatística");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        //======================================================================
        // Abas
        JTabbedPane abasPanel = new JTabbedPane();
        abasPanel.setTabPlacement(JTabbedPane.LEFT);
        abasPanel.setBounds(0, 125, 880, 530);


        //==========================================================
        //Aba Total Clientes
        JPanel totalClientesPanel = new JPanel();
        totalClientesPanel.setLayout(null);
        totalClientesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Botoes de filtragem

        JPanel totalClientes = new JPanel(new GridLayout(1, 2, 0, 0));
        totalClientes.setBounds(200, 350, 250, 15);
        JLabel totalClientesLabel = new JLabel("Total Clientes:");
        totalClientesPreenchidoLabel = new JLabel(String.valueOf(aor_autocarro.contarCliente() - 1));
        totalClientes.add(totalClientesLabel);
        totalClientes.add(totalClientesPreenchidoLabel);

        totalClientesPanel.add(totalClientes);
        abasPanel.add("Total Clientes", totalClientesPanel);
        this.add(abasPanel);

        //==============================================================
        //Aba total Motoristas
        JPanel totalMotoristasPanel = new JPanel();
        totalMotoristasPanel.setLayout(null);

        // Botoes de filtragem

        JPanel totalMotoristas = new JPanel(new GridLayout(1, 2, 0, 0));
        totalMotoristas.setBounds(200, 350, 250, 15);
        JLabel totalMotoristasLabel = new JLabel("Total Motoristas:");
        totalMotoristasPreenchidoLabel = new JLabel(String.valueOf(aor_autocarro.contarMotorista()));
        totalMotoristas.add(totalMotoristasLabel);
        totalMotoristas.add(totalMotoristasPreenchidoLabel);

        totalMotoristasPanel.add(totalMotoristas);
        abasPanel.add("Total Motoristas", totalMotoristasPanel);

        //===============================================================
        //Aba total Autocarros
        JPanel totalAutocarrosPanel = new JPanel();
        totalAutocarrosPanel.setLayout(null);


        JPanel totalAutocarros = new JPanel(new GridLayout(1, 2, 0, 0));
        totalAutocarros.setBounds(200, 350, 250, 15);
        JLabel totalAutocarrosLabel = new JLabel("Total Autocarros:");
        totalAutocarrosPreenchidoLabel = new JLabel(String.valueOf(aor_autocarro.contarAutocarro()));
        totalAutocarros.add(totalAutocarrosLabel);
        totalAutocarros.add(totalAutocarrosPreenchidoLabel);

        totalAutocarrosPanel.add(totalAutocarros);
        abasPanel.add("Total Autocarros", totalAutocarrosPanel);

        //===============================================================
        //Aba total Autocarro mais Requisitado
        JPanel autocarroMaisRequisitado = new JPanel();
        autocarroMaisRequisitado.setLayout(null);

        // Botoes de filtragem

        JPanel totalRequisitado = new JPanel(new GridLayout(1, 2, 0, 0));
        totalRequisitado.setBounds(200, 350, 400, 15);
        JLabel totalRequisitadoLabel = new JLabel("Autocarro mais Requisitado:");
        JLabel totalRequisitadoPreenchidoLabel = new JLabel(aor_autocarro.matriculaMaisRequisitado());
        totalRequisitado.add(totalRequisitadoLabel);
        totalRequisitado.add(totalRequisitadoPreenchidoLabel);

        autocarroMaisRequisitado.add(totalRequisitado);

        abasPanel.add("Autocarro mais requisitado", autocarroMaisRequisitado);


        //===================================================
        //Cliente com mais viagens

        JPanel totalViagensPanel = new JPanel();
        totalViagensPanel.setLayout(null);

        // Botoes de filtragem

        JPanel totalViagens = new JPanel(new GridLayout(1, 2, 0, 0));
        totalViagens.setBounds(150, 350, 400, 15);
        JLabel totalViagensLabel = new JLabel("Cliente com mais viagens");
        JLabel totalViagensPreenchidoLabel = new JLabel(aor_autocarro.clienteComMaisReservas());
        totalViagens.add(totalViagensLabel);
        totalViagens.add(totalViagensPreenchidoLabel);

        totalViagensPanel.add(totalViagens);
        abasPanel.add("Cliente com mais viagens", totalViagensPanel);


        //==========================================================================
        //Autocarros Reservados
        autocarrosReservados = new JPanel();
        autocarrosReservados.setLayout(null);

        //Botoes
        JPanel botoesFiltroReservados = new JPanel(new GridLayout(1, 2, 10, 5));
        botoesFiltroReservados.setBounds(225, 25, 200, 30);
        JLabel mesLabelReservados = new JLabel("Mes:");
        String[] mes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        mesCombobox1 = new JComboBox(mes);

        botoesFiltroReservados.add(mesLabelReservados);
        botoesFiltroReservados.add(mesCombobox1);

        //Tabela
        String[] colunas = {"Matricula", "Data Partida", "Data Chegada"};

        String[][] data = new String[aor_autocarro.getReservas().size()][3];
        for (int i = 0; i < aor_autocarro.getReservas().size(); i++) {
            data[i][0] = aor_autocarro.getReservas().get(i).getAutocarro().getMatricula();
            data[i][1] = String.valueOf(aor_autocarro.getReservas().get(i).getDataPartida());
            data[i][2] = String.valueOf(aor_autocarro.getReservas().get(i).getDataFim());

        }

        tabela = new JTable(data, colunas);
        sp = new JScrollPane(tabela);
        sp.setBounds(200, 100, 300, 400);
        autocarrosReservados.add(botoesFiltroReservados);
        autocarrosReservados.add(sp);
        abasPanel.add("Autocarros reservados", autocarrosReservados);


        //=======================================================
        //Requisições Canceladas

        autocarrosCanceladas = new JPanel();
        autocarrosCanceladas.setLayout(null);

        //Botoes
        JPanel botoesFiltroCanceladas = new JPanel(new GridLayout(1, 2, 0, 5));
        botoesFiltroCanceladas.setBounds(225, 50, 200, 30);
        JLabel mesLabelCanceladas = new JLabel("Mes:");
        String[] mescanceladas = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        mesCombobox2 = new JComboBox(mescanceladas);

        botoesFiltroCanceladas.add(mesLabelCanceladas);
        botoesFiltroCanceladas.add(mesCombobox2);

        //Tabela
        String[] colunasCanceladas = {"Matricula", "Data Partida", "Data Chegada", "Cliente", "Motorista"};

        String[][] dataCanceladas = new String[aor_autocarro.getReservasCanceladas().size()][5];

        for (int i = 0; i < aor_autocarro.getReservasCanceladas().size(); i++) {
            dataCanceladas[i][0] = aor_autocarro.getReservasCanceladas().get(i).getAutocarro().getMatricula();
            dataCanceladas[i][1] = String.valueOf(aor_autocarro.getReservasCanceladas().get(i).getDataPartida());
            dataCanceladas[i][2] = String.valueOf(aor_autocarro.getReservasCanceladas().get(i).getDataFim());
            dataCanceladas[i][3] = aor_autocarro.getReservasCanceladas().get(i).getCliente().getNome();
            dataCanceladas[i][4] = aor_autocarro.getReservasCanceladas().get(i).getMotorista().getNome();
        }


        canceladasTable = new JTable(dataCanceladas, colunasCanceladas);
        spCanceladas = new JScrollPane(canceladasTable);
        spCanceladas.setBounds(100, 100, 500, 400);
        autocarrosCanceladas.add(botoesFiltroCanceladas);
        autocarrosCanceladas.add(spCanceladas);
        abasPanel.add("Requisições Canceladas", autocarrosCanceladas);

        //========================================================================
        //Reservas em Espera

        autocarrosEspera = new JPanel();
        autocarrosEspera.setLayout(null);

        //Botoes
        JPanel botoesFiltroReservas = new JPanel(new GridLayout(1, 4, 0, 5));
        botoesFiltroReservas.setBounds(225, 50, 200, 30);
        JLabel mesLabelReservas = new JLabel("Mes:");
        String[] mesemEspera = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        mesCombobox3 = new JComboBox(mesemEspera);
        botoesFiltroReservas.add(mesLabelReservas);
        botoesFiltroReservas.add(mesCombobox3);

        //Tabela
        colunasEspera = new String[]{"Cliente", "Data Partida", "Data Chegada"};

        String[][] dataReservas = new String[aor_autocarro.getReservasemEspera().size()][3];

        for (int i = 0; i < aor_autocarro.getReservasemEspera().size(); i++) {
            dataReservas[i][0] = aor_autocarro.getReservasemEspera().get(i).getCliente().getNome();
            dataReservas[i][1] = String.valueOf(aor_autocarro.getReservasemEspera().get(i).getDataPartida());
            dataReservas[i][2] = String.valueOf(aor_autocarro.getReservasemEspera().get(i).getDataFim());

        }


        esperaTabela = new JTable(dataReservas, colunasEspera);
        spEspera = new JScrollPane(esperaTabela);
        spEspera.setBounds(100, 100, 500, 400);
        autocarrosEspera.add(botoesFiltroReservas);
        autocarrosEspera.add(spEspera);
        abasPanel.add("Reservas em Espera", autocarrosEspera);

        //============================================
        //Volume de reservas

        JPanel volumeReservas = new JPanel();
        volumeReservas.setLayout(null);


        //Tabela
        String[] colunasVolume = {"Mês", "Volume"};

        String[] meses;
        meses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        int[] volume = contarReservasMes();
        String[][] dataVolume = new String[12][2];

        for (int i = 0; i < meses.length; i++) {
            dataVolume[i][0] = meses[i];
            dataVolume[i][1] = String.valueOf(volume[i]);
        }

        JTable volumeTable = new JTable(dataVolume, colunasVolume);
        JScrollPane spvolume = new JScrollPane(volumeTable);
        spvolume.setBounds(100, 80, 500, 218);
        volumeReservas.add(spvolume);

        //Label
        JPanel volumePreenchidoPanel = new JPanel(new GridLayout(1, 2, 40, 5));
        volumePreenchidoPanel.setBounds(150, 300, 400, 300);
        JLabel volumePreenchido = new JLabel("Dia com mais reservas:");
        JLabel volumePreenchidoLabel = new JLabel(dataMaisOcorrencias());
        volumePreenchidoPanel.add(volumePreenchido);
        volumePreenchidoPanel.add(volumePreenchidoLabel);
        volumeReservas.add(volumePreenchidoPanel);
        abasPanel.add("Volume de Reservas", volumeReservas);


        this.add(abasPanel);

        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        opcao6.addActionListener(this);
        sairButton.addActionListener(this);
        mesCombobox1.addActionListener(this);
        mesCombobox2.addActionListener(this);
        mesCombobox3.addActionListener(this);


    }

    /** Metodo para atualizar o JTextField da interface associada a estatistica
     *
     */
    public void nomeLogado() {

        if (aor_autocarro.getUserLogado() == null) {
            clienteNome.setText("");
        } else{
            clienteNome.setText(aor_autocarro.getUserLogado().getNome());
            totalClientesPreenchidoLabel.setText(String.valueOf(aor_autocarro.contarCliente() - 1));
            totalMotoristasPreenchidoLabel.setText(String.valueOf(aor_autocarro.contarMotorista()));
            totalAutocarrosPreenchidoLabel.setText(String.valueOf(aor_autocarro.contarAutocarro()));
            revalidate();
            repaint();
        }


    }


    /** Metodo contar as reserva por mes
     *
     *
     * @return the int [ ]
     */
    public int[] contarReservasMes() {
        int[] volume = new int[12];
        for (int i = 1; i <= 12; i++) {
            int contador = 0;
            for (Reserva reserva : aor_autocarro.getReservas()) {
                if (reserva.getDataPartida().getMonthValue() == i) {
                    contador++;
                }
            }
            volume[i - 1] = contador;
        }
        return volume;
    }

    /**Metodo para filtrar as tabela das reservas ativas pelo mes escolhido
     *
     *
     * @param mes String - mes pretendido para filtrar a tabela na lista de reservas ativas
     */
    public void listagemPorMesReservas(String mes) {
        if (!mes.equals("0")) {
            autocarrosReservados.remove(sp);
            sp.removeAll();
            String[] colunas = {"Matricula", "Data Partida", "Data Chegada"};
            ArrayList<String[]> data = new ArrayList<>();
            for (Reserva reserva : aor_autocarro.getReservas()) {
                String[] reservaInfo = new String[3];
                if (String.valueOf(reserva.getDataPartida().getMonthValue()).equals(mes)) {
                    reservaInfo[0] = reserva.getAutocarro().getMatricula();
                    reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfo[2] = String.valueOf(reserva.getDataFim());
                    data.add(reservaInfo);
                }
            }
            String[][] dataArray = data.toArray(new String[0][0]);
            tabela = new JTable(dataArray, colunas);
            sp = new JScrollPane(tabela);
            autocarrosReservados.add(sp);
            sp.setBounds(200, 100, 300, 400);
        } else {
            autocarrosReservados.remove(sp);
            sp.removeAll();
            String[] colunas = {"Matricula", "Data Partida", "Data Chegada"};
            ArrayList<String[]> data = new ArrayList<>();
            for (Reserva reserva : aor_autocarro.getReservas()) {
                String[] reservaInfo = new String[3];
                reservaInfo[0] = reserva.getAutocarro().getMatricula();
                reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                reservaInfo[2] = String.valueOf(reserva.getDataFim());
                data.add(reservaInfo);
            }
            String[][] dataArray = data.toArray(new String[0][0]);
            tabela = new JTable(dataArray, colunas);
            sp = new JScrollPane(tabela);
            autocarrosReservados.add(sp);
            sp.setBounds(200, 100, 300, 400);

        }

    }

    /**Metodo para filtrar as tabela das reservas canceladas pelo mes escolhido
     *
     *
     * @param mes String - mes pretendido para filtrar a tabela na lista de reservas canceladas
     */
    public void listagemPorMesReservasCanceladas(String mes) {
        if (!mes.equals("0")) {
            autocarrosCanceladas.remove(spCanceladas);
            spCanceladas.removeAll();
            String[] colunasCanceladas = {"Matricula", "Data Partida", "Data Chegada", "Cliente", "Motorista"};
            ArrayList<String[]> data = new ArrayList<>();
            for (Reserva reserva : aor_autocarro.getReservasCanceladas()) {
                String[] reservaInfo = new String[5];
                if (String.valueOf(reserva.getDataPartida().getMonthValue()).equals(mes)) {
                    reservaInfo[0] = reserva.getAutocarro().getMatricula();
                    reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfo[2] = String.valueOf(reserva.getDataFim());
                    reservaInfo[3] = reserva.getCliente().getNome();
                    reservaInfo[4] = reserva.getMotorista().getNome();
                    data.add(reservaInfo);
                }
            }
            String[][] dataArray = data.toArray(new String[0][0]);
            canceladasTable = new JTable(dataArray, colunasCanceladas);
            spCanceladas = new JScrollPane(canceladasTable);
            autocarrosCanceladas.add(spCanceladas);
            spCanceladas.setBounds(100, 100, 500, 400);
        } else {
            autocarrosCanceladas.remove(spCanceladas);
            spCanceladas.removeAll();
            String[] colunasCanceladas = {"Matricula", "Data Partida", "Data Chegada", "Cliente", "Motorista"};
            ArrayList<String[]> data = new ArrayList<>();
            for (Reserva reserva : aor_autocarro.getReservasCanceladas()) {
                String[] reservaInfo = new String[5];
                reservaInfo[0] = reserva.getAutocarro().getMatricula();
                reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                reservaInfo[2] = String.valueOf(reserva.getDataFim());
                reservaInfo[3] = reserva.getCliente().getNome();
                reservaInfo[4] = reserva.getMotorista().getNome();
                data.add(reservaInfo);
            }
            String[][] dataArray = data.toArray(new String[0][0]);
            canceladasTable = new JTable(dataArray, colunasCanceladas);
            spCanceladas = new JScrollPane(canceladasTable);
            autocarrosCanceladas.add(spCanceladas);
            spCanceladas.setBounds(100, 100, 500, 400);

        }

    }


    /**Metodo para filtrar as tabela das reservas espera pelo mes escolhido
     *
     *
     * @param  mes String - mes pretendido para filtrar a tabela na lista de reservas espera
     */
    public void listagemPorMesReservasEspera(String mes) {
        if (!mes.equals("0")) {
            autocarrosEspera.remove(spEspera);
            spEspera.removeAll();
            colunasEspera = new String[]{"Cliente", "Data Partida", "Data Chegada"};
            ArrayList<String[]> data = new ArrayList<>();
            for (Reserva reserva : aor_autocarro.getReservasemEspera()) {
                String[] reservaInfo = new String[5];
                if (String.valueOf(reserva.getDataPartida().getMonthValue()).equals(mes)) {
                    reservaInfo[0] = reserva.getCliente().getNome();
                    reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfo[2] = String.valueOf(reserva.getDataFim());
                    data.add(reservaInfo);
                }
            }
            String[][] dataArray = data.toArray(new String[0][0]);
            esperaTabela = new JTable(dataArray, colunasEspera);
            spEspera = new JScrollPane(esperaTabela);
            autocarrosEspera.add(spEspera);
            spEspera.setBounds(100, 100, 500, 400);
        } else {
            autocarrosEspera.remove(spEspera);
            spEspera.removeAll();
            colunasEspera = new String[]{"Cliente", "Data Partida", "Data Chegada"};
            ArrayList<String[]> data = new ArrayList<>();
            for (Reserva reserva : aor_autocarro.getReservasemEspera()) {
                String[] reservaInfo = new String[5];
                reservaInfo[0] = reserva.getCliente().getNome();
                reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                reservaInfo[2] = String.valueOf(reserva.getDataFim());
                data.add(reservaInfo);
            }
            String[][] dataArray = data.toArray(new String[0][0]);
            esperaTabela = new JTable(dataArray, colunasEspera);
            spEspera = new JScrollPane(esperaTabela);
            autocarrosEspera.add(spEspera);
            spEspera.setBounds(100, 100, 500, 400);

        }

    }

    /** Metodo para identificar a data com mais reservas
     *
     *
     * @return String - data com mais reserva
     */
    public String dataMaisOcorrencias() {
        String dataMaisFrequente = "";
        int contadorMax = 0;
        for (Reserva reserva : aor_autocarro.getReservas()) {
            if ((reserva.getDataPartida().getYear() == LocalDate.now().getYear())) {
                for (int i = 0; i < aor_autocarro.getReservas().size(); i++) {
                    int contador = 0;
                    for (int j = 0; j < aor_autocarro.getReservas().size(); j++) {
                        if ((aor_autocarro.getReservas().get(i).getDataPartida()).isEqual(aor_autocarro.getReservas().get(j).getDataPartida())) {
                            contador++;
                        }
                    }
                    if (contador > contadorMax) {
                        contadorMax = contador;
                        dataMaisFrequente = String.valueOf(aor_autocarro.getReservas().get(i).getDataPartida());
                    }
                }
            }
        }
        return dataMaisFrequente;
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        String mes = (String) mesCombobox1.getSelectedItem();
        listagemPorMesReservas(mes);
        String mesCanceladas = (String) mesCombobox2.getSelectedItem();
        listagemPorMesReservasCanceladas(mesCanceladas);
        String mesEspera = (String) mesCombobox3.getSelectedItem();
        listagemPorMesReservasEspera(mesEspera);


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
            ((AdicionarClientes) (painelFundo.mapaPaineis.get("AdicionarClientes"))).nomeLogado();
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

    }
}
