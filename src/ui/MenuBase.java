package ui;

import java.util.List;
import java.util.Scanner;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public abstract class MenuBase {

    protected static final Scanner sc = new Scanner(System.in);

    private static final String accionSolicitadaLabel = "Seleccione: ";

    protected void mostrarOpciones(List<String> opcionesMenu) {
        for (int i = 0; i < opcionesMenu.size(); i++) {
            System.out.println((i + 1) + ". " + opcionesMenu.get(i));
        }
    }

    protected void estructurarMenu(String titulo, List<String> listaOpciones, String opcionSalida) {
        System.out.println(titulo);
        mostrarOpciones(listaOpciones);
        System.out.println(opcionSalida);
        System.out.print(accionSolicitadaLabel);
    }

    protected String solicitarOpcion() {
        return sc.nextLine().trim();
    }

    public abstract void start();
}
