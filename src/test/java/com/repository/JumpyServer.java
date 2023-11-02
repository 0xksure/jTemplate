package com.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JumpyServer {

    @Test
    @DisplayName("Generate Not found handler")
    void testNotFoundHandler(){
        TemplateHandler elem = NotFoundHandler.generate();
        String expected = "<html><div>Not found</div></html>";
        String rendered = elem.render();
        assertEquals(rendered,expected,String.format("%s does not equal %s",rendered,expected));

    }
    }
