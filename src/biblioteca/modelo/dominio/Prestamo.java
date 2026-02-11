package biblioteca.modelo.dominio;

import java.time.LocalDate;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate inicio;
    private LocalDate fin;
    private boolean devuelto;

    public Prestamo(Libro libro, Usuario usuario, LocalDate inicio) {
        this.libro = new Libro(libro);       // copia profunda
        this.usuario = new Usuario(usuario);
        this.inicio = inicio;
        this.fin = null;
        this.devuelto = false;
    }

    // Constructor copia
    public Prestamo(Prestamo otro) {
        this.libro = new Libro(otro.libro);
        this.usuario = new Usuario(otro.usuario);
        this.inicio = otro.inicio;
        this.fin = otro.fin;
        this.devuelto = otro.devuelto;
    }

    public Libro getLibro() {
        return new Libro(libro);
    }

    public Usuario getUsuario() {
        return new Usuario(usuario);
    }

    public LocalDate getFinicio() {
        return inicio;
    }

    public LocalDate getFin(LocalDate fecha) {
        return fin;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void devolver(LocalDate fecha) {
        if (!devuelto) {
            this.fin = fecha;
            this.devuelto = true;
        }
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "libro=" + libro +
                ", usuario=" + usuario +
                ", inicio=" + inicio +
                ", fin=" + fin +
                ", devuelto=" + devuelto +
                '}';
    }
}
