package interfacegrafica;

import programa.*;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Aor_Autocarro aor_autocarro = new Aor_Autocarro();
        FicheiroDeObjectos ficheiroDeObjectos = new FicheiroDeObjectos();
        Aor_Autocarro aor_autocarro = ficheiroDeObjectos.leObjeto();

        if (aor_autocarro == null) {
            aor_autocarro = new Aor_Autocarro();
        } else {
            aor_autocarro.setUserLogado(null);
        }
        PainelFundo painelFundo = new PainelFundo(aor_autocarro);

        if (aor_autocarro.getUtilizadores().size() == 0) {
            Utilizador gerente = new Administrador("Adm0", "admin@gmail.com", "ADMIN2022", "Rodrigo Ferreira",
                    "123456789", "Coimbra", "967895432");
            aor_autocarro.getUtilizadores().add(gerente);

        }

        ficheiroDeObjectos.escreveObjeto(aor_autocarro);




    }

}