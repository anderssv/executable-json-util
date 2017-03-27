package no.f12;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CliTest {

    @Test
    public void testShouldEncryptRot13() throws IOException {
        assertEquals("grfgFgevatLrf", App.run("say", "--encrypt=rot13", "testStringYes"));
    }

    @Test
    public void testShouldSay() throws IOException {
        assertEquals("testStringYes", App.run("say", "testStringYes"));
    }
}
