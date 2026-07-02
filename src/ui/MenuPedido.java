package ui;

import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.FormaPago;
import exceptions.DatoInvalidoException;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.List;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class MenuPedido extends MenuCRUD {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    private final List<String> opcionesSiNo = List.of("si", "no");

    public MenuPedido(
            PedidoService pedidoService,
            UsuarioService usuarioService,
            ProductoService productoService
    ) {
        super("=== MENÚ PEDIDO ===");
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    @Override
    protected void listar() {
        // TODO: Se permite filtrar por usuario (opcional).
        System.out.println("=== MOSTRAR PEDIDOS ===");
        try {
            List<Pedido> pedidos = pedidoService.listar();
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        } catch (EntidadInexistenteException eie) {
            System.out.println("Advertencia: " + eie.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
    }

    @Override
    protected void crear() {
        System.out.println("=== CREAR PEDIDO ===");

        try {
            System.out.println("Usuarios disponibles:");
            for (Usuario usuario : usuarioService.listar()) {
                System.out.println(usuario);
            }

            // El menú permite seleccionar usuario por id (debe existir y no estar eliminado).
            System.out.print("Ingrese ID del usuario: ");
            String idUsuario = solicitarTexto("ID de usuario");
            // Usuarios eliminados: No aparece en listados ni puede seleccionarse al crear pedidos.
            Usuario usuario = usuarioService.obtenerPorId(idUsuario);

            FormaPago formaPago = solicitarFormaPago();

            List<Producto> productos = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();

            boolean agregarOtroDetalle = true;

            while (agregarOtroDetalle) {
                System.out.println("Productos disponibles:");
                for (Producto producto : productoService.listar()) {
                    System.out.println(producto);
                }

                System.out.print("Ingrese ID del producto: ");
                String idProducto = solicitarTexto("ID de producto");
                Producto producto = productoService.obtenerPorId(idProducto);

                System.out.print("Ingrese cantidad: ");
                int cantidad = solicitarEntero("cantidad");

                productos.add(producto);
                cantidades.add(cantidad);

                System.out.print("¿Desea agregar otro producto? si/no: ");
                String respuesta = solicitarTexto("respuesta", opcionesSiNo);

                /* Permite agregar 1..N detalles utilizando obligatoriamente 
                el método addDetallePedido(...) de la clase Pedido.*/
                agregarOtroDetalle = respuesta.equalsIgnoreCase("si");
            }

            Pedido pedidoCreado = pedidoService.crear(usuario, formaPago, productos, cantidades);

            System.out.println("Pedido creado correctamente:");
            System.out.println(pedidoCreado);

        } catch (DatoInvalidoException | EntidadInexistenteException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Se canceló la creación del pedido.");
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
            System.out.println("Se canceló la creación del pedido.");
        }
    }

    @Override
    protected void editar() {
        pedidoService.editar();
    }

    @Override
    protected void eliminar() {
        pedidoService.eliminar();
    }

    private FormaPago solicitarFormaPago() {
        while (true) {
            System.out.println("Formas de pago disponibles:");

            for (FormaPago formaPago : FormaPago.values()) {
                System.out.println("- " + formaPago);
            }

            System.out.print("Ingrese forma de pago: ");
            String valor = solicitarTexto("forma de pago").toUpperCase();

            try {
                return FormaPago.valueOf(valor);
            } catch (IllegalArgumentException iae) {
                System.out.println("Forma de pago inválida. Intente nuevamente.");
            }
        }
    }
}
