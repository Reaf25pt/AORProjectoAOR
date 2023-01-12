package programa;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe para objetos do tipo notificacao, onde serao contidos, valores e metodos para o mesmo.
 */
public class Notificação implements Serializable {
    private String email;
    private  String tipoNotificação;
    private String descrição;
    private LocalDate data;
    private Boolean lido;

    public Boolean getLido() {
        return lido;
    }

    /**
     * Instantiates a new Notificação.
     *
     * @param email          String - email do cliente
     * @param tipoNotificação String - tipo de notificacao
     * @param descrição       String - descricao da notificacao
     * @param data           Local Date - data da notificacao
     * @param lido           boolean - se foi lido ou nao
     */
    public Notificação(String email,String tipoNotificação, String descrição, LocalDate data, Boolean lido) {
        this.email = email;
        this.tipoNotificação=tipoNotificação;
        this.descrição = descrição;
        this.data = data;
        this.lido = lido;
    }

    public void setLido(Boolean lido) {
        this.lido = lido;
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

    /**
     * Get tipo notificação string.
     *
     * @return the string
     */
    public String getTipoNotificação() {
        return tipoNotificação;
    }


    public String getDescrição() {
        return descrição;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Notificação:" +
                "email=" + email +
                ",tipoNotificação=" + tipoNotificação +
                ",descrição='" + descrição +
                ",data=" + data +
                ",lido=" + lido;
    }
}
