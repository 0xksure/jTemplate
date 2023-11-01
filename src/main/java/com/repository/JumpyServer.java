package com.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class JumpyServer {
    private HashMap<String, TemplateHandler> handlers = new HashMap<String, TemplateHandler>();

    public static void JumpyServer() {
        HashMap<String, TemplateHandler> handlers = new HashMap<String, TemplateHandler>();
    }

    public void Attach(String path, TemplateHandler handler) {

            handlers.put(path, handler);

    }

    private TemplateHandler GetHandler(String path) {
        return handlers.get(path);
    }

    public void Start() {
        String input;
        ResponseFormatter formatter = new ResponseFormatter();
        RequestParser headerParser = new RequestParser();
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    headerParser.parseReader(in);
                    // call method
                    TemplateHandler handler = GetHandler(headerParser.path);
                    String payload = handler.render();

                    System.out.printf("Write back %s \n", payload);
                    out.write(payload);
                    out.close();
                } catch (SocketTimeoutException err) {
                    err.printStackTrace();
                }
            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
