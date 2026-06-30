package services;

import entities.Categoria;
import entities.Producto;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.List;
import validations.Validation;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class ProductoService extends BaseService<Producto> {

    List<Producto> productos = new ArrayList<>();

    public List<Producto> listar() {
        // Se muestran solo productos no eliminados.
        List<Producto> productosActivos = new ArrayList<>();

        for (Producto p : productos) {
            if (p.isActive()) {
                productosActivos.add(p);
            }
        }

        if (productosActivos.isEmpty()) {
            throw new EntidadInexistenteException("Aún no existen productos");
        }

        return productosActivos;
    }

    public String crear(String n, String des, double p, int s, String i, String dis, Categoria c) {
        n = Validation.validarTextoNoVacio(n, "nombre");
        des = Validation.validarTextoNoVacio(des, "descripción");

        // Validaciones: precio >= 0 y stock >= 0; nombre no vacío.
        p = Validation.validarDecimalNoNegativo(p, "precio");
        s = Validation.validarEnteroNoNegativo(s, "stock");
        i = Validation.validarTextoNoVacio(i, "ruta al archivo de la imagen");

        // estado de disponibilidad (booleano)
        // viene como String hay que convertirlo a boolean
        dis = Validation.validarTextoNoVacio(dis, "disponibilida");

        // Convertimos el valor que viene como String a boolean
        boolean booleanDis = "si".equalsIgnoreCase(dis);

        // Se agrega el producto a la colección y se informa el id generado.
        Producto producto = new Producto(n, p, des, s, i, booleanDis, c);
        // Agregamos el producto a la categoria correspondiente
        c.agregarProducto(producto);
        // Agregamos el producto a la lista de productos
        productos.add(producto);

        // Parseamos el id (Long) a String para poder devolverlo al menú
        String idProductoCreado = producto.getId().toString();

        return idProductoCreado;
    }

    public void editar() {
        //TODO: 
        System.out.println("ProductoService editar");
    }

    public Producto eliminar(String id) {

        // ¿El valor ingresado por el usuario es valido? Si? Continuar No? throw new DatoInvalidoException
        id = Validation.validarTextoNoVacio(id, "id");

        // ¿El id existe? Si? Continuar No? throw new EntidadInexistenteException
        Producto productoEncontrado = buscarPorId(productos, id);

        // ¿Esta activa? Si? Continuar No? throw new EntidadInexistenteException
        if (!productoEncontrado.isActive()) {
            throw new EntidadInexistenteException("No existe un producto con ID: " + id);
        } // Por el momento le decimos al usuario que el producto no existe.

        // La operación marca eliminado = true (sin remover físicamente el objeto de la colección).
        productoEncontrado.setEliminado(true);
        // No se muestra en listados posteriores --> Lógica en listar
        
        // TODO: Probar al implementar pedidos y detalles
        // Si el producto está referenciado en detalles de pedidos, 
        // no debe romperse la integridad de los datos (no remover de la colección).

        return productoEncontrado;
    }
}
