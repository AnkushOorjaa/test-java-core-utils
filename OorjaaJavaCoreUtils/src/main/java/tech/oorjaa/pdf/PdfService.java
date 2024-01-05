package tech.oorjaa.pdf;

import java.io.IOException;

public interface PdfService {

    byte[] convertHtmlToPdf(String html) throws Exception;

    void savePdfToLocal(byte[] pdfBytes, String filePath);

    void uploadPdfToS3Bucket(byte[] pdf);
}
