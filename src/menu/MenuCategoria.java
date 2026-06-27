package menu;

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
        categoriaService.crear();
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
