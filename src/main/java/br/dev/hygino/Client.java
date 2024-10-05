package br.dev.hygino;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Client {

    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramPacket packet = null;
    InetAddress ip = null;

    public static void main(String[] args) {
        Client client = new Client();
        client.initializeVariable();
        client.connecting();
    }

    private void initializeVariable() {
        try {
            socket = new MulticastSocket(Constants.PORT);
            buffer = new byte[Constants.BUFFER_SIZE];
            ip = InetAddress.getByName(Constants.IP);
        } catch (SocketException ex) {
            log("initializeVariable: " + ex.toString());
        } catch (IOException ex) {
            log("initializeVariable: " + ex.toString());
        }
    }

    private String receiveData() {
        String line = "";
        try {
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            line = new String(packet.getData(), 0, packet.getLength());
        } catch (IOException ex) {
            log("receiveData: " + ex.toString());
        }
        return line;
    }

    private void joinGroup() {
        try {
            socket.joinGroup(ip);
            log("Client running ...");
        } catch (IOException ex) {
            log("joinGroup: " + ex.toString());
        }
    }

    private void connecting() {
        joinGroup();
        while (true) {
            String line = receiveData();
            log("Client received: " + line);
        }
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
