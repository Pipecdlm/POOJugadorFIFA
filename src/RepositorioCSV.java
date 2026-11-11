import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Repositorio genérico: sirve para jugadores, técnicos y equipos
public class RepositorioCSV<T> {
    private final Path ruta;
    private final String encabezado;
    private final Function<String, T> desdeCsv;
    private final Function<T, String> aCsv;

    public RepositorioCSV(String archivo, String encabezado,
                          Function<String, T> desdeCsv, Function<T, String> aCsv) {
        this.ruta = Paths.get(archivo);
        this.encabezado = encabezado;
        this.desdeCsv = desdeCsv;
        this.aCsv = aCsv;
    }

    public List<T> leer() {
        List<T> items = new ArrayList<>();
        try {
            if (!Files.exists(ruta)) {
                Files.write(ruta, List.of(encabezado));
                return items;
            }
            List<String> lineas = Files.readAllLines(ruta);
            for (int i = 1; i < lineas.size(); i++) {   // salta el encabezado
                if (lineas.get(i).isBlank()) continue;
                try {
                    items.add(desdeCsv.apply(lineas.get(i)));
                } catch (Exception e) {
                    System.out.println("Línea " + (i + 1) + " inválida en " + ruta + ", se omite.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo " + ruta + ": " + e.getMessage());
        }
        return items;
    }

    public void guardar(List<T> items) {
        List<String> lineas = new ArrayList<>();
        lineas.add(encabezado);
        for (T item : items) lineas.add(aCsv.apply(item));
        try {
            Files.write(ruta, lineas);
        } catch (IOException e) {
            System.out.println("Error guardando " + ruta + ": " + e.getMessage());
        }
    }
}