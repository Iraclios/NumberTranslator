package com.example.demo.translator;

public interface ITranslator {
    /**
     * Translates a decimal number to binary.
     * @param decimal input decimal number
     * @return output binary number
     * @throws NumberFormatException
     */
    String translateDecimalToHex(String decimal) throws NumberFormatException;

    /**
     * Translates a decimal number to hexadecimal.
     * @param decimal input decimal number
     * @return output hexadecimal number
     * @throws NumberFormatException
     */
    String translateDecimalToBinary(String decimal) throws NumberFormatException;

    /**
     * Translates a binary number to decimal.
     * @param binary input binary number
     * @return output decimal number
     * @throws NumberFormatException
     */
    String translateBinaryToDecimal(String binary) throws NumberFormatException;
}
