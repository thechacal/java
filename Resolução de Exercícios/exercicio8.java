/*
A função `FirstReverse(str)` recebe o parâmetro `str` e deve retornar a string em ordem reversa. 
Por exemplo, se a string de entrada for "Hello World and Coders", o programa deve retornar a 
string "sredoC dna dlroW olleH".
*/
import java.io.*;

public class Reverser {
    private String str;

    public Reverser(String str) {
        this.str = str;
    }

    public String reverseString() {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        // Teste com exemplo "Hello World and Coders"
        Reverser reverser = new Reverser("Hello World and Coders");
        System.out.println(reverser.reverseString()); // Saída: "sredoC dna dlroW olleH"
    }
}
