package com.repository;

import jakarta.annotation.Nullable;


public class HelloWriter extends TemplateHandler{
    public HelloWriter(@Nullable HtmlElement protoGenerated){
        super(protoGenerated);
    }

    public static HelloWriter generate(){
        Html html= new Html("","",new Div("","hello"));
        return new HelloWriter(html);
    }
}
