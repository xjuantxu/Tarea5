package biblioteca.controlador;

import java.time.LocalDate;

import biblioteca.modelo.Modelo;
import biblioteca.vista.Vista;
import biblioteca.modelo.dominio.*;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null || vista == null)
            throw new IllegalArgumentException("Modelo y Vista no pueden ser null");

        this.modelo = modelo;
        this.vista = vista;
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
        System.out.println("Termina Controlador");
    }

    /* ===== USUARIOS ===== */

    public boolean alta(Usuario usuario) {
        if (modelo.buscarUsuario(usuario) != null) return false;
        modelo.altaUsuario(usuario);
        return true;
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

    /* ===== LIBROS ===== */

    public boolean alta(Libro libro) {
        if (modelo.buscarLibro(libro) != null) return false;
        modelo.altaLibro(libro);
        return true;
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

    /* ===== PRÉSTAMOS ===== */

    public boolean prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        return modelo.prestar(libro, usuario, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        return modelo.devolver(libro, usuario, fecha);
    }

    public Prestamo[] listadoPrestamos() {
        return modelo.listadoPrestamos();
    }

    public Prestamo[] listadoPrestamos(Usuario usuario) {
        return modelo.listadoPrestamos(usuario);
    }
}