package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

class Client {

    public static void main(String[] args)
    {
        try (Socket socket = new Socket("localhost", 1234)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);

            String line = null;

            while (!"EXIT".equals(line)) {

                line = sc.nextLine();

                out.println(line);

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
