package com.ethan.businesscore.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DemoClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8080);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String message = "my first socket";
            String message2 = "my second socket";
            bufferedWriter.write(message);
            bufferedWriter.write(message2);
            bufferedWriter.flush();
            socket.close();
            System.out.println("Message sent");
        }

    }

}
