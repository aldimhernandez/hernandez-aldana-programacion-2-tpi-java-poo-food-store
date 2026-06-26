package entities;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class DetallePedido extends Base {

    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(Long id, int cantidad, Producto producto) {
        super(id);
        this.setProducto(producto);
        this.setCantidad(cantidad);
    }

    // Cantidad - getter y setter
    public int getCantidad() {
        return cantidad;
    }

    /*  Los setters pueden incluir lógica de recalculo (ej: setCantidad() en DetallePedido
        recalcula subtotal) */
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.cantidad = cantidad;

        if (this.producto != null) {
            this.subtotal = calcularSubtotal();
        }
    }

    // Producto - getter y setter
    public Producto getProducto() {
        return producto;
    }

    /*
    3. Añadir el método setProducto(Producto) en DetallePedido
    para asegurar que el subtotal se recalcule si el producto asociado cambia.
     */
    public void setProducto(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        this.producto = p;
        if (this.cantidad > 0) {
            this.subtotal = calcularSubtotal();
        }
    }

    // Subtotal - getter y metodo de calculo
    public double getSubtotal() {
        return subtotal;
    }

    /*
    4. Considerar añadir validación para producto != null en DetallePedido.calcularSubtotal()
    para mayor robustez.
     */
    public double calcularSubtotal() {
        if (producto == null) {
            throw new IllegalStateException("No se puede calcular el subtotal porque el producto es nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalStateException("No se puede calcular el subtotal porque la cantidad no es válida.");
        }
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return String.format(
                "DetallePedido #%d: %s x %d => Subtotal: $%.2f",
                this.getId(),
                producto.getNombre(),
                cantidad,
                subtotal
        );
    }
}
