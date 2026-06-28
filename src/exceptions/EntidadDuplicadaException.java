package exceptions;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class EntidadDuplicadaException extends RuntimeException {

    public EntidadDuplicadaException(String mensaje) {
        super(mensaje);
    }

    public EntidadDuplicadaException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntidadDuplicadaException(Throwable cause) {
        super(cause);
    }
}
