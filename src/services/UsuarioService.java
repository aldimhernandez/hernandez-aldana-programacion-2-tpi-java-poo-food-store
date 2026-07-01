package services;

import entities.Usuario;
import exceptions.EntidadDuplicadaException;
import exceptions.EntidadInexistenteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import validations.Validation;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class UsuarioService extends BaseService<Usuario> {

    List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected List<Usuario> getLista() {
        return usuarios;
    }

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

    public String crear(String nombre, String apellido, String mail, String celular) {

        // El mail debe ser único: si ya existe, se informa el error y se solicita otro.
        if (existeMail(mail)) {
            throw new EntidadDuplicadaException("Ya existe un usuario con el mail: " + mail);
        }

        // Se persiste el usuario y se informa el id generado.
        Usuario usuarioCreado = new Usuario(nombre, apellido, mail, celular);
        usuarios.add(usuarioCreado);
        String idGenerado = usuarioCreado.getId().toString();

        return idGenerado;
    }

    public Usuario editar(String id, String nombre, String apellido, String mail, String celular) {
        Usuario usuarioEncontrado = obtenerPorId(id);

        if (nombre != null) {
            usuarioEncontrado.setNombre(nombre);
        }

        if (apellido != null) {
            usuarioEncontrado.setApellido(apellido);
        }

        if (mail != null) {
            mail = Validation.validarMail(mail, "mail");

            if (!usuarioEncontrado.getMail().equalsIgnoreCase(mail)
                    && existeMailEnOtroUsuario(mail, usuarioEncontrado.getId())) {
                throw new EntidadDuplicadaException("Ya existe otro usuario con el mail: " + mail);
            }

            usuarioEncontrado.setMail(mail);
        }

        if (celular != null) {
            usuarioEncontrado.setCelular(celular);
        }

        return usuarioEncontrado;
    }

    public void eliminar() {
        //TODO: 
        System.out.println("UsuarioService eliminar");
    }

    // Buscador de identificador unico en colecciones recomendado por la catedra
    private boolean existeMail(String mail) {
        boolean existe = false;
        String mailNormalizado = mail.trim();

        Iterator<Usuario> it = this.usuarios.iterator();

        while (it.hasNext() && !existe) {
            Usuario usuario = it.next();
            if (usuario.getMail().equalsIgnoreCase(mailNormalizado)) {
                existe = true;
            }
        }

        return existe;
    }

    private boolean existeMailEnOtroUsuario(String mail, Long idUsuarioActual) {
        boolean existe = false;
        String mailNormalizado = mail.trim();

        Iterator<Usuario> it = this.usuarios.iterator();

        while (it.hasNext() && !existe) {
            Usuario usuario = it.next();

            if (!usuario.getId().equals(idUsuarioActual)
                    && usuario.getMail().equalsIgnoreCase(mailNormalizado)) {
                existe = true;
            }
        }

        return existe;
    }
}
