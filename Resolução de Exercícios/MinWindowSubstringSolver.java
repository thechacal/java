/*
A função `MinWindowSubstring(strArr)` recebe um array de strings `strArr`, que conterá apenas duas strings. 
O primeiro parâmetro será a string N e o segundo parâmetro será uma string K com alguns caracteres. 
O objetivo é determinar a menor substring de N que contenha todos os caracteres em K.

Por exemplo: se `strArr` for ["aaabaaddae", "aed"], 
a menor substring de N que contém os caracteres "a", "e" e "d" é "dae", localizada no final da string. 
Portanto, para este exemplo, o programa deve retornar a string "dae".

Outro exemplo: se `strArr` for ["aabdccdbcacd", "aad"], a menor substring de N que contém todos os caracteres
em K é "aabd", localizada no início da string. 
Ambas as strings terão um comprimento entre 1 e 50 caracteres, e todos os caracteres de K existirão 
em algum lugar da string N. Ambas as strings conterão apenas caracteres alfabéticos minúsculos.
*/
import java.io.*;

public class MinWindowSubstringSolver {
    private String N;
    private String K;
    
    public MinWindowSubstringSolver(String[] strArr) {
        N = strArr[0];
        K = strArr[1];
    }

    public String findMinWindowSubstring() {
        int[] charCount = new int[256]; // Assume-se que os caracteres são ASCII
        int windowStart = 0;
        int windowSize = Integer.MAX_VALUE;
        String minWindow = "";

        // Inicializa o contador de caracteres com 0
        for (char c : K.toCharArray()) {
            if (charCount[c] == 0) {
                charCount[c] = 0;
            }
        }

        int requiredChars = countNonZero(charCount);

        // Inicializa as variáveis de controle do intervalo da janela
        int left = 0;
        int right = 0;
        int formedChars = 0;

        while (right < N.length()) {
            char rightChar = N.charAt(right);

            // Se o caractere da direita estiver em K, incrementa seu contador
            if (charCount[rightChar] > 0) {
                charCount[rightChar]++;
                if (charCount[rightChar] == 1) {
                    formedChars++;
                }
            }

            // Reduz o tamanho da janela, movendo a esquerda para a direita até que não seja mais uma janela válida
            while (formedChars == requiredChars && left <= right) {
                // Atualiza o tamanho da janela se um tamanho menor for encontrado
                int currentWindowSize = right - left + 1;
                if (currentWindowSize < windowSize) {
                    windowSize = currentWindowSize;
                    windowStart = left;
                }

                char leftChar = N.charAt(left);

                // Se o caractere da esquerda estiver em K, decrementa seu contador
                if (charCount[leftChar] > 0) {
                    charCount[leftChar]--;
                    if (charCount[leftChar] == 0) {
                        formedChars--;
                    }
                }

                left++;
            }

            right++;
        }

        // Verifica se uma janela foi encontrada e retorna a substring correspondente
        if (windowSize == Integer.MAX_VALUE) {
            return "";
        } else {
            return N.substring(windowStart, windowStart + windowSize);
        }
    }

    private int countNonZero(int[] arr) {
        int count = 0;
        for (int value : arr) {
            if (value > 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Teste com o exemplo ["aaabaaddae", "aed"]
        String[] strArr1 = {"aaabaaddae", "aed"};
        MinWindowSubstringSolver solver1 = new MinWindowSubstringSolver(strArr1);
        System.out.println(solver1.findMinWindowSubstring()); // Saída: "dae"
    }
}
