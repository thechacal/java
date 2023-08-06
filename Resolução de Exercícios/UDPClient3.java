/*
O código a seguir é uma função chamada dg_cli que implementa um cliente UDP com um tempo limite (timeout)
de socket. Ele lê linhas da entrada padrão (geralmente um arquivo) e as envia para um servidor usando
um socket UDP. A função verifica se há dados disponíveis no socket dentro de um tempo limite de 5 segundos
usando a função Readable_timeo. Se houver dados disponíveis, ele recebe os dados e os imprime.

Certifique-se de ajustar o código conforme necessário para atender às suas necessidades e de tratar
adequadamente as exceções e erros.
*/

import java.io.*;
import java.net.*;

public class UDPClient {
    public static boolean readableTimeo(DatagramSocket socket, int timeout) {
        try {
            socket.setSoTimeout(timeout * 1000); // Set socket timeout in milliseconds
            socket.receive(new DatagramPacket(new byte[1], 1));
            return true;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            socket.setSoTimeout(0); // Reset socket timeout
        }
    }

    public static void dg_cli(BufferedReader input, DatagramSocket socket, InetAddress serverAddress, int serverPort) throws IOException {
        byte[] sendBuffer = new byte[1024];
        byte[] receiveBuffer = new byte[1024];

        String userInput;
        while ((userInput = input.readLine()) != null) {
            sendBuffer = userInput.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            if (!readableTimeo(socket, 5)) {
                System.err.println("Socket timeout");
            } else {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(receivedData);
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