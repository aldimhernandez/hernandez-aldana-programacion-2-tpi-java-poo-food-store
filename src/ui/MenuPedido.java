package ui;

import services.PedidoService;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class MenuPedido extends MenuCRUD {

    private final PedidoService pedidoService;
    
    public MenuPedido(PedidoService pedidoService) {
        super("=== MENÚ PEDIDO ===");
        this.pedidoService = pedidoService;
    }

    @Override
    protected void listar() {
        pedidoService.listar();
    }

    @Override
    protected void crear() {
        pedidoService.crear();
    }

    @Override
    protected void editar() {
        pedidoService.editar();
    }

    @Override
    protected void eliminar() {
        pedidoService.eliminar();
    }

}
