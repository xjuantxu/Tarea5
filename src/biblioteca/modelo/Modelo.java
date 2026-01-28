package biblioteca.modelo;

import biblioteca.negocio.Libros;
import biblioteca.negocio.Usuarios;
import biblioteca.negocio.Prestamos;

import biblioteca.dominio.Libro;
import biblioteca.dominio.Usuario;
import biblioteca.dominio.Prestamo;

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
        libros.insertar(libro);
    }

    public boolean bajaLibro(Libro libro) {
        if (libro == null) {
            return false;
        }
        return libros.borrar(libro);
    }

    public Libro buscarLibro(Libro libro) {
        if (libro == null) {
            return null;
        }
        return libros.buscar(libro);
    }

    public Libro[] listadoLibros() {
        return libros.listar();
    }

    /* ---------- USUARIOS ---------- */

    public void altaUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        usuarios.insertar(usuario);
    }

    public boolean bajaUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        return usuarios.borrar(usuario);
    }

    public Usuario buscarUsuario(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return usuarios.buscar(usuario);
    }

    public Usuario[] listadoUsuarios() {
        return usuarios.listar();
    }

    /* ---------- PRÉSTAMOS ---------- */

    public boolean prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null || fecha == null) {
            return false;
        }
        return prestamos.prestar(libro, usuario, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null || fecha == null) {
            return false;
        }
        return prestamos.devolver(libro, usuario, fecha);
    }

    public Prestamo[] listadoPrestamos() {
        return prestamos.listar();
    }

    public Prestamo[] listadoPrestamos(Usuario usuario) {
        if (usuario == null) {
            return new Prestamo[0];
        }
        return prestamos.listar(usuario);
    }
}
