package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Usuario;

import java.time.LocalDate;
/**
 * Clase Prestamos (paquete negocio).
 * Gestiona la colección de préstamos de la biblioteca.
 * Permite crear, devolver y listar préstamos.
 */
public class Prestamos {

    //Atributos
    private Prestamo[] prestamos;
    private int capacidad;

    // Constructor
    public Prestamos(int capacidad) {
        this.capacidad = capacidad;
        prestamos = new Prestamo[capacidad];
    }

    // Realiza un préstamo
    public Prestamo prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null || fecha == null) {
            return null;
        }

        // Comprobar que el libro no esté ya prestado
        for (Prestamo p : prestamos) {
            if (p != null
                    && p.getLibro().equals(libro)
                    && p.getFin(fecha) == null) {
                return null; // libro ya prestado
            }
        }

        // Buscar hueco
        for (int i = 0; i < capacidad; i++) {
            if (prestamos[i] == null) {
                Prestamo nuevo = new Prestamo(libro, usuario, fecha);
                prestamos[i] = new Prestamo(nuevo); // copia profunda
                return new Prestamo(nuevo);          // copia para fuera
            }
        }

        return null; // sin espacio
    }

    // Devuelve un préstamo.
    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null || fecha == null) {
            return false;
        }
        {

            for (Prestamo p : prestamos) {
                if (p != null &&
                        p.getLibro().equals(libro) &&
                        p.getUsuario().equals(usuario) &&
                        !p.isDevuelto()) {

                    p.devolver(fecha);
                    return true;
                }
            }

            return false;
        }
    }

    // Lista todos los préstamos
    public Prestamo[] todos() {
        int contador = 0;

        for (Prestamo p : prestamos) {
            if (p != null) {
                contador++;
            }
        }

        Prestamo[] resultado = new Prestamo[contador];
        int i = 0;

        for (Prestamo p : prestamos) {
            if (p != null) {
                resultado[i++] = new Prestamo(p); // copia profunda
            }
        }

        return resultado;
    }

    // Lista los préstamos de un usuario
    public Prestamo[] todos(Usuario usuario) {
        if (usuario == null) {
            return new Prestamo[0];
        }

        int contador = 0;

        for (Prestamo p : prestamos) {
            if (p != null && p.getUsuario().equals(usuario)) {
                contador++;
            }
        }

        Prestamo[] resultado = new Prestamo[contador];
        int i = 0;

        for (Prestamo p : prestamos) {
            if (p != null && p.getUsuario().equals(usuario)) {
                resultado[i++] = new Prestamo(p); // copia profunda
            }
        }

        return resultado;
    }
}
