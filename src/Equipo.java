import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String pais;
    private String nombreTecnico;    // referencia al técnico por su nombre

    public Equipo(String nombre, String pais, String nombreTecnico) {
        this.nombre = nombre;
        this.pais = pais;
        this.nombreTecnico = nombreTecnico;
    }

    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public String getNombreTecnico() { return nombreTecnico; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPais(String pais) { this.pais = pais; }
    public void setNombreTecnico(String nombreTecnico) { this.nombreTecnico = nombreTecnico; }

    // Jugadores de la lista general que pertenecen a este equipo
    public List<JugadorFifa> plantilla(List<JugadorFifa> todos) {
        List<JugadorFifa> resultado = new ArrayList<>();
        for (JugadorFifa j : todos) {
            if (j.getEquipo().equalsIgnoreCase(nombre)) resultado.add(j);
        }
        return resultado;
    }

    public String toCsv() {
        return nombre + "," + pais + "," + nombreTecnico;
    }

    public static Equipo fromCsv(String linea) {
        String[] p = linea.split(",");
        return new Equipo(p[0].trim(), p[1].trim(), p[2].trim());
    }

    @Override
    public String toString() {
        return nombre + " (" + pais + ") | DT: " + nombreTecnico;
    }
}