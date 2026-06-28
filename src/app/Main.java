package app;

import services.CategoriaService;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;
import ui.MenuCategoria;
import ui.MenuPedido;
import ui.MenuProducto;
import ui.MenuUsuario;
import ui.MenuPrincipal;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico Integrador
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService();
        UsuarioService usuarioService = new UsuarioService();
        PedidoService pedidoService = new PedidoService();

        MenuCategoria menuCategoria = new MenuCategoria(categoriaService);
        MenuProducto menuProducto = new MenuProducto(productoService);
        MenuUsuario menuUsuario = new MenuUsuario(usuarioService);
        MenuPedido menuPedido = new MenuPedido(pedidoService);

        MenuPrincipal menuPrincipal = new MenuPrincipal(
                menuCategoria,
                menuProducto,
                menuUsuario,
                menuPedido
        );

        menuPrincipal.start();
    }
}
