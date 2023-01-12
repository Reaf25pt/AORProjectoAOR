package programa;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Multibanco, onde serao contidos, valores e metodos para o mesmo.
 */
public class MB extends Pagamento implements Serializable {


    private final int entidade;
    private final String referencia;
    private final double montante;

    /**
     * Instantiates a new Mb.
     *
     * @param reserva   Reserva - recebe a reserva
     * @param entidade   String  - entidade do pagamento
     * @param referencia String  - referencia do pagamento
     * @param montante   double - valor a pagar
     */
    public MB(Reserva reserva, int entidade, String referencia, double montante) {
        super(reserva);
        this.entidade = 12345;
        this.referencia = referencia;
        this.montante = getReserva().getCusto();
    }

    @Override
    public String toString() {
        return "Reserva=" + reserva +"|Multibanco:" +
                "entidade=" + entidade +
                ",referencia=" + referencia +
                ",montante=" + montante;
    }

    /**Metodo para gerar referencia multibanco - gera 3 grupos de 3 numeros aleatorios
     *
     *
     * @return String - retorna referenciaMB
     */
    public static String gerarRefMultibanco(){
        String referenciaMB= String.valueOf(100+(Math.round((Math.random()*899)*100)/100))+" "+
                String.valueOf(100+(Math.round((Math.random()*899)*100)/100))+" "+
                String.valueOf(100+(Math.round((Math.random()*899)*100)/100));
        return referenciaMB;
    }

}
