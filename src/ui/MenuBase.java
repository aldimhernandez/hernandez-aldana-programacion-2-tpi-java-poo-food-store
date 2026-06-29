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

    protected String solicitarTexto(String nombreCampo) {
        while (true) {
            String texto = sc.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.print("Ingrese un/a " + nombreCampo + " válido/a: ");
        }
    }

    // Sobrecarga de metodo solicitarTexto
    protected String solicitarTexto(String nombreCampo, List<String> opcionesValidas) {
        while (true) {
            String texto = sc.nextLine().trim();

            if (!texto.isEmpty() && opcionesValidas.contains(texto)) {
                return texto;
            }

            System.out.println("Ingrese un/a " + nombreCampo + " válido/a.");
            System.out.println("Las opciones válidas son: " + opcionesValidas);
            System.out.print("Ingrese " + nombreCampo + ": ");
        }
    }

    protected int solicitarEntero(String nombreCampo) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException nfe) {
                System.out.print("Ingrese un/a " + nombreCampo + " válido/a: ");
            }
        }
    }

    protected double solicitarDecimal(String nombreCampo) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException nfe) {
                System.out.print("Ingrese un/a " + nombreCampo + " válido/a: ");
            }
        }
    }

    public abstract void start();
}
