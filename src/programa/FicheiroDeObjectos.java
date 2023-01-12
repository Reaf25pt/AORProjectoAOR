package programa;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe Ficheiros de Objectos serve para escrever e ler no Ficheiro
 */
public class FicheiroDeObjectos {

    private  static ObjectInputStream iS;
    private static ObjectOutputStream oS;

    //metodo para abrir ficheiro para leitura
    private static void  abreLeitura(String nomeDoFicheiro) throws IOException {
        iS = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
    }

    //metodo para abrir ficheiro para escrita
    private static void  abreEscrita(String nomeDoFicheiro) throws IOException {
        oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
    }

    /** Metodo para ler um objeto do ficheiro
     *
     *
     * @return  aor autocarro
     */
//metodo para ler um objeto do ficheiro
    public static Aor_Autocarro leObjeto() {
        try {
            abreLeitura("AOR");
            Aor_Autocarro a=(Aor_Autocarro) iS.readObject();
            fechaLeitura();
            return a;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro" + e.getMessage());
            return null;
        }

    }

    /**metodo para escrever um objecto
     *
     *
     * @param aor_autocarro
     */

    public static void escreveObjeto(Aor_Autocarro aor_autocarro){
        try {
            abreEscrita("AOR");
            oS.writeObject(aor_autocarro);
            fechaEscrita();

        } catch (IOException e) {
            System.out.println("Erro" + e);
        }

    }

    //metodo para fechar um ficheiro aberto em modo leitura
    private static void fechaLeitura() throws IOException {
        iS.close();
    }

    //metodo para fechar um ficheiro aberto em modo escrita
    private static void fechaEscrita() throws IOException {
        oS.close();
    }
}
