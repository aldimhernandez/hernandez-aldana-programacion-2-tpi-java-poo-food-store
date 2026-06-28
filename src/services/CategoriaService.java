package services;

import entities.Categoria;
import exceptions.CategoriaConProductosException;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.List;
import validations.Validation;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class CategoriaService extends BaseService<Categoria> {

    private final List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> listar() {

        List<Categoria> categoriasActivas = new ArrayList<>();

        for (Categoria categoria : categorias) {
            if (categoria.isActive()) {
                categoriasActivas.add(categoria);
            }
        }

        if (categoriasActivas.isEmpty()) {
            throw new EntidadInexistenteException("Aún no existen categorias");
        }

        return categoriasActivas;
    }

    public Categoria crear(String nombre, String descripcion) {
        nombre = Validation.validarTextoNoVacio(nombre, "nombre");
        descripcion = Validation.validarTextoNoVacio(descripcion, "descripción");

        if (existeCategoriaActivaConNombre(nombre)) {
            throw new EntidadDuplicadaException("Ya existe una categoría activa con ese nombre.");
        }

        Categoria categoria = new Categoria(nombre, descripcion);
        categorias.add(categoria);

        return categoria;
    }

    public Categoria editar() {
        //TODO: 
        System.out.println("CategoriaService editar");
        return null;
    }

    public Categoria eliminar(String id) {
        // ¿El valor ingresado por el usuario es valido? Si? Continuar No? throw new DatoInvalidoException
        id = Validation.validarTextoNoVacio(id, "id");

        // ¿El id existe? Si? Continuar No? throw new EntidadInexistenteException
        Categoria categoriaEncontrada = buscarPorId(categorias, id);

        // ¿Esta activa? Si? Continuar No? throw new EntidadInexistenteException
        if (!categoriaEncontrada.isActive()) {
            throw new EntidadInexistenteException("No existe una categoría con el ID: " + id);
        } // Por el momento le decimos al usuario que la Categoria no existe.

        // ¿No tiene productos activos asociados?
        // Si, no tiene.? Continuar No, si tiene.? throw new CategoriaConProductosException
        if (!categoriaEncontrada.isEliminable()) {
        // TODO: Probar esta validación al implementar crear Productos.
        // Debe prohibir la eliminación de categorias con productos asociados
            throw new CategoriaConProductosException(
                    "No se puede eliminar una categoria con productos asociados."
            );
        }
        // Obtener la categoria por id y cambiar isEliminado = true;
        categoriaEncontrada.setEliminado(true);

        return categoriaEncontrada;
    }

    private boolean existeCategoriaActivaConNombre(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.isActive() && categoria.tieneNombre(nombre)) {
                return true;
            }
        }
        return false;
    }
}
