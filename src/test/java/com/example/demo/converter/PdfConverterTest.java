package com.example.demo.converter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PdfConverterTest {
    private IPdfConverter pdfConverter;

    @BeforeEach
    void setUp() {
        pdfConverter = new PdfConverter();
    }

    @Test
    void createPdf() throws Exception {
        List<String> inputLines = Arrays.asList("First", "Second", "Third");

        byte[] pdfBytes = pdfConverter.createPdf(inputLines);

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0, "PDF data must not be empty");

        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            for (String line : inputLines) {
                assertTrue(text.contains(line), "PDF must contain the line: " + line);
            }
        }
    }
}