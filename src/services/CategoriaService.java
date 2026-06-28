package services;

import entities.Categoria;
import exceptions.EntidadDuplicadaException;
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

    public Categoria listar() {
        //TODO: 
        System.out.println("CategoriaService listar");
        return null;
    }

    public Categoria crear(String nombre, String descripcion) {
        nombre = Validation.validarTextoNoVacio(nombre, "nombre");
        descripcion = Validation.validarTextoNoVacio(descripcion, "descripción");

        if (existeCategoriaActivaConNombre(nombre)) {
            throw new EntidadDuplicadaException("Ya existe una categoría activa con ese nombre.");
        }
        
        System.out.println(categorias.toString());

        Categoria categoria = new Categoria(nombre, descripcion);
        categorias.add(categoria);
        
        System.out.println(categorias.toString());
 
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
