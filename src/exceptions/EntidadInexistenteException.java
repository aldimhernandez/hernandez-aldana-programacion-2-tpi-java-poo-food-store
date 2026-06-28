package exceptions;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class EntidadInexistenteException extends RuntimeException {

    public EntidadInexistenteException(String message) {
        super(message);
    }

    public EntidadInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntidadInexistenteException(Throwable cause) {
        super(cause);
    }
}
