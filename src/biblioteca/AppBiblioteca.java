import biblioteca.controlador.Controlador;
import biblioteca.modelo.Modelo;
import biblioteca.vista.Vista;

public class AppBiblioteca {

    public static void main(String[] args) {

        try {
            Vista vista = new Vista();
            Modelo modelo = new Modelo();

            Controlador controlador = new Controlador(modelo, vista);
            controlador.comenzar();

        } catch (Exception e) {
            System.out.println("Error grave en la ejecución.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
