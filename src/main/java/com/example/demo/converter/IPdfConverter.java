package com.example.demo.converter;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.util.List;

public interface IPdfConverter {
    byte[] CreatePdf(List<String> list) throws IOException;
}
