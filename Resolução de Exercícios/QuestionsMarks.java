/*
A função `QuestionsMarks(str)` recebe um parâmetro `str`, que é uma string contendo números de um único
dígito, letras e pontos de interrogação. O objetivo é verificar se existem exatamente 3 pontos
de interrogação entre cada par de dois números que somam 10. Se sim, a função deve retornar a string "true"; 
caso contrário, deve retornar a string "false". Se não houver dois números que somem 10 na string, 
a função também deve retornar "false".

Por exemplo: se `str` for "arrb6???4xxbl5???eee5", a função deve retornar "true" porque há exatamente 3 pontos
de interrogação entre 6 e 4, e 3 pontos de interrogação entre 5 e 5 no final da string.
*/
import java.io.*;

public class QuestionsMarks {
    private String str;

    public QuestionsMarks(String str) {
        this.str = str;
    }

    public boolean hasExactlyThreeMarks() {
        char[] chars = str.toCharArray();
        boolean pairFound = false;
        int sum = 0;
        int markCount = 0;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                if (pairFound && sum == 10 && markCount == 3) {
                    return true;
                }
                sum = num;
                pairFound = true;
                markCount = 0;
            } else if (c == '?') {
                markCount++;
            } else {
                pairFound = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Teste com exemplo "arrb6???4xxbl5???eee5"
        QuestionsMarks questionMarks = new QuestionsMarks("arrb6???4xxbl5???eee5");
        System.out.println(questionMarks.hasExactlyThreeMarks() ? "true" : "false"); // Saída: true

        // Teste com exemplo "aa6?9"
        QuestionsMarks questionMarks2 = new QuestionsMarks("aa6?9");
        System.out.println(questionMarks2.hasExactlyThreeMarks() ? "true" : "false"); // Saída: false
    }
}
