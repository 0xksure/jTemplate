package com.repository;

import java.io.IOException;

public class TemplateHandler {
    private HtmlElement generated = null;

    public String render() throws IOException {
        if (generated != null) {
            return generated.render();
        }
        return "";
    }
}
