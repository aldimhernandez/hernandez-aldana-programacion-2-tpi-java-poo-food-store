package entities;

import java.util.ArrayList;
import java.util.List;
import enums.Rol;

/**
 * UTN - TUPAD - PROGRAMACIÓN 2 Trabajo Práctico N° -
 *
 * @author María Aldana Hernández Cohorte Agosto 2025 - Comisión: 5 Matrícula 102505
 */
public class Usuario extends Base {

    private static Long usuarioIdCounter = 1L;

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;
    private List<Pedido> pedidos; // Relación bidireccional usuario-pedido

    public Usuario(String nombre, String apellido, String mail, String celular, String contraseña, Rol rol) {
        super(usuarioIdCounter++);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contraseña;
        this.rol = rol;
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContraseña() {
        return contrasenia;
    }

    public boolean cambiarContrasenia(String contraseniaActual, String contraseniaNueva) {
        if (contraseniaActual == null || contraseniaNueva == null) {
            return false;
        }

        if (!this.contrasenia.equals(contraseniaActual)) {
            return false;
        }

        if (contraseniaNueva.isBlank()) {
            return false;
        }

        if (contraseniaNueva.equals(this.contrasenia)) {
            return false;
        }

        this.contrasenia = contraseniaNueva;
        return true;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // Aseguramos consistencia entre las entidades Usuario y Pedido
    public void agregarPedido(Pedido pedido) {
        if (pedido == null) {
            return;
        }

        if (pedido.getUsuario() != null && pedido.getUsuario() != this) {
            throw new IllegalArgumentException("El pedido ya pertenece a otro usuario.");
        }

        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
            pedido.setUsuario(this);
        }
    }

    // TPI: Se muestra: id, nombre, apellido, mail, rol (si aplica en el UML).
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String separador = "=".repeat(90);
        double totalAcumulado = 0.0;

        sb.append(String.format("%s%n", separador));

        sb.append(String.format(
                "USUARIO: %s %s | ID: %d | Mail: %s | Rol: %s%n",
                getNombre(),
                getApellido(),
                getId(),
                getMail(),
                getRol()
        ));

        sb.append(String.format("%s%n", separador));

        for (Pedido pedido : pedidos) {
            sb.append(String.format("%s%n", pedido));
            totalAcumulado += pedido.getTotal();
        }

        sb.append(String.format("TOTAL ACUMULADO del usuario: $%.2f%n", totalAcumulado));
        sb.append(String.format("%s%n", separador));

        return sb.toString();
    }
}
