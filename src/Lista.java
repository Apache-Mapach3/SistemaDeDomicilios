public class Lista {
    private int cuenta = 0;
    private Nodo primero = null;
    private Nodo ultimo = null;

    public boolean esVacia() { return cuenta == 0; }

    public void agregar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo objetivo = new Nodo(dato);
        if (esVacia()) {
            primero = objetivo;
            ultimo = objetivo;
        } else {
            ultimo.setDerecha(objetivo);
            objetivo.setIzquierda(ultimo);
            ultimo = objetivo;
        }
        cuenta++;
    }

    public int cuentaElementos() { return cuenta; }

    public Object buscarDato(int indice) throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");
        if (indice < 0 || indice >= cuenta) throw new Exception("Posición fuera de rango");
        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        return actual.getDato();
    }
}