package com.example.demo.translator.adapter;

import com.example.demo.converter.IPdfConverter;
import com.example.demo.converter.PdfConverter;
import com.example.demo.translator.JavaTranslator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslateAdapterTest {
    private ITranslateAdapter translateAdapter;

    @BeforeEach
    void setUp() {
        translateAdapter = new TranslateAdapter(new JavaTranslator());
    }

    @Test
    void translateNumber() throws NumberFormatException, IllegalArgumentException {
        assertEquals("A",translateAdapter.translateNumber("10","DecimalToHex"));
        assertEquals("1010",translateAdapter.translateNumber("10","DecimalToBinary"));
        assertEquals("2",translateAdapter.translateNumber("10","BinaryToDecimal"));
    }
}