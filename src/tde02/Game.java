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
    private Scanner sc;
    private boolean decrescente; 
    private int contador; 

    public Game() {
        sc = new Scanner(System.in);
        decrescente = false; 
        contador = 0; 
        pilhaA = new ListaEncadeada();
        pilhaB = new ListaEncadeada();
        pilhaC = new ListaEncadeada();
    }

    public void setOrdem() {
        System.out.print("Objetivo: ordem decrescente (d) ou ordem crescente (a): ");
        char escolha = sc.nextLine().charAt(0);

        if (escolha == 'd' || escolha == 'D') {
            decrescente = true;
            System.out.println("Objetivo: ordem decrescente.");
        } else if (escolha == 'a' || escolha == 'A') {
            decrescente = false;
            System.out.println("Objetivo: ordem crescente.");
        } else {
            System.out.println("Opção inválida. A ordem padrão é crescente.");
        }
    }

    public void popularPilhaA(int capacidade) {
        Random random = new Random();
        for (int i = 0; i < capacidade; i++) {
            pilhaA.push(random.nextInt(101)); 
        }
    }

    private void moverDeA() {
        Integer elemento = pilhaA.pop();
        if (elemento != null) {
            pilhaB.push(elemento);
            contador++; 
        } else {
            System.out.println("A pilha A está vazia.");
        }
    }

    private void moverDeB() {
        Integer elemento = pilhaB.pop();
        if (elemento != null) {
            pilhaC.push(elemento);
            contador++; 
        } else {
            System.out.println("A pilha B está vazia.");
        }
    }

    private void autoCompletar() {
        while (!pilhaA.isEmpty()) {
            Integer elemento = pilhaA.pop();
            if (elemento != null) {
                pilhaC.push(elemento);
                contador++; 
            }
        }
        System.out.println("Preenchimento automático: todos os elementos da pilha A foram movidos para a pilha C.");
    }

    public void printTodasPilhas() {
        System.out.println("Pilha A:");
        pilhaA.print();
        System.out.println("Pilha B:");
        pilhaB.print();
        System.out.println("Pilha C:");
        pilhaC.print();
    }

    private void printContador() {
        System.out.println("Número total de movimentos:" + contador);
    }

    public void menu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("Pressione 0 para sair");
            System.out.println("Pressione 1 para mover de A. . .");
            System.out.println("Pressione 2 para mover de B. . . ");
            System.out.println("Pressione 3 pra auto-completar. . .");
            System.out.print("Digite sua escolha: ");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo.");
                    sc.close();
                    printContador(); 
                    return;
                case 1:
                    moverDeA();
                    printTodasPilhas();
                    break;
                case 2:
                    moverDeB();
                    printTodasPilhas();
                    break;
                case 3:
                    autoCompletar();
                    printTodasPilhas(); 
                    printContador(); 
                    return; 
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente. . .");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Game roundOne = new Game();
        roundOne.setOrdem();

        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o tamanho das três pilhas: ");
        int capacidade = sc.nextInt();
        sc.nextLine();
        roundOne.popularPilhaA(capacidade);
        roundOne.printTodasPilhas();
        roundOne.menu();
        sc.close();
    }
}
