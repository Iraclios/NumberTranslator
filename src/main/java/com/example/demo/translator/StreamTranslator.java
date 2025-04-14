package com.example.demo.translator;

import java.util.stream.IntStream;

public class StreamTranslator extends AbstractTranslator {
    public String TranslateDecimalToHex(String decimal) throws NumberFormatException {
        int decimalValue = GetDecimalNumber(decimal);
        return RepresentInBaseSystem(decimalValue,16);
    }
    public String TranslateDecimalToBinary(String decimal) throws NumberFormatException {
        int decimalValue = GetDecimalNumber(decimal);
        return RepresentInBaseSystem(decimalValue,2);
    }
    public String TranslateBinaryToDecimal(String binary) throws NumberFormatException {
        int binaryValue = GetBinaryNumber(binary);
        return RepresentInBaseSystem(binaryValue,10);
    }

    private int GetDecimalNumber(String decimal) throws NumberFormatException {
        int length = decimal.length();

        int[] powers = IntStream.iterate(1, i -> i * 2)
                .limit(length)
                .toArray();

        int[] digits = IntStream.range(0, length)
                .map(i -> {
                    char ch = decimal.charAt(i);
                    return switch (ch) {
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
                }).toArray();

        return IntStream.range(0, length)
                .map(i -> powers[length - i - 1] * digits[i])
                .sum();
    }

    private int GetBinaryNumber(String binary) throws NumberFormatException {
        int length = binary.length();

        int[] powers = IntStream.iterate(1, i -> i * 2)
                .limit(length)
                .toArray();

        int[] digits = IntStream.range(0, length)
                .map(i -> {
                    char ch = binary.charAt(i);
                    return switch (ch) {
                        case '0' -> 0;
                        case '1' -> 1;
                        default -> throw new NumberFormatException("Invalid input: " + ch);
                    };
                }).toArray();

        return IntStream.range(0, length)
                .map(i -> powers[length - i - 1] * digits[i])
                .sum();
    }

    private String RepresentInBaseSystem(int number, int base) throws NumberFormatException {
        return IntStream.iterate(number, i -> i > 0, i -> i / base)
                .map(i -> i % base)
                .mapToObj(i -> GetDigitInBase(number, base))
                .reduce(new StringBuilder(), (a, b) -> b.append(a))
                .toString();
    }

    private StringBuilder GetDigitInBase(int number, int base) throws NumberFormatException {
        String s = switch (number % base) {
            case 0 -> "0";
            case 1 -> "1";
            case 2 -> "2";
            case 3 -> "3";
            case 4 -> "4";
            case 5 -> "5";
            case 6 -> "6";
            case 7 -> "7";
            case 8 -> "8";
            case 9 -> "9";
            case 10 -> "A";
            case 11 -> "B";
            case 12 -> "C";
            case 13 -> "D";
            case 14 -> "E";
            case 15 -> "F";
            default -> throw new NumberFormatException("Invalid input: " + base);
        };
        return new StringBuilder(s);
    }
}
