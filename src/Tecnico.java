public class Tecnico extends Persona {
    private String esquema;          // por ejemplo 4-3-3
    private int aniosExperiencia;

    public Tecnico(String nombre, int edad, String nacionalidad,
                   String esquema, int aniosExperiencia) {
        super(nombre, edad, nacionalidad);
        this.esquema = esquema;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getEsquema() { return esquema; }
    public int getAniosExperiencia() { return aniosExperiencia; }

    public void setEsquema(String esquema) { this.esquema = esquema; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    public String toCsv() {
        return nombre + "," + edad + "," + nacionalidad + "," + esquema + "," + aniosExperiencia;
    }

    public static Tecnico fromCsv(String linea) {
        String[] p = linea.split(",");
        return new Tecnico(p[0].trim(), Integer.parseInt(p[1].trim()), p[2].trim(),
                p[3].trim(), Integer.parseInt(p[4].trim()));
    }

    @Override
    public String toString() {
        return super.toString() + " | Esquema: " + esquema + " | Experiencia: " + aniosExperiencia + " años";
    }
}
