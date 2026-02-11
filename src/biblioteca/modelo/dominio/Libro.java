package biblioteca.modelo.dominio;

import java.util.Arrays;
import java.util.Objects;

public class Libro {

    public static final String PATRON_ISBN = "\\d{10}|\\d[13]";
    public static final int MAX_AUTORES = 3;

    private String isbn;
    private String titulo;
    private int anio;
    private Categoria categoria;
    private Autor[] autores;
    private int numAutores;

    /* ---------- CONSTRUCTOR PRINCIPAL ---------- */
    public Libro(String isbn, String titulo, int anio, Categoria categoria) {

        setIsbn(isbn);
        setTitulo(titulo);
        setAnio(anio);
        setCategoria(categoria);
        this.autores = new Autor[MAX_AUTORES];
        this.numAutores = 0;
    }

    public Libro(Libro libro) {
        this(libro.isbn, libro.titulo, libro.anio, libro.categoria);
        for (Autor a : libro.getAutores()) {
            addAutor(new Autor(a));
        }
    }



    /* ---------- GESTIÓN DE AUTORES ---------- */

    public void addAutor(Autor autor) throws IllegalArgumentException {
        if (autor == null) {
            throw new IllegalArgumentException("Autor no puede ser nulo");
        }

        for (int i = 0; i < MAX_AUTORES; i++) {
            if (autores[i] == null) {
                autores[i] = new Autor(autor); // copia profunda
                return;
            }
        }

        throw new IllegalStateException("Máximo número de autores alcanzado");
    }

    public void borrarAutor(Autor autor) {
        if (autor == null) return;

        for (int i = 0; i < MAX_AUTORES; i++) {
            if (autores[i] != null && autores[i].equals(autor)) {
                autores[i] = null;
                return;
            }
        }
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
        Autor[] copia = new Autor[MAX_AUTORES];
        for (int i = 0; i < MAX_AUTORES; i++) {
            if (autores[i] != null) {
                copia[i] = new Autor(autores[i]);
            }
        }
        return copia;
    }

    /* ---------- SETTERS ---------- */

    public void setIsbn(String isbn) throws IllegalArgumentException {
        if (isbn == null) throw new IllegalArgumentException("ISBN no puede ser nulo");
        if (isbn.trim().isEmpty()) throw new IllegalArgumentException("ISBN no puede estar vacío");
        //Si el ISBN no sigue el patrón lanza una excepción
        if (!isbn.matches(PATRON_ISBN)) throw new IllegalArgumentException("ISBN no válido");
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) throws IllegalArgumentException {
        if (titulo == null) throw new IllegalArgumentException("Título no puede ser nulo");
        if (titulo.trim().isEmpty()) throw new IllegalArgumentException("Título no puede estar vacío");
        this.titulo = titulo;
    }

    public void setAnio(int anio) throws IllegalArgumentException {
        if (anio <= 0) throw new IllegalArgumentException("Año inválido");
        this.anio = anio;
    }

    public void setCategoria(Categoria categoria) throws IllegalArgumentException {
        if (categoria == null) throw new IllegalArgumentException("Categoría no puede ser nulo");
        this.categoria = categoria;
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
    public int hashCode() {return Objects.hash(isbn);}

    /* ---------- TOSTRING ---------- */

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anio=" + anio +
                ", categoria=" + categoria +
                ", autores=" + Arrays.toString(autores) +
                '}';
    }
}

