/*
A função `BracketMatcher(str)` recebe a string `str` como parâmetro e deve retornar 1 se os parênteses
estiverem corretamente combinados e cada um estiver devidamente fechado. Caso contrário, deve retornar 0. 
Por exemplo: se `str` for "(hello (world))", o resultado deve ser 1, 
mas se `str` for "((hello (world))", o resultado deve ser 0 porque os parênteses não estão corretamente 
combinados. Apenas os caracteres "(" e ")" serão usados como parênteses. 
Se `str` não contiver parênteses, a função deve retornar 1.
*/
import java.io.*;

public class BracketMatcher {
    private String str;
    private int openCount;

    public BracketMatcher(String str) {
        this.str = str;
        this.openCount = 0;
    }

    public boolean isCorrectlyMatched() {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                if (openCount == 0) {
                    return false; // Encontrou um parêntese fechando sem um correspondente aberto
                }
                openCount--;
            }
        }

        // Verifica se todos os parênteses foram corretamente fechados
        return (openCount == 0);
    }

    public static void main(String[] args) {
        // Teste com exemplos
        BracketMatcher matcher1 = new BracketMatcher("(hello (world))");
        System.out.println(matcher1.isCorrectlyMatched() ? "1" : "0"); // Saída: 1

        BracketMatcher matcher2 = new BracketMatcher("((hello (world))");
        System.out.println(matcher2.isCorrectlyMatched() ? "1" : "0"); // Saída: 0

        BracketMatcher matcher3 = new BracketMatcher("hello world");
        System.out.println(matcher3.isCorrectlyMatched() ? "1" : "0"); // Saída: 1 (nenhum parêntese)
    }
}