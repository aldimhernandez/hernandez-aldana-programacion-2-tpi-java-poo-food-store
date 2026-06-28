package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class Categoria extends Base {

    private static Long categoriaIdCounter = 1L;

    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    public Categoria(String nombre, String descripcion) {
        super(categoriaIdCounter++);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    public boolean tieneNombre(String nombre) {
        return this.nombre.equalsIgnoreCase(nombre.trim());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public boolean productosActivos() {
        return productos.stream().anyMatch(Producto::isActive);
    }
    
    public boolean isEliminable() {
        return !productosActivos();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    @Override
    public String toString() {
        return String.format(
                "Categoria #%d | Nombre: %s | Descripción: %s | Productos: %d",
                getId(),
                nombre,
                descripcion,
                productos.size()
        );
    }

}
