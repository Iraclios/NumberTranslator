package com.example.demo.translator;

class CustomTranslatorTest extends ITranslatorTest<CustomTranslator> {
    @Override
    protected CustomTranslator createInstance() {
        return new CustomTranslator();
    }
}
