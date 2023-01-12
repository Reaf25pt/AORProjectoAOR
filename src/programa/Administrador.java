package programa;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Administrador, onde serao contidos, valores e metodos para o mesmo.
 */
public class Administrador extends Utilizador implements Serializable {

    private String id;


    /**
     * Construtor receber os atributos do Administrador
     *
     * @param id           String  id - id do administrador
     * @param email        String  email - email do admnistrador
     * @param palavraChave String  palavra chave -palavra chave do admnistrador
     * @param nome         String nome - nome do administrador
     * @param nif          String nif - nif do administrador
     * @param morada       String morada - morada do administrador
     * @param telefone     String telefone - telefone do administrador
     */
    public Administrador(String id, String email, String palavraChave, String nome, String nif, String morada, String telefone) {
        super(id, email, palavraChave, nome, nif, morada, telefone);
        this.id = id;
        this.palavraChave = "ADMIN2022";
    }

    @Override
    public String toString() {
        return "Administrador:" +
                "id=" + id +
                ",nome=" + nome +
                ",nif=" + nif +
                ",morada=" + morada +
                ",telefone=" + telefone +
                ",email=" + email +
                ",palavraChave=" + palavraChave + "\n";
    }



    public String getId() {
        return id;
    }
}
