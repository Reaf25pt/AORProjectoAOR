package programa;

import javax.swing.*;
import java.io.Serializable;

/**
 * Classe para objetos do tipo Autocarro, onde serao contidos, valores e metodos para o mesmo.
 */
public class Autocarro implements Serializable {

    private String lotacao;
    private String matricula;
    private String marca;
    private String modelo;

    /**
     * Instantiates a new Autocarro.
     */
    public Autocarro() {
    }

    /**Construtor autocarro
     *
     *
     * @param matricula String - recebe matricula do autocarro
     * @param marca     String - recebe marca do autocarro
     * @param modelo    String - recebe modelo do autocarro
     * @param lotacao   String - recebe lotacao do autocarro
     */
    public Autocarro( String matricula, String marca, String modelo,String lotacao) {
        this.lotacao = lotacao;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    /**
     * Gets lotacao.
     *
     * @return the lotacao
     */
    public String getLotacao() {
        return lotacao;
    }

    /**
     * Sets lotacao.
     *
     * @param lotacao the lotacao
     */
    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    /**
     * Gets matricula.
     *
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Sets matricula.
     *
     * @param matricula the matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Gets marca.
     *
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets marca.
     *
     * @param marca the marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Gets modelo.
     *
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets modelo.
     *
     * @param modelo the modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Autocarro{" +
                "lotacao=" + lotacao +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }


    //=================================================
    //Validar a matricula
    //========================================

    /**Metodo valida a matricula do autocarro, com base na existencia de @ e .
     *
     * @param matricula String - recebe matricula do autocarro
     * @return  boolean
     */
    public static boolean validarMatricula(String matricula) {
        int traco = 0;
        int numero = 0;
        int letra = 0;
        if (matricula.length() == 8) {
            for (int i = 0; i < matricula.length(); i++) {
                if (matricula.charAt(i) == '-') {
                    traco++;
                }
                if (Character.isDigit(matricula.charAt(i))) {
                    numero++;
                }
                if (Character.isLetter(matricula.charAt(i))) {
                    letra++;
                }

            }
        }
        if ((traco == 2) && (numero == letra * 2 || letra == numero * 2)) {
            return true;
        }
        return false;
    }

    /**Metodo valida a marca do autocarro - contem apenas letras
     *
     *
     * @param marca String - marca do autocarro
     * @return  boolean
     */
    public static boolean validarMarca(String marca) {
        int contador = 0;
        for (int i = 0; i < marca.length(); i++) {
            if (Character.isDigit(marca.charAt(i))) {
                contador++;
            }
        }
        if (contador == marca.length()) {
            return true;
        }
        return false;
    }

}