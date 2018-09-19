package ediciones.b.h.mitiempo;

import java.util.ArrayList;

/**
 * Created by V on 4/2/16.
 */
public class Tiempo {

    private String ciudad;
    private String dia;
    private ArrayList pronosticoHora;

    public Tiempo(String ciudad, String dia, ArrayList pronosticoHora) {
        this.ciudad = ciudad;
        this.dia = dia;
        this.pronosticoHora = pronosticoHora;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDia() {
        return dia;
    }

    public ArrayList getPronosticoHora() {
        return pronosticoHora;
    }
}
