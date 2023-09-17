package tde02;
/**
 *
 * @author RAFMo
 */
import java.util.Scanner;
import java.util.Random;

public class Game {
    private ListaEncadeada pilhaA;
    private ListaEncadeada pilhaB;
    private ListaEncadeada pilhaC;
    private boolean decrescente;
    private int contador;
    private int capacidade;
    private Scanner sc;

    public Game() { 
        sc = new Scanner(System.in);
        decrescente = false;
        contador = 0;
        pilhaA = new ListaEncadeada();
        pilhaB = new ListaEncadeada();
        pilhaC = new ListaEncadeada();
    }

    public void setCapacidade(int capacidade) { 
        this.capacidade = capacidade;
    }

    public void popularPilhaA(int capacidade) { 
        Random random = new Random(); 
        for (int i = 0; i < capacidade; i++) {
            pilhaA.push(random.nextInt(101));
        }
    }

    private boolean isPilhaCheia(ListaEncadeada pilha) { 
        return pilha.size() >= capacidade;
    }

    private boolean ordenarPilha(ListaEncadeada pilha) {
        Celula atual = pilha.frente; 
        boolean crescente = true;
        boolean decrescente = true;

        while (atual != null && (crescente || decrescente)) {
            if (atual.proximo != null) {
                if (atual.data < atual.proximo.data) {
                    decrescente = false;
                } else if (atual.data > atual.proximo.data) {
                    crescente = false;
                }
            }
            atual = atual.proximo;
        }

        return crescente || decrescente;
    }

    private void verificarResultado() {
        if (isPilhaCheia(pilhaC) && ordenarPilha(pilhaC)) {
            System.out.println("Ordenação concluída em " + contador + " jogadas.");
        }
    }

    private void moverElemento(String pilhaOrigem, String pilhaDestino) { 
        ListaEncadeada origem = null;
        ListaEncadeada destino = null;

        switch (pilhaOrigem) {
            case "A":
                origem = pilhaA;
                break;
            case "B":
                origem = pilhaB;
                break;
        }

        switch (pilhaDestino) {
            case "A":
                destino = pilhaA;
                break;
            case "B":
                destino = pilhaB;
                break;
            case "C":
                destino = pilhaC;
                break;
        }
        
        if (origem != null && destino != null) {
            Integer elemento = origem.pop();
            if (elemento != null) {
                destino.push(elemento);
            } else {
                System.out.println("A pilha " + pilhaOrigem + " está vazia.");
            }
        } else {
            System.out.println("Pilhas de origem ou destino inválidas.");
        }
    }


    private void autoCompletar() { 
        while (!pilhaA.vazia()) { 
            Integer elemento = pilhaA.pop();
            if (elemento != null) {
                pilhaC.push(elemento);
            }
        }
        System.out.println("Preenchimento automático: todos os elementos da pilha A foram movidos para a pilha C.");
    }

 private void ordenarCelulaPilhaC(boolean decrescente) {
    boolean trocou;      
    int movimentos = 0;   

    do {                  
        trocou = false;    
        Celula atual = pilhaC.frente;  

        while (atual != null && atual.proximo != null) {  
            Celula proximo = atual.proximo;   

            boolean deveTrocar;
            if (decrescente) {
                deveTrocar = atual.data < proximo.data;
            } else {
                deveTrocar = atual.data > proximo.data;
            }

            if (deveTrocar) {  
                int temp = atual.data;
                atual.data = proximo.data;
                proximo.data = temp;

                trocou = true;    
                movimentos++;     
            }

            atual = proximo;   
        }
    } while (trocou);   

    contador += movimentos;   
}


    public void printTodasPilhas() { 
        System.out.println("Pilha A:");
        pilhaA.print();
        System.out.println("Pilha B:");
        pilhaB.print();
        System.out.println("Pilha C:");
        pilhaC.print();
    }
    
    public void setOrdem() {
        System.out.print("Objetivo: ordem decrescente (d) ou ordem crescente (c): ");
        char escolha = sc.nextLine().charAt(0);
        if (escolha == 'd' || escolha == 'D') {
            decrescente = true;
            System.out.println("Objetivo: ordem decrescente.");
        } else if (escolha == 'c' || escolha == 'C') {
            decrescente = false;
            System.out.println("Objetivo: ordem crescente.");
        } else {
            System.out.println("Opção inválida. A ordem padrão é crescente.");
        }
    }
    public void menu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("Pressione 0 para sair");
            System.out.println("Pressione 1 para mover de A para B");
            System.out.println("Pressione 2 para mover de B para C");
            System.out.println("Pressione 3 para mover de A para C");
            System.out.println("Pressione 4 para mover de B para A");
            System.out.println("Pressione 5 para auto-completar e ordenar");
            System.out.print("Digite sua escolha: ");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo.");
                    sc.close();
                    verificarResultado();
                    return;

                case 1:
                    moverElemento("A", "B");
                    printTodasPilhas();
                    verificarResultado();
                    break;

                case 2:
                    moverElemento("B", "C");
                    printTodasPilhas();
                    verificarResultado();
                    break;

                case 3:
                    moverElemento("A", "C");
                    printTodasPilhas();
                    verificarResultado();
                    break;

                case 4:
                    moverElemento("B", "A");
                    printTodasPilhas();
                    verificarResultado();
                    break;
                    
                case 5:
                    autoCompletar();
                    ordenarCelulaPilhaC(decrescente);
                    printTodasPilhas();
                    verificarResultado();
                    return;

                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente...");
                    break;
            }

            contador++; 
        }
    }
    public static void main(String[] args) {
        Game roundOne = new Game();
        roundOne.setOrdem();

        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o tamanho desejado para as pilhas: ");
        int capacidade = sc.nextInt();
        sc.nextLine(); 

        roundOne.setCapacidade(capacidade);

        roundOne.popularPilhaA(capacidade);
        roundOne.printTodasPilhas();
        roundOne.menu();
        sc.close();
    }
}
