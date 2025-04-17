package com.example.demo.converter;

import java.io.IOException;
import java.util.List;

public interface IPdfConverter {
    /**
     * Creates a pdf file and saves it as a byte stream. List items are separated by linebreak.
     * @param list collection pf strings
     * @return an array of bytes representing this pdf file.
     * @throws IOException
     */
    byte[] createPdf(List<String> list) throws IOException;
}
