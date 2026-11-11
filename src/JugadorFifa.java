public class JugadorFifa extends Persona {
    private Posicion posicion;
    private String equipo;      // nombre del equipo al que pertenece
    private int calificacion;

    public JugadorFifa(String nombre, int edad, String nacionalidad,
                       Posicion posicion, String equipo, int calificacion) {
        super(nombre, edad, nacionalidad);
        this.posicion = posicion;
        this.equipo = equipo;
        this.calificacion = calificacion;
    }

    public Posicion getPosicion() { return posicion; }
    public String getEquipo() { return equipo; }
    public int getCalificacion() { return calificacion; }

    public void setPosicion(Posicion posicion) { this.posicion = posicion; }
    public void setEquipo(String equipo) { this.equipo = equipo; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }

    public String toCsv() {
        return nombre + "," + edad + "," + nacionalidad + "," + posicion.name() + "," + equipo + "," + calificacion;
    }

    public static JugadorFifa fromCsv(String linea) {
        String[] p = linea.split(",");
        return new JugadorFifa(p[0].trim(), Integer.parseInt(p[1].trim()), p[2].trim(),
                Posicion.desdeTexto(p[3]), p[4].trim(), Integer.parseInt(p[5].trim()));
    }

    @Override
    public String toString() {
        return super.toString() + " | " + posicion.getAbreviatura() + " | " + equipo + " | Calificación: " + calificacion;
    }
}