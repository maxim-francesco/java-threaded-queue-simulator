package GUI;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream {
    private final JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // Append one character to the text area
        textArea.append(String.valueOf((char) b));
        // Auto-scroll
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
