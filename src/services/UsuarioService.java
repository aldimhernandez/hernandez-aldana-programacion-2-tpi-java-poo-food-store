package services;

import entities.Usuario;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.List;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class UsuarioService {

    List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> listar() {

        // Se listan solo usuarios no eliminados.
        List<Usuario> usuariosActivos = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.isActive()) {
                usuariosActivos.add(u);
            }
        }

        //  Si no hay usuarios, se informa adecuadamente.
        if (usuariosActivos.isEmpty()) {
            throw new EntidadInexistenteException("Aún no existen usuarios");
        }

        return usuariosActivos;
    }

    public void crear() {
        //TODO: 
        System.out.println("UsuarioService crear");
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
