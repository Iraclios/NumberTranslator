package com.example.demo.translator.adapter;

import com.example.demo.translator.ITranslator;

public abstract class AbstractTranslateAdapter implements ITranslateAdapter {
    protected ITranslator iTranslator;
    public AbstractTranslateAdapter(ITranslator iTranslator) {
        this.iTranslator = iTranslator;
    }
}
