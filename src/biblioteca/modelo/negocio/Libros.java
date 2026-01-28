package biblioteca.negocio;

import biblioteca.dominio.Libro;

public class Libros {

    private Libro[] libros;
    private int capacidad;

    public Libros(int capacidad) {
        this.capacidad = capacidad;
        libros = new Libro[capacidad];
    }

    /* ---------- ALTA ---------- */
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

    /* ---------- BAJA ---------- */
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

    /* ---------- BUSCAR ---------- */
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

    /* ---------- LISTAR ---------- */
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
