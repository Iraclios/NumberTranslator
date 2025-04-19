package com.example.demo.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Service
public class AppService extends AbstractAppService {
    public AppService (MinioClient minioClient) {
        super(minioClient);
    }

    public String translateNumberAndSaveAsPdfInMinio (String fullname,
                                                      String number,
                                                      String chosenOperation) {
        logger.info("Translating input numbers");
        LinkedList<String> list = new LinkedList<>();
        list.add("ФИО: " + fullname);
        list.add("Введенное число: " + number);
        try {
            long timestampBefore1 = System.nanoTime();
            list.add("Результат работы с использованием встроенных методов Java: " +
                    translators[0].translateNumber(number,chosenOperation));
            long timestampAfter1 = System.nanoTime();
            list.add("Время работы в наносекундах: " + (timestampAfter1-timestampBefore1));
            long timestampBefore2 = System.nanoTime();
            list.add("Результат работы с использованием собственных алгоритмов: " +
                    translators[1].translateNumber(number,chosenOperation));
            long timestampAfter2 = System.nanoTime();
            list.add("Время работы в наносекундах: " + (timestampAfter2-timestampBefore2));
            long timestampBefore3 = System.nanoTime();
            list.add("Результат работы с использованием Stream Api: " +
                    translators[2].translateNumber(number,chosenOperation));
            long timestampAfter3 = System.nanoTime();
            list.add("Время работы в наносекундах: " + (timestampAfter3-timestampBefore3));
        }
        catch (NumberFormatException e) {
            logger.info("Number representation doesn't match desired number system");
            return "Обнаружен недопустимый символ для выбранной системы счисления";
        }
        catch (IllegalArgumentException e) {
            logger.warn("Unexpected operation was selected");
            return "Выбрано недопустимое преобразование";
        }

        logger.info("Writing in pdf document");
        byte[] pdfBytes;
        try {
            pdfBytes = pdfConverter.createPdf(list);
        } catch (IOException e) {
            logger.error("An error occurred during writing in pdf process");
            return "Ошибка записи";
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

        logger.info("Saving document and returning url");
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("mybucket")
                            .object(fullname + "/"  + number + ".pdf")
                            .stream(inputStream, pdfBytes.length, -1)
                            .contentType("application/pdf")
                            .build()
            );

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket("mybucket")
                            .object(fullname + "/"  + number + ".pdf")
                            .expiry(5, TimeUnit.MINUTES)
                            .build());

            return "<a href=\"" + url + "\">Ссылка на результат работы</a>";
        }
        catch (Exception e) {
            logger.error("An error occurred via connecting Minio");
            return e.getMessage();
        }
    }
}
