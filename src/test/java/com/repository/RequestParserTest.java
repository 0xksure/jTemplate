package com.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {

    @Test
    @DisplayName("Test parsing connection strings")
    void testParsingConnection(){
        RequestParser parser = new RequestParser();
        parser.parseConnection("Connection: keep-alive");
        assertEquals(parser.connection,"keep-alive",String.format("expected %s should match %s","keep-alive",parser.connection));
    }

    @Test
    @DisplayName("Test parsing host strings")
    void testParsingHost(){
        RequestParser parser = new RequestParser();
        parser.parseHost("Host: localhost:8081");
        String expected= "localhost:8081";
        assertEquals(parser.host,expected,String.format("expected %s should match %s","keep-alive",parser.host));
    }

    @Test
    @DisplayName("Test parsing path strings")
    void testParsingReferrer(){
        RequestParser parser = new RequestParser();
        parser.parsePath("Referer: http://localhost:8081/hello");
        String expectedPath = "/hello";
        assertEquals(parser.path,expectedPath,String.format("expected %s should match %s","keep-alive",parser.path));
    }
}
