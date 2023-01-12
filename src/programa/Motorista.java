package programa;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Motorista, onde serao contidos, valores e metodo para o mesmo.
 */
public class Motorista implements Serializable {

    private String nome;
    private String email;

    /**
     * Instantiates a new Motorista.
     */
    public Motorista(){
    }

    /**
     * Instantiates a new Motorista.
     *
     * @param nome  String - nome do motorista
     * @param email String - email
     */
    public Motorista(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
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
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
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
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}