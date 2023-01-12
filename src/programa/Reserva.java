package programa;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe para objetos do tipo Reserva, onde serao contidos, valores e metodos para o mesmo.
 */
public class Reserva implements Serializable {

    private Cliente cliente;
    private Autocarro autocarro;
    private Motorista motorista;
    private LocalDate dataReserva;
    private LocalDate dataPartida;
    private String numeroDias;
    private String numeroPessoas;
    private String localPartida;
    private String localDestino;
    private String distancia;

    private LocalDate dataFim;
    private double custo; // não pode estar no construtor
    private String id;

    /**
     * Instantiates a new Reserva.
     */
    public Reserva() {
    }

    /**
     * Instantiates a new Reserva.
     *
     * @param cliente       Cliente - informacao do cliente
     * @param autocarro    Autocarro - informacao do autocarro
     * @param motorista    Motorista - informacao do motorista
     * @param dataReserva   LocalDate - data da reserva
     * @param dataPartida  LocalDate - data de partida
     * @param numeroDias   String - numero de dias de aluguer
     * @param numeroPessoas String - numero de pessoas a ocupar o autocarro
     * @param localPartida  String - local de partida
     * @param localDestino  String - local de destino
     * @param distancia    String - distancia da viagem
     * @param id           String - id da reserva
     */
    public Reserva(Cliente cliente, Autocarro autocarro, Motorista motorista, LocalDate dataReserva, LocalDate dataPartida, String numeroDias, String numeroPessoas, String localPartida, String localDestino, String distancia, String id) {
        this.cliente = cliente;
        this.autocarro = autocarro;
        this.motorista = motorista;
        this.dataReserva = dataReserva;
        this.dataPartida = dataPartida;
        this.numeroDias = numeroDias;
        this.numeroPessoas = numeroPessoas;
        this.localPartida = localPartida;
        this.localDestino = localDestino;
        this.distancia = distancia;
        dataFim = dataPartida.plusDays(Integer.parseInt(numeroDias));//calcula data fim do periodo de reserva pretendido
        this.id = id;
        this.custo = getCusto();
    }

    /**
     * Gets cliente.
     *
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets cliente.
     *
     * @param cliente the cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets autocarro.
     *
     * @return the autocarro
     */
    public Autocarro getAutocarro() {
        return autocarro;
    }

    /**
     * Sets autocarro.
     *
     * @param autocarro the autocarro
     */
    public void setAutocarro(Autocarro autocarro) {
        this.autocarro = autocarro;
    }

    /**
     * Gets motorista.
     *
     * @return the motorista
     */
    public Motorista getMotorista() {
        return motorista;
    }

    /**
     * Sets motorista.
     *
     * @param motorista the motorista
     */
    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }


    /**
     * Gets data reserva.
     *
     * @return the data reserva
     */
    public LocalDate getDataReserva() {
        return dataReserva;
    }

    /**
     * Gets data fim.
     *
     * @return the data fim
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * Sets data reserva.
     *
     * @param dataReserva the data reserva
     */
    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    /**
     * Gets data partida.
     *
     * @return the data partida
     */
    public LocalDate getDataPartida() {
        return dataPartida;
    }

    /**
     * Gets local partida.
     *
     * @return the local partida
     */
    public String getLocalPartida() {
        return localPartida;
    }

    /**
     * Gets local destino.
     *
     * @return the local destino
     */
    public String getLocalDestino() {
        return localDestino;
    }

    /**
     * Gets numero pessoas.
     *
     * @return the numero pessoas
     */
    public String getNumeroPessoas() {
        return numeroPessoas;
    }

    /**
     * Gets numero dias.
     *
     * @return the numero dias
     */
    public String getNumeroDias() {
        return numeroDias;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**Metodo calcula o custo da reserva
     *
     *
     * @return double custo da reserva
     */
    public double getCusto() {
        int distancia1 = 0;
        int nPessoas = 0;
        try {
            distancia1 = Integer.parseInt(distancia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preencha número Km");
        }
        try {
            nPessoas = Integer.parseInt(numeroPessoas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preencha número pessoas");
        }
        return custo = 0.55 * distancia1 + 1.2 * nPessoas;
    }

    /** Metodo valida se o local e composto apenas por letras
     *
     *
     * @param local String - local
     * @return  boolean
     */
    public static boolean validarLocal(String local) {
        boolean validar = false;
        local = local.replaceAll("\\s", "");
        int contadorLetras = 0;

        for (int i = 0; i < local.length(); i++) {
            if (Character.isLetter(local.charAt(i))) {
                contadorLetras++;
            }
        }
        if (local.length() == contadorLetras) {
            validar = true;
        }
        return validar;
    }

    /**
     * Metodo valida se o dado recebido e composto apenas por numeros
     *
     * @param dadoNumerico String - dados para preencher a reserva
     * @return  boolean
     */
    public static boolean validarNumeros(String dadoNumerico) {
        boolean validar = false;
        int contadorNumeros = 0;

        for (int i = 0; i < dadoNumerico.length(); i++) {
            if (Character.isDigit(dadoNumerico.charAt(i))) {
                contadorNumeros++;
            }
        }
        if (dadoNumerico.length() == contadorNumeros) {
            validar = true;
        }
        return validar;
    }

    /**Metodo para validar o formato da data inserida
     *
     * @param data String - data inserida
     * @return  boolean
     */
    public static boolean validarDataFormato(String data) {
        boolean validar = false;
        //Validar o formato da data
        if ((data.length() == 10) && (data.charAt(2) == '/') && (data.charAt(5) == '/')) {
            data.replaceAll("/", "");
            for (int i = 0; i < data.length(); i++) {
                if (Character.isDigit(data.charAt(i))) {
                    validar = true;
                }
            }
        }
        return validar;
    }

    /** Metodo para validar se os valores dados aos dias,meses e anos, sao validos
     *
     *
     * @param data String - data inserida
     * @return  boolean
     */
//Validar o valor da data
    public static boolean validarDataValida(String data) {
        boolean validar = false;
        String dataSemEspaços = data.replaceAll("/", "");

        int dia = Integer.parseInt(dataSemEspaços.substring(0, 2));
        int mes = Integer.parseInt(dataSemEspaços.substring(2, 4));

        if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia <= 31)) {
            validar = true;
        } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia <= 30)) {
            validar = true;
        } else if (mes == 2 && dia <= 28) {
            validar = true;
        }
        return validar;
    }

    /**Metodo para validar se a data de aluguer e superior a data de hoje
     *
     *
     * @param data LocalDate - data do aluguer
     * @return  boolean
     */
//Verificar se a data é igual ou superior à data atual
    public static boolean validarDataAluguer(String data) {
        boolean validar = false;
        LocalDate dataAluguer = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (dataAluguer.isAfter(LocalDate.now())) {
            validar = true;
        }
        return validar;
    }


    @Override
    public String toString() {
        return "Reserva:cliente=" + cliente +
                ",autocarro=" + autocarro +
                ",motorista=" + motorista +
                ",nºreserva= " + id +
                ",data da reserva=" + dataReserva +
                ",data da partida=" + dataPartida +
                ",numero dias=" + numeroDias +
                ",numero de pessoas=" + numeroPessoas +
                ",local de partida='" + localPartida + '\'' +
                ",local de destino='" + localDestino + '\'' +
                ",distancia=" + distancia +
                ",custo=" + custo;
    }
}
