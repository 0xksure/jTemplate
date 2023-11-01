package com.repository;


import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HtmlElement {
    private HtmlElement[] children;
    private String className;
    private String content;
    private String tag;

    public HtmlElement(String protoClassName, String protoContent,String protoTag, @Nullable HtmlElement... args) {
        tag = protoTag;
        className = protoClassName;
        content = protoContent;
        if(args != null){
            children = args;
        }

    }

    public String render() {
        String rendered = String.format("<%s",tag);
        if(className != null && !className.isEmpty()) rendered = String.format("%s className=\"%s\"",rendered,className);
        rendered = String.format("%s>",rendered);

        if(content != null) rendered = String.format("%s%s",rendered,content);
        if (children != null) {
            String renderedSubElements = Arrays.stream(children).map(HtmlElement::render).collect(Collectors.joining(" "));
            rendered =  String.format("%s%s", rendered, renderedSubElements);
        }
        rendered = String.format("%s</%s>",rendered,tag);
        return rendered;
    }
}


class Html extends HtmlElement{
    public Html(String className,String content,@Nullable HtmlElement... args){super(className,content,"html", args);}
}


class Div extends HtmlElement{
    public Div(String className,String content,@Nullable HtmlElement... args){super(className,content,"div", args);}
}

class P extends HtmlElement{
    public P(String className,String content,@Nullable HtmlElement... args){super(className,content,"p", args);}
}
