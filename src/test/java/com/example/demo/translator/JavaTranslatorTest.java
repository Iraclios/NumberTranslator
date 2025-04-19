package com.example.demo.translator;

class JavaTranslatorTest extends ITranslatorTest<JavaTranslator> {
    @Override
    protected JavaTranslator createInstance() {
        return new JavaTranslator();
    }
}
