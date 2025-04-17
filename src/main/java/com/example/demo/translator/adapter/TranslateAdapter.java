package com.example.demo.translator.adapter;

import com.example.demo.translator.ITranslator;

public class TranslateAdapter extends AbstractTranslateAdapter {
    public TranslateAdapter(ITranslator iTranslator) {
        super(iTranslator);
    }
    public String translateNumber(String number, String option) throws NumberFormatException, IllegalArgumentException {
        return switch (option) {
            case "DecimalToHex" -> iTranslator.translateDecimalToHex(number);
            case "DecimalToBinary" -> iTranslator.translateDecimalToBinary(number);
            case "BinaryToDecimal" -> iTranslator.translateBinaryToDecimal(number);
            default -> throw new IllegalArgumentException("Invalid input: " + option);
        };
    }
}
