package biblioteca.vista;

import biblioteca.controlador.Controlador;
import biblioteca.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

/**
 * Clase Vista.
 * Gestiona la interacción con el usuario.
 * Llama a la Consola para la entrada de datos y al Controlador para ejecutar operaciones.
 */
public class Vista {

    //Atributos
    private Controlador controlador;
    private Consola consola;

    //Iniciamos la consola
    public Vista() {
        consola = new Consola();
    }

    //Asignamos controlador
    public void setControlador(Controlador controlador) {
        if (controlador == null)
            throw new IllegalArgumentException("Controlador no puede ser nulo");

        this.controlador = controlador;
    }

    //Comenzamos la interacción con el usuario
    public void comenzar() {
        int opcion;

        do {
            consola.mostrarMenu();
            opcion = Entrada.entero();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    //Terminamos la vista
    public void terminar() {
        consola.terminar();
    }

    //Ejecutamos la opción seleccionada
    private void ejecutarOpcion(int opcion) {

        switch (opcion) {

            case 0:
                controlador.terminar();
                break;

            case 1:
                insertarUsuario();
                break;

            case 2:
                borrarUsuario();
                break;

            case 3:
                listarUsuarios();
                break;

            case 4:
                insertarLibro();
                break;

            case 5:
                borrarLibro();
                break;

            case 6:
                listarLibros();
                break;

            case 7:
                nuevoPrestamo();
                break;

            case 8:
                devolverPrestamo();
                break;

            case 9:
                mostrarPrestamos();
                break;

            case 10:
                mostrarPrestamosUsuario();
                break;

            default:
                System.out.println("Opción no válida");
        }
    }

    //Métodos de usuarios
    private void insertarUsuario() {
        Usuario usuario = consola.nuevoUsuario(false);

        if (controlador.alta(usuario))
            System.out.println("Usuario insertado correctamente");
        else
            System.out.println("El usuario ya existe");
    }
    private void borrarUsuario() {
        Usuario usuario = consola.nuevoUsuario(true);

        if (controlador.baja(usuario))
            System.out.println("Usuario eliminado");
        else
            System.out.println("Usuario no encontrado");
    }
    private void listarUsuarios() {
        Usuario[] usuarios = controlador.listadoUsuarios();

        System.out.println("\n--- LISTADO DE USUARIOS ---");
        for (Usuario u : usuarios) {
            if (u != null)
                System.out.println(u);
        }
    }

    //Métodos de libros
    private void insertarLibro() {
        Libro libro = consola.nuevoLibro(false);

        if (controlador.alta(libro))
            System.out.println("Libro insertado correctamente");
        else
            System.out.println("El libro ya existe");
    }
    private void borrarLibro() {
        Libro libro = consola.nuevoLibro(true);

        if (controlador.baja(libro))
            System.out.println("Libro eliminado");
        else
            System.out.println("Libro no encontrado");
    }
    private void listarLibros() {
        Libro[] libros = controlador.listadoLibros();

        System.out.println("\n--- LISTADO DE LIBROS ---");
        for (Libro l : libros) {
            if (l != null)
                System.out.println(l);
        }
    }

    //Métodos de préstamos
    private void nuevoPrestamo() {

        System.out.println("--- NUEVO PRÉSTAMO ---");

        Usuario usuario = consola.nuevoUsuario(true);
        usuario = controlador.buscar(usuario);

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        Libro libro = consola.nuevoLibro(true);
        libro = controlador.buscar(libro);

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        LocalDate fecha = consola.leerFecha();

        if (controlador.prestar(libro, usuario, fecha))
            System.out.println("Préstamo realizado correctamente");
        else
            System.out.println("No se pudo realizar el préstamo");
    }
    private void devolverPrestamo() {

        System.out.println("--- DEVOLUCIÓN ---");

        Usuario usuario = consola.nuevoUsuario(true);
        usuario = controlador.buscar(usuario);

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        Libro libro = consola.nuevoLibro(true);
        libro = controlador.buscar(libro);

        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        LocalDate fecha = consola.leerFecha();

        if (controlador.devolver(libro, usuario, fecha))
            System.out.println("Préstamo devuelto correctamente");
        else
            System.out.println("No se encontró préstamo activo");
    }
    private void mostrarPrestamos() {

        Prestamo[] prestamos = controlador.listadoPrestamos();

        System.out.println("\n--- LISTADO DE PRÉSTAMOS ---");

        for (Prestamo p : prestamos) {
            if (p != null)
                System.out.println(p);
        }
    }
    private void mostrarPrestamosUsuario() {

        System.out.print("DNI del usuario: ");
        String dni = Entrada.cadena();

        Usuario usuario = controlador.buscar(new Usuario(dni));

        if (usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        Prestamo[] prestamos = controlador.listadoPrestamos(usuario);

        System.out.println("\n--- PRÉSTAMOS DEL USUARIO ---");

        for (Prestamo p : prestamos) {
            if (p != null)
                System.out.println(p);
        }
    }
}