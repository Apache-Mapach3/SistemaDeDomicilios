public class Cola {
    private int cuenta = 0;
    private Nodo frente = null;
    private Nodo fin = null;

    public boolean esVacia() { return cuenta == 0; }

    public void encolar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setDerecha(nuevo);
            nuevo.setIzquierda(fin);
            fin = nuevo;
        }
        cuenta++;
    }

    public Object desencolar() throws Exception {
        if (esVacia()) throw new Exception("La cola está vacía");
        Object dato = frente.getDato();
        frente = frente.getDerecha();
        if (frente != null) {
            frente.setIzquierda(null);
        } else {
            fin = null;
        }
        cuenta--;
        return dato;
    }

    public Object peek() throws Exception {
        if (esVacia()) throw new Exception("La cola está vacía");
        return frente.getDato();
    }

    public int tamanio() { return cuenta; }
}