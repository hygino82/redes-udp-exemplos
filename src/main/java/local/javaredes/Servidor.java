package local.javaredes;

/**
 *
 * @author hygino
 */
import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) throws Exception {
        
        DatagramSocket serverSocket = new DatagramSocket(60000);
        
        byte[] dadosEnviar = new byte[1024];
        byte[] dadosReceber = new byte[1024];
        
        while (true) {
            DatagramPacket pacoteReceber
                    = new DatagramPacket(dadosReceber, dadosReceber.length);
            
            serverSocket.receive(pacoteReceber);
            
            String mensagem = new String(pacoteReceber.getData());
            
            InetAddress endereco = pacoteReceber.getAddress();
            int porta = pacoteReceber.getPort();
            
            String novaMensagem = mensagem.toUpperCase();
            
            dadosEnviar = novaMensagem.getBytes();
            
            DatagramPacket pacoteEnviar
                    = new DatagramPacket(dadosEnviar, dadosEnviar.length, endereco, porta);
            
            serverSocket.send(pacoteEnviar);
        }
    }
}
