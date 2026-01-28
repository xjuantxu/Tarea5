package biblioteca.controlador;

import biblioteca.modelo.Modelo;
import biblioteca.vista.Vista;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.modelo.dominio.Prestamo;

import java.time.LocalDate;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null || vista == null) {
            throw new IllegalArgumentException("Modelo y/o Vista no pueden ser nulos");
        }
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        vista.terminar();
        modelo.terminar();
        System.out.println("Termina Controlador");
    }

    /* ---------- LIBROS ---------- */

    public void alta(Libro libro) {
        modelo.altaLibro(libro);
    }

    public boolean baja(Libro libro) {
        return modelo.bajaLibro(libro);
    }

    public Libro buscar(Libro libro) {
        return modelo.buscarLibro(libro);
    }

    public Libro[] listadoLibros() {
        return modelo.listadoLibros();
    }

    /* ---------- USUARIOS ---------- */

    public void alta(Usuario usuario) {
        modelo.altaUsuario(usuario);
    }

    public boolean baja(Usuario usuario) {
        return modelo.bajaUsuario(usuario);
    }

    public Usuario buscar(Usuario usuario) {
        return modelo.buscarUsuario(usuario);
    }

    public Usuario[] listadoUsuarios() {
        return modelo.listadoUsuarios();
    }

    /* ---------- PRÉSTAMOS ---------- */

    public boolean prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        return modelo.prestar(libro, usuario, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        return modelo.devolver(libro, usuario, fecha);
    }

    public Prestamo[] listadoPrestamos() {
        return modelo.listadoPrestamos();
    }
}
