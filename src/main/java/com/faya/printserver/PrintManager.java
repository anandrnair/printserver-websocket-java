package com.faya.printserver;

public class PrintManager {
    public void startPrintServer(String filePath) {
//        int port = 9090;

        new PrintServerThread(filePath).start();

//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//
//            System.out.println("Server is listening on port " + port);
//
//            while (true) {
//                Socket socket = serverSocket.accept();
//                System.out.println("New client connected");
//
//                new com.faya.printserver.PrintServerThread(socket, filePath).start();
//            }
//
//        } catch (Exception ex) {
//            System.out.println("Server exception: " + ex.getMessage());
//            ex.printStackTrace();
//        }
    }
}
