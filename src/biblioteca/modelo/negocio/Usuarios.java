package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Usuario;

public class Usuarios {

    private Usuario[] usuarios;
    private int capacidad;

    public Usuarios(int capacidad) {
        this.capacidad = capacidad;
        usuarios = new Usuario[capacidad];
    }

    /* ---------- ALTA ---------- */
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

    /* ---------- BAJA ---------- */
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

    /* ---------- BUSCAR ---------- */
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

    /* ---------- LISTAR ---------- */
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
