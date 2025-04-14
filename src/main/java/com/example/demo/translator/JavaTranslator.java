package com.example.demo.translator;

public class JavaTranslator extends AbstractTranslator {
    public String TranslateDecimalToHex(String decimal) throws NumberFormatException {
        int decimalValue = Integer.parseInt(decimal);
        return Integer.toHexString(decimalValue);
    }
    public String TranslateDecimalToBinary(String decimal) throws NumberFormatException {
        int decimalValue = Integer.parseInt(decimal);
        return Integer.toBinaryString(decimalValue);
    }
    public String TranslateBinaryToDecimal(String binary) throws NumberFormatException {
        int binaryValue = Integer.parseInt(binary,2);
        return Integer.toString(binaryValue);
    }
}
