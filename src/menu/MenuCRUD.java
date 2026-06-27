package menu;

import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public abstract class MenuCRUD extends MenuBase {

    private final String titulo;
    private static final String opcionVolverMenuLabel = "0. Volver al menú principal";
    private static final List<String> opcionesSubMenu = List.of(
            "Listar",
            "Crear",
            "Editar",
            "Eliminar"
    );

    public MenuCRUD(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public final void start() {
        String opcion;

        do {
            estructurarMenu(titulo, opcionesSubMenu, opcionVolverMenuLabel);
            opcion = solicitarOpcion();

            System.out.println();

            switch (opcion) {
                case "1" ->
                    listar();
                case "2" ->
                    crear();
                case "3" ->
                    editar();
                case "4" ->
                    eliminar();
                case "0" ->
                    System.out.println("Volviendo al menú principal...");
                default ->
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println();

        } while (!opcion.equals("0"));
    }

    protected abstract void listar();

    protected abstract void crear();

    protected abstract void editar();

    protected abstract void eliminar();
}
