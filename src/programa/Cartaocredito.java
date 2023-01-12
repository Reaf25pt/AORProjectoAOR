package programa;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Classe para objetos do tipo Cartao de credito, onde serao contidos, valores e metodos para o mesmo.
 */
public class Cartaocredito extends Pagamento implements Serializable {

    private final String numeroCartao;
    private final String nomeClienteCartao;
    private final String dataExpiracao;
    private String codigoSeguranca;

    /**
     * Instantiates a new Cartaocredito.
     *
     * @param reserva          Reserva
     * @param numeroCartao      String - numero do cartao de credito
     * @param nomeClienteCartao String - nome do cliente associado ao cartao
     * @param dataExpiracao     String - data expiracao do cartao
     * @param codigoSeguranca   String - codigo de seguranca
     */
    public Cartaocredito(Reserva reserva, String numeroCartao, String nomeClienteCartao, String dataExpiracao, String codigoSeguranca) {
        super(reserva);
        this.numeroCartao = numeroCartao;
        this.nomeClienteCartao = nomeClienteCartao;
        this.dataExpiracao = dataExpiracao;
        this.codigoSeguranca = codigoSeguranca;
    }

    /**
     * Gets numero cartao.
     *
     * @return the numero cartao
     */
    public String getNumeroCartao() {
        return numeroCartao;
    }

    /**
     * Gets nome cliente cartao.
     *
     * @return the nome cliente cartao
     */
    public String getNomeClienteCartao() {
        return nomeClienteCartao;
    }

    @Override
    public String toString() {
        return "reserva=" + reserva +"\n" +
                "|CartaoCredito:" +
                "numeroCartao=" + numeroCartao +
                ",nomeClienteCartao='" + nomeClienteCartao +
                ",dataExpiracao=" + dataExpiracao;
    }

    /**Metodo para validar se todos os caracteres sao numeros e o tamanho do numero
     *
     *
     * @param dadoNumerico String - numero do cartao de credito
     * @return boolean
     */
    public static boolean validarnumCartaoCredito(String dadoNumerico) {
        boolean validar=false;
        dadoNumerico=dadoNumerico.replaceAll("\\s","");
        int contadorNumeros=0;

        for(int i=0;i<dadoNumerico.length();i++){
            if(Character.isDigit(dadoNumerico.charAt(i))){
                contadorNumeros++;
            }
        }
        if(contadorNumeros<=16&&contadorNumeros>=14){
            validar=true;
        }
        return validar;
    }

    /**Metodo para validar se todos os caracteres sao letras
     *
     *
     * @param nome String - nome utilizador
     * @return  boolean
     */
    public static boolean validarNome(String nome) {
        boolean validar = false;
        nome=nome.replaceAll("\\s","");
        int contadorLetras=0;

        for(int i=0;i<nome.length();i++){
            if(!Character.isDigit(nome.charAt(i))){
                contadorLetras++;
            }
        }
        if(nome.length()==contadorLetras){
            validar=true;
        }
        return validar;
    }

    /**Metodo para validar o formato da data
     *
     *
     * @param data String - data do cartao de credito
     * @return  boolean
     */
    public static boolean validarDataFormato(String data) {
        boolean validar = false;
        //Validar o formato da data
        if ((data.length() == 5) && (data.charAt(2) == '/')) {
            data.replaceAll("/", "");
            for (int i = 0; i < data.length(); i++) {
                if (Character.isDigit(data.charAt(i))) {
                    validar = true;
                }
            }
        }return validar;
    }

    /**Metodo para validar se a data de expiracao é superior a atual
     *
     *
     * @param data Local Date
     * @return  boolean
     */
    public static boolean validarDataExpiração(String data) {
        boolean validar = false;
        LocalDate hoje=LocalDate.now();
        String dataHoje=hoje.toString();
        int mesAtual = Integer.parseInt(dataHoje.substring(5, 7));
        int anoAtual= Integer.parseInt(dataHoje.substring(2, 4));

        String dataSemEspaços=data.replaceAll("/", "");

        int mes = Integer.parseInt(dataSemEspaços.substring(0, 2));
        int ano= Integer.parseInt(dataSemEspaços.substring(2, 4));

        if(ano==anoAtual&&mes>=mesAtual&&mes>0&&mes<=12){
            validar=true;
        }
        if(ano>anoAtual&&mes>0&&mes<=12){
            validar=true;
        }
        return validar;

    }

    /** Metodo para validar o pin do cartao de credito - 3 numeros
     *
     *
     * @param dadoNumerico String - codigo segurança do cartao
     * @return  boolean
     */
    public static boolean validarPinCartaoCredito(String dadoNumerico) {
        boolean validar=false;
        dadoNumerico=dadoNumerico.replaceAll("\\s","");
        int contadorNumeros=0;

        for(int i=0;i<dadoNumerico.length();i++){
            if(Character.isDigit(dadoNumerico.charAt(i))){
                contadorNumeros++;
            }
        }
        if(contadorNumeros==3){
            validar=true;
        }
        return validar;
    }
}
