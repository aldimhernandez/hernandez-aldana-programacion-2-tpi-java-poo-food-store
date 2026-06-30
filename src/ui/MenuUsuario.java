package ui;

import entities.Usuario;
import exceptions.EntidadInexistenteException;
import java.util.List;
import services.UsuarioService;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class MenuUsuario extends MenuCRUD {

    private final UsuarioService usuarioService;

    public MenuUsuario(UsuarioService usuarioService) {
        super("=== MENÚ USUARIOS ===");
        this.usuarioService = usuarioService;
    }

    @Override
    protected void listar() {
        System.out.println("=== MOSTRAR USUARIOS ===");
        try {
            List<Usuario> usuarios = usuarioService.listar();
            for (Usuario usuario : usuarios) {
                System.out.println(usuarios);
            }
        } catch (EntidadInexistenteException eie) {
            System.out.println("Advertencia: " + eie.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
    }

    @Override
    protected void crear() {
        usuarioService.crear();
    }

    @Override
    protected void editar() {
        usuarioService.editar();
    }

    @Override
    protected void eliminar() {
        usuarioService.eliminar();
    }

}
