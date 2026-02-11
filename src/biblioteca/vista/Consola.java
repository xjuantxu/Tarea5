package biblioteca.vista;

import java.time.LocalDate;
import java.util.InputMismatchException;

import biblioteca.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 * Clase Consola: gestiona la interacción con el usuario en la biblioteca.
 * Contiene métodos para mostrar menú, pedir datos de usuarios y libros,
 * y leer fechas de préstamos y devoluciones.
 */
public class Consola {

    // Muestra el menú principal
    public void mostrarMenu() {
        System.out.println("--- MENÚ BIBLIOTECA ---");
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

    //Crea un nuevo Usuario
    public Usuario nuevoUsuario(boolean buscar) {
        String dni;
        String nombre;
        String email;
        String via;
        String numero;
        String piso;
        String cp;
        String localidad;

        Direccion prueba = new Direccion("Calle Falsa", "123", "2º A", "12345", "Springfield");
        while (true) {
            System.out.print("ID: ");
            dni = Entrada.cadena();
            try {
                Usuario test = new Usuario(dni, "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que el ID es correcto y lanza una excepción en caso contrario
        if (buscar) {
            return new Usuario(dni);
        }
        while (true) {
            System.out.print("Nombre: ");
            nombre = Entrada.cadena();
            try {
                Usuario test = new Usuario(dni, nombre);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que el nombre es correcto y lanza una excepción en caso contrario
        Usuario usuario = new Usuario(dni, nombre);
        while (true) {
            System.out.print("Email: ");
            email = Entrada.cadena();
            try {
                Usuario test = new Usuario(dni, nombre);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que el email es correcto y lanza una excepción en caso contrario
        usuario.setEmail(email);

        while (true) {
            System.out.print("Vía: ");
            via = Entrada.cadena();
            try {
                Direccion prueba2 = new Direccion(via, "1", "2ºA", "00000", "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que la vía es correcta y lanza una excepción en caso contrario
        while (true) {
            System.out.print("Número: ");
            numero = Entrada.cadena();
            try {
                Direccion prueba2 = new Direccion(via, numero, "2ºA", "00000", "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que el número es correcto y lanza una excepción en caso contrario
        while (true) {
            System.out.print("Piso: ");
            piso = Entrada.cadena();
            try {
                Direccion prueba2 = new Direccion(via, numero, piso, "00000", "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que el piso es correcto y lanza una excepción en caso contrario
        while (true) {
            System.out.print("Código Postal: ");
            cp = Entrada.cadena();
            try {
                Direccion prueba2 = new Direccion(via, numero, piso, cp, "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que el código postal es correcto y lanza una excepción en caso contrario
        while (true) {
            System.out.print("Localidad: ");
            localidad = Entrada.cadena();
            try {
                Direccion prueba2 = new Direccion(via, numero, piso, cp, localidad);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba que la localidad es correcta y lanza una excepción en caso contrario
        usuario.setDireccion(new Direccion(via, numero, piso, cp, localidad));

        return usuario;
    }

    // Crea un nuevo Libro
    public Libro nuevoLibro(boolean buscar) {

        String isbn;
        String titulo;
        Categoria categoria = null;
        int entrada = 0;
        int anio = -1;
        int nAutores = 0;

        while (true) {
            System.out.print("ISBN: ");
            isbn = Entrada.cadena();
            try {
                Libro prueba = new Libro(isbn, "tmp", 1, Categoria.OTROS);
                ;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }//Bucle que comprueba que el ISBN es correcto y lanza una excepción en caso contrario
        if (buscar) {
            return new Libro(isbn, "", 0, Categoria.OTROS);
        }
        while (true) {
            System.out.print("Título: ");
            titulo = Entrada.cadena();
            try {
                Libro test = new Libro(isbn, titulo, 2025, Categoria.TECNICO);

                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }//Bucle que comprueba que el título es correcto y lanza una excepción en caso contrario


        while (anio <= 0) {
            System.out.print("Año: ");
            anio = Entrada.entero();
            try {
                Libro test = new Libro(isbn, titulo, anio, Categoria.TECNICO);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduce un numero entero");
            }
        } //Bucle que comprueba la validez del año y lanza excepción si no es válido

        System.out.println("Categorías disponibles:");
        int l = 1;
        for (Categoria cat : Categoria.values()) {
            System.out.println(l + ". " + cat);
            l++;
        }

        while ((entrada < 1) || (entrada > 7)) {

            try {
                System.out.print("Selecciona una categoría: ");
                entrada = Entrada.entero();
                if (entrada < 1 || entrada > 7) {
                    System.out.println("Categoría no válida.");
                } else {
                    categoria = Categoria.values()[entrada - 1];
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor. Introduce un número entero.");
            }
        } //Bucle que comprueba la validez de la categoría y lanza excepción si no es válida.

        Libro libro = new Libro(isbn, titulo, anio, categoria);

        while ((nAutores <= 0) || (nAutores > Libro.MAX_AUTORES)) {
            try {
                System.out.print("Número de autores: ");
                nAutores = Entrada.entero();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor. Introduce un número entero.");
            }
        }//Bucle que comprueba el número de autores y lanza excepción si no es válido

        for (int i = 0; i < nAutores && i < Libro.MAX_AUTORES; i++)
            libro.addAutor(nuevoAutor());

        return libro;
    }

    // Crea un nuevo Autor
    public Autor nuevoAutor() {
        String nombre;
        String apellidos;
        String nac;

        while (true) {
            System.out.print("Nombre autor: ");
            nombre = Entrada.cadena();
            try {
                Autor autor = new Autor(nombre, "tmp", "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba la validez del nombre y lanza excepción si no es válido.
        while (true) {
            System.out.print("Apellidos: ");
            apellidos = Entrada.cadena();
            try {
                Autor autor = new Autor("tmp", apellidos, "tmp");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba la validez de los apellidos y lanza excepción si no es válido.
        while (true) {
            System.out.print("Nacionalidad: ");
            nac = Entrada.cadena();
            try {
                Autor autor = new Autor("tmp", "tmp", nac);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } //Bucle que comprueba la validez de la nacionalidad y lanza excepción si no es válido.

        return new Autor(nombre, apellidos, nac);
    }

    // Devuelve la fecha actual
    public LocalDate leerFecha() {
        return LocalDate.now();
    }

    // Metodo de cierre de consola
    public void terminar() {
        System.out.println("Termina consola");
    }
}