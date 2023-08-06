/*
 Este código cria um cliente UDP que se conecta ao servidor usando o endereço IP e a porta especificados, 
 envia um datagrama vazio para o servidor e, em seguida, recebe a resposta do servidor. 
 
 Lembre-se de tratar adequadamente as exceções e erros, além de adicionar mais detalhes ou recursos, 
 conforme necessário, para atender às necessidades do seu programa.
 
  */
import java.io.*;
import java.net.*;

public class UDPConnectClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: UDPConnectClient <hostname or IPaddress> <service or port#>");
            System.exit(1);
        }

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int serverPort = Integer.parseInt(args[1]);

        byte[] sendData = new byte[1];
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        int bytesRead = receivePacket.getLength();
        int pendingBytes = socket.getReceiveBufferSize() - bytesRead;

        System.out.println("Connected to " + serverAddress.getHostAddress() + ":" + serverPort);
        System.out.println(bytesRead + " bytes from PEEK, " + pendingBytes + " bytes pending");
        System.out.println(new String(receiveData, 0, bytesRead));

        socket.close();
    }
}
