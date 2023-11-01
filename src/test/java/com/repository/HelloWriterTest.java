package com.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HelloWriterTest {

    @Test
    @DisplayName("Test generation of htmlelement")
    void testGeneratingHtml(){
        HtmlElement element = new Html("","");
        String renderedHtml = element.render();
        String expected = "<html></html>";
        System.out.printf("Render: %s",renderedHtml);
        assertEquals(renderedHtml,expected,String.format("%s should be %s",renderedHtml,expected));
    }

    @Test
    @DisplayName("Test nested elements")
    void testNestedElements(){
        HtmlElement element = new Html("","",new Div("thisIsCool","Hello"));
        String renderedHtml = element.render();
        String expected = "<html><div className=\"thisIsCool\">Hello</div></html>";
        assertEquals(renderedHtml,expected,String.format("%s should be %s",renderedHtml,expected));
    }
}
