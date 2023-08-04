/*
A função `CodelandUsernameValidation(str)` recebe o parâmetro `str` e deve determinar se a string
é um nome de usuário válido de acordo com as seguintes regras:

1. O nome de usuário deve ter entre 4 e 25 caracteres.
2. Deve começar com uma letra.
3. Pode conter apenas letras, números e o caractere de sublinhado (underscore).
4. Não pode terminar com um caractere de sublinhado.

Se o nome de usuário for válido, o programa deve retornar a string "true", caso contrário, 
deve retornar a string "false".
*/
import java.io.*;

public class UsernameValidator {
    private String str;

    public UsernameValidator(String str) {
        this.str = str;
    }

    public boolean isValidUsername() {
        // Verifica se o tamanho do nome de usuário está dentro do intervalo permitido
        int length = str.length();
        if (length < 4 || length > 25) {
            return false;
        }

        // Verifica se o nome de usuário começa com uma letra
        if (!Character.isLetter(str.charAt(0))) {
            return false;
        }

        // Verifica se o nome de usuário contém apenas letras, números e sublinhados
        if (!str.matches("^[a-zA-Z0-9_]+$")) {
            return false;
        }

        // Verifica se o nome de usuário termina com uma letra ou número, e não com um sublinhado
        char lastChar = str.charAt(length - 1);
        if (!Character.isLetterOrDigit(lastChar) || lastChar == '_') {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // Teste com exemplos
        UsernameValidator validator1 = new UsernameValidator("username123");
        System.out.println(validator1.isValidUsername() ? "true" : "false"); // Saída: true

        UsernameValidator validator2 = new UsernameValidator("_invalid");
        System.out.println(validator2.isValidUsername() ? "true" : "false"); // Saída: false

        UsernameValidator validator3 = new UsernameValidator("user_name_");
        System.out.println(validator3.isValidUsername() ? "true" : "false"); // Saída: false
    }
}