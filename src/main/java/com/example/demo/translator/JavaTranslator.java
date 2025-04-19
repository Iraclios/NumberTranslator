package com.example.demo.translator;

public class JavaTranslator extends AbstractTranslator {
    public String translateDecimalToHex(String decimal) throws NumberFormatException {
        logger.debug("Parsing decimal string to hex string");
        int decimalValue = Integer.parseInt(decimal);
        return Integer.toHexString(decimalValue).toUpperCase();
    }
    public String translateDecimalToBinary(String decimal) throws NumberFormatException {
        logger.debug("Parsing decimal string to binary string");
        int decimalValue = Integer.parseInt(decimal);
        return Integer.toBinaryString(decimalValue);
    }
    public String translateBinaryToDecimal(String binary) throws NumberFormatException {
        logger.debug("Parsing binary string to decimal string");
        int binaryValue = Integer.parseInt(binary,2);
        return Integer.toString(binaryValue);
    }
}
