package programa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe para objetos do tipo Cliente, onde serao contidos, valores e metodos para o mesmo.
 */
public class Cliente extends Utilizador implements Serializable {

    private String id;

    private ArrayList<Notificação> notificações=new ArrayList<>();
    private String tipoCliente;
    private LocalDate data; // Data da subscrição, nova ou alterada

    public Cliente (){
        super();
    }

    /**
     * Construtor receber os atributos do Administrador
     *
     * @param id           String  id - id do cliente
     * @param email        String  email - email do cliente
     * @param palavraChave String  palavra chave -palavra chave do cliente
     * @param nome         String nome - nome do cliente
     * @param nif          String nif - nif do cliente
     * @param morada       String morada - morada do cliente
     * @param telefone     String telefone - telefone do cliente
     * @param tipoCliente  String - tipo de subscricao
     * @param data         Local Date - data subscricao/alteracao
     */
    public Cliente(String id,String email, String palavraChave, String nome, String nif, String morada, String telefone, String tipoCliente, LocalDate data) {
        super(id,email, palavraChave, nome, nif, morada, telefone);
        this.tipoCliente = tipoCliente;
        this.data = data;
        this.palavraChave = palavraChave;
        this.id=id;

    }

    public String getPalavraChave() {
        return palavraChave;
    }


    public String getTipoCliente() {
        return tipoCliente;
    }


    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public ArrayList<Notificação> getNotificações() {
        return notificações;
    }

    @Override
    public String toString() {
        return "Cliente:" +
                "id=" + id +
                ",nome=" + nome +
                ",nif=" + nif +
                ",morada=" + morada  +
                ",telefone=" + telefone +
                ",email=" + email +
                ",palavraChave=" + palavraChave+
                ",data do registo="+data+
                ",tipo de cliente="+tipoCliente+"\n";
    }

    public String getId() {
        return id;
    }

}
