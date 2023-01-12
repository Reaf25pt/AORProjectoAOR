package interfacegrafica;

import programa.Aor_Autocarro;
import programa.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**Classe Interface grafica, para consultar o historico das reservas efetuadas e canceladas, com data inferior a data atual
 *
 */
public class HistoricoReservas extends JPanel implements ActionListener {

    private final PainelFundo painelFundo;
    private JTable tabelaReservas;
    private JScrollPane spReservas;
    private final JLabel clienteNome;
    private final Aor_Autocarro aor_autocarro;

    private  JTable tabelaCanceladas;
    private  JScrollPane spCanceladas;

    /** Constroi a interface grafica
     * @param painelFundo   - Faz a gestao da interface
     * @param aor_autocarro - Guarda a informacao do programa
     *
     */
    public HistoricoReservas(PainelFundo painelFundo, Aor_Autocarro aor_autocarro) {
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
        clienteNome = new JLabel("Nome do Cliente");
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
        JLabel segundoTitulo = new JLabel("Historico de reservas");
        segundoTitulo.setBounds(50, 100, 900, 30);
        this.add(segundoTitulo);

        JLabel terceiroTitulo = new JLabel("Reservas Efectuadas");
        terceiroTitulo.setBounds(375, 135, 900, 30);
        this.add(terceiroTitulo);

        JLabel quartoTitulo = new JLabel("Reservas Canceladas");
        quartoTitulo.setBounds(375, 400, 900, 30);
        this.add(quartoTitulo);


        //========================================
        // Tabela
        String[] colunasReservas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Matricula", "Custo total Viagem"};
        ArrayList<String[]> dataReservas = new ArrayList<>();

        String[][] dataArrayReservas = dataReservas.toArray(new String[0][0]);
        tabelaReservas = new JTable(dataArrayReservas, colunasReservas);
        spReservas = new JScrollPane(tabelaReservas);
        spReservas.setBounds(150, 180, 600, 150);
        this.add(spReservas);
        revalidate();
        repaint();




        String[] colunasCanceladas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Matricula", "Custo total Viagem"};
        ArrayList<String[]> dataCanceladas = new ArrayList<>();

        String[][] dataArrayCanceladas = dataCanceladas.toArray(new String[0][0]);
        tabelaCanceladas = new JTable(dataArrayCanceladas, colunasCanceladas);
        spCanceladas = new JScrollPane(tabelaCanceladas);
        spCanceladas.setBounds(150, 450, 600, 100);
        this.add(spCanceladas);
        revalidate();
        repaint();




        opcao1.addActionListener(this);
        opcao2.addActionListener(this);
        opcao3.addActionListener(this);
        opcao4.addActionListener(this);
        opcao5.addActionListener(this);
        sairBotao.addActionListener(this);


    }

    /**Metodo para atualizar as tabela das reservas efetuadas
     *
     */
    public void listagemPorMesReservasEfetuadas() {
        remove(spReservas);
        String[] colunas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Matricula", "Custo total Viagem"};
        ArrayList<String[]> data = new ArrayList<>();
        for (Reserva reserva : aor_autocarro.getReservas()) {
            if (reserva.getCliente().getEmail().equals(aor_autocarro.getUserLogado().getEmail())) {
                if (reserva.getDataPartida().isBefore(LocalDate.now())) {
                    String[] reservaInfo = new String[8];
                    reservaInfo[0] = reserva.getId();
                    reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfo[2] = reserva.getNumeroDias();
                    reservaInfo[3] = reserva.getNumeroPessoas();
                    reservaInfo[4] = reserva.getLocalPartida();
                    reservaInfo[5] = reserva.getLocalDestino();
                    reservaInfo[6] = reserva.getAutocarro().getMatricula();
                    reservaInfo[7] = String.valueOf(reserva.getCusto());
                    data.add(reservaInfo);
                }
            }
        }
        String[][] dataArray = data.toArray(new String[0][0]);
        tabelaReservas = new JTable(dataArray, colunas);
        spReservas = new JScrollPane(tabelaReservas);
        spReservas.setBounds(150, 180, 600, 150);
        this.add(spReservas);
        clienteNome.setText(aor_autocarro.getUserLogado().getNome());
        revalidate();
        repaint();


    }

    /**Metodo para atualizar as tabela das reservas canceladas
     *
     */
    public void listagemPorMesReservasCanceladas() {
        remove(spCanceladas);
        String[] colunas = {"Nº Reserva", "Data Aluguer", "NºDias", "NºPessoas",
                "Local Partida", "Local Destino", "Matricula", "Custo total Viagem"};
        ArrayList<String[]> data = new ArrayList<>();
        for (Reserva reserva : aor_autocarro.getReservasCanceladas()) {
            if (reserva.getCliente().getEmail().equals(aor_autocarro.getUserLogado().getEmail())) {
                if (reserva.getDataPartida().isBefore(LocalDate.now())) {
                    String[] reservaInfo = new String[8];
                    reservaInfo[0] = reserva.getId();
                    reservaInfo[1] = String.valueOf(reserva.getDataPartida());
                    reservaInfo[2] = reserva.getNumeroDias();
                    reservaInfo[3] = reserva.getNumeroPessoas();
                    reservaInfo[4] = reserva.getLocalPartida();
                    reservaInfo[5] = reserva.getLocalDestino();
                    reservaInfo[6] = reserva.getAutocarro().getMatricula();
                    reservaInfo[7] = String.valueOf(reserva.getCusto());
                    data.add(reservaInfo);
                }
            }
        }
        String[][] dataArray = data.toArray(new String[0][0]);
        tabelaCanceladas = new JTable(dataArray, colunas);
        spCanceladas = new JScrollPane(tabelaCanceladas);
        spCanceladas.setBounds(150, 450, 600, 100);
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
