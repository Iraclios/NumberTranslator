package com.example.demo.service.translator;

public interface ITranslateService {
    String TranslateNumber (String number, String option) throws NumberFormatException, IllegalArgumentException;
}
