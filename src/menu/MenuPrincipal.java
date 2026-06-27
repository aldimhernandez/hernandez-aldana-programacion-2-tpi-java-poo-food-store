package menu;

import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
  

    public class MenuPrincipal extends MenuBase {

        private static final String tituloMenu = "=== SISTEMA DE PEDIDOS (FOOD STORE) ===";
        private static final String mensajeSalida = "¡Gracias por usar SISTEMA DE PEDIDOS (FOOD STORE)!";
        private static final String opcionSalirLabel = "0. Salir";

        private static final List<String> opcionesMenu = List.of(
                "Categorías",
                "Productos",
                "Usuarios",
                "Pedidos"
        );

        private final MenuCategoria menuCategoria;
        private final MenuProducto menuProducto;
        private final MenuUsuario menuUsuario;
        private final MenuPedido menuPedido;

        public MenuPrincipal(
                MenuCategoria menuCategoria,
                MenuProducto menuProducto,
                MenuUsuario menuUsuario,
                MenuPedido menuPedido
        ) {
            this.menuCategoria = menuCategoria;
            this.menuProducto = menuProducto;
            this.menuUsuario = menuUsuario;
            this.menuPedido = menuPedido;
        }

        @Override
        public void start() {
            String opcion;

            do {
                estructurarMenu(tituloMenu, opcionesMenu, opcionSalirLabel);
                opcion = solicitarOpcion();

                System.out.println();

                switch (opcion) {
                    case "1" ->
                        menuCategoria.start();
                    case "2" ->
                        menuProducto.start();
                    case "3" ->
                        menuUsuario.start();
                    case "4" ->
                        menuPedido.start();
                    case "0" ->
                        System.out.println(mensajeSalida);
                    default ->
                        System.out.println("Opción inválida. Intente nuevamente.");
                }

                System.out.println();

            } while (!opcion.equals("0"));
        }
    }
