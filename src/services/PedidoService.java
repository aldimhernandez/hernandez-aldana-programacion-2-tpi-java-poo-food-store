package services;

import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.FormaPago;
import exceptions.DatoInvalidoException;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class PedidoService extends BaseService<Pedido> {

    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    protected List<Pedido> getLista() {
        return pedidos;
    }

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

    public Pedido crear(
            Usuario usuario,
            FormaPago formaPago,
            List<Producto> productos,
            List<Integer> cantidades
    ) {
        validarDatosPedido(usuario, formaPago, productos, cantidades);

        Pedido pedidoCreado = null;

        try {
            pedidoCreado = new Pedido(usuario, formaPago);

            // Se calcula el subtotal de cada detalle al agregarlo (cantidad * precio del producto).
            // Responsabilidad del metodo: addDetallePedido
            for (int i = 0; i < productos.size(); i++) {
                pedidoCreado.addDetallePedido(cantidades.get(i), productos.get(i));
            }

            /*  Se debe utilizar obligatoriamente la interfaz Calculable y su método calcularTotal() 
            para establecer el total final del Pedido.*/
            pedidoCreado.calcularTotal();

            descontarStock(productos, cantidades);

            pedidos.add(pedidoCreado);

            return pedidoCreado;

            /*  Manejo de errores: 
            si ocurre una excepción al agregar un detalle (por ejemplo, por falta de
            stock), se debe capturar el error y cancelar la creación del pedido en 
            memoria para no dejar datos inconsistentes. */
        } catch (RuntimeException re) {
            if (pedidoCreado != null && usuario != null) {
                usuario.quitarPedido(pedidoCreado);
            }
            throw re;
        }
    }

    public void editar() {
        //TODO: 
        System.out.println("PedidoService editar");
    }

    public void eliminar() {
        //TODO: 
        System.out.println("PedidoService eliminar");
    }

    private void validarDatosPedido(
            Usuario usuario,
            FormaPago formaPago,
            List<Producto> productos,
            List<Integer> cantidades
    ) {
        if (usuario == null) {
            throw new DatoInvalidoException("Debe seleccionar un usuario.");
        }

        if (formaPago == null) {
            throw new DatoInvalidoException("Debe seleccionar una forma de pago.");
        }

        if (productos == null || productos.isEmpty()) {
            throw new DatoInvalidoException("El pedido debe tener al menos un detalle.");
        }

        if (cantidades == null || cantidades.isEmpty()) {
            throw new DatoInvalidoException("El pedido debe tener al menos una cantidad.");
        }

        if (productos.size() != cantidades.size()) {
            throw new DatoInvalidoException(
                    "La cantidad de productos no coincide con la cantidad de cantidades."
            );
        }
    }

    private void descontarStock(List<Producto> productos, List<Integer> cantidades) {
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            int cantidad = cantidades.get(i);

            producto.setStock(producto.getStock() - cantidad);
        }
    }
}
