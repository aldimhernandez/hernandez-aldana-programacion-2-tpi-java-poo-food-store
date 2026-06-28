package ui;

import entities.Categoria;
import exceptions.DatoInvalidoException;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadInexistenteException;
import java.util.List;
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
        System.out.println("=== MOSTRAR CATEGORÍAS ===");
        try {
            List<Categoria> categorias = categoriaService.listar();
            for (Categoria categoria : categorias) {
                System.out.println(categoria);
            }
        } catch (EntidadInexistenteException eie) {
            System.out.println("Advertencia: " + eie.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
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
        System.out.println("=== ELIMINAR CATEGORÍA ===");

        System.out.println();
        this.listar();

        System.out.print("Ingrese el ID de la categoria que desea eliminar: ");
        String id = solicitarOpcion();

        try {
            Categoria categoria = categoriaService.eliminar(id);
            System.out.println("Categoría eliminada correctamente: " + categoria.getNombre().toUpperCase());
        } catch (DatoInvalidoException | EntidadInexistenteException mce) {
            System.out.println("Error: " + mce.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }
}
