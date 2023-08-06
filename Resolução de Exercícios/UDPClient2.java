/*
 * Esta versão do código cria um cliente UDP que lê linhas da entrada padrão (teclado) 
 * e as envia para o servidor especificado. Ele também lida com um timeout de 5 segundos no recebimento
 * de dados do servidor. Certifique-se de tratar adequadamente as exceções e erros, e ajustar o 
 * código conforme necessário para atender às suas necessidades.
 */
import java.io.*;
import java.net.*;

public class UDPClient2 {
    public static void dg_cli(BufferedReader input, DatagramSocket socket, InetAddress serverAddress, int serverPort) throws IOException {
        byte[] sendBuffer = new byte[1024];
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        socket.setSoTimeout(5000); // Set socket timeout to 5 seconds

        String userInput;
        while ((userInput = input.readLine()) != null) {
            sendBuffer = userInput.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            try {
                socket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(receivedData);
            } catch (SocketTimeoutException e) {
                System.err.println("Socket timeout");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: UDPClient <hostname or IPaddress> <service or port#>");
            System.exit(1);
        }

        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int serverPort = Integer.parseInt(args[1]);

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket socket = new DatagramSocket()) {

            dg_cli(input, socket, serverAddress, serverPort);
        }
    }
}
