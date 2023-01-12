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
 * Classe Interface grafica, para proceder a consulta de reservas agendadas, canceladas e em espera
 */
public class ConsultarReservas extends JPanel implements ActionListener {

    private final PainelFundo painelFundo;
    private final Aor_Autocarro aor_autocarro;

    private JTable tabelaAtivas;
    private JScrollPane spAtivas;
    private final JLabel clienteNome;

    private JTable tabelaEspera;
    private JScrollPane spEspera;

    private JTable tabelaCanceladas;
    private JScrollPane spCanceladas;

    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public ConsultarReservas(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
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
        JLabel segundoTitulo = new JLabel("Consultar reservas agendadas");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        //=====================================================================
        //Terceiro titulo
        JLabel terceiroTitulo = new JLabel("Reservas agendadas");
        terceiroTitulo.setBounds(350, 150, 900, 30);
        this.add(terceiroTitulo);

        //Quarto titulo
        JLabel quatroTitulo = new JLabel("Reservas em Espera");
        quatroTitulo.setBounds(350, 360, 900, 30);
        this.add(quatroTitulo);

        //Quinto titulo
        JLabel quintoTitulo = new JLabel("Reservas Canceladas");
        quintoTitulo.setBounds(350, 475, 900, 30);
        this.add(quintoTitulo);


        //========================================
        // Tabela Reservas Ativas

        String[] colunasAtivas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Custo total Viagem"};
        String[][] dataAtivas = new String[aor_autocarro.getReservasemEspera().size()][8];
        tabelaAtivas = new JTable(dataAtivas, colunasAtivas);
        spAtivas = new JScrollPane(tabelaAtivas);
        spAtivas.setBounds(100, 200, 700, 150);
        this.add(spAtivas);

        //========================================
        // Tabela Reservas Em Espera

        String[] colunasEspera = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Custo total Viagem"};
        String[][] dataEspera = new String[aor_autocarro.getReservasemEspera().size()][8];
        tabelaEspera = new JTable(dataEspera, colunasEspera);
        spEspera = new JScrollPane(tabelaEspera);
        spEspera.setBounds(100, 400, 700, 75);
        this.add(spEspera);


        //========================================
        // Tabela Reservas Canceladas

        String[] colunasCanceladas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Custo total Viagem"};
        String[][] dataCanceladas = new String[aor_autocarro.getReservasCanceladas().size()][8];
        tabelaCanceladas = new JTable(dataCanceladas, colunasCanceladas);
        spCanceladas = new JScrollPane(tabelaCanceladas);
        spCanceladas.setBounds(100, 510, 700, 100);
        this.add(spCanceladas);


        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        sairBotao.addActionListener(this);


    }


    /**Metodo atualiza as tabelas da interface
     *
     */
    public void listagemPorMes() {
        remove(spAtivas);
        String[] colunasAtivas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Matricula", "Custo total Viagem"};

        ArrayList<String[]> dataAtivas = new ArrayList<>();
        for (Reserva reserva : aor_autocarro.getReservas()) {
            if (reserva.getCliente().getEmail().equals(aor_autocarro.getUserLogado().getEmail())) {
                String[] reservaInfoAtivas = new String[8];
                if (reserva.getDataPartida().isAfter(LocalDate.now())) {
                    reservaInfoAtivas[0] = reserva.getId();
                    reservaInfoAtivas[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfoAtivas[2] = reserva.getNumeroDias();
                    reservaInfoAtivas[3] = reserva.getNumeroPessoas();
                    reservaInfoAtivas[4] = reserva.getLocalPartida();
                    reservaInfoAtivas[5] = reserva.getLocalDestino();
                    reservaInfoAtivas[6] = reserva.getAutocarro().getMatricula();
                    reservaInfoAtivas[7] = String.valueOf(reserva.getCusto());
                    dataAtivas.add(reservaInfoAtivas);
                }
            }
        }
        String[][] dataArrayAtivas = dataAtivas.toArray(new String[0][0]);
        tabelaAtivas = new JTable(dataArrayAtivas, colunasAtivas);
        spAtivas = new JScrollPane(tabelaAtivas);
        spAtivas.setBounds(100, 200, 700, 150);
        this.add(spAtivas);
        clienteNome.setText(aor_autocarro.getUserLogado().getNome());
        revalidate();
        repaint();



        remove(spEspera);
        String[] colunasEspera = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Custo total Viagem"};

        ArrayList<String[]> dataEspera = new ArrayList<>();
        for (Reserva reserva : aor_autocarro.getReservasemEspera()) {
            if (reserva.getCliente().getEmail().equals(aor_autocarro.getUserLogado().getEmail())) {
                String[] reservaInfoEspera = new String[8];
                if (reserva.getDataPartida().isAfter(LocalDate.now())) {
                    reservaInfoEspera[0] = reserva.getId();
                    reservaInfoEspera[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfoEspera[2] = reserva.getNumeroDias();
                    reservaInfoEspera[3] = reserva.getNumeroPessoas();
                    reservaInfoEspera[4] = reserva.getLocalPartida();
                    reservaInfoEspera[5] = reserva.getLocalDestino();
                    reservaInfoEspera[7] = String.valueOf(reserva.getCusto());
                    dataEspera.add(reservaInfoEspera);
                }
            }
        }
        String[][] dataArrayEspera = dataEspera.toArray(new String[0][0]);
        tabelaEspera = new JTable(dataArrayEspera, colunasEspera);
        spEspera = new JScrollPane( tabelaEspera);
        spEspera.setBounds(100, 400, 700, 75);
        this.add(spEspera);
        clienteNome.setText(aor_autocarro.getUserLogado().getNome());
        revalidate();
        repaint();



        remove(spCanceladas);
        String[] colunasCanceladas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Custo total Viagem"};

        ArrayList<String[]> dataCanceladas = new ArrayList<>();
        for (Reserva reserva : aor_autocarro.getReservasCanceladas()) {
            if (reserva.getCliente().getEmail().equals(aor_autocarro.getUserLogado().getEmail())) {
                String[] reservaInfoCanceladas = new String[8];
                if (reserva.getDataPartida().isAfter(LocalDate.now())) {
                    reservaInfoCanceladas[0] = reserva.getId();
                    reservaInfoCanceladas[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfoCanceladas[2] = reserva.getNumeroDias();
                    reservaInfoCanceladas[3] = reserva.getNumeroPessoas();
                    reservaInfoCanceladas[4] = reserva.getLocalPartida();
                    reservaInfoCanceladas[5] = reserva.getLocalDestino();
                    reservaInfoCanceladas[6] = reserva.getAutocarro().getMatricula();
                    reservaInfoCanceladas[7] = String.valueOf(reserva.getCusto());
                    dataCanceladas.add(reservaInfoCanceladas);
                }
            }
        }
        String[][] dataArrayCanceladas = dataCanceladas.toArray(new String[0][0]);
        tabelaCanceladas = new JTable(dataArrayCanceladas, colunasCanceladas);
        spCanceladas = new JScrollPane( tabelaCanceladas);
        spCanceladas.setBounds(100, 510, 700, 100);
        this.add(spCanceladas);
        clienteNome.setText(aor_autocarro.getUserLogado().getNome());
        revalidate();
        repaint();
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
    }

}
