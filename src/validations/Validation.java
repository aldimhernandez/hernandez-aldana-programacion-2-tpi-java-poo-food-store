package validations;

import exceptions.DatoInvalidoException;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class Validation {

    public static String validarTextoNoVacio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DatoInvalidoException("El campo " + nombreCampo + " no puede estar vacío.");
        }

        return valor.trim();
    }

    public static double validarNumeroNoNegativo(double valor, String nombreCampo) {
        if (valor < 0) {
            throw new DatoInvalidoException("El campo " + nombreCampo + " no puede ser negativo.");
        }

        return valor;
    }

    public static int validarEnteroPositivo(int valor, String nombreCampo) {
        if (valor <= 0) {
            throw new DatoInvalidoException("El campo " + nombreCampo + " debe ser mayor a cero.");
        }

        return valor;
    }

    public static double validarDecimalPositivo(double valor, String nombreCampo) {
        if (valor <= 0) {
            throw new DatoInvalidoException("El campo " + nombreCampo + " debe ser mayor a cero.");
        }

        return valor;
    }
}
