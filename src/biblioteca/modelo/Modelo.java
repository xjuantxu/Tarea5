package biblioteca.modelo;

import biblioteca.modelo.negocio.Libros;
import biblioteca.modelo.negocio.Usuarios;
import biblioteca.modelo.negocio.Prestamos;

import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.modelo.dominio.Prestamo;

import java.time.LocalDate;

/**
 * Clase Modelo.
 * Gestiona el acceso a las colecciones de negocio (Usuarios, Libros, Prestamos)
 * y ofrece métodos para que el Controlador pueda realizar operaciones de alta,
 * baja, búsqueda y listado.
 */
public class Modelo {

    // Capacidad máxima de cada colección
    private static final int CAPACIDAD = 100;

    private Libros libros;
    private Usuarios usuarios;
    private Prestamos prestamos;

    // Constructor de modelo (Se inicia en comenzar())
    public Modelo() {}

    // Inicializa las colecciones
    public void comenzar() {
        libros = new Libros(CAPACIDAD);
        usuarios = new Usuarios(CAPACIDAD);
        prestamos = new Prestamos(CAPACIDAD);
    }

    // Termina el modelo
    public void terminar() {
        System.out.println("Termina Modelo");
    }

    // Métodos para usuarios
    public void altaUsuario(Usuario u) { usuarios.alta(u); }
    public boolean bajaUsuario(Usuario u) { return usuarios.baja(u); }
    public Usuario buscarUsuario(Usuario u) { return usuarios.buscar(u); }
    public Usuario[] listadoUsuarios() { return usuarios.todos(); }

    // Métodos para libros
    public void altaLibro(Libro l) { libros.alta(l); }
    public boolean bajaLibro(Libro l) { return libros.baja(l); }
    public Libro buscarLibro(Libro l) { return libros.buscar(l); }
    public Libro[] listadoLibros() { return libros.todos(); }

    // Métodos para préstamos
    public boolean prestar(Libro l, Usuario u, LocalDate f) {
        return prestamos.prestar(l, u, f) != null;
    }
    public boolean devolver(Libro l, Usuario u, LocalDate f) {
        return prestamos.devolver(l, u, f);
    }
    public Prestamo[] listadoPrestamos() {
        return prestamos.todos();
    }
    public Prestamo[] listadoPrestamos(Usuario u) {
        return prestamos.todos(u);
    }
}
