package com.example.demo.service;

import com.example.demo.converter.IPdfConverter;
import com.example.demo.converter.PdfConverter;
import com.example.demo.translator.adapter.ITranslateAdapter;
import com.example.demo.translator.adapter.TranslateAdapter;
import com.example.demo.translator.CustomTranslator;
import com.example.demo.translator.JavaTranslator;
import com.example.demo.translator.StreamTranslator;
import io.minio.MinioClient;

public abstract class AbstractAppService implements IAppService {
    protected final MinioClient minioClient;
    protected final ITranslateAdapter[] translators;
    protected final IPdfConverter pdfConverter;
    public AbstractAppService (MinioClient minioClient) {
        this.minioClient = minioClient;
        translators = new ITranslateAdapter[]{
                new TranslateAdapter(new JavaTranslator()),
                new TranslateAdapter(new CustomTranslator()),
                new TranslateAdapter(new StreamTranslator())
        };
        pdfConverter = new PdfConverter();
    }
}
