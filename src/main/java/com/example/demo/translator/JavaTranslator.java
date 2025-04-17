package com.example.demo.translator;

public class JavaTranslator extends AbstractTranslator {
    public String translateDecimalToHex(String decimal) throws NumberFormatException {
        int decimalValue = Integer.parseInt(decimal);
        return Integer.toHexString(decimalValue);
    }
    public String translateDecimalToBinary(String decimal) throws NumberFormatException {
        int decimalValue = Integer.parseInt(decimal);
        return Integer.toBinaryString(decimalValue);
    }
    public String translateBinaryToDecimal(String binary) throws NumberFormatException {
        int binaryValue = Integer.parseInt(binary,2);
        return Integer.toString(binaryValue);
    }
}
