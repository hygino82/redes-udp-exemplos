package br.dev.hygino;

import static br.dev.hygino.Constants.BUFFER_SIZE;
import static br.dev.hygino.Constants.IP;
import static br.dev.hygino.Constants.PORT;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Server {

    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramPacket receivePacket = null;
    Scanner scan = null;

    public static void main(String[] args) {
        Server server = new Server();
        server.initializeVariable();
        server.connecting();

    }

    private void initializeVariable() {
        try {
            socket = new MulticastSocket();
            buffer = new byte[BUFFER_SIZE];
            scan = new Scanner(System.in);

            log("Server running...");
        } catch (SocketException ex) {
            log(ex.toString());
        } catch (IOException ex) {
            log(ex.toString());
        }
    }

    private String readFromKeyboard() {
        String line = scan.nextLine();
        return line;
    }

    private void send(String message) {
        try {
            InetAddress ip = InetAddress.getByName(IP);

            buffer = message.getBytes();
            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, ip, PORT);
            socket.send(packetSend);
            log("Message sent");
        } catch (IOException ex) {
            System.out.println("Send: " + ex.toString());
        }
    }

    private void connecting() {
        while (true) {
            String data = readFromKeyboard();
            send(data);

            buffer = new byte[BUFFER_SIZE];
        }
    }

    private void log(String message) {
        System.out.println("Message: " + message);
    }
}
