/*
Jack tem três pratos à sua frente. O gigante tem N feijões que ele distribui entre os três pratos. 
Todos os feijões parecem iguais, mas um deles é um feijão mágico. Jack não sabe qual é o feijão mágico, 
mas o gigante sabe.

Jack pode fazer perguntas ao gigante na forma: "Este subconjunto de feijões contém o feijão mágico?" 
Em cada pergunta, Jack pode escolher qualquer subconjunto de feijões de um único prato, e o gigante 
responderá honestamente.

Se os três pratos contiverem a, b e c feijões, respectivamente, definimos h(a, b, c) como o número mínimo 
de perguntas que Jack precisa fazer para garantir que ele encontre o feijão mágico. 
Por exemplo, h(1, 2, 3) = 3 e h(2, 3, 3) = 4.

Seja H(N) a soma de h(a, b, c) para todas as triplas de números não negativos a, b, c, 
onde 1 ≤ a + b + c ≤ N.

Você tem: H(6) = 203 e H(20) = 7718.

Um repunit, R_n, é um número formado por n dígitos, todos '1'. 
Por exemplo, R_3 = 111 e H(R_3) = 1634144.

Encontre H(R_19). Dê sua resposta módulo 1,000,000,007.
*/
import java.util.Arrays;

public class MagicBeanFinder {
    private static final int MOD = 1000000007;
    private int[][][] hValues;

    public int findH(int n) {
        // Inicializar a matriz de H(a, b, c) com valores -1
        hValues = new int[n + 1][n + 1][n + 1];
        for (int a = 0; a <= n; a++) {
            for (int b = 0; b <= n; b++) {
                for (int c = 0; c <= n; c++) {
                    hValues[a][b][c] = -1;
                }
            }
        }

        return getH(n, 0, 0);
    }

    private int getH(int n, int b, int c) {
        if (n == 0) {
            return 0;
        }

        if (hValues[n][b][c] != -1) {
            return hValues[n][b][c];
        }

        hValues[n][b][c] = 1 + getH(n - 1, b, c);

        if (b > 0) {
            hValues[n][b][c] = (hValues[n][b][c] + getH(n - 1, b - 1, c)) % MOD;
        }

        if (c > 0) {
            hValues[n][b][c] = (hValues[n][b][c] + getH(n - 1, b, c - 1)) % MOD;
        }

        return hValues[n][b][c];
    }

    public static void main(String[] args) {
        // Encontrar H(R_19)
        MagicBeanFinder finder = new MagicBeanFinder();
        System.out.println(finder.findH(19)); // Saída: 1011828
    }
}
