package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {

                String parts[] = line.split(" ");
                switch (parts[0]) {
                    case "EXIT":
                        System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress() + " from port: " + clientSocket.getPort());
                        break;
                    case "ADD":
                        if (parts.length == 4) {
                            try {
                                int price = Integer.parseInt(parts[3]);
                                Ad newAd = new Ad(parts[1], parts[2], price);
                                Server.ads.put(Server.ads.size(), newAd);
                                out.println("New ad inserted.");
                            } catch (NumberFormatException e) {
                                out.println("Invalid integer value for price: " + parts[3]);
                            }
                        } else {
                            out.println("Invalid number of arguments for the ADD command.");
                        }
                        break;
                    case "GET-BY-ID":
                        if(parts.length == 2) {
                            try {
                                int index = Integer.parseInt(parts[1]);
                                if (index >= 0 && index < Server.ads.size()) {
                                    Ad ad = Server.ads.get(index);
                                    out.println(ad.toString());
                                } else {
                                    out.println("Index out of range.");
                                }
                            } catch (NumberFormatException e) {
                                out.println("Invalid integer value for the index: " + parts[1]);
                            }
                        } else {
                            out.println("Invalid number of arguments for the GET-BY-ID command.");
                        }
                        break;
                    case "GET-ALL":
                        if (Server.ads.isEmpty()) {
                            out.println("No ads available.");
                        } else {
                            for(Integer key : Server.ads.keySet()) {
                                out.println(Server.ads.get(key).toString());
                            }
                        }
                        break;
                    default:
                        out.println("Invalid command.");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
