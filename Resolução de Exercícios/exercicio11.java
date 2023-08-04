/*
A função `LongestWord(sen)` recebe o parâmetro `sen` e deve retornar a palavra mais longa na string. 
Se houver duas ou mais palavras com o mesmo comprimento, deve-se retornar a primeira palavra da string
com esse comprimento. A função deve ignorar pontuação e assume que `sen` não estará vazio. 
As palavras também podem conter números, por exemplo, "Hello world123 567".
*/
import java.util.regex.Pattern;

public class LongestWordFinder {
    private String sen;

    public LongestWordFinder(String sen) {
        this.sen = sen;
    }

    public String findLongestWord() {
        String[] words = sen.split("\\W+");
        String longestWord = "";

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        return longestWord;
    }

    public static void main(String[] args) {
        // Encontrar a palavra mais longa na string
        LongestWordFinder finder = new LongestWordFinder("Hello world123 567");
        System.out.println(finder.findLongestWord()); // Saída: "world123"
    }
}
