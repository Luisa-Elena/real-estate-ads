package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The {@code Client} class represents a simple client-side application that
 * connects to a server using a socket, sends user input, and receives responses.
 */
class Client {

    /**
     * The main method establishes a connection to the server, reads user input,
     * sends it to the server, and prints the server's response.
     */
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("localhost", 1234)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            String line = null;
            while (true) {
                line = sc.nextLine();
                out.println(line);

                if("EXIT".equalsIgnoreCase(line)) {
                    break;
                }

                String serverResponse;
                while((serverResponse = in.readLine()) != null) {
                    System.out.println(serverResponse);
                    if(!in.ready()) break;
                }
            }

            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
