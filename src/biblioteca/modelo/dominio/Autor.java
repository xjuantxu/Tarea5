package biblioteca.modelo.dominio;

import java.util.Objects;

public class Autor {

    private String nombre;
    private String apellidos;
    private String nacionalidad;

    public Autor(String nombre, String apellidos, String nacionalidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }

    // Constructor copia
    public Autor(Autor otro) {
        this.nombre = otro.nombre;
        this.apellidos = otro.apellidos;
        this.nacionalidad = otro.nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre)
                && Objects.equals(apellidos, autor.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos);
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " (" + nacionalidad + ")";
    }
}
