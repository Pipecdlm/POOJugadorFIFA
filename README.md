# FIFAS-CSV-Menú

Aplicación de consola en Java para gestionar jugadores, técnicos y equipos de fútbol estilo FIFA. Los datos se guardan en archivos CSV, así que se conservan al cerrar el programa.

## Qué hace

- Registra, lista y elimina **jugadores**, **técnicos** y **equipos**.
- Muestra la **plantilla de un equipo**: su técnico y sus jugadores.
- Guarda todo automáticamente en archivos CSV (persistencia).
- Valida las entradas: números donde corresponde, posiciones válidas y técnicos existentes.

## Diseño de clases

| Clase | Descripción |
|---|---|
| `Persona` | Clase base: nombre, edad, nacionalidad |
| `JugadorFifa` | Hereda de `Persona`. Agrega posición, equipo y calificación |
| `Tecnico` | Hereda de `Persona`. Agrega esquema y años de experiencia |
| `Equipo` | Nombre, país y técnico. Devuelve su plantilla de jugadores |
| `Posicion` | Enum: PORTERO, DEFENSA, MEDIOCAMPISTA, DELANTERO |
| `RepositorioCSV<T>` | Repositorio genérico que lee y guarda cualquier clase en CSV |
| `Main` | Menú en consola |

## Archivos de datos

Se crean solos en la carpeta del proyecto la primera vez que se ejecuta:

- `jugadores.csv`
- `tecnicos.csv`
- `equipos.csv`

Los registros se enlazan por nombre: cada jugador guarda el nombre de su equipo y cada equipo guarda el nombre de su técnico.

## Cómo ejecutarlo

1. Clona el repositorio:
   ```
   git clone https://github.com/Pipecdlm/POOJugadorFIFA.git
   ```
2. Ábrelo en IntelliJ IDEA (o cualquier IDE de Java).
3. Ejecuta la clase `Main`.

También se puede compilar desde la terminal, dentro de la carpeta `src`:

```
javac *.java
java Main
```

## Ejemplo del menú

```
=== FIFAS CSV MENÚ ===
1. Jugadores
2. Técnicos
3. Equipos
4. Ver plantilla de un equipo
0. Salir
```

## Orden recomendado de uso

1. Registrar un técnico.
2. Registrar un equipo con el nombre exacto de ese técnico.
3. Registrar jugadores con el nombre exacto del equipo.
4. Consultar la plantilla desde la opción 4.

## Autor

[Andres Ortiz] · [20261007045 / Universidad Distrital]