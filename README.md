# Legends Fantasy NBA

Aplicación de escritorio en Java Swing para crear un equipo Fantasy con leyendas del baloncesto. El jugador explora el mercado, consulta fichas y valoraciones, elige hasta tres leyendas y compara su equipo.

## Requisitos de la rúbrica

| Requisito | Cómo se cumple |
| --- | --- |
| Imágenes incluidas en el proyecto | Las pantallas cargan imágenes desde `src/main/resources/images` mediante `getResource`. |
| Imágenes desde Internet | Iverson, Durant y Kareem tienen direcciones web directas en `modelo/RepositorioJugadores.java`; `ImageIO` las carga. Hace falta conexión a Internet. Si la descarga falla, se usa `default_player.png`. |
| Más de un `JFrame` | `VentanaInicio`, `VentanaMercado` y `VentanaEquipo` son ventanas independientes. |
| Cerrar la ventana actual | Al navegar a otra pantalla se abre la siguiente y se llama a `dispose()` en la actual. |
| Pasar parámetros entre ventanas | Las ventanas comparten el mismo `RepositorioJugadores` y `EquipoFantasy` mediante sus constructores. |
| Uso sencillo de `JDialog` | Los botones de detalles e informe abren diálogos modales con `JDialog`; no hay clases que hereden de `JDialog`. |
| Tamaño fijo | Las ventanas y los diálogos establecen su tamaño y desactivan el redimensionamiento. |
| `FlowLayout` | Se usa para organizar botones, filtros y las tarjetas del mercado. |

## Organización

- `Main.java`: inicia la aplicación.
- `modelo/`: jugadores, equipo y catálogo de leyendas.
- `gui/`: ventanas, tarjetas, estadísticas y estilos.
- `src/main/resources/images/`: imágenes incluidas en el proyecto.

## Ejecutar

Con Java 17 y Maven instalados, ejecuta `mvn exec:java` desde la carpeta que contiene `pom.xml`.

Las imágenes locales funcionan sin conexión. Las tres imágenes remotas necesitan Internet; sin conexión, se muestra la imagen de reserva.
