package com.repository;

import jakarta.annotation.Nullable;

import java.io.IOException;

public class TemplateHandler {
    private HtmlElement generated;

    public TemplateHandler(@Nullable HtmlElement gen){
        generated = gen;
    }

    public String render() {
        if (generated != null) {
            return generated.render();
        }
        return "";
    }
}
