package biblioteca.modelo.dominio;

import java.util.Arrays;
import java.util.Objects;

public class Libro {

    public static final String ISBN_PATTERN = "[0-9]{13}";
    public static final int MAX_AUTORES = 3;

    private String isbn;
    private String titulo;
    private int anio;
    private Categoria categoria;
    private Autor[] autores;
    private int numAutores;

    public Libro(String isbn, String titulo, int anio, Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.categoria = categoria;
        this.autores = new Autor[MAX_AUTORES];
        this.numAutores = 0;
    }

    // Constructor copia
    public Libro(Libro otro) {
        this.isbn = otro.isbn;
        this.titulo = otro.titulo;
        this.anio = otro.anio;
        this.categoria = otro.categoria;
        this.autores = new Autor[MAX_AUTORES];
        this.numAutores = otro.numAutores;

        for (int i = 0; i < otro.numAutores; i++) {
            this.autores[i] = new Autor(otro.autores[i]);
        }
    }

    /* ---------- GESTIÓN DE AUTORES ---------- */

    public boolean addAutor(Autor autor) {
        if (autor == null) {
            return false;
        }
        if (numAutores < MAX_AUTORES) {
            autores[numAutores++] = new Autor(autor);
            return true;
        }
        return false;
    }

    public boolean removeAutor(Autor autor) {
        if (autor == null) return false;

        for (int i = 0; i < numAutores; i++) {
            if (autores[i].equals(autor)) {
                // Desplazar elementos
                for (int j = i; j < numAutores - 1; j++) {
                    autores[j] = autores[j + 1];
                }
                autores[--numAutores] = null;
                return true;
            }
        }
        return false;
    }

    /* ---------- GETTERS ---------- */

    public String getISBN() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor[] getAutores() {
        Autor[] copia = new Autor[numAutores];
        for (int i = 0; i < numAutores; i++) {
            copia[i] = new Autor(autores[i]);
        }
        return copia;
    }

    /* ---------- EQUALS / HASHCODE ---------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    /* ---------- TOSTRING ---------- */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(titulo).append(" (").append(anio).append(") - ").append(categoria);
        sb.append("\nAutores: ");
        for (int i = 0; i < numAutores; i++) {
            sb.append(autores[i].toString());
            if (i < numAutores - 1) sb.append(", ");
        }
        sb.append("\nISBN: ").append(isbn);
        return sb.toString();
    }
}
