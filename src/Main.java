import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        RepositorioCSV<JugadorFifa> repoJ = new RepositorioCSV<>("jugadores.csv",
                "nombre,edad,nacionalidad,posicion,equipo,calificacion",
                JugadorFifa::fromCsv, JugadorFifa::toCsv);
        RepositorioCSV<Tecnico> repoT = new RepositorioCSV<>("tecnicos.csv",
                "nombre,edad,nacionalidad,esquema,aniosExperiencia",
                Tecnico::fromCsv, Tecnico::toCsv);
        RepositorioCSV<Equipo> repoE = new RepositorioCSV<>("equipos.csv",
                "nombre,pais,tecnico",
                Equipo::fromCsv, Equipo::toCsv);

        List<JugadorFifa> jugadores = repoJ.leer();
        List<Tecnico> tecnicos = repoT.leer();
        List<Equipo> equipos = repoE.leer();

        int opcion;
        do {
            System.out.println("\n=== FIFAS CSV MENÚ ===");
            System.out.println("1. Jugadores");
            System.out.println("2. Técnicos");
            System.out.println("3. Equipos");
            System.out.println("4. Ver plantilla de un equipo");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> menuJugadores(jugadores, repoJ);
                case 2 -> menuTecnicos(tecnicos, repoT);
                case 3 -> menuEquipos(equipos, tecnicos, repoE);
                case 4 -> verPlantilla(equipos, jugadores, tecnicos);
                case 0 -> System.out.println("Hasta pronto.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // ---------- Jugadores ----------
    private static void menuJugadores(List<JugadorFifa> jugadores, RepositorioCSV<JugadorFifa> repo) {
        System.out.println("\n-- JUGADORES --  1. Listar  2. Agregar  3. Eliminar  0. Volver");
        int op = leerEntero("Opción: ");
        switch (op) {
            case 1 -> listar(jugadores);
            case 2 -> {
                jugadores.add(new JugadorFifa(
                        leerTexto("Nombre: "), leerEntero("Edad: "), leerTexto("Nacionalidad: "),
                        leerPosicion(), leerTexto("Equipo: "), leerEntero("Calificación: ")));
                repo.guardar(jugadores);
                System.out.println("Jugador agregado.");
            }
            case 3 -> { if (eliminar(jugadores)) repo.guardar(jugadores); }
            case 0 -> { }
            default -> System.out.println("Opción no válida.");
        }
    }

    // ---------- Técnicos ----------
    private static void menuTecnicos(List<Tecnico> tecnicos, RepositorioCSV<Tecnico> repo) {
        System.out.println("\n-- TÉCNICOS --  1. Listar  2. Agregar  3. Eliminar  0. Volver");
        int op = leerEntero("Opción: ");
        switch (op) {
            case 1 -> listar(tecnicos);
            case 2 -> {
                tecnicos.add(new Tecnico(
                        leerTexto("Nombre: "), leerEntero("Edad: "), leerTexto("Nacionalidad: "),
                        leerTexto("Esquema (ej. 4-3-3): "), leerEntero("Años de experiencia: ")));
                repo.guardar(tecnicos);
                System.out.println("Técnico agregado.");
            }
            case 3 -> { if (eliminar(tecnicos)) repo.guardar(tecnicos); }
            case 0 -> { }
            default -> System.out.println("Opción no válida.");
        }
    }

    // ---------- Equipos ----------
    private static void menuEquipos(List<Equipo> equipos, List<Tecnico> tecnicos, RepositorioCSV<Equipo> repo) {
        System.out.println("\n-- EQUIPOS --  1. Listar  2. Agregar  3. Eliminar  0. Volver");
        int op = leerEntero("Opción: ");
        switch (op) {
            case 1 -> listar(equipos);
            case 2 -> {
                String nombre = leerTexto("Nombre del equipo: ");
                String pais = leerTexto("País: ");
                String dt = leerTexto("Nombre del técnico: ");
                if (buscarTecnico(tecnicos, dt) == null) {
                    System.out.println("Ese técnico no existe. Regístralo primero en el menú de técnicos.");
                    return;
                }
                equipos.add(new Equipo(nombre, pais, dt));
                repo.guardar(equipos);
                System.out.println("Equipo agregado.");
            }
            case 3 -> { if (eliminar(equipos)) repo.guardar(equipos); }
            case 0 -> { }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void verPlantilla(List<Equipo> equipos, List<JugadorFifa> jugadores, List<Tecnico> tecnicos) {
        String nombre = leerTexto("Nombre del equipo: ");
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("\n" + e);
                Tecnico t = buscarTecnico(tecnicos, e.getNombreTecnico());
                if (t != null) System.out.println("Técnico: " + t);
                List<JugadorFifa> plantilla = e.plantilla(jugadores);
                if (plantilla.isEmpty()) System.out.println("Sin jugadores registrados.");
                else plantilla.forEach(j -> System.out.println(" - " + j));
                return;
            }
        }
        System.out.println("Equipo no encontrado.");
    }

    private static Tecnico buscarTecnico(List<Tecnico> tecnicos, String nombre) {
        for (Tecnico t : tecnicos) {
            if (t.getNombre().equalsIgnoreCase(nombre)) return t;
        }
        return null;
    }

    // ---------- Utilidades ----------
    private static <T> void listar(List<T> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private static <T> boolean eliminar(List<T> lista) {
        listar(lista);
        if (lista.isEmpty()) return false;
        int n = leerEntero("Número a eliminar: ");
        if (n < 1 || n > lista.size()) {
            System.out.println("Número fuera de rango.");
            return false;
        }
        lista.remove(n - 1);
        System.out.println("Eliminado.");
        return true;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
            }
        }
    }

    private static Posicion leerPosicion() {
        while (true) {
            System.out.print("Posición (PORTERO/POR, DEFENSA/DEF, MEDIOCAMPISTA/MED, DELANTERO/DEL): ");
            try {
                return Posicion.desdeTexto(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Posición no válida, intenta de nuevo.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}