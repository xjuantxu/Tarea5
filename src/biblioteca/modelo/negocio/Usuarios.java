package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Usuario;

/**
 * Clase Usuarios (paquete negocio).
 * Gestiona la colección de usuarios de la biblioteca.
 * Permite alta, baja, búsqueda y listado de usuarios.
 */
public class Usuarios {

    //Atributos
    private Usuario[] usuarios;
    private int capacidad;

    //Constructor
    public Usuarios(int capacidad) {
        this.capacidad = capacidad;
        usuarios = new Usuario[capacidad];
    }

    // Alta de un usuario
    public void alta(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        for (int i = 0; i < capacidad; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = new Usuario(usuario); // copia profunda
                return;
            }
        }

        throw new IllegalStateException("No hay espacio para más usuarios");
    }

    // Baja de un usuario
    public boolean baja(Usuario usuario) {
        if (usuario == null) {
            return false;
        }

        for (int i = 0; i < capacidad; i++) {
            if (usuarios[i] != null && usuarios[i].equals(usuario)) {
                usuarios[i] = null;
                return true;
            }
        }
        return false;
    }

    // Búsqueda de un usuario
    public Usuario buscar(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        for (int i = 0; i < capacidad; i++) {
            if (usuarios[i] != null && usuarios[i].equals(usuario)) {
                return new Usuario(usuarios[i]); // copia profunda
            }
        }
        return null;
    }

    // Lista de usuarios
    public Usuario[] todos() {
        int contador = 0;

        for (Usuario u : usuarios) {
            if (u != null) {
                contador++;
            }
        }

        Usuario[] resultado = new Usuario[contador];
        int j = 0;

        for (Usuario u : usuarios) {
            if (u != null) {
                resultado[j++] = new Usuario(u); // copia profunda
            }
        }

        return resultado;
    }
}
