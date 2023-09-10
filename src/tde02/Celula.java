package tde02;
/**
 *
 * @author RAFMo
 */
class Celula {
    Integer data;
    Celula proximo;

    public Celula(Integer data) {
        this.data = data;
        this.proximo= null;
    }
}