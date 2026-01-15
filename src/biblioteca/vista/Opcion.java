package biblioteca.vista;

    public enum Opcion {

        SALIR("Salir"),
        INSERTAR_USUARIO("Insertar usuario"),
        BORRAR_USUARIO("Borrar usuario"),
        MOSTRAR_USUARIOS("Mostrar usuarios"),
        INSERTAR_LIBRO("Insertar libro"),
        BORRAR_LIBRO("Borrar libro"),
        MOSTRAR_LIBROS("Mostrar libros"),
        NUEVO_PRESTAMO("Nuevo préstamo"),
        DEVOLVER_PRESTAMO("Devolver préstamo"),
        MOSTRAR_PRESTAMOS("Mostrar todos los préstamos"),
        MOSTRAR_PRESTAMOS_USUARIOS("Mostrar préstamos de un usuario");

        private final String descripcion;

        private Opcion(String descripcion) {
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }
