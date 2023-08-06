/*
 Lembre-se de que a classe Socket é usada para criar uma conexão de rede com o servidor, 
 e as classes InputStream e BufferedReader são usadas para ler os dados recebidos. 
 */
import java.io.*;
import java.net.*;

public class NetworkClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: NetworkClient <hostname or IPaddress> <service or port#>");
            System.exit(1);
        }

        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        InetAddress peerAddress = socket.getInetAddress();
        int peerPort = socket.getPort();
        System.out.println("Connected to " + peerAddress.getHostAddress() + ":" + peerPort);

        try (InputStream inputStream = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                System.out.println(bytesRead + " bytes from PEEK");
                System.out.println(new String(buffer, 0, bytesRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
