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
    List<String> opcionesSiNo = List.of("si", "no");

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
        String nombre = solicitarTexto("nombre");

        System.out.print("Ingrese descripción: ");
        String descripcion = solicitarTexto("descripción");

        try {
            Categoria categoria = categoriaService.crear(nombre, descripcion);
            System.out.println("Categoría creada correctamente. ID generado: " + categoria.getId());
        } catch (DatoInvalidoException | EntidadInexistenteException | EntidadDuplicadaException mce) {
            System.out.println("Error: " + mce.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
    }

    @Override
    protected void editar() {
        System.out.println("=== EDITAR CATEGORÍA ===");

        // El sistema permite seleccionar la categoría por id (listando previamente de forma opcional).
        System.out.println();
        // TODO: Sí no hay categorias avisar y no solicitar id
        this.listar();

        System.out.print("Ingrese el ID de la categoria que desea modificar: ");
        String id = solicitarTexto("ID de categoria");

        try {
            Categoria categoriaEncontrada = categoriaService.obtenerPorId(id);

            System.out.print("Ingrese nombre: ");
            String nombre = solicitarTexto("nombre");

            System.out.print("Ingrese descripción: ");
            String descripcion = solicitarTexto("descripción");

            Categoria categoriaEditada = categoriaService.editar(categoriaEncontrada, nombre, descripcion);
            System.out.println(
                    "Categoría editada correctamente: "
                    + categoriaEditada.getNombre().toUpperCase()
            );
        } catch (DatoInvalidoException | EntidadInexistenteException | EntidadDuplicadaException mce) {
            System.out.println("Error: " + mce.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void eliminar() {
        // Al seleccionar “Eliminar”, se pide id y confirmación (S/N).
        System.out.println("=== ELIMINAR CATEGORÍA ===");

        System.out.println();
        this.listar();

        // Se pide id
        System.out.print("Ingrese el ID de la categoria que desea eliminar: ");
        String id = solicitarTexto("ID de categoria");

        // Se pide confirmación (S/N)
        System.out.println("¿Esta seguro que desea eliminar la categoría con ID: " + id);
        System.out.print("Ingrese si/no: ");
        String confirmacion = solicitarTexto("confirmación", opcionesSiNo);

        if (confirmacion.equalsIgnoreCase("no")) {
            System.out.println("Operación cancelada.");
            return;
        }

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
