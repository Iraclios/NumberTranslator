package com.example.demo.service.translator;



import com.example.demo.translator.ITranslator;

public class TranslateService extends AbstractTranslateService {
    public TranslateService(ITranslator iTranslator) {
        super(iTranslator);
    }
    public String TranslateNumber(String number, String option) throws NumberFormatException, IllegalArgumentException {
        return switch (option) {
            case "1" -> iTranslator.TranslateDecimalToHex(number);
            case "2" -> iTranslator.TranslateDecimalToBinary(number);
            case "3" -> iTranslator.TranslateBinaryToDecimal(number);
            default -> throw new IllegalArgumentException("Invalid input: " + option);
        };
    }
}
