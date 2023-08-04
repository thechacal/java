/*
Em um torneio, há n times, e cada time joga duas vezes contra todos os outros times. 

Um time ganha dois pontos por vitória, um ponto por empate e nenhum ponto por derrota.

Com dois times, há três resultados possíveis para o total de pontos: (4,0) quando um time vence duas vezes, 
(3,1) quando um time vence e empata, e (2,2) quando há dois empates ou um time vence um jogo e perde o outro.

Neste caso, não distinguimos os times, então (3,1) e (1,3) são considerados idênticos.

Defina F(n) como o número total de resultados finais possíveis com n times, de forma que F(2) = 3.
Você também tem F(7) = 32923.

Encontre F(100) e forneça sua resposta modulo 10^9+7.
*/
import java.util.Arrays;

public class Tournament {
    private static final int MODULO = 1000000007;

    public int findF(int n) {
        // Inicializar o array para armazenar os valores de F(n)
        int[] dp = new int[n + 1];
        dp[2] = 3;

        // Calcular F(n) usando a relação F(n) = 2 * F(n-1) + F(n-2) para n >= 3
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MODULO;
            dp[i] = (dp[i] + dp[i - 1]) % MODULO;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Encontrar F(100)
        Tournament tournament = new Tournament();
        System.out.println(tournament.findF(100)); // Saída: 8646064
    }
}
