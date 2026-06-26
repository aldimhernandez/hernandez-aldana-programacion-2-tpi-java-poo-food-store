package entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 - Trabajo Práctico Integrador
 *
 * @author María Aldana Hernández - Cohorte Agosto 2025 - Comisión: 5 - Matrícula 102505
 */
public abstract class Base {

    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    /* Corrección examen parcial 2:
    1. Modificar la clase Base para incluir un constructor que reciba un Long id, 
    permitiendo a las subclases delegar explícitamente la identidad al padre como se solicitaba.
     */
    public Base(Long id) {
        this.id = id;
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public abstract String toString();

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Base other = (Base) obj;
        return Objects.equals(this.id, other.id);
    }
}
