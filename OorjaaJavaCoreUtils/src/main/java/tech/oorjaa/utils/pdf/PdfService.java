package tech.oorjaa.utils.pdf;

import com.lowagie.text.DocumentException;

import java.io.IOException;

public interface PdfService {

    byte[] convertHtmlToPdf(String html) throws Exception;

    void savePdfToLocal(byte[] pdfBytes, String filePath) throws IOException;

    void uploadPdfToS3Bucket(byte[] pdf) throws Exception;
}
