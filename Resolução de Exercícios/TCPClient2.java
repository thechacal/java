/*
Aqui está uma explicação do que o código faz:

1) A função str_cli cria um descritor de arquivo para o dispositivo /dev/poll usando a chamada Open.
2) Inicializa um array pollfd com dois descritores de arquivo: o descritor do arquivo de entrada padrão
(fp) e o descritor do socket conectado (sockfd).
3) Escreve o array pollfd no descritor de arquivo do dispositivo /dev/poll usando a chamada Write.
4) Entra em um loop infinito.
5) Usa a chamada Ioctl com o comando DP_POLL para bloquear até que o /dev/poll indique que há operações
prontas para serem executadas nos descritores de arquivo. O resultado dessa operação é armazenado na 
variável result.
6) Percorre os descritores de arquivo que estão prontos para E/S, com base no resultado retornado 
pelo /dev/poll.
7) Se o descritor de socket (sockfd) estiver pronto para leitura, lê os dados do socket usando Read, 
imprime-os na saída padrão usando Write, e lida com o encerramento do servidor.
8) Se o descritor de entrada padrão (fp) estiver pronto para leitura, lê os dados da entrada 
padrão usando Read, os envia para o servidor usando Writen, e lida com o encerramento da entrada padrão.
 */
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Iterator;

public class TCPClient {
    public static void str_cli(BufferedReader input, SocketChannel socketChannel) throws IOException {
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selKey = keyIterator.next();
                keyIterator.remove();

                if (selKey.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = socketChannel.read(buffer);
                    if (bytesRead == -1) {
                        System.out.println("Server terminated prematurely");
                        return;
                    }
                    buffer.flip();
                    byte[] receivedData = new byte[buffer.remaining()];
                    buffer.get(receivedData);
                    System.out.print(new String(receivedData, "UTF-8"));
                }

                if (selKey.isWritable() && input.ready()) {
                    String userInput = input.readLine();
                    if (userInput == null) {
                        socketChannel.shutdownOutput();
                        key.interestOps(SelectionKey.OP_READ);
                        continue;
                    }
                    ByteBuffer buffer = ByteBuffer.wrap(userInput.getBytes());
                    socketChannel.write(buffer);
                }
            }
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
            SocketChannel socketChannel = SocketChannel.open();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        ) {
            socketChannel.connect(new InetSocketAddress(hostname, port));
            str_cli(input, socketChannel);
        }
    }
}
