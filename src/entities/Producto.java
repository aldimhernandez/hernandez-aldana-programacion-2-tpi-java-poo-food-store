package entities;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class Producto extends Base {

    // Atributos constantes de clase
    private static final String descripcionPorDefecto = "Sin descripción";
    private static final String imagenRutaPorDefecto = "src\\imagenes\\default.png";
    private static Long productoIdCounter = 1L;

    // Atributos de instancia
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    // Constructor 1
    public Producto(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) {
        super(productoIdCounter++);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    // Constructor 2
    public Producto(String nombre, double precio, int stock, Categoria categoria) {
        this(nombre, precio, descripcionPorDefecto, stock, imagenRutaPorDefecto, true, categoria);
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    // TPI: Cada fila muestra:
    // id, nombre, precio, stock y categoría asociada (al menos el id o nombre).
    @Override
    public String toString() {
        return String.format(
                "Producto #%d | Nombre: %s | Precio: $%.2f | Stock: %d | Disponible: %s | Categoría: %s",
                getId(),
                nombre,
                precio,
                stock,
                disponible ? "Sí" : "No",
                categoria.getNombre()
        );
    }
}
