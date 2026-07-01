package services;

import entities.Base;
import exceptions.EntidadInexistenteException;
import java.util.List;
import validations.Validation;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public abstract class BaseService<T extends Base> {

    protected abstract List<T> getLista();

    protected T buscarPorId(List<T> lista, String id) {
        for (T entidad : lista) {
            if (entidad.tieneID(id)) {
                return entidad;
            }
        }
        throw new EntidadInexistenteException("No existe una entidad con el ID ingresado.");
    }

    public T obtenerPorId(String id) {
        id = Validation.validarTextoNoVacio(id, "id");

        T entidadEncontrada = buscarPorId(getLista(), id);

        if (!entidadEncontrada.isActive()) {
            throw new EntidadInexistenteException("No existe una entidad con el ID ingresado.");
        }

        return entidadEncontrada;
    }
}
