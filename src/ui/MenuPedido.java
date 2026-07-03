package ui;

import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
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
        System.out.println("=== EDITAR PEDIDO ===");

        try {
            System.out.println("Pedidos disponibles:");

            for (Pedido pedido : pedidoService.listar()) {
                System.out.println(pedido);
            }

            System.out.print("Ingrese el ID del pedido que desea modificar: ");
            String id = solicitarTexto("ID de pedido");

            Pedido pedidoEncontrado = pedidoService.obtenerPorId(id);

            System.out.println("Pedido encontrado:");
            System.out.println(pedidoEncontrado);

            Estado nuevoEstado = solicitarEstadoOpcional(pedidoEncontrado.getEstado());
            FormaPago nuevaFormaPago = solicitarFormaPagoOpcional(pedidoEncontrado.getFormaPago());

            if (nuevoEstado == null && nuevaFormaPago == null) {
                System.out.println("No se realizaron cambios.");
                return;
            }

            Pedido pedidoEditado = pedidoService.editar(id, nuevoEstado, nuevaFormaPago);

            System.out.println("Pedido actualizado correctamente:");
            System.out.println(pedidoEditado);

        } catch (DatoInvalidoException | EntidadInexistenteException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void eliminar() {
        System.out.println("=== ELIMINAR PEDIDO ===");

        //  Se pide id y confirmación.
        System.out.print("Ingrese el ID del pedido que desea eliminar: ");
        String id = solicitarTexto("ID de pedido");

        try {
            Pedido pedido = pedidoService.obtenerPorId(id);

            System.out.println("Pedido encontrado:");
            System.out.println(pedido);

            //  Se pide id y confirmación.
            System.out.println("¿Está seguro que desea eliminar el pedido con ID: " + id + "?");
            System.out.print("Ingrese si/no: ");
            String confirmacion = solicitarTexto("confirmación", List.of("si", "no"));

            if (confirmacion.equalsIgnoreCase("no")) {
                System.out.println("Operación cancelada.");
                return;
            }

            String pedidoEliminadoID = pedidoService.eliminar(id);

            System.out.println("Pedido eliminado correctamente. ID: " + pedidoEliminadoID);

        } catch (EntidadInexistenteException eie) {
            System.out.println("Error: No existe un pedido activo con id: " + id);
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
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

    private FormaPago solicitarFormaPagoOpcional(FormaPago formaPagoActual) {
        while (true) {
            System.out.println("Formas de pago disponibles:");
            System.out.println("0. Mantener forma de pago actual (" + formaPagoActual + ")");

            FormaPago[] formasPago = FormaPago.values();

            for (int i = 0; i < formasPago.length; i++) {
                System.out.println((i + 1) + ". " + formasPago[i]);
            }

            System.out.print("Seleccione nueva forma de pago: ");
            int opcion = solicitarEntero("forma de pago");

            if (opcion == 0) {
                return null;
            }

            if (opcion >= 1 && opcion <= formasPago.length) {
                return formasPago[opcion - 1];
            }

            System.out.println("Forma de pago inválida. Intente nuevamente.");
        }
    }

    private Estado solicitarEstadoOpcional(Estado estadoActual) {
        while (true) {
            System.out.println("Estados disponibles:");
            System.out.println("0. Mantener estado actual (" + estadoActual + ")");

            Estado[] estados = Estado.values();

            for (int i = 0; i < estados.length; i++) {
                System.out.println((i + 1) + ". " + estados[i]);
            }

            System.out.print("Seleccione nuevo estado: ");
            int opcion = solicitarEntero("estado");

            if (opcion == 0) {
                return null;
            }

            if (opcion >= 1 && opcion <= estados.length) {
                return estados[opcion - 1];
            }

            System.out.println("Estado inválido. Intente nuevamente.");
        }
    }
}
