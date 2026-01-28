package biblioteca.modelo;

import biblioteca.modelo.negocio.Libros;
import biblioteca.negocio.Usuarios;
import biblioteca.negocio.Prestamos;

import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.modelo.dominio.Prestamo;

import java.time.LocalDate;

public class Modelo {

    public static final int CAPACIDAD = 100;

    private Libros libros;
    private Usuarios usuarios;
    private Prestamos prestamos;

    public Modelo() {
        // Constructor vacío a propósito
    }

    public void comenzar() {
        libros = new Libros(CAPACIDAD);
        usuarios = new Usuarios(CAPACIDAD);
        prestamos = new Prestamos(CAPACIDAD);

        System.out.println("Modelo iniciado");
    }

    public void terminar() {
        System.out.println("Modelo terminado");
    }

    /* ---------- LIBROS ---------- */

    public void altaLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }
        libros.alta(libro);
    }

    public boolean bajaLibro(Libro libro) {
        if (libro == null) {
            return false;
        }
        return libros.baja(libro);
    }

    public Libro buscarLibro(Libro libro) {
        if (libro == null) {
            return null;
        }
        return libros.buscar(libro);
    }

    public Libro[] listadoLibros() {
        return libros.todos();
    }

    /* ---------- USUARIOS ---------- */

    public void altaUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        usuarios.alta(usuario);
    }

    public boolean bajaUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        return usuarios.baja(usuario);
    }

    public Usuario buscarUsuario(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return usuarios.buscar(usuario);
    }

    public Usuario[] listadoUsuarios() {
        return usuarios.todos();
    }

    /* ---------- PRÉSTAMOS ---------- */

    public boolean prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null || fecha == null) {
            return false;
        }
        return prestamos.prestar(libro, usuario, fecha).isDevuelto();
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null || fecha == null) {
            return false;
        }
        return prestamos.devolver(libro, usuario, fecha);
    }

    public Prestamo[] listadoPrestamos() {
        return prestamos.todos();
    }

    public Prestamo[] listadoPrestamos(Usuario usuario) {
        if (usuario == null) {
            return new Prestamo[0];
        }
        return prestamos.todos(usuario);
    }
}
