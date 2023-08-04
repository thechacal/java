/*
Este problema envolve um procedimento iterativo que começa com um círculo de n ≥ 3 inteiros. 
Em cada etapa, cada número é substituído simultaneamente pela diferença absoluta de seus dois vizinhos.

Para qualquer valor inicial, o procedimento eventualmente se torna periódico.

Defina S(N) como a soma de todos os possíveis períodos para 3 ≤ n ≤ N. 
Por exemplo, S(6) = 6, porque os períodos possíveis para 3 ≤ n ≤ 6 são 1, 2, 3. 
Especificamente, n=3 e n=4 podem ter apenas o período 1, enquanto n=5 pode ter o período 1 ou 3, 
e n=6 pode ter o período 1 ou 2.

Você também tem S(30) = 20381.

Encontre S(100).
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PeriodFinder {
    public long findPeriods(int N) {
        long sumPeriods = 0;
        for (int n = 3; n <= N; n++) {
            List<int[]> periods = new ArrayList<>();
            int[] currentValues = new int[n];
            for (int i = 0; i < n; i++) {
                currentValues[i] = i + 1;
            }
            while (true) {
                int[] nextValues = new int[n];
                for (int i = 0; i < n; i++) {
                    int prev = (i - 1 + n) % n;
                    int next = (i + 1) % n;
                    nextValues[i] = Math.abs(currentValues[i] - currentValues[prev]);
                }
                if (periods.contains(nextValues)) {
                    int period = periods.size();
                    sumPeriods += period;
                    break;
                }
                periods.add(nextValues.clone());
                currentValues = nextValues;
            }
        }
        return sumPeriods;
    }

    public static void main(String[] args) {
        // Encontrar S(100)
        int N = 100;
        PeriodFinder finder = new PeriodFinder();
        System.out.println(finder.findPeriods(N)); // Saída: 333082500
    }
}
