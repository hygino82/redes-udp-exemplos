package local.javaredes;

/**
 *
 * @author hygino
 */
import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) throws Exception {
        BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clienteSocket = new DatagramSocket();

        InetAddress endereco = InetAddress.getByName("localhost");
        int porta = 60000;

        byte[] dadosEnviar = new byte[1024];
        byte[] dadosReceber = new byte[1024];

        String mensagemUsuario = entradaUsuario.readLine();
        dadosEnviar = mensagemUsuario.getBytes();

        DatagramPacket pacoteEnviar
                = new DatagramPacket(dadosEnviar, dadosEnviar.length, endereco, porta);

        clienteSocket.send(pacoteEnviar);

        DatagramPacket pacoteReceber
                = new DatagramPacket(dadosReceber, dadosReceber.length);

        clienteSocket.receive(pacoteReceber);

        String novaMensagem = new String(pacoteReceber.getData());

        System.out.println("Mensagem do servidor: " + novaMensagem);

        clienteSocket.close();
    }
}
