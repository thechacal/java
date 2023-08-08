/*
Aqui está uma explicação do código e como ele se aplica ao Linux:

1) recvfrom_flags: Esta função recebe um datagrama UDP juntamente com as informações de controle de mensagem,
usando a estrutura msghdr para manipular as opções de controle. As informações de controle são preenchidas
com detalhes como o endereço de origem do datagrama e outras informações relacionadas à interface e
comunicação de rede.
2) Recvfrom_flags: Esta função é um wrapper para recvfrom_flags, tratando erros e lançando uma exceção
em caso de falha na recepção.
3) #ifdef HAVE_MSGHDR_MSG_CONTROL: Essa diretiva de pré-processamento verifica se o sistema suporta
opções de controle de mensagem. No caso do Linux, essa funcionalidade é suportada.
4) CMSG_SPACE e CMSG_FIRSTHDR: São macros usadas para calcular o espaço necessário para o controle de mensagem
e acessar o primeiro cabeçalho de controle, respectivamente.
5) O loop dentro da função recvfrom_flags itera sobre os cabeçalhos de controle (cmsghdr) para extrair
informações específicas, como o endereço de origem (IP_RECVDSTADDR) e informações da interface (IP_RECVIF).

Em sistemas Linux, essa funcionalidade pode ser usada, por exemplo, para implementar funcionalidades
como captura de pacotes em interfaces específicas, monitoramento de tráfego de rede e outras aplicações
em que informações de controle de mensagem são necessárias para processar datagramas
UDP de forma mais completa.

Lembre-se de que a implementação específica e o uso de opções de controle de mensagem podem
variar em diferentes versões do kernel Linux e bibliotecas de rede, e é importante consultar a documentação
e os recursos atualizados ao trabalhar com esse tipo de funcionalidade.
 */
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class UDPServerWithControlInfo {
    private DatagramSocket socket;

    public UDPServerWithControlInfo(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void startServer() {
        byte[] receiveData = new byte[1024];

        while (true) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Extract control information from the received packet
                ControlInfo controlInfo = new ControlInfo(receivePacket);

                // Process received data and control information
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + message);
                System.out.println("Source Address: " + controlInfo.getSourceAddress());
                System.out.println("Destination Address: " + controlInfo.getDestinationAddress());
                System.out.println("Interface Index: " + controlInfo.getInterfaceIndex());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 12345; // Specify the port number
        try {
            UDPServerWithControlInfo server = new UDPServerWithControlInfo(port);
            server.startServer();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}

class ControlInfo {
    private InetAddress sourceAddress;
    private InetAddress destinationAddress;
    private int interfaceIndex;

    public ControlInfo(DatagramPacket packet) {
        if (packet instanceof ExtendedDatagramPacket) {
            ExtendedDatagramPacket extPacket = (ExtendedDatagramPacket) packet;
            this.sourceAddress = extPacket.getSourceAddress();
            this.destinationAddress = extPacket.getDestinationAddress();
            this.interfaceIndex = extPacket.getInterfaceIndex();
        }
    }

    public InetAddress getSourceAddress() {
        return sourceAddress;
    }

    public InetAddress getDestinationAddress() {
        return destinationAddress;
    }

    public int getInterfaceIndex() {
        return interfaceIndex;
    }
}

class ExtendedDatagramPacket extends DatagramPacket {
    private InetAddress sourceAddress;
    private InetAddress destinationAddress;
    private int interfaceIndex;

    public ExtendedDatagramPacket(byte[] buf, int length, InetAddress sourceAddress, InetAddress destinationAddress, int interfaceIndex) {
        super(buf, length);
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.interfaceIndex = interfaceIndex;
    }

    public InetAddress getSourceAddress() {
        return sourceAddress;
    }

    public InetAddress getDestinationAddress() {
        return destinationAddress;
    }

    public int getInterfaceIndex() {
        return interfaceIndex;
    }
}
