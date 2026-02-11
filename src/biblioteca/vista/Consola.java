package biblioteca.vista;

import java.time.LocalDate;

import biblioteca.modelo.dominio.Autor;
import biblioteca.modelo.dominio.Categoria;
import biblioteca.modelo.dominio.Direccion;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Usuario;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    public void mostrarMenu() {
        System.out.println("\n--- MENÚ BIBLIOTECA ---");
        System.out.println("0 - Salir");
        System.out.println("1 - Insertar usuario");
        System.out.println("2 - Borrar usuario");
        System.out.println("3 - Mostrar usuarios");
        System.out.println("4 - Insertar libro");
        System.out.println("5 - Borrar libro");
        System.out.println("6 - Mostrar libros");
        System.out.println("7 - Nuevo préstamo");
        System.out.println("8 - Devolver préstamo");
        System.out.println("9 - Mostrar todos los préstamos");
        System.out.println("10 - Mostrar préstamos de un usuario");
        System.out.print("Seleccione opción: ");
    }

    public Usuario nuevoUsuario(boolean buscar) {
        System.out.print("DNI: ");
        String dni = Entrada.cadena();

        if (buscar) {
            return new Usuario(dni);
        }

        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();

        Usuario usuario = new Usuario(dni, nombre);

        System.out.print("Email: ");
        usuario.setEmail(Entrada.cadena());

        System.out.print("Vía: ");
        String via = Entrada.cadena();

        System.out.print("Número: ");
        String numero = Entrada.cadena();

        System.out.print("Piso: ");
        String piso = Entrada.cadena();

        System.out.print("CP: ");
        String cp = Entrada.cadena();

        System.out.print("Localidad: ");
        String localidad = Entrada.cadena();

        usuario.setDireccion(new Direccion(via, numero, piso, cp, localidad));

        return usuario;
    }

    public Libro nuevoLibro(boolean buscar) {
        System.out.print("ISBN: ");
        String isbn = Entrada.cadena();

        if (buscar) {
            return new Libro(isbn, "", 0, Categoria.OTROS);
        }

        System.out.print("Título: ");
        String titulo = Entrada.cadena();

        System.out.print("Año: ");
        int anio = Entrada.entero();

        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++)
            System.out.println(i + " - " + categorias[i]);

        int opcion = Entrada.entero();
        Categoria categoria = categorias[opcion];

        Libro libro = new Libro(isbn, titulo, anio, categoria);

        System.out.print("Número de autores (1-3): ");
        int numAutores = Entrada.entero();

        for (int i = 0; i < numAutores && i < Libro.MAX_AUTORES; i++)
            libro.addAutor(nuevoAutor());

        return libro;
    }

    public Autor nuevoAutor() {
        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();

        System.out.print("Apellidos: ");
        String apellidos = Entrada.cadena();

        System.out.print("Nacionalidad: ");
        String nacionalidad = Entrada.cadena();

        return new Autor(nombre, apellidos, nacionalidad);
    }

    public LocalDate leerFecha() {
        return LocalDate.now();
    }

    public void terminar() {
        System.out.println("Termina consola");
    }
}