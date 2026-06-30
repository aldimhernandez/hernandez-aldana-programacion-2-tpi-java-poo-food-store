package entities;

import interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import enums.Estado;
import enums.FormaPago;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class Pedido extends Base implements Calculable {

    private static Long pedidoIdCounter = 1L;
    /* Corrección examen parcial 2:
    2. Implementar un contador estático (detallIdCounter) dentro de la clase Pedido
    para auto-generar los IDs de los DetallePedido internamente, 
    reforzando el concepto de composición.
     */
    private static Long detalleIdCounter = 1L;

    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private List<DetallePedido> detalles;
    private Usuario usuario; // Cada pedido debe pertenecer a un usuario especifico (relación bidireccional).

    public Pedido(Usuario usuario, FormaPago formaPago) {
        super(pedidoIdCounter++);
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
        this.detalles = new ArrayList<>(); // Detalles se crea dentro de Pedido por su relación de composición
        this.setUsuario(usuario);
        this.setFormaPago(formaPago);
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser null.");
        }

        if (this.estado == Estado.CANCELADO) {
            throw new IllegalArgumentException("El pedido fue cancelado no puede cambiar de estado.");
        }

        if (this.estado == Estado.TERMINADO) {
            throw new IllegalArgumentException("El pedido esta cerrado no puede cambiar de estado.");
        }

        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario != null) {
            this.usuario = usuario;
            if (!usuario.getPedidos().contains(this)) {
                usuario.agregarPedido(this);
            }
        }
    }

    public void setFormaPago(FormaPago formaPago) {
        if (formaPago != null) {
            this.formaPago = formaPago;
        }
    }

    /*  addDetallePedido(int, Double, Producto): 
        crea un nuevo DetallePedido, lo agrega a la lista y recalcula el total. */
    public void addDetallePedido(int cantidad, Producto p) {
        DetallePedido nuevoDetalle = new DetallePedido(detalleIdCounter++, cantidad, p);
        detalles.add(nuevoDetalle);
        this.calcularTotal();
    }

    /*  findDetallePedidoByProducto(Producto): 
        recorre la lista y retorna el DetallePedido cuyo producto coincida por id. 
        Retorna null si no existe.*/
    public DetallePedido findDetallePedidoByProducto(Producto p) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(p.getId())) {
                return detalle;
            }
        }

        return null;
    }

    /*  deleteDetallePedidoByProducto(Producto): 
    elimina de la lista el detalle asociado al producto dado y recalcula el total.*/
    public void deleteDetallePedidoByProducto(Producto p) {
        DetallePedido detalleAEliminar = findDetallePedidoByProducto(p);

        if (detalleAEliminar != null) {
            detalles.remove(detalleAEliminar);
            this.calcularTotal();
        }
    }

    /*
    • Solo Pedido implementa Calculable.
    • El método calcularTotal() debe sumar los subtotales de todos los DetallePedido.
    • Debe invocarse automáticamente cada vez que se agrega o elimina un DetallePedido.
     */
    @Override
    public void calcularTotal() {
        double totalCalculado = 0;
        for (DetallePedido detalle : detalles) {
            totalCalculado += detalle.calcularSubtotal();
        }
        this.total = totalCalculado;
    }

    // Examen parcial 2: El toString() de Pedido debe mostrar al menos:
    // id, fecha, estado, formaPago y total.
    // TPI: Se muestra: id, usuario asociado, estado, forma de pago, total y fecha.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String separadorPedido = "-".repeat(80);

        sb.append(String.format(
                "> Pedido #%d | Usuario: %s | Estado: %s | FormaPago: %s | Fecha: %s%n",
                getId(),
                getUsuario(),
                estado,
                formaPago,
                fecha
        ));

        sb.append(String.format("%s%n", separadorPedido));

        for (DetallePedido detalle : detalles) {
            sb.append(String.format("  - %s%n", detalle));
        }

        sb.append(String.format("TOTAL DEL PEDIDO: $%.2f%n", total));
        sb.append(String.format("%s%n", separadorPedido));

        return sb.toString();
    }
}
