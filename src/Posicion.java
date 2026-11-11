public enum Posicion {
    PORTERO("POR"),
    DEFENSA("DEF"),
    MEDIOCAMPISTA("MED"),
    DELANTERO("DEL");

    private final String abreviatura;

    Posicion(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() { return abreviatura; }

    // Acepta el nombre completo o la abreviatura, sin importar mayúsculas
    public static Posicion desdeTexto(String texto) {
        String t = texto.trim();
        for (Posicion p : values()) {
            if (p.name().equalsIgnoreCase(t) || p.abreviatura.equalsIgnoreCase(t)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Posición inválida: " + texto);
    }
}