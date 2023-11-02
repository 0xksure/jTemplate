package com.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Constants {
    static final String CONTENTTYPE="Content-type";
    static final String HTML="text/html";
    static final String JSON = "Application/json";

    static byte[] getHtml(String name) throws IOException{
        return "<html><p>hello</p></html>".getBytes();
    }
}

public class RequestParser {
    public String host;
    public String connection;

    public String path;


    public void parseHost(String s) {
        if(s.startsWith("Host:")){
            Pattern r = Pattern.compile("(\\w+:\\d+)");
            Matcher m = r.matcher(s);
            if(m.find()){
                host = m.group().strip();
            }

        }
    }

    public void parseConnection(String s) {
        if(s.startsWith("Connection:")){
            connection = s.replace("Connection: ","").strip();
        }
    }

    public void parsePath(String s)  {
        if(s.startsWith("Referer:")){
            Pattern r = Pattern.compile("(\\/\\w+)+$");
            Matcher m = r.matcher(s);
            if(m.find()){
                path = m.group().strip();
            }

        }
    }

    public void parseReader(BufferedReader r ) throws IOException{
        String input;
        while (!(input = r.readLine()).isEmpty()) {
            parseHost(input);
            parseConnection(input);
            parsePath(input);
        }
    }
}
