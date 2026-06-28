package ui;

import entities.Categoria;
import exceptions.DatoInvalidoException;
import exceptions.EntidadDuplicadaException;
import services.CategoriaService;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class MenuCategoria extends MenuCRUD {

    private final CategoriaService categoriaService;

    public MenuCategoria(CategoriaService categoriaService) {
        super("=== MENÚ CATEGORÍAS ===");
        this.categoriaService = categoriaService;
    }

    @Override
    protected void listar() {
        categoriaService.listar();
    }

    @Override
    protected void crear() {
        System.out.println("=== CREAR CATEGORÍA ===");

        System.out.print("Ingrese nombre: ");
        String nombre = solicitarOpcion();

        System.out.print("Ingrese descripción: ");
        String descripcion = solicitarOpcion();

        try {
            Categoria categoria = categoriaService.crear(nombre, descripcion);
            System.out.println("Categoría creada correctamente. ID generado: " + categoria.getId());
        } catch (DatoInvalidoException | EntidadDuplicadaException mce) {
            System.out.println("Error: " + mce.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
    }

    @Override
    protected void editar() {
        categoriaService.editar();
    }

    @Override
    protected void eliminar() {
        categoriaService.eliminar();
    }
}
