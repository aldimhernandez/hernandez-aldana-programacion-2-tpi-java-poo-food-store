package ui;

import exceptions.DatoInvalidoException;
import java.util.List;
import java.util.Scanner;
import validations.Validation;

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

    protected String solicitarTextoOpcional(String nombreCampo) {
        System.out.print(nombreCampo + " (Enter para mantener): ");
        String valor = sc.nextLine().trim();

        if (valor.isBlank()) {
            return null;
        }

        return valor;
    }

    protected int solicitarEntero(String nombreCampo) {
        while (true) {
            String valor = sc.nextLine().trim();

            try {
                int numero = Integer.parseInt(valor);
                return Validation.validarEnteroNoNegativo(numero, nombreCampo);
            } catch (NumberFormatException nfe) {
                System.out.print("El campo " + nombreCampo + " debe ser un número entero. Intente nuevamente: ");
            } catch (DatoInvalidoException die) {
                System.out.print(die.getMessage() + " Intente nuevamente: ");
            }
        }
    }

    protected Integer solicitarEnteroOpcional(String nombreCampo) {
        System.out.print(nombreCampo + " (Enter para mantener): ");
        String valor = sc.nextLine().trim();

        if (valor.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException nfe) {
            throw new DatoInvalidoException("El campo " + nombreCampo + " debe ser un número entero.");
        }
    }

    protected double solicitarDecimal(String nombreCampo) {
        while (true) {
            String valor = sc.nextLine().trim();

            try {
                double numero = Double.parseDouble(valor);
                return Validation.validarDecimalNoNegativo(numero, nombreCampo);
            } catch (NumberFormatException nfe) {
                throw new DatoInvalidoException("El campo " + nombreCampo + " debe ser un número decimal.");
            } catch (DatoInvalidoException die) {
                System.out.print(die.getMessage() + " Intente nuevamente: ");
            }
        }
    }

    protected Double solicitarDecimalOpcional(String nombreCampo) {
        System.out.print(nombreCampo + " (Enter para mantener): ");
        String valor = sc.nextLine().trim();

        if (valor.isBlank()) {
            return null;
        }

        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException nfe) {
            throw new DatoInvalidoException("El campo " + nombreCampo + " debe ser un número decimal.");
        }
    }

    public abstract void start();
}
