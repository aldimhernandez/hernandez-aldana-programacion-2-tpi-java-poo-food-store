package ui;

import entities.Categoria;
import entities.Producto;
import exceptions.DatoInvalidoException;
import exceptions.EntidadDuplicadaException;
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
    private final MenuCategoria menuCategoria;
    List<String> opcionesSiNo = List.of("si", "no");

    public MenuProducto(ProductoService pS, CategoriaService cS, MenuCategoria mC) {
        super("=== MENÚ PRODUCTOS ===");
        this.productoService = pS;
        this.categoriaService = cS;
        this.menuCategoria = mC;
    }

    @Override
    protected void listar() {
        // TODO: Se permite listar por categoría (opcional) o listado general.
        System.out.println("=== MOSTRAR PRODUCTOS ===");
        try {
            List<Producto> productos = productoService.listar();
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        } catch (EntidadInexistenteException eie) {
            System.out.println("Advertencia: " + eie.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
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

        System.out.print("Ingrese disponibilidad (si/no): ");
        String dis = solicitarTexto("disponibilidad", opcionesSiNo);
        String disNormalizado = dis.toLowerCase();

        // TODO: Evitar dependencia entre MenuProducto y MenuCategoria.
        // Listar categorías directamente desde CategoriaService.
        System.out.println();
        menuCategoria.listar();

        System.out.print("Ingrese ID de categoría: ");
        String idC = solicitarTexto("ID de categoría");

        try {
            // Si la categoría no existe o está eliminada, no se permite continuar.
            Categoria categoriaValida = categoriaService.obtenerPorId(idC);
            // Creamos el producto y guardamos el id
            String idProdCreado = productoService.crear(n, des, p, s, i, disNormalizado, categoriaValida);
            System.out.println("Producto creado exitosamente. ID: " + idProdCreado);
        } catch (EntidadInexistenteException eie) {
            System.out.println("Error: No existe una Categoria con id: " + idC);
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void editar() {
        System.out.println("=== EDITAR PRODUCTO ===");

        // Se selecciona producto por id
        System.out.print("Ingrese el ID del producto que desea modificar: ");
        String id = solicitarTexto("ID de producto");

        try {
            // Si el id no existe o está eliminado, se informa y se cancela.
            Producto pE = productoService.obtenerPorId(id);

            System.out.println("Producto encontrado:");
            System.out.println(pE);

            String nuevoNombre = solicitarTextoOpcional(
                    "Nuevo nombre [actual: " + pE.getNombre() + "]"
            );

            String nuevaDescripcion = solicitarTextoOpcional(
                    "Nueva descripción [actual: " + pE.getDescripcion() + "]"
            );

            Double nuevoPrecio = solicitarDecimalOpcional(
                    "Nuevo precio [actual: " + pE.getPrecio() + "]"
            );

            Integer nuevoStock = solicitarEnteroOpcional(
                    "Nuevo stock [actual: " + pE.getStock() + "]"
            );

            String nuevaImagen = solicitarTextoOpcional(
                    "Nueva ruta al archivo de la imagen [actual: " + pE.getDescripcion() + "]"
            );

            System.out.println("Ingrese disponibilidad (si/no): ");
            String dis = solicitarTexto("disponibilidad", opcionesSiNo);
            String disNormalizado = dis.toLowerCase();

            // TODO: Evitar dependencia entre MenuProducto y MenuCategoria.
            // Listar categorías directamente desde CategoriaService.
            System.out.println();
            menuCategoria.listar();

            String nuevaCategoria = solicitarTextoOpcional(
                    "Nueva categoria [actual: " + pE.getCategoria().getId() + "]"
            );

            Categoria categoriaValida = null;
            if (nuevaCategoria != null) {
                categoriaValida = categoriaService.obtenerPorId(nuevaCategoria);
            }

            Producto productoEditado = productoService.editar(
                    id,
                    nuevoNombre,
                    nuevaDescripcion,
                    nuevoPrecio,
                    nuevoStock,
                    nuevaImagen,
                    disNormalizado,
                    categoriaValida
            );
            System.out.println("Producto actualizado correctamente:");
            System.out.println(productoEditado);
        } catch (DatoInvalidoException die) {
            System.out.println("Error: " + die.getMessage());
        } catch (EntidadInexistenteException eie) {
            System.out.println("Error: No existe un producto con id: " + id);
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void eliminar() {
        // Al seleccionar “Eliminar”, se pide id y confirmación (S/N).
        System.out.println("=== ELIMINAR PRODUCTO ===");

        // Se pide id
        System.out.print("Ingrese el ID del producto que desea eliminar: ");
        String id = solicitarTexto("ID de producto");

        // Se pide confirmación (S/N)
        System.out.println("¿Esta seguro que desea eliminar el producto con ID: " + id);
        System.out.print("Ingrese si/no: ");
        String confirmacion = solicitarTexto("confirmación", opcionesSiNo);

        if (confirmacion.equalsIgnoreCase("no")) {
            System.out.println("Operación cancelada.");
            return;
        }

        try {
            Producto producto = productoService.eliminar(id);
            System.out.println("Producto eliminado correctamente: " + producto.getNombre().toUpperCase());
        } catch (DatoInvalidoException die) {
            System.out.println("Error: " + die.getMessage());
        } catch (EntidadInexistenteException eie) {
            System.out.println("Error: No existe un producto con id: " + id);
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }
}
