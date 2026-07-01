package entities;

import exceptions.DatoInvalidoException;
import validations.Validation;

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
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setDescripcion(descripcion);
        this.setStock(stock);
        this.setImagen(imagen);
        this.setDisponible(disponible);
        this.setCategoria(categoria);
    }

    // Constructor 2
    public Producto(String nombre, double precio, int stock, Categoria categoria) {
        this(nombre, precio, descripcionPorDefecto, stock, imagenRutaPorDefecto, true, categoria);
    }

    public static String getDescripcionPorDefecto() {
        return descripcionPorDefecto;
    }

    public static String getImagenRutaPorDefecto() {
        return imagenRutaPorDefecto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = Validation.validarTextoNoVacio(nombre, "nombre");
    }

    public void setPrecio(double precio) {
        this.precio = Validation.validarDecimalNoNegativo(precio, "precio");
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStock(int stock) {
        this.stock = Validation.validarEnteroNoNegativo(stock, "stock");
    }

    public void setImagen(String imagen) {
        this.imagen = Validation.validarTextoNoVacio(imagen, "imagen");
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new DatoInvalidoException("La categoría no puede ser nula.");
        }

        this.categoria = categoria;
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
