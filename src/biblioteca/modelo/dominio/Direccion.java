package biblioteca.modelo.dominio;

public class Direccion {

    public static final String CP_PATTERN = "[0-9]{5}";

    private String via;
    private String numero;
    private String piso;
    private String cp;
    private String localidad;

    public Direccion(String via, String numero, String piso, String cp, String localidad) {
        this.via = via;
        this.numero = numero;
        this.piso = piso;
        this.cp = cp;
        this.localidad = localidad;
    }

    // Constructor copia
    public Direccion(Direccion otra) {
        this.via = otra.via;
        this.numero = otra.numero;
        this.piso = otra.piso;
        this.cp = otra.cp;
        this.localidad = otra.localidad;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return via + " " + numero + ", " + piso + " - " + cp + " " + localidad;
    }
}
