package com.repository;

import jakarta.annotation.Nullable;


public class HelloWriter extends TemplateHandler{
    private HtmlElement generated = null;
    public HelloWriter(@Nullable HtmlElement generated){
    }

    public HelloWriter generate(){
        generated = new Html("","",new Div("","hello"));
        return new HelloWriter(generated);
    }
}
