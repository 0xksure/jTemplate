package com.repository;

import java.io.IOException;

class ResponseFormatter {
    private static String getHeaders(int code, int dataLength) throws IOException {
        return String.format("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: %s" +
                "\r\n\r\n", dataLength);
    }

    String getPayload(String payload, int code) throws IOException {
        return getHeaders(code, payload.length()) + payload;
    }
}
