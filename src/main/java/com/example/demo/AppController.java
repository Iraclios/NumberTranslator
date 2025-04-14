package com.example.demo;

import com.example.demo.converter.IPdfConverter;
import com.example.demo.converter.PdfConverter;
import com.example.demo.service.translator.ITranslateService;
import com.example.demo.service.translator.TranslateService;
import com.example.demo.translator.CustomTranslator;
import com.example.demo.translator.ITranslator;
import com.example.demo.translator.JavaTranslator;
import com.example.demo.translator.StreamTranslator;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import io.minio.MinioClient;

import java.io.ByteArrayInputStream;

import io.minio.PutObjectArgs;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Controller
public class AppController {

    private final MinioClient minioClient;
    private final ITranslateService[] translators;
    private final IPdfConverter pdfConverter;

    public AppController(MinioClient minioClient) {
        this.minioClient = minioClient;
        translators = new ITranslateService[]{
                new TranslateService(new JavaTranslator()),
                new TranslateService(new CustomTranslator()),
                new TranslateService(new StreamTranslator())
        };
        pdfConverter = new PdfConverter();
    }

    @GetMapping(value = "/")
    public String Index()
    {
        return "index";
    }

    @PostMapping("/api")
    @ResponseBody
    public String CreatePdfAndUpload(@RequestParam(name = "number") String number,
                                     @RequestParam(name = "option") String option) throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.add("Введенное число: " + number);
        try {
            long timestampBefore1 = System.nanoTime();
            list.add("Результат работы с использованием встроенных методов Java: " +
                    translators[0].TranslateNumber(number,option));
            long timestampAfter1 = System.nanoTime();
            list.add("Время работы в наносекундах: " + (timestampAfter1-timestampBefore1));
            long timestampBefore2 = System.nanoTime();
            list.add("Результат работы с использованием собственных алгоритмов: " +
                    translators[0].TranslateNumber(number,option));
            long timestampAfter2 = System.nanoTime();
            list.add("Время работы в наносекундах: " + (timestampAfter2-timestampBefore2));
            long timestampBefore3 = System.nanoTime();
            list.add("Результат работы с использованием Stream Api: " +
                    translators[0].TranslateNumber(number,option));
            long timestampAfter3 = System.nanoTime();
            list.add("Время работы в наносекундах: " + (timestampAfter3-timestampBefore3));
        }
        catch (NumberFormatException e) {
            return "Обнаружен недопустимый символ для выбранной системы счисления";
        }
        catch (IllegalArgumentException e) {
            return "Выбрано недопустимое преобразование";
        }

        byte[] pdfBytes;
        try {
            pdfBytes = pdfConverter.CreatePdf(list);
        } catch (IOException e) {
            return "Ошибка записи";
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("mybucket")
                        .object("user/"  + number + ".pdf")
                        .stream(inputStream, pdfBytes.length, -1)
                        .contentType("application/pdf")
                        .build()
        );

        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket("mybucket")
                        .object("user/"  + number + ".pdf")
                        .expiry(5, TimeUnit.MINUTES)
                        .build());

        return "<a href=\"" + url + "\">Ссылка на результат работы</a>";
    }
}
