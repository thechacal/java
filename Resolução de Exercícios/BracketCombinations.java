/*
A função `BracketCombinations(num)` recebe um número inteiro `num` maior ou igual a zero e deve retornar
a quantidade de combinações válidas que podem ser formadas com `num` pares de parênteses. 
Por exemplo, se o valor de entrada for 3, as combinações possíveis com 3 pares de parênteses são: 
()()(), ()(()), (())(), ((())), e (()()). 
Existem 5 combinações no total quando o valor de entrada é 3, então o programa deve retornar 5.
*/


public class BracketCombinations {
    private int num;

    public BracketCombinations(int num) {
        this.num = num;
    }

    public int calculateCombinations() {
        return calculate(num);
    }

    private int calculate(int num) {
        // Caso base: se num for 0, só há uma combinação possível: uma string vazia.
        if (num == 0) {
            return 1;
        }

        int totalCombinations = 0;

        // Loop para calcular todas as combinações possíveis de pares de parênteses
        for (int i = 0; i < num; i++) {
            // Calcula as combinações da parte esquerda da string
            int leftCombinations = calculate(i);

            // Calcula as combinações da parte direita da string
            int rightCombinations = calculate(num - i - 1);

            // Multiplica as combinações da parte esquerda pelas combinações da parte direita
            totalCombinations += leftCombinations * rightCombinations;
        }

        return totalCombinations;
    }

    public static void main(String[] args) {
        // Teste com o valor 3
        int num = 3;
        BracketCombinations combinations = new BracketCombinations(num);
        int result = combinations.calculateCombinations();
        System.out.println("O número de combinações válidas é: " + result);
    }
}
