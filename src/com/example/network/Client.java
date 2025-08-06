package com.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] arg) {
        String serverIP = "netology.homework";
        int serverPort = 8088;
        try (Socket socket = new Socket(serverIP, serverPort)) {
            System.out.println("Connected success");

            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            responceServer( bf.readLine());

            String name = "Anastasia";
            output.println(name);
            System.out.println("Send: " + name);

            responceServer(bf.readLine());            ;
            responceServer(bf.readLine());
            String isChild = "No";
            output.println(isChild);
            System.out.println("Send: "+ isChild);
            responceServer(bf.readLine());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void responceServer(String string){
        System.out.println("Server: " + string);
    }
}
