package com.example.demo.translator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class ITranslatorTest<T extends ITranslator> {
    protected abstract T createInstance();

    @Test
    void translateDecimalToHex() {
        ITranslator translator = createInstance();
        assertEquals("C735B",translator.translateDecimalToHex("815963"));
        assertEquals("102FEA",translator.translateDecimalToHex("1060842"));
        assertEquals("48D96",translator.translateDecimalToHex("298390"));
    }

    @Test
    void translateDecimalToBinary() {
        ITranslator translator = createInstance();
        assertEquals("11000100110",translator.translateDecimalToBinary("1574"));
        assertEquals("1100101001",translator.translateDecimalToBinary("809"));
        assertEquals("11101100",translator.translateDecimalToBinary("236"));
    }

    @Test
    void translateBinaryToDecimal() {
        ITranslator translator = createInstance();
        assertEquals("1025",translator.translateBinaryToDecimal("10000000001"));
        assertEquals("369",translator.translateBinaryToDecimal("101110001"));
        assertEquals("487",translator.translateBinaryToDecimal("111100111"));
    }
}