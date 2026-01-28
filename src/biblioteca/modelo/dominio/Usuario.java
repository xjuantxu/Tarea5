package biblioteca.dominio;

import java.util.Objects;

public class Usuario {

    public static final String DNI_PATTERN = "[0-9]{8}[A-Z]";
    public static final String EMAIL_REGEX = ".+@.+\\..+";

    private String dni;
    private String nombre;
    private String email;
    private Direccion direccion;

    public Usuario(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    // Constructor copia
    public Usuario(Usuario otro) {
        this.dni = otro.dni;
        this.nombre = otro.nombre;
        this.email = otro.email;
        this.direccion = otro.direccion != null ? new Direccion(otro.direccion) : null;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion != null ? new Direccion(direccion) : null;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion != null ? new Direccion(direccion) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return dni + " - " + nombre;
    }
}
