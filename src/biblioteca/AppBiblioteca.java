import biblioteca.controlador.Controlador;
import biblioteca.modelo.Modelo;
import biblioteca.vista.Vista;

public class AppBiblioteca {

    static void main(String[] args) {

        Vista vista = new Vista();
        Modelo modelo = new Modelo();

        Controlador controlador = new Controlador(modelo, vista);

        controlador.comenzar();
    }
}
