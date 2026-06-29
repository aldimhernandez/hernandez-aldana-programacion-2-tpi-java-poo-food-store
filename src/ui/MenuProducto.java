package ui;

import entities.Categoria;
import entities.Producto;
import exceptions.EntidadInexistenteException;
import java.util.List;
import services.CategoriaService;
import services.ProductoService;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class MenuProducto extends MenuCRUD {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public MenuProducto(ProductoService productoService, CategoriaService categoriaService) {
        super("=== MENÚ PRODUCTOS ===");
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @Override
    protected void listar() {
        System.out.println("=== MOSTRAR PRODUCTOS ===");
        productoService.listar();
    }

    @Override
    protected void crear() {
        System.out.println("=== CREAR PRODUCTO ===");

        // El menú solicita: 
        // nombre, descripción, precio, stock, imagen, estado de disponibilidad (booleano) y categoría (por id).
        System.out.print("Ingrese nombre: ");
        String n = solicitarTexto("nombre");

        System.out.print("Ingrese descripción: ");
        String des = solicitarTexto("descripción");

        System.out.print("Ingrese precio: ");
        double p = solicitarDecimal("precio");

        System.out.print("Ingrese stock: ");
        int s = solicitarEntero("stock");

        System.out.print("Ingrese imagen: ");
        String i = solicitarTexto("ruta al archivo de la imagen");

        List<String> opcionesDisponibilidad = List.of("si", "no");
        System.out.print("Ingrese disponibilidad (si/no): ");
        String dis = solicitarTexto("disponibilidad", opcionesDisponibilidad);
        String disNormalizado = dis.toLowerCase();

        System.out.println();
        this.listar();

        System.out.print("Ingrese ID de categoría: ");
        String idC = solicitarTexto("ID de categoría");

        try {
            // Si la categoría no existe o está eliminada, no se permite continuar.
            Categoria categoriaValida = categoriaService.obtenerCategoriaPorId(idC);
            // Creamos el producto y guardamos el id
            String idProdCreado = productoService.crear(n, des, p, s, i, disNormalizado, categoriaValida);
            System.out.println("Producto creado exitosamente. ID: " + idProdCreado);
        } catch (EntidadInexistenteException eie) {
            System.out.println("Error: No existe un Categoria con id: " + idC);
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void editar() {
        System.out.println("=== EDITAR PRODUCTO ===");
        productoService.editar();
    }

    @Override
    protected void eliminar() {
        System.out.println("=== ELIMINAR PRODUCTO ===");
        productoService.eliminar();
    }

}
