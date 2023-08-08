/*
 * Este código Java implementa um servidor UDP que ecoa os datagramas recebidos de volta ao remetente.
 * Aqui está uma breve explicação do que o código faz:
 * 
 * 1) Define um tamanho máximo de linha de 20 bytes para ver a truncagem de datagramas.
 * 2) Configura opções de socket usando setsockopt para habilitar o recebimento do endereço de
 * destino (IP_RECVDSTADDR) e informações da interface de recebimento (IP_RECVIF).
 * 
 * 3) Inicia um loop infinito para receber e ecoar datagramas.
 * 4) Utiliza a função Recvfrom_flags para receber datagramas com informações adicionais.
 * 5) Exibe informações sobre o datagrama recebido, incluindo o tamanho, endereço do remetente, 
 * endereço de destino e informações da interface de recebimento.
 * 6) Ecoa o datagrama de volta para o remetente usando Sendto.
 */
import java.io.*;
import java.net.*;

public class UDPEchoServer {
    public static void dg_echo(DatagramSocket socket) throws IOException {
        byte[] receiveBuffer = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println(receivedData + " from " + clientAddress.getHostAddress() + ":" + clientPort);

            DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(),
                                                           clientAddress, clientPort);
            socket.send(sendPacket);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: UDPEchoServer <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try (DatagramSocket socket = new DatagramSocket(port)) {
            dg_echo(socket);
        }
    }
}
