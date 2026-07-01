package services;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class UsuarioService {

    public void listar() {
        //TODO: 
        System.out.println("UsuarioService listar");
    }

    public String crear(String nombre, String apellido, String mail, String celular) {
        String idGenerado = null;
        //TODO:
        // Validación de mail no vacío y formato básico (opcional).
        // El mail debe ser único:si ya existe, se informa el error y se solicita otro.
        // Se persiste el usuario y se informa el id generado.
        System.out.println("UsuarioService crear");
        return idGenerado;
    }

    public void editar() {
        //TODO: 
        System.out.println("UsuarioService editar");
    }

    public void eliminar() {
        //TODO: 
        System.out.println("UsuarioService eliminar");
    }
}
