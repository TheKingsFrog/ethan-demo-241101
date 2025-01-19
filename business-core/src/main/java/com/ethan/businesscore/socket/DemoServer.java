package com.ethan.businesscore.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

    private static final Integer BYTE_SIZE = 10;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("Server Started...");

        while (true) {

            Socket clientSocket = serverSocket.accept();

            try (InputStream inputStream = clientSocket.getInputStream()) {

                byte[] bytes = new byte[BYTE_SIZE];

                while (inputStream.read(bytes, 0, BYTE_SIZE) > 0) {
                    System.out.println("Client received: " + new String(bytes).trim());
                    bytes = new byte[BYTE_SIZE];
                }

            }

        }

    }

}
