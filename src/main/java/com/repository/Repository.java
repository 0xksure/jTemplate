package com.repository;

import java.io.*;


public class Repository {
    public static void main(String[] args) throws IOException{
        JumpyServer server =new JumpyServer();
        server.Attach("/hello",new HelloWriter(null));
        server.Start();

    }
}



