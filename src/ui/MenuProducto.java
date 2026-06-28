package ui;

import services.ProductoService;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class MenuProducto extends MenuCRUD {

    private final ProductoService productoService;
    
    public MenuProducto(ProductoService productoService) {
        super("=== MENÚ PRODUCTOS ===");
        this.productoService = productoService;
    }

    @Override
    protected void listar() {
        productoService.listar();
    }

    @Override
    protected void crear() {
        productoService.crear();
    }

    @Override
    protected void editar() {
        productoService.editar();
    }

    @Override
    protected void eliminar() {
        productoService.eliminar();
    }

}
