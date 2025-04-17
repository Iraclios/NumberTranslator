package com.example.demo.converter;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfConverter extends AbstractPdfConverter {
    public byte[] createPdf(List<String> list) throws IOException {
        PDDocument pdfDocument = new PDDocument();
        PDPage pdfPage = new PDPage();
        pdfDocument.addPage(pdfPage);
        PDPageContentStream pdfTextStream = new PDPageContentStream(pdfDocument, pdfPage);
        File fontFile = new File("src/main/resources/static","times.ttf");
        PDType0Font font = PDType0Font.load(pdfDocument,fontFile);
        pdfTextStream.setFont(font, 12);
        pdfTextStream.beginText();
        pdfTextStream.newLineAtOffset(100, 700);
        for (String s: list) {
            pdfTextStream.showText(s);
            pdfTextStream.newLineAtOffset(0, -20);
        }
        pdfTextStream.endText();
        pdfTextStream.close();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        pdfDocument.save(byteStream);
        pdfDocument.close();
        return byteStream.toByteArray();
    }
}
