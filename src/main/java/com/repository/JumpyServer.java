package com.repository;

import jakarta.annotation.Nullable;
import org.w3c.dom.html.HTMLElement;

import javax.swing.text.html.HTML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

class NotFoundHandler extends TemplateHandler {

    public NotFoundHandler(@Nullable HtmlElement protoGenerated){
        super(protoGenerated);
    }

    public static TemplateHandler generate(){
        Html elem = new Html("","",new Div("","Not found"));
        return new NotFoundHandler(elem);
    }
}

public class JumpyServer {
    private HashMap<String, TemplateHandler> handlers = new HashMap<String, TemplateHandler>();

    public static void JumpyServer() {
        HashMap<String, TemplateHandler> handlers = new HashMap<String, TemplateHandler>();
    }

    public void Attach(String path, TemplateHandler handler) {
            handlers.put(path, handler);
    }

    private TemplateHandler GetHandler(String path) {
        TemplateHandler template = handlers.get(path);
        if(path != null) return template;
        return NotFoundHandler.generate();
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
                    System.out.printf("Path: %s \n",headerParser.path);
                    TemplateHandler handler = GetHandler(headerParser.path);

                    String html = handler.render();
                    String payload = formatter.getPayload(html,200);
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
