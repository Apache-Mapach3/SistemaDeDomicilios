public class Domicilio {
    private String numeroOrden;
    private String direccion;
    private String cliente;
    private String descripcion;

    public Domicilio(String numeroOrden, String direccion, String cliente, String descripcion) throws Exception {
        if(numeroOrden == null || numeroOrden.trim().isEmpty()){
            throw new Exception("El numero de orden es obligatorio");
        }
        if(cliente == null || cliente.trim().isEmpty()){
            throw new Exception("El cliente es obligatorio");
        }
        this.numeroOrden = numeroOrden;
        this.direccion = direccion;
        this.cliente = cliente;
        this.descripcion = descripcion;
    }

    public String getNumeroOrden() { return numeroOrden; }
    public void setNumeroOrden(String numeroOrden) { this.numeroOrden = numeroOrden; }
    public String getDireccion() { return direccion; }
    public String getCliente() { return cliente; }
    public String getDescripcion() { return descripcion; }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Domicilio otro = (Domicilio) obj;
        return numeroOrden.equals(otro.numeroOrden);
    }

    @Override
    public String toString() {
        return "\n---------------------------" +
                "\nNumero Orden: " + numeroOrden +
                "\nCliente: " + cliente +
                "\nDirección: " + direccion +
                "\nDescripción: " + descripcion +
                "\n---------------------------";
    }
}