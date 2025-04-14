package com.example.demo.translator;

public interface ITranslator {
    String TranslateDecimalToHex(String decimal) throws NumberFormatException;
    String TranslateDecimalToBinary(String decimal) throws NumberFormatException;
    String TranslateBinaryToDecimal(String binary) throws NumberFormatException;
}
