package programa;

import java.io.Serializable;

/**
 * Classe para objetos do tipo Utilizador, onde serao contidos, valores e metodos para o mesmo.
 */
public class Utilizador implements Serializable {


    Aor_Autocarro aor_autocarro = new Aor_Autocarro();


    protected String email;


    protected String palavraChave;

    protected String nome;

    protected String nif;

    protected String morada;

    protected String telefone;

    protected String id;

    /**
     * Instantiates a new Utilizador.
     *
     * @param id           String  id - id do utilizador
     * @param email        String  email - email do utilizador
     * @param palavraChave String  palavra chave -palavra chave do utilizador
     * @param nome         String nome - nome do utilizador
     * @param nif          String nif - nif do utilizador
     * @param morada       String morada - morada do utilizador
     * @param telefone     String telefone - telefone do utilizador
     */
//Fostes
    public Utilizador(String id,String email, String palavraChave, String nome, String nif, String morada, String telefone) {
        this.email = email;
        this.palavraChave = palavraChave;
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.telefone = telefone;
    }

    /**
     * Instantiates a new Utilizador.
     */
    public Utilizador() {

    }

    /**Metodo validar email do utilizador :@ e .
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

    /**Metodo para validar telefone e nif : contem 9 numeros
     * .
     *
     * @param dadoNumerico String - dados do utilizador
     * @return  boolean
     */
    public static boolean validarTlfeNif(String dadoNumerico) {
        boolean validar=false;
        dadoNumerico=dadoNumerico.replaceAll("\\s","");
        int contadorNumeros=0;

        for(int i=0;i<dadoNumerico.length();i++){
            if(Character.isDigit(dadoNumerico.charAt(i))){
                contadorNumeros++;
            }
        }
        if(contadorNumeros==9){
            validar=true;
        }
        return validar;
    }

    /** Metodo para validar o nome: so tem letras
     *
     *
     * @param nome Strign - nome do utilizador
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
     * Get palavra chave string.
     *
     * @return the string
     */
    public String getPalavraChave(){
        return this.palavraChave;
    }

    /**
     * Sets palavra chave.
     *
     * @param palavraChave the palavra chave
     */
    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
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
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets nif.
     *
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * Sets nif.
     *
     * @param nif the nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Gets morada.
     *
     * @return the morada
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Sets morada.
     *
     * @param morada the morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Gets telefone.
     *
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Sets telefone.
     *
     * @param telefone the telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

}
