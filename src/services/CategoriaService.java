package services;

import entities.Categoria;
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
public class CategoriaService {

    private final List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> listar() {
        
        List<Categoria> categoriasActivas = new ArrayList<>();
        
        for (Categoria categoria : categorias) {
            if (categoria.estaActiva()) {
                categoriasActivas.add(categoria);
            }
        }
        
        if (categoriasActivas.isEmpty()) {
            throw new EntidadInexistenteException("Aún no existen categorias");
        }
        
        // TODO: Probar esta validación al implementar el método eliminar.
        // Debe listar solo categorías activas.
        
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

    public Categoria eliminar() {
        //TODO: 
        System.out.println("CategoriaService eliminar");
        return null;
    }

    private boolean existeCategoriaActivaConNombre(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.estaActiva() && categoria.tieneNombre(nombre)) {
                return true;
            }
        }

        return false;
    }
}
