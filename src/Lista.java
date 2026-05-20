public class Lista {
    private int cuenta = 0;
    private Nodo primero = null;
    private Nodo ultimo = null;

    public boolean esVacia() { return cuenta == 0; }
    public int cuentaElementos() { return cuenta; }

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

    public void agregarAlInicio(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo objetivo = new Nodo(dato);
        if (esVacia()) {
            primero = objetivo;
            ultimo = objetivo;
        } else {
            objetivo.setDerecha(primero);
            primero.setIzquierda(objetivo);
            primero = objetivo;
        }
        cuenta++;
    }

    public void agregarEnPosicion(Object dato, int indice) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        if (indice < 0 || indice > cuenta) throw new Exception("Posición fuera de rango");
        if (indice == 0) { agregarAlInicio(dato); return; }
        if (indice == cuenta) { agregar(dato); return; }

        Nodo objetivo = new Nodo(dato);
        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        Nodo anterior = actual.getIzquierda();
        anterior.setDerecha(objetivo);
        objetivo.setIzquierda(anterior);
        objetivo.setDerecha(actual);
        actual.setIzquierda(objetivo);
        cuenta++;
    }

    public Object eliminarPrimero() throws Exception {
        if (esVacia()) throw new Exception("Lista vacía");
        Object dato = primero.getDato();
        if (cuenta == 1) {
            limpiar();
        } else {
            primero = primero.getDerecha();
            primero.setIzquierda(null);
        }
        cuenta--;
        return dato;
    }

    public Object eliminarUltimo() throws Exception {
        if (esVacia()) throw new Exception("Lista vacía");
        Object dato = ultimo.getDato();
        if (cuenta == 1) {
            limpiar();
        } else {
            ultimo = ultimo.getIzquierda();
            ultimo.setDerecha(null);
        }
        cuenta--;
        return dato;
    }

    public Object eliminarEnPosicion(int indice) throws Exception {
        if (esVacia()) throw new Exception("Lista vacía");
        if (indice < 0 || indice >= cuenta) throw new Exception("Posición fuera de rango");
        if (indice == 0) return eliminarPrimero();
        if (indice == cuenta - 1) return eliminarUltimo();

        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        Nodo anterior = actual.getIzquierda();
        Nodo siguiente = actual.getDerecha();
        anterior.setDerecha(siguiente);
        siguiente.setIzquierda(anterior);
        cuenta--;
        return actual.getDato();
    }

    public Object buscarDato(int indice) throws Exception {
        if (esVacia()) throw new Exception("La lista está vacía");
        if (indice < 0 || indice >= cuenta) throw new Exception("Posición fuera de rango");
        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        return actual.getDato();
    }

    public boolean buscarDato(Object dato) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getDerecha();
        }
        return false;
    }

    public boolean contiene(Object dato) {
        return buscarDato(dato);
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        cuenta = 0;
    }

    public void mostrarAdelante() {
        Nodo actual = primero;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getDerecha();
        }
    }

    public void mostrarAtras() {
        Nodo actual = ultimo;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getIzquierda();
        }
    }
}