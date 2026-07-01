package ui;

import entities.Pedido;
import exceptions.EntidadInexistenteException;
import java.util.List;
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
        // TODO: Se permite filtrar por usuario (opcional).
        System.out.println("=== MOSTRAR PEDIDOS ===");
        try {
            List<Pedido> pedidos = pedidoService.listar();
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        } catch (EntidadInexistenteException eie) {
            System.out.println("Advertencia: " + eie.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
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
