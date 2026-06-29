package services;

import entities.Categoria;
import entities.Producto;
import validations.Validation;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class ProductoService {
    
    public void listar() {
        //TODO: 
        System.out.println("ProductoService listar");
    }
    
    public String crear(String n, String des, double p, int s, String i, String dis, Categoria c) {
        System.out.println("ProductoService crear");
        n = Validation.validarTextoNoVacio(n, "nombre");
        des = Validation.validarTextoNoVacio(des, "descripción");

        // Validaciones: precio >= 0 y stock >= 0; nombre no vacío.
        p = Validation.validarDecimalPositivo(p, "precio");
        s = Validation.validarEnteroPositivo(s, "stock");
        i = Validation.validarTextoNoVacio(i, "ruta al archivo de la imagen");

        // estado de disponibilidad (booleano)
        // viene como String hay que convertirlo a boolean
        dis = Validation.validarTextoNoVacio(dis, "disponibilida");

        // Convertimos el valor que viene como String a boolean
        boolean booleanDis = "si".equalsIgnoreCase(dis) ? true : false;

        // Se agrega el producto a la colección y se informa el id generado.
        Producto producto = new Producto(n, p, des, s, i, booleanDis, c);
        c.agregarProducto(producto);
        
        // Parseamos el id (Long) a String para poder devolverlo al menú
        String idProductoCreado = producto.getId().toString();
        
        return idProductoCreado;
    }
    
    public void editar() {
        //TODO: 
        System.out.println("ProductoService editar");
    }
    
    public void eliminar() {
        //TODO: 
        System.out.println("ProductoService eliminar");
    }
}
