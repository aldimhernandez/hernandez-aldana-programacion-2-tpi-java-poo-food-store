package services;

import entities.Pedido;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class PedidoService {

    List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> listar() {
        // Se listan solo pedidos no eliminados.
        // Usuarios eliminados: Pedidos existentes del usuario deben seguir pudiendo consultarse (historial).
        List<Pedido> pedidosActivos = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.isActive()) {
                pedidosActivos.add(p);
            }
        }

        if (pedidosActivos.isEmpty()) {
            throw new EntidadInexistenteException("Aún no existen pedidos");
        }

        return pedidosActivos;
    }

    public void crear() {
        //TODO: 
        // Usuarios eliminados: No aparece en listados ni puede seleccionarse al crear pedidos.
        System.out.println("PedidoService crear");
    }

    public void editar() {
        //TODO: 
        System.out.println("PedidoService editar");
    }

    public void eliminar() {
        //TODO: 
        System.out.println("PedidoService eliminar");
    }
}
