package programa;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe para objetos do tipo Pagamentos, onde serao contidos, valores e metodos para o mesmo.
 */
public class Pagamento implements Serializable
{


    protected Reserva reserva;

    /**
     * Instantiates a new Pagamento.
     *
     * @param reserva the reserva
     */
    public Pagamento(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * Gets reserva.
     *
     * @return the reserva
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Sets reserva.
     *
     * @param reserva the reserva
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }


    @Override
    public String toString() {
        return "reserva=" + reserva + "Pagamento:";
    }
}


