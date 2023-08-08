/*
Aqui está uma explicação do que o código faz:

1) A função str_cli inicia um loop infinito que gerencia entradas do usuário e respostas do servidor.
2) Usa a função FD_ZERO para inicializar o conjunto de descritores de arquivo rset a ser usado na função select.
3) No loop, a função verifica se há dados disponíveis para leitura no socket usando a função FD_SET e, 
em seguida, calcula o valor de maxfdp1, que é o valor máximo do descritor de arquivo + 1, 
necessário para a função select.
4) A função Select é usada para esperar por qualquer um dos descritores de arquivo ficar pronto para leitura.
5) Se o socket estiver pronto para leitura (FD_ISSET(sockfd, &rset)), a função lê dados do servidor 
usando Readline e os imprime na saída padrão.
6) Se a entrada padrão estiver pronta para leitura (FD_ISSET(fileno(fp), &rset)), a função lê uma linha
da entrada padrão usando Fgets e a envia para o servidor usando Writen.
7) Se a função Fgets retornar NULL, isso indica que não há mais entrada do usuário, e a função fecha a 
parte de escrita do socket (Shutdown(sockfd, SHUT_WR)) e continua a leitura do socket.
8) O loop continua até que o servidor termine prematuramente ou a entrada do usuário seja encerrada.

Este código Java implementa um cliente TCP, ele lê linhas da entrada padrão e as envia para o 
servidor através de um socket TCP. Em seguida, lê a resposta do servidor e a imprime na saída padrão. 
Certifique-se de tratar exceções e erros adequadamente no seu código Java.

*/

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void str_cli(BufferedReader input, PrintWriter output, Socket socket) throws IOException {
        String sendLine;
        while ((sendLine = input.readLine()) != null) {
            output.println(sendLine);
            output.flush();

            String receiveLine = input.readLine();
            if (receiveLine == null) {
                System.out.println("Server terminated prematurely");
                break;
            }
            System.out.println(receiveLine);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: TCPClient <hostname> <port>");
            System.exit(1);
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (
            Socket socket = new Socket(hostname, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        ) {
            str_cli(input, output, socket);
        }
    }
}
