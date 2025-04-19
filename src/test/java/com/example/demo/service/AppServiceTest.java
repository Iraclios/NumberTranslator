package com.example.demo.service;

import com.example.demo.converter.IPdfConverter;
import com.example.demo.translator.ITranslator;
import com.example.demo.translator.adapter.ITranslateAdapter;
import io.minio.MinioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppServiceTest {
    private AppService appService;
    private MinioClient minioClient;
    private ITranslateAdapter translatorJava;
    private ITranslateAdapter translatorCustom;
    private ITranslateAdapter translatorStream;
    private IPdfConverter pdfConverter;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        minioClient = mock(MinioClient.class);
        appService = new AppService(minioClient);

        translatorJava = mock(ITranslateAdapter.class);
        translatorCustom = mock(ITranslateAdapter.class);
        translatorStream = mock(ITranslateAdapter.class);
        pdfConverter = mock(IPdfConverter.class);

        Field translatorsField = AbstractAppService.class.getDeclaredField("translators");
        translatorsField.setAccessible(true);
        translatorsField.set(appService, new ITranslateAdapter[]{
                translatorJava, translatorCustom, translatorStream
        });

        Field pdfConverterField = AbstractAppService.class.getDeclaredField("pdfConverter");
        pdfConverterField.setAccessible(true);
        pdfConverterField.set(appService, pdfConverter);
    }

    @Test
    void translateNumberAndSaveAsPdfInMinio() throws Exception {
        String fullname = "testuser";
        String number = "36";
        String operation = "DecimalToHex";

        when(translatorJava.translateNumber(number, operation)).thenReturn("24");
        when(translatorCustom.translateNumber(number, operation)).thenReturn("24");
        when(translatorStream.translateNumber(number, operation)).thenReturn("24");

        byte[] pdfContent = "placeholder".getBytes();
        when(pdfConverter.createPdf(anyList())).thenReturn(pdfContent);

        when(minioClient.getPresignedObjectUrl(any()))
                .thenReturn("localhost:9000/mybucket/testuser/36.pdf");

        String result = appService.translateNumberAndSaveAsPdfInMinio(fullname, number, operation);

        verify(minioClient).putObject(any());
        assertTrue(result.contains("Ссылка на результат работы"));
    }
}