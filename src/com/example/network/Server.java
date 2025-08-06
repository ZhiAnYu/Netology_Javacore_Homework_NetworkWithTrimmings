package com.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8088; //диапазон 1024-65388
        System.out.println("Starting server on port: " + port);
        String clientName;
        boolean isAdult;        //может понадобится для доступа к контенту 18+

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    String clientIp = clientSocket.getInetAddress().getHostAddress();
                    int clientPort = clientSocket.getPort();
                    System.out.println("Accepted connection from:");
                    System.out.println("IP client: " + clientIp);
                    System.out.println("Port сlient: " + clientPort);
                    BufferedReader bf = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                    output.println("Welcome! Write your name, please!");
                    clientName = bf.readLine();
                    String responce = String.format("Welcome, %s! Your port: %d ", clientName, clientPort);
                    output.println(responce);
                    output.println(clientName + ", are you child? (yes/no)");
                    if (bf.readLine().toLowerCase().equals("no")) {
                        isAdult = true;
                        responce = String.format(
                                "Welcome to the adult zone, %s! Have a good rest, or a good working day!", clientName);

                    } else {
                        isAdult = false;
                        responce = String.format("Welcome to the kids area, %s! Let's play!", clientName);
                    }
                    output.println(responce);

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }


}
