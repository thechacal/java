/*
Exercício 1
Uma empresa comercial possui um programa para controle das receitas e despesas em seus 10 projetos. 
Os projetos são numerados de 0 até 9. 
Faça um programa C que controle a entrada e saída de recursos dos projetos. 
O programa deverá ler um conjunto de informações contendo: 
Número do projeto, valor, tipo despesa ("R" - Receita e "D" - Despesa). 
O programa termina quando o valor do código do projeto for igual a -1. 
Sabe-se que Receita deve ser somada ao saldo do projeto e despesa subtraída do saldo do projeto. 
Ao final do programa, imprirmir o saldo final de cada projeto.

Dica: Usar uma estrutura do tipo vetor para controlar os saldos dos projetos. 
Usar o conceito de struct para agrupar as informações lidas.
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Projeto {
    public int numero;
    public float saldo;

    public Projeto(int num) {
        numero = num;
        saldo = 0;
    }

    public void atualizarSaldo(float valor, char tipoDespesa) {
        if (tipoDespesa == 'R' || tipoDespesa == 'r') {
            saldo += valor;
        } else if (tipoDespesa == 'D' || tipoDespesa == 'd') {
            saldo -= valor;
        } else {
            System.out.println("Tipo de despesa inválido! Use 'R' para Receita ou 'D' para Despesa.");
        }
    }
}

public class ControleProjetos {
    public static void main(String[] args) {
        final int NUM_PROJETOS = 10;
        List<Projeto> projetos = new ArrayList<>();

        // Inicializando a lista de projetos com objetos Projeto
        for (int i = 0; i < NUM_PROJETOS; i++) {
            projetos.add(new Projeto(i));
        }

        Scanner scanner = new Scanner(System.in);
        int numeroProjeto;
        float valor;
        char tipoDespesa;

        // Loop para ler as informações até que o número do projeto seja -1
        while (true) {
            System.out.print("Digite o número do projeto (-1 para encerrar): ");
            numeroProjeto = scanner.nextInt();

            if (numeroProjeto == -1) {
                break; // Encerra o loop caso seja digitado -1
            }

            System.out.print("Digite o valor: ");
            valor = scanner.nextFloat();

            System.out.print("Digite o tipo de despesa (R - Receita, D - Despesa): ");
            tipoDespesa = scanner.next().charAt(0);

            if (numeroProjeto >= 0 && numeroProjeto < NUM_PROJETOS) {
                projetos.get(numeroProjeto).atualizarSaldo(valor, tipoDespesa);
            } else {
                System.out.println("Projeto não encontrado!");
            }
        }

        // Imprimindo o saldo final de cada projeto
        System.out.println("\nSaldo final de cada projeto:");
        for (Projeto projeto : projetos) {
            System.out.println("Projeto " + projeto.numero + ": R$ " + projeto.saldo);
        }
    }
}