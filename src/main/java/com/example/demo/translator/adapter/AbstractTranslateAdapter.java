package com.example.demo.translator.adapter;

import com.example.demo.service.AbstractAppService;
import com.example.demo.translator.ITranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTranslateAdapter implements ITranslateAdapter {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractAppService.class);
    protected ITranslator iTranslator;
    public AbstractTranslateAdapter(ITranslator iTranslator) {
        this.iTranslator = iTranslator;
    }
}
