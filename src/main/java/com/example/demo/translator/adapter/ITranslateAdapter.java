package com.example.demo.translator.adapter;

public interface ITranslateAdapter {
    String translateNumber(String number, String option) throws NumberFormatException, IllegalArgumentException;
}
