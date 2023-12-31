package tde02;
/**
 *
 * @author RAFMo
 */
class ListaEncadeada {
    Celula frente;

    public ListaEncadeada() {
        frente = null;
    }

    public void push(Integer elemento) {
        Celula newCelula = new Celula(elemento);
        newCelula.proximo = frente;
        frente = newCelula;
    }

    public Integer pop() {
        if (vazia()) {
            return null;
        }
        Integer data = frente.data;
        frente = frente.proximo;
        return data;
    }

    public boolean vazia() {
        return frente == null;
    }

    public void print() {
        Celula current = frente;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.proximo;
        }
        System.out.println();
    }
    public int size() {
        int count = 0;
        Celula current = frente;
        while (current != null) {
            count++;
            current = current.proximo;
        }
        return count;
    }
}