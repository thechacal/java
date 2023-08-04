/*
Se listarmos todos os números naturais abaixo de 10 que são múltiplos de 3 ou 5, obtemos 3, 5, 6 e 9. 
A soma desses múltiplos é 23.
Encontre a soma de todos os múltiplos de 3 ou 5 abaixo de 1000.
*/
import java.util.*;
import java.io.*;

public class MultiplesSum {
    private int limit;

    public MultiplesSum(int limit) {
        this.limit = limit;
    }

    public int findMultiplesSum() {
        int sum = 0;

        for (int i = 1; i < limit; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        // Encontre a soma dos múltiplos de 3 ou 5 abaixo de 1000
        MultiplesSum multiplesSum = new MultiplesSum(1000);
        System.out.println(multiplesSum.findMultiplesSum()); // Saída: 233168
    }
}
