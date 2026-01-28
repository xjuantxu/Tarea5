package biblioteca.vista;

import biblioteca.controlador.Controlador;
import biblioteca.modelo.dominio.*;
import biblioteca.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Vista {

    private Controlador controlador;

    public Vista() {
        // Constructor vacío
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador no puede ser nulo");
        }
        this.controlador = controlador;
    }

    /* ---------- INICIO / TERMINAR ---------- */
    public void comenzar() {
        Opcion opcion;
        do {
            opcion = mostrarMenu();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Fin de la Vista");
    }

    /* ---------- MENÚ ---------- */
    private Opcion mostrarMenu() {
        System.out.println("\n--- MENÚ BIBLIOTECA ---");
        for (Opcion op : Opcion.values()) {
            System.out.println(op.ordinal() + " - " + op);
        }
        System.out.print("Elige una opción: ");
        int eleccion = Entrada.entero();
        if (eleccion < 0 || eleccion >= Opcion.values().length) {
            System.out.println("Opción inválida, se tomará Salir");
            return Opcion.SALIR;
        }
        return Opcion.values()[eleccion];
    }

    public void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_USUARIO -> insertarUsuario();
            case BORRAR_USUARIO -> mostrarUsuario(); // se podría pedir DNI y borrar
            case MOSTRAR_USUARIOS -> listarUsuarios();
            case INSERTAR_LIBRO -> insertarLibro();
            case BORRAR_LIBRO -> mostrarLibro(); // pedir ISBN y borrar
            case MOSTRAR_LIBROS -> listarLibros();
            case NUEVO_PRESTAMO -> nuevoPrestamo();
            case DEVOLVER_PRESTAMO -> devolverPrestamo();
            case MOSTRAR_PRESTAMOS -> mostrarPrestamos();
            case MOSTRAR_PRESTAMOS_USUARIOS -> mostrarPrestamosUsuario();
            case SALIR -> terminar();
        }
    }

    /* ---------- USUARIOS ---------- */
    public void listarUsuarios() {
        Usuario[] usuarios = controlador.listadoUsuarios();
        System.out.println("\n--- LISTADO USUARIOS ---");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    public void mostrarUsuario() {
        System.out.print("Introduce DNI del usuario: ");
        String dni = Entrada.cadena();
        Usuario u = controlador.buscar(new Usuario(dni, ""));
        if (u != null) {
            System.out.println(u);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    public void insertarUsuario() {
        System.out.print("Introduce DNI: ");
        String dni = Entrada.cadena();
        System.out.print("Introduce nombre: ");
        String nombre = Entrada.cadena();
        controlador.alta(new Usuario(dni, nombre));
        System.out.println("Usuario insertado correctamente");
    }

    /* ---------- LIBROS ---------- */
    public void listarLibros() {
        Libro[] libros = controlador.listadoLibros();
        System.out.println("\n--- LISTADO LIBROS ---");
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    public void mostrarLibro() {
        System.out.print("Introduce ISBN del libro: ");
        String isbn = Entrada.cadena();
        Libro l = controlador.buscar(new Libro(isbn, "", 0, null));
        if (l != null) {
            System.out.println(l);
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void insertarLibro() {
        System.out.print("Introduce ISBN: ");
        String isbn = Entrada.cadena();
        System.out.print("Introduce título: ");
        String titulo = Entrada.cadena();
        System.out.print("Introduce año: ");
        int anio = Entrada.entero();
        System.out.print("Introduce categoría (NOVELA, ENSAYO, INFANTIL, COMIC, POESIA, TECNICO, OTROS): ");
        String cat = Entrada.cadena().toUpperCase();
        Libro l = new Libro(isbn, titulo, anio, Enum.valueOf(Categoria.class, cat));

        System.out.print("Número de autores (1-" + Libro.MAX_AUTORES + "): ");
        int n = Entrada.entero();
        if (n < 1) n = 1;
        if (n > Libro.MAX_AUTORES) n = Libro.MAX_AUTORES;

        for (int i = 0; i < n; i++) {
            System.out.println("Autor " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = Entrada.cadena();
            System.out.print("Apellidos: ");
            String apellidos = Entrada.cadena();
            System.out.print("Nacionalidad: ");
            String nac = Entrada.cadena();
            l.addAutor(new Autor(nombre, apellidos, nac));
        }

        controlador.alta(l);
        System.out.println("Libro insertado correctamente");
    }

    /* ---------- PRÉSTAMOS ---------- */
    public void nuevoPrestamo() {
        System.out.print("Introduce ISBN del libro: ");
        String isbn = Entrada.cadena();
        Libro l = controlador.buscar(new Libro(isbn, "", 0, null));
        if (l == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        System.out.print("Introduce DNI del usuario: ");
        String dni = Entrada.cadena();
        Usuario u = controlador.buscar(new Usuario(dni, ""));
        if (u == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        java.time.LocalDate hoy = java.time.LocalDate.now();
        boolean exito = controlador.prestar(l, u, hoy);
        System.out.println(exito ? "Préstamo realizado" : "No se pudo realizar el préstamo");
    }

    public void devolverPrestamo() {
        System.out.print("Introduce ISBN del libro: ");
        String isbn = Entrada.cadena();
        Libro l = controlador.buscar(new Libro(isbn, "", 0, null));
        if (l == null) {
            System.out.println("Libro no encontrado");
            return;
        }

        System.out.print("Introduce DNI del usuario: ");
        String dni = Entrada.cadena();
        Usuario u = controlador.buscar(new Usuario(dni, ""));
        if (u == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        java.time.LocalDate hoy = java.time.LocalDate.now();
        boolean exito = controlador.devolver(l, u, hoy);
        System.out.println(exito ? "Préstamo devuelto" : "No se pudo devolver");
    }

    public void mostrarPrestamos() {
        Prestamo[] prestamos = controlador.listadoPrestamos();
        System.out.println("\n--- LISTADO DE PRÉSTAMOS ---");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    public void mostrarPrestamosUsuario() {
        System.out.print("Introduce DNI del usuario: ");
        String dni = Entrada.cadena();
        Usuario u = controlador.buscar(new Usuario(dni, ""));
        if (u == null) {
            System.out.println("Usuario no encontrado");
            return;
        }

        Prestamo[] prestamos = controlador.listadoPrestamos(u);
        System.out.println("\n--- PRÉSTAMOS DEL USUARIO ---");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }
}