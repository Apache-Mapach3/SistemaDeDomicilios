public class Pila {
    private int cuenta = 0;
    private Nodo cima = null;

    public boolean esVacia() { return cuenta == 0; }
    public int tamanio() { return cuenta; }

    public void apilar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            cima = nuevo;
        } else {
            nuevo.setIzquierda(cima);
            cima.setDerecha(nuevo);
            cima = nuevo;
        }
        cuenta++;
    }

    public Object desapilar() throws Exception {
        if (esVacia()) throw new Exception("La pila está vacía");
        Object dato = cima.getDato();
        cima = cima.getIzquierda();
        if (cima != null) {
            cima.setDerecha(null);
        }
        cuenta--;
        return dato;
    }

    public Object peek() throws Exception {
        if (esVacia()) throw new Exception("La pila está vacía");
        return cima.getDato();
    }

    public boolean contiene(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getIzquierda();
        }
        return false;
    }

    public Object buscar(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return actual.getDato();
            actual = actual.getIzquierda();
        }
        return null;
    }

    public void limpiar() {
        cima = null;
        cuenta = 0;
    }

    public void mostrar() {
        Nodo actual = cima;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getIzquierda();
        }
    }
}