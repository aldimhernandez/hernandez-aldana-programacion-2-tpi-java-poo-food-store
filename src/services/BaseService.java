package services;

import entities.Base;
import exceptions.EntidadInexistenteException;
import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public abstract class BaseService<T extends Base> {

    protected T buscarPorId(List<T> lista, String id) {
        for (T entidad : lista) {
            if (entidad.tieneID(id)) {
                return entidad;
            }
        }
        throw new EntidadInexistenteException("No existe una entidad con el ID ingresado.");
    }
}
