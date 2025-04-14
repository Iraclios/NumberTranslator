package com.example.demo.service.translator;

import com.example.demo.translator.ITranslator;

public abstract class AbstractTranslateService implements ITranslateService {
    protected ITranslator iTranslator;
    public AbstractTranslateService(ITranslator iTranslator) {
        this.iTranslator = iTranslator;
    }
}
