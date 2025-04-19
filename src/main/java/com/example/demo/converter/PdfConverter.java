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
        logger.debug("Creating pdf document");
        PDDocument pdfDocument = new PDDocument();
        PDPage pdfPage = new PDPage();
        pdfDocument.addPage(pdfPage);
        PDPageContentStream pdfTextStream = new PDPageContentStream(pdfDocument, pdfPage);
        logger.debug("Opening font file");
        File fontFile = new File("src/main/resources/static","times.ttf");
        PDType0Font font = PDType0Font.load(pdfDocument,fontFile);
        logger.debug("Setting text font");
        pdfTextStream.setFont(font, 12);
        pdfTextStream.beginText();
        pdfTextStream.newLineAtOffset(100, 700);
        logger.debug("Filling pdf document with text");
        for (String s: list) {
            pdfTextStream.showText(s);
            pdfTextStream.newLineAtOffset(0, -20);
        }
        pdfTextStream.endText();
        pdfTextStream.close();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        pdfDocument.save(byteStream);
        pdfDocument.close();
        logger.trace("Returning pdf document as an array of bytes");
        return byteStream.toByteArray();
    }
}
