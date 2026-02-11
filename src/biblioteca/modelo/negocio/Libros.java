package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Libro;

/**
 * Clase Libros (paquete negocio).
 * Gestiona la colección de libros de la biblioteca.
 * Permite alta, baja, búsqueda y listado de libros.
 */
public class Libros {

    // Atributos
    private Libro[] libros;
    private int capacidad;

    //Constructor
    public Libros(int capacidad) {
        this.capacidad = capacidad;
        libros = new Libro[capacidad];
    }

    // Alta de un libro
    public void alta(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }

        for (int i = 0; i < capacidad; i++) {
            if (libros[i] == null) {
                libros[i] = new Libro(libro); // copia profunda
                return;
            }
        }

        throw new IllegalStateException("No hay espacio para más libros");
    }

    // Baja de un libro
    public boolean baja(Libro libro) {
        if (libro == null) {
            return false;
        }

        for (int i = 0; i < capacidad; i++) {
            if (libros[i] != null && libros[i].equals(libro)) {
                libros[i] = null;
                return true;
            }
        }
        return false;
    }

    // Búsqueda de un libro
    public Libro buscar(Libro libro) {
        if (libro == null) {
            return null;
        }

        for (int i = 0; i < capacidad; i++) {
            if (libros[i] != null && libros[i].equals(libro)) {
                return new Libro(libros[i]); // copia profunda
            }
        }
        return null;
    }

    // Lista de todos los libros
    public Libro[] todos() {
        int contador = 0;

        for (Libro l : libros) {
            if (l != null) {
                contador++;
            }
        }

        Libro[] resultado = new Libro[contador];
        int j = 0;

        for (Libro l : libros) {
            if (l != null) {
                resultado[j++] = new Libro(l); // copia profunda
            }
        }

        return resultado;
    }
}
