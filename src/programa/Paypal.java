package programa;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe para objetos do tipo Paypal, onde serao contidos, valores e metodos para o mesmo.
 */
public class Paypal extends Pagamento implements Serializable
{
    private String email;
    private String palavraChave;

    /**
     * Instantiates a new Paypal.
     *
     * @param reserva      Reserva - reserva a pagar
     * @param email      String - email do utilizador
     * @param palavraChave String - palavra chave do utilizador
     */
    public Paypal(Reserva reserva, String email, String palavraChave) {
        super(reserva);
        this.email = email;
        this.palavraChave = palavraChave;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Paypal{" +
                "email='" + email + '\'' +
                ", palavraChave='" + palavraChave + '\'' +
                ", reserva=" + reserva +
                '}';
    }

    /** Validar email do utilizador - valida @ e .
     *
     *
     * @param email String - email do utilizador
     * @return  boolean
     */
    public static boolean validarEmail(String email) {
        boolean validar = false;
        String[] email2 = email.split("");
        for (int i = 0; i < email2.length; i++) {
            if (email2[i].equals("@")) {
                for (int j = i; j < email2.length; j++) {
                    if (email2[j].equals(".")) {
                        validar = true;
                    }
                }
            }
        }return validar;
    }
}
