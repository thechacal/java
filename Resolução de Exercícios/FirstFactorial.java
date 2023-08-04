/*
A função `FirstFactorial(num)` recebe o parâmetro `num` e deve retornar o fatorial dele. 
Por exemplo: se `num` for igual a 4, o programa deve retornar (4 * 3 * 2 * 1) = 24. 
Para os casos de teste, o intervalo de valores de `num` estará entre 1 e 18, e a entrada será
sempre um número inteiro.
*/
import java.lang.IllegalArgumentException;

public class FirstFactorial {
    private int num;

    public FirstFactorial(int num) {
        this.num = num;
    }

    public long calculateFactorial() {
        if (num < 0) {
            throw new IllegalArgumentException("O fatorial não está definido para números negativos.");
        }

        long factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }

        return factorial;
    }

    public static void main(String[] args) {
        // Calcular o fatorial do número 4
        FirstFactorial firstFactorial = new FirstFactorial(4);
        System.out.println(firstFactorial.calculateFactorial()); // Saída: 24
    }
}
