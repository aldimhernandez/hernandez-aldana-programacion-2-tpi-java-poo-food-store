package ui;

import entities.Usuario;
import exceptions.DatoInvalidoException;
import exceptions.EntidadDuplicadaException;
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
    private final List<String> opcionesSiNo = List.of("si", "no");

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
                System.out.println(usuario);
            }
        } catch (EntidadInexistenteException eie) {
            System.out.println("Advertencia: " + eie.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado. No se pudo completar la operación.");
        }
    }

    @Override
    protected void crear() {
        System.out.println("=== CREAR USUARIO ===");

        // El menú solicita: nombre, apellido, mail, celular (según UML).
        try {
            System.out.print("Nombre: ");
            String nombre = solicitarTexto("nombre");

            System.out.print("Apellido: ");
            String apellido = solicitarTexto("apellido");

            System.out.print("Celular: ");
            String celular = solicitarTexto("celular");

            boolean usuarioCreado = false;

            while (!usuarioCreado) {
                try {
                    // Validación de mail no vacío y formato básico (opcional).
                    System.out.print("Mail: ");
                    String mail = solicitarEmail();

                    String usuarioID = usuarioService.crear(nombre, apellido, mail, celular);

                    System.out.println("Usuario creado correctamente con id: " + usuarioID);

                    usuarioCreado = true;

                    // El mail debe ser único: si ya existe, se informa el error y se solicita otro.
                } catch (EntidadDuplicadaException ede) {
                    System.out.println("Error: " + ede.getMessage());
                    System.out.println("Ingrese otro mail.");
                }
            }

        } catch (DatoInvalidoException die) {
            System.out.println("Error: " + die.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void editar() {
        System.out.println("=== EDITAR USUARIO ===");

        // Selección por id.
        System.out.print("Ingrese el ID del usuario que desea modificar: ");
        String id = solicitarTexto("ID de usuario");

        try {
            // Si el id no existe o está eliminado, se informa
            Usuario usuarioEncontrado = usuarioService.obtenerPorId(id);

            System.out.println("Usuario encontrado:");
            System.out.println(usuarioEncontrado);

            String nuevoNombre = solicitarTextoOpcional(
                    "Nuevo nombre [actual: " + usuarioEncontrado.getNombre() + "]"
            );

            String nuevoApellido = solicitarTextoOpcional(
                    "Nuevo apellido [actual: " + usuarioEncontrado.getApellido() + "]"
            );

            String nuevoCelular = solicitarTextoOpcional(
                    "Nuevo celular [actual: " + usuarioEncontrado.getCelular() + "]"
            );

            String nuevoMail = solicitarTextoOpcional(
                    "Nuevo mail [actual: " + usuarioEncontrado.getMail() + "]"
            );

            boolean usuarioValido = false;

            while (!usuarioValido) {
                try {
                    if (nuevoMail != null) {
                        String nuevoMailConfirmado;
                        do {
                            System.out.println("Reingrese el mail para confirmar: ");
                            nuevoMailConfirmado = solicitarEmail();
                        } while (!nuevoMail.equalsIgnoreCase(nuevoMailConfirmado));
                    }

                    Usuario usuarioEditado = usuarioService.editar(
                            id,
                            nuevoNombre,
                            nuevoApellido,
                            nuevoMail,
                            nuevoCelular
                    );

                    // Se confirma actualización al finalizar.
                    System.out.println("Usuario editado correctamente.");
                    System.out.println(usuarioEditado);

                    usuarioValido = true;

                    // El mail debe ser único: si ya existe, se informa el error y se solicita otro.
                } catch (EntidadDuplicadaException ede) {
                    System.out.println("Error: " + ede.getMessage());
                    System.out.println("Ingrese otro mail.");
                }
            }

        } catch (DatoInvalidoException die) {
            System.out.println("Error: " + die.getMessage());
        } catch (EntidadInexistenteException eie) {
            // Si el id no existe o está eliminado, se informa
            System.out.println("Error: No existe un usuario activo con id: " + id);
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }

    @Override
    protected void eliminar() {
        System.out.println("=== ELIMINAR USUARIO ===");

        try {
            this.listar();

            // Se pide id y confirmación.
            System.out.print("Ingrese el ID del usuario que desea eliminar: ");
            String id = solicitarTexto("ID de usuario");

            System.out.println("¿Está seguro que desea eliminar el usuario con ID: " + id + "?");
            System.out.print("Ingrese si/no: ");
            String confirmacion = solicitarTexto("confirmación", opcionesSiNo);

            if (confirmacion.equalsIgnoreCase("no")) {
                System.out.println("Operación cancelada.");
                return;
            }

            Usuario usuarioEliminado = usuarioService.eliminar(id);

            System.out.println("Usuario eliminado correctamente:");
            System.out.println(usuarioEliminado);

        } catch (DatoInvalidoException | EntidadInexistenteException die) {
            System.out.println("Error: " + die.getMessage());
        } catch (RuntimeException re) {
            System.out.println("Error inesperado: " + re.getMessage());
        }
    }
}
