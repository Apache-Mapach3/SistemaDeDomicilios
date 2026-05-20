public class Domicilio {
    private String numeroOrden; // Identificador
    private String direccion;
    private String cliente;
    private String descripcion;

    public Domicilio(String numeroOrden, String direccion, String cliente, String descripcion) {
        this.numeroOrden = numeroOrden;
        this.direccion = direccion;
        this.cliente = cliente;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(String numeroOrden) { this.numeroOrden = numeroOrden; }
    public String getDireccion() { return direccion; }
    public String getCliente() { return cliente; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "Orden #" + numeroOrden + " | Cliente: " + cliente + " | Dirección: " + direccion + " | Detalle: " + descripcion;
    }
}