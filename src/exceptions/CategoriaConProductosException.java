package exceptions;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class CategoriaConProductosException extends RuntimeException {

    public CategoriaConProductosException(String message) {
        super(message);
    }

    public CategoriaConProductosException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoriaConProductosException(Throwable cause) {
        super(cause);
    }

}
