package com.example.demo.translator;

public class CustomTranslator extends AbstractTranslator {
    public String translateDecimalToHex(String decimal) throws NumberFormatException {
        int decimalValue = getDecimalNumber(decimal);
        return representInNumberSystem(decimalValue, 16);
    }

    public String translateDecimalToBinary(String decimal) throws NumberFormatException {
        int decimalValue = getDecimalNumber(decimal);
        return representInNumberSystem(decimalValue, 2);
    }

    public String translateBinaryToDecimal(String binary) throws NumberFormatException {
        int binaryValue = getBinaryNumber(binary);
        return representInNumberSystem(binaryValue, 10);
    }

    private int getDecimalNumber(String decimal) throws NumberFormatException {
        int decimalValue = 0;
        int stringLength=decimal.length();
        for (int i=0;i<stringLength;i++) {
            decimalValue*=10;
            char ch =decimal.charAt(i);
            decimalValue+= switch (decimal.charAt(i)) {
                case '0' -> 0;
                case '1' -> 1;
                case '2' -> 2;
                case '3' -> 3;
                case '4' -> 4;
                case '5' -> 5;
                case '6' -> 6;
                case '7' -> 7;
                case '8' -> 8;
                case '9' -> 9;
                default -> throw new NumberFormatException("Invalid input: " + ch);
            };
        }
        return decimalValue;
    }

    private int getBinaryNumber(String binary) throws NumberFormatException {
        int binaryValue = 0;
        int stringLength=binary.length();
        for (int i=0;i<stringLength;i++) {
            binaryValue*=2;
            char ch =binary.charAt(i);
            binaryValue+= switch (ch) {
                case '0' -> 0;
                case '1' -> 1;
                default -> throw new NumberFormatException("Invalid input: " + ch);
            };
        }
        return binaryValue;
    }

    private String representInNumberSystem(int number, int base) throws NumberFormatException {
        StringBuilder stringBuilder = new StringBuilder();
        do {
            stringBuilder.append(getDigitInBase(number, base));
            number /= base;
        } while (number > 0);
        return stringBuilder.reverse().toString();
    }

    private char getDigitInBase(int number, int base) throws NumberFormatException {
        return switch (number % base) {
            case 0 -> '0';
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            case 8 -> '8';
            case 9 -> '9';
            case 10 -> 'A';
            case 11 -> 'B';
            case 12 -> 'C';
            case 13 -> 'D';
            case 14 -> 'E';
            case 15 -> 'F';
            default -> throw new NumberFormatException("Invalid input: " + base);
        };
    }
}
