
package menu;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import enums.Rol;
import java.util.ArrayList;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2
 * Trabajo Práctico Integrador
 * @author María Aldana Hernández
 * Cohorte Agosto 2025 - Comisión: 5
 * Matrícula 102505
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Instanciación requerida en main()
        // En el método main() se deben crear e interrelacionar los siguientes objetos:

        // Categoria 3 Con nombre y descripción distintos
        Categoria bebidas = new Categoria("Bebidas", "Bebidas frías y calientes");
        Categoria comidas = new Categoria("Comidas", "Comidas rápidas");
        Categoria postres = new Categoria("Postres", "Postres helados, fríos y calientes");

        // Producto 6 2 por cada categoría
        // Producto 1 categoria 1
        Producto soda = new Producto(
                "Agua con gas",
                1500.0,
                "Agua con gas",
                50,
                "src\\images\\aguaConGas.png",
                true,
                bebidas
        );
        bebidas.agregarProducto(soda);
        // Producto 2 categoria 1
        Producto cafe = new Producto(
                "Cafe",
                2000.0,
                "Cafe negro sin leche y sin azucar",
                100,
                "src\\images\\cafe.png",
                true,
                bebidas
        );
        bebidas.agregarProducto(cafe);
        // Producto 3 categoria 2
        Producto medialunaDulce = new Producto(
                "Medialuna Dulce",
                1000.0,
                "Panificado dulce simple",
                40,
                "src\\images\\medialunaManteca.png",
                true,
                comidas
        );
        comidas.agregarProducto(medialunaDulce);
        // Producto 4 categoria 2
        Producto medialunaSalada = new Producto(
                "Medialuna de Grasa",
                1000.0,
                "Panificado de grasa salado simple",
                40,
                "src\\images\\medialunaGrasa.png",
                true,
                comidas
        );
        comidas.agregarProducto(medialunaSalada);
        // Producto 5 categoria 3
        Producto heladoChocolate = new Producto(
                "Helado de Chocolate",
                2500.0,
                "Helado artesanal de chocolate",
                30,
                "src\\images\\heladoChocolate.png",
                true,
                postres
        );
        postres.agregarProducto(heladoChocolate);
        // Producto 6 categoria 3
        Producto flanCasero = new Producto(
                "Flan Casero",
                2200.0,
                "Flan casero con dulce de leche",
                25,
                "src\\images\\flanCasero.png",
                true,
                postres
        );
        postres.agregarProducto(flanCasero);
        // Usuario 2 Con Rol diferente (ADMIN / USUARIO)
        // Admin
        Usuario admin = new Usuario(
                "Adelo",
                "Adminter",
                "adm@adm.com",
                "+54 9 11 43258758",
                "cl4v3@",
                Rol.ADMIN
        );
        // Usuario 2 Con Rol diferente (ADMIN / USUARIO)
        // Usuario
        Usuario usuario1 = new Usuario(
                "Ursula",
                "Ulrich",
                "u.ursula@usuario.com",
                "+54 9 11 43258759",
                "cl4v3!",
                Rol.USUARIO
        );
        // Pedido 4 2 por cada usuario

        // Pedido 1 para usuario ADMIN
        Pedido adminPedido1 = new Pedido(admin, FormaPago.TRANSFERENCIA);
        // Pedido 2 para usuario ADMIN
        Pedido adminPedido2 = new Pedido(admin, FormaPago.EFECTIVO);
        // Pedido 1 para usuario USUARIO
        Pedido user1Pedido1 = new Pedido(usuario1, FormaPago.TARJETA);
        // Pedido 2 para usuario USUARIO
        Pedido user1Pedido2 = new Pedido(usuario1, FormaPago.TRANSFERENCIA);

        // DetallePedido 12 3 por cada pedido
        // ADMIN Pedido 1
        adminPedido1.addDetallePedido(1, cafe);
        adminPedido1.addDetallePedido(1, soda);
        adminPedido1.addDetallePedido(2, medialunaDulce);
        adminPedido1.setEstado(Estado.CONFIRMADO);
        // ADMIN Pedido 2
        adminPedido2.addDetallePedido(2, cafe);
        adminPedido2.addDetallePedido(1, heladoChocolate);
        adminPedido2.addDetallePedido(2, flanCasero);
        adminPedido2.setEstado(Estado.PENDIENTE);
        // USUARIO Pedido 1
        user1Pedido1.addDetallePedido(2, soda);
        user1Pedido1.addDetallePedido(2, cafe);
        user1Pedido1.addDetallePedido(2, medialunaSalada);
        user1Pedido1.setEstado(Estado.TERMINADO);
        // USUARIO Pedido 2
        user1Pedido2.addDetallePedido(2, soda);
        user1Pedido2.addDetallePedido(2, cafe);
        user1Pedido2.addDetallePedido(2, medialunaDulce);
        user1Pedido2.setEstado(Estado.CANCELADO);

        // Lista de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(admin);
        usuarios.add(usuario1);

        // Mostramos los pedidos de los usuarios
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
